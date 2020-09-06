package net.jasonhk.minecraft.mods.torvaldsinput.core.utilities;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

import lombok.experimental.UtilityClass;
import lombok.val;
import lombok.var;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.FrameNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LocalVariableNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;
import static org.objectweb.asm.Opcodes.ACC_INTERFACE;
import static org.objectweb.asm.Type.getConstructorDescriptor;
import static org.objectweb.asm.Type.getDescriptor;
import static org.objectweb.asm.Type.getInternalName;
import static org.objectweb.asm.Type.getMethodDescriptor;

import static cpw.mods.modlauncher.api.ITransformer.Target;

import net.jasonhk.minecraft.mods.torvaldsinput.core.ModInfo;

@SuppressWarnings("RedundantModifiersUtilityClassLombok")
@UtilityClass
public class TransformerUtility
{
    public static Target createTarget(Class<?> clazz)
    {
        return Target.targetClass(getInternalName(clazz));
    }

    public static Target createTarget(Field field)
    {
        return Target.targetField(getInternalName(field.getDeclaringClass()), field.getName());
    }

    public static Target createTarget(Constructor<?> constructor)
    {
        return Target.targetMethod(getInternalName(constructor.getDeclaringClass()),
                                   "<init>",
                                   getConstructorDescriptor(constructor));
    }

    public static Target createTarget(Method method)
    {
        return Target.targetMethod(getInternalName(method.getDeclaringClass()),
                                   method.getName(),
                                   getMethodDescriptor(method));
    }

    public static FrameNode createFrameNode(
            int type,
            int nLocal,
            Object[] local,
            int nStack,
            Object[] stack)
    {
        return new FrameNode(type,
                             nLocal,
                             convertFrameNodeElements(local),
                             nStack,
                             convertFrameNodeElements(stack));
    }

    @SuppressWarnings("rawtypes")
    private Object[] convertFrameNodeElements(Object[] source)
    {
        return Optional
                .ofNullable(source)
                .map((array) ->
                     {
                         return Arrays
                                 .stream(array)
                                 .map((element) ->
                                      {
                                          if (element instanceof Class)
                                          {
                                              return getDescriptor((Class<?>) element);
                                          }

                                          return element;
                                      })
                                 .toArray();
                     })
                .orElse(null);
    }

    public static FieldInsnNode createFieldInsnNode(int opcode, Field field)
    {
        return new FieldInsnNode(opcode,
                                 getInternalName(field.getDeclaringClass()),
                                 field.getName(),
                                 getDescriptor(field.getType()));
    }

    public static FieldInsnNode createFieldInsnNode(int opcode, ClassNode clazz, FieldNode field)
    {
        return new FieldInsnNode(opcode, clazz.name, field.name, field.desc);
    }

    public static MethodInsnNode createMethodInsnNode(int opcode, Constructor<?> constructor)
    {
        return createMethodInsnNode(opcode,
                                    constructor,
                                    constructor.getDeclaringClass().isInterface());
    }

    public static MethodInsnNode createMethodInsnNode(
            int opcode,
            Constructor<?> constructor,
            boolean isInterface)
    {
        return new MethodInsnNode(opcode,
                                  getInternalName(constructor.getDeclaringClass()),
                                  "<init>",
                                  getConstructorDescriptor(constructor),
                                  isInterface);
    }

    public static MethodInsnNode createMethodInsnNode(int opcode, Method method)
    {
        return createMethodInsnNode(opcode, method, method.getDeclaringClass().isInterface());
    }

    public static MethodInsnNode createMethodInsnNode(
            int opcode,
            Method method,
            boolean isInterface)
    {
        return new MethodInsnNode(opcode,
                                  getInternalName(method.getDeclaringClass()),
                                  method.getName(),
                                  getMethodDescriptor(method),
                                  isInterface);
    }

    public static MethodInsnNode createMethodInsnNode(
            int opcode,
            ClassNode clazz,
            MethodNode method)
    {
        return new MethodInsnNode(opcode,
                                  clazz.name,
                                  method.name,
                                  method.desc,
                                  (clazz.access & ACC_INTERFACE) == ACC_INTERFACE);
    }

    public static MethodInsnNode createMethodInsnNode(
            int opcode,
            ClassNode clazz,
            MethodNode method,
            boolean isInterface)
    {
        return new MethodInsnNode(opcode, clazz.name, method.name, method.desc, isInterface);
    }

    public static TypeInsnNode createTypeInsnNode(int opcode, Class<?> clazz)
    {
        return new TypeInsnNode(opcode, getInternalName(clazz));
    }

    public static VarInsnNode createVarInsnNode(int opcode, LocalVariableNode variable)
    {
        return new VarInsnNode(opcode, variable.index);
    }

    public static LocalVariableNode createLocalVariableNode(
            String name,
            Class<?> clazz,
            String signature,
            LabelNode start,
            LabelNode end,
            int index)
    {
        return new LocalVariableNode(name, getDescriptor(clazz), signature, start, end, index);
    }

    public static boolean addField(ClassNode classNode, FieldNode fieldNode)
    {
        for (val field : classNode.fields)
        {
            if (field.name.equals(fieldNode.name))
            {
                return false;
            }
        }

        classNode.fields.add(fieldNode);
        return true;
    }

    public static void addFieldOrThrow(ClassNode classNode, FieldNode fieldNode)
    {
        if (!addField(classNode, fieldNode))
        {
            throw new IllegalStateException(String.format(
                    "A field with the name of \"%s\" already exists in `%s`.",
                    fieldNode.name,
                    classNode.name.replace('/', '.')));
        }
    }

    public static String getInjectedMemberName(String name)
    {
        return String.format("%s__%s", ModInfo.ID, name);
    }

    public static String getInjectedMemberName(Class<?> clazz)
    {
        return getInjectedMemberName(clazz.getSimpleName());
    }
}
