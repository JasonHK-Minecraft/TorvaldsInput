//#if MINECRAFT>=11300 && MINECRAFT<11400
//$$ package net.jasonhk.minecraft.mods.torvaldsinput.input.transformers;
//$$
//$$ import java.util.Arrays;
//$$ import java.util.Collections;
//$$ import java.util.Set;
//$$
//$$ import javax.annotation.Nonnull;
//$$
//$$ import com.sun.jna.Pointer;
//$$
//$$ import net.minecraft.client.MainWindow;
//$$
//$$ import net.minecraftforge.common.MinecraftForge;
//$$ import net.minecraftforge.eventbus.api.IEventBus;
//$$
//$$ import org.apache.logging.log4j.Logger;
//$$
//$$ import lombok.experimental.UtilityClass;
//$$ import lombok.val;
//$$ import lombok.var;
//$$
//$$ import org.objectweb.asm.Opcodes;
//$$ import org.objectweb.asm.tree.ClassNode;
//$$ import org.objectweb.asm.tree.FieldInsnNode;
//$$ import org.objectweb.asm.tree.FieldNode;
//$$ import org.objectweb.asm.tree.FrameNode;
//$$ import org.objectweb.asm.tree.InsnList;
//$$ import org.objectweb.asm.tree.InsnNode;
//$$ import org.objectweb.asm.tree.JumpInsnNode;
//$$ import org.objectweb.asm.tree.LabelNode;
//$$ import org.objectweb.asm.tree.LocalVariableNode;
//$$ import org.objectweb.asm.tree.MethodInsnNode;
//$$ import org.objectweb.asm.tree.MethodNode;
//$$ import org.objectweb.asm.tree.TypeInsnNode;
//$$ import org.objectweb.asm.tree.VarInsnNode;
//$$ import static org.objectweb.asm.Opcodes.*;
//$$ import static org.objectweb.asm.Type.VOID_TYPE;
//$$ import static org.objectweb.asm.Type.getDescriptor;
//$$ import static org.objectweb.asm.Type.getInternalName;
//$$ import static org.objectweb.asm.Type.getMethodDescriptor;
//$$
//$$ import cpw.mods.modlauncher.api.ITransformer;
//$$ import cpw.mods.modlauncher.api.ITransformerVotingContext;
//$$ import cpw.mods.modlauncher.api.TransformerVoteResult;
//$$
//$$ import net.jasonhk.minecraft.mods.torvaldsinput.core.transformer.PreInitializedMethodNode;
//$$ import net.jasonhk.minecraft.mods.torvaldsinput.core.utilities.LoggerUtility;
//$$ import net.jasonhk.minecraft.mods.torvaldsinput.gui.handlers.AbstractGuiHandler;
//$$ import net.jasonhk.minecraft.mods.torvaldsinput.gui.handlers.X11GuiHandler;
//$$ import static net.jasonhk.minecraft.mods.torvaldsinput.core.utilities.TransformerUtility.addFieldOrThrow;
//$$ import static net.jasonhk.minecraft.mods.torvaldsinput.core.utilities.TransformerUtility.createFieldInsnNode;
//$$ import static net.jasonhk.minecraft.mods.torvaldsinput.core.utilities.TransformerUtility.createFrameNode;
//$$ import static net.jasonhk.minecraft.mods.torvaldsinput.core.utilities.TransformerUtility.createLocalVariableNode;
//$$ import static net.jasonhk.minecraft.mods.torvaldsinput.core.utilities.TransformerUtility.createMethodInsnNode;
//$$ import static net.jasonhk.minecraft.mods.torvaldsinput.core.utilities.TransformerUtility.createTarget;
//$$ import static net.jasonhk.minecraft.mods.torvaldsinput.core.utilities.TransformerUtility.createTypeInsnNode;
//$$ import static net.jasonhk.minecraft.mods.torvaldsinput.core.utilities.TransformerUtility.createVarInsnNode;
//$$ import static net.jasonhk.minecraft.mods.torvaldsinput.core.utilities.TransformerUtility.getInjectedMemberName;
//$$ import static net.jasonhk.minecraft.mods.torvaldsinput.natives.glfw.v3_3_0_pre_2017_11_20.GlfwX11.GlfwWindow;
//$$ import static net.jasonhk.minecraft.mods.torvaldsinput.natives.unix.X11.XIC;
//$$
//$$ public final class MainWindowTransformer implements ITransformer<ClassNode>
//$$ {
//$$     private static final Logger LOGGER = LoggerUtility.getLogger();
//$$
//$$     @Nonnull
//$$     @Override
//$$     public Set<Target> targets()
//$$     {
//$$         return Collections.singleton(Target.targetClass("net.minecraft.client.MainWindow"));
//$$     }
//$$
//$$     @Nonnull
//$$     @Override
//$$     public TransformerVoteResult castVote(ITransformerVotingContext context)
//$$     {
//$$         return TransformerVoteResult.YES;
//$$     }
//$$
//$$     @Nonnull
//$$     @Override
//$$     public ClassNode transform(@Nonnull ClassNode input, @Nonnull ITransformerVotingContext context)
//$$     {
//$$         val guiHandler = new guiHandler();
//$$         addFieldOrThrow(input, guiHandler);
//$$
//$$         val inject_init_RETURN = new inject_init_RETURN();
//$$         input.methods.add(inject_init_RETURN);
//$$
//$$         val inject_close_HEAD = new inject_close_HEAD();
//$$         input.methods.add(inject_close_HEAD);
//$$
//$$         for (val method : input.methods)
//$$         {
//$$             LOGGER.debug(method.name);
//$$
//$$             if (method.name.equals("<init>"))
//$$             {
//$$                 val instructions = method.instructions;
//$$
//$$                 if (instructions.size() == 0) { continue; }
//$$
//$$                 val instructionsIterator = instructions.iterator();
//$$                 while (instructionsIterator.hasNext())
//$$                 {
//$$                     val instruction = instructionsIterator.next();
//$$
//$$                     val opcode = instruction.getOpcode();
//$$                     if ((opcode >= IRETURN) && (opcode <= RETURN))
//$$                     {
//$$                         val insnList = new InsnList();
//$$                         insnList.add(new VarInsnNode(ALOAD, 0));
//$$                         insnList.add(createMethodInsnNode(
//$$                                 INVOKEVIRTUAL,
//$$                                 input, inject_init_RETURN));
//$$
//$$                         instructions.insert(instruction.getPrevious(), insnList);
//$$                     }
//$$                 }
//$$             }
//$$
//$$             switch (method.name)
//$$             {
//$$                 case "<init>":
//$$                 {
//$$                     val that = 0;
//$$
//$$                     val instructions = method.instructions;
//$$                     if (instructions.size() == 0) { continue; }
//$$
//$$                     val instructionsIterator = instructions.iterator();
//$$                     while (instructionsIterator.hasNext())
//$$                     {
//$$                         val instruction = instructionsIterator.next();
//$$
//$$                         val opcode = instruction.getOpcode();
//$$                         if ((opcode >= IRETURN) && (opcode <= RETURN))
//$$                         {
//$$                             val appending = new InsnList();
//$$                             appending.add(new VarInsnNode(ALOAD, that));
//$$                             appending.add(createMethodInsnNode(
//$$                                     INVOKEVIRTUAL,
//$$                                     input, inject_init_RETURN));
//$$
//$$                             instructions.insertBefore(instruction, appending);
//$$                         }
//$$                     }
//$$
//$$                     break;
//$$                 }
//$$                 case "close":
//$$                 {
//$$                     val that = 0;
//$$
//$$                     val instructions = method.instructions;
//$$                     if (instructions.size() == 0) { continue; }
//$$
//$$                     val appending = new InsnList();
//$$                     appending.add(new VarInsnNode(ALOAD, that));
//$$                     appending.add(createMethodInsnNode(
//$$                             INVOKEVIRTUAL,
//$$                             input, inject_close_HEAD));
//$$
//$$                     instructions.insertBefore(instructions.getFirst(), appending);
//$$                     break;
//$$                 }
//$$             }
//$$         }
//$$
//$$         return input;
//$$     }
//$$
//$$     private static class guiHandler extends FieldNode
//$$     {
//$$         private static final String NAME       = getInjectedMemberName(guiHandler.class);
//$$         private static final String DESCRIPTOR = getDescriptor(AbstractGuiHandler.class);
//$$
//$$         private guiHandler()
//$$         {
//$$             super(ASM6, ACC_PRIVATE, NAME, DESCRIPTOR, null, null);
//$$         }
//$$     }
//$$
//$$     private static class inject_init_RETURN extends PreInitializedMethodNode
//$$     {
//$$         private static final String NAME       = getInjectedMemberName(inject_init_RETURN.class);
//$$         private static final String DESCRIPTOR = getMethodDescriptor(VOID_TYPE);
//$$
//$$         private inject_init_RETURN()
//$$         {
//$$             super(ASM6, (ACC_PRIVATE | ACC_FINAL), NAME, DESCRIPTOR, null, null);
//$$         }
//$$
//$$         @Override
//$$         protected void init() throws NoSuchFieldException, NoSuchMethodException
//$$         {
//$$             val methodStart = new LabelNode();
//$$             val methodEnd   = new LabelNode();
//$$
//$$             //<editor-fold desc="Local Variables">
//$$             val that = createLocalVariableNode(
//$$                     "this",
//$$                     MainWindow.class, null,
//$$                     methodStart, methodEnd,
//$$                     0);
//$$
//$$             val window = createLocalVariableNode(
//$$                     "window",
//$$                     GlfwWindow.class, null,
//$$                     methodStart, methodEnd,
//$$                     1);
//$$             val ic = createLocalVariableNode(
//$$                     "ic",
//$$                     XIC.class, null,
//$$                     methodStart, methodEnd,
//$$                     2);
//$$             //</editor-fold>
//$$
//$$             //<editor-fold desc="Instructions">
//$$             instructions.add(methodStart);
//$$
//$$             //<editor-fold desc="// val window = GlfwWindow.of(new Pointer(handle));">
//$$             instructions.add(createTypeInsnNode(NEW, Pointer.class));
//$$             instructions.add(new InsnNode(DUP));
//$$             instructions.add(createVarInsnNode(ALOAD, that));
//$$             instructions.add(createFieldInsnNode(
//$$                     GETFIELD,
//$$                     MainWindow.class.getDeclaredField("handle")));
//$$             instructions.add(createMethodInsnNode(
//$$                     INVOKESPECIAL,
//$$                     Pointer.class.getConstructor(long.class)));
//$$             instructions.add(createMethodInsnNode(
//$$                     INVOKESTATIC,
//$$                     GlfwWindow.class.getMethod("of", Pointer.class)));
//$$             instructions.add(createVarInsnNode(ASTORE, window));
//$$             //</editor-fold>
//$$             //<editor-fold desc="// window.read();">
//$$             instructions.add(createVarInsnNode(ALOAD, window));
//$$             instructions.add(createMethodInsnNode(
//$$                     INVOKEVIRTUAL,
//$$                     GlfwWindow.class.getMethod("read")));
//$$             //</editor-fold>
//$$
//$$             //<editor-fold desc="// val ic = window.getX11InputContext();">
//$$             instructions.add(createVarInsnNode(ALOAD, window));
//$$             instructions.add(createMethodInsnNode(
//$$                     INVOKEVIRTUAL,
//$$                     GlfwWindow.class.getMethod("getX11InputContext")));
//$$             instructions.add(createVarInsnNode(ASTORE, ic));
//$$             //</editor-fold>
//$$             {
//$$                 val endIf = new LabelNode();
//$$
//$$                 //<editor-fold desc="// if (ic != null)">
//$$                 instructions.add(createVarInsnNode(ALOAD, ic));
//$$                 instructions.add(new JumpInsnNode(IFNULL, endIf));
//$$                 //</editor-fold>
//$$                 // {
//$$                 //<editor-fold desc="//     guiHandler = new X11GuiHandler(ic);">
//$$                 instructions.add(createVarInsnNode(ALOAD, that));
//$$                 instructions.add(createTypeInsnNode(NEW, X11GuiHandler.class));
//$$                 instructions.add(new InsnNode(DUP));
//$$                 instructions.add(createVarInsnNode(ALOAD, ic));
//$$                 instructions.add(createMethodInsnNode(
//$$                         INVOKESPECIAL,
//$$                         X11GuiHandler.class.getConstructor(XIC.class)));
//$$                 instructions.add(new FieldInsnNode(
//$$                         PUTFIELD,
//$$                         getInternalName(MainWindow.class), guiHandler.NAME, guiHandler.DESCRIPTOR));
//$$                 //</editor-fold>
//$$                 // }
//$$
//$$                 instructions.add(endIf);
//$$                 instructions.add(createFrameNode(
//$$                         F_APPEND,
//$$                         2, new Object[] { GlfwWindow.class, XIC.class },
//$$                         0, null));
//$$             }
//$$
//$$             {
//$$                 val endIf = new LabelNode();
//$$
//$$                 //<editor-fold desc="// if (guiHandler != null)">
//$$                 instructions.add(createVarInsnNode(ALOAD, that));
//$$                 instructions.add(new FieldInsnNode(
//$$                         GETFIELD,
//$$                         getInternalName(MainWindow.class), guiHandler.NAME, guiHandler.DESCRIPTOR));
//$$                 instructions.add(new JumpInsnNode(IFNULL, endIf));
//$$                 //</editor-fold>
//$$                 // {
//$$                 //<editor-fold desc="//     MinecraftForge.EVENT_BUS.register(guiHandler);">
//$$                 instructions.add(createFieldInsnNode(
//$$                         GETSTATIC,
//$$                         MinecraftForge.class.getField("EVENT_BUS")));
//$$                 instructions.add(createVarInsnNode(ALOAD, that));
//$$                 instructions.add(new FieldInsnNode(
//$$                         GETFIELD,
//$$                         getInternalName(MainWindow.class), guiHandler.NAME, guiHandler.DESCRIPTOR));
//$$                 instructions.add(createMethodInsnNode(
//$$                         INVOKEINTERFACE,
//$$                         IEventBus.class.getMethod("register", Object.class)));
//$$                 //</editor-fold>
//$$                 // }
//$$
//$$                 instructions.add(endIf);
//$$                 instructions.add(new FrameNode(F_SAME, 0, null, 0, null));
//$$             }
//$$
//$$             //<editor-fold desc="// return;">
//$$             instructions.add(new InsnNode(RETURN));
//$$             //</editor-fold>
//$$
//$$             instructions.add(methodEnd);
//$$             //</editor-fold>
//$$
//$$             localVariables.addAll(Arrays.asList(that, window, ic));
//$$             maxStack = 4;
//$$             maxLocals = 3;
//$$         }
//$$     }
//$$
//$$     private static class inject_close_HEAD extends PreInitializedMethodNode
//$$     {
//$$         private static final String NAME       = getInjectedMemberName(inject_close_HEAD.class);
//$$         private static final String DESCRIPTOR = getMethodDescriptor(VOID_TYPE);
//$$
//$$         private inject_close_HEAD()
//$$         {
//$$             super(ASM6, (ACC_PRIVATE | ACC_FINAL), NAME, DESCRIPTOR, null, null);
//$$         }
//$$
//$$         @Override
//$$         protected void init() throws NoSuchFieldException, NoSuchMethodException
//$$         {
//$$             val methodStart = new LabelNode();
//$$             val methodEnd   = new LabelNode();
//$$
//$$             //<editor-fold desc="Local Variables">
//$$             val that = createLocalVariableNode(
//$$                     "this",
//$$                     MainWindow.class, null,
//$$                     methodStart, methodEnd,
//$$                     0);
//$$             //</editor-fold>
//$$
//$$             //<editor-fold desc="Instructions">
//$$             instructions.add(methodStart);
//$$
//$$             {
//$$                 val endIf = new LabelNode();
//$$
//$$                 //<editor-fold desc="// if (guiHandler != null)">
//$$                 instructions.add(createVarInsnNode(ALOAD, that));
//$$                 instructions.add(new FieldInsnNode(
//$$                         GETFIELD,
//$$                         getInternalName(MainWindow.class), guiHandler.NAME, guiHandler.DESCRIPTOR));
//$$                 instructions.add(new JumpInsnNode(IFNULL, endIf));
//$$                 //</editor-fold>
//$$                 // {
//$$                 //<editor-fold desc="//     MinecraftForge.EVENT_BUS.unregister(guiHandler);">
//$$                 instructions.add(createFieldInsnNode(
//$$                         GETSTATIC,
//$$                         MinecraftForge.class.getField("EVENT_BUS")));
//$$                 instructions.add(createVarInsnNode(ALOAD, that));
//$$                 instructions.add(new FieldInsnNode(
//$$                         GETFIELD,
//$$                         getInternalName(MainWindow.class), guiHandler.NAME, guiHandler.DESCRIPTOR));
//$$                 instructions.add(createMethodInsnNode(
//$$                         INVOKEINTERFACE,
//$$                         IEventBus.class.getMethod("unregister", Object.class)));
//$$                 //</editor-fold>
//$$                 //<editor-fold desc="//     guiHandler = null;">
//$$                 instructions.add(createVarInsnNode(ALOAD, that));
//$$                 instructions.add(new InsnNode(ACONST_NULL));
//$$                 instructions.add(new FieldInsnNode(
//$$                         PUTFIELD,
//$$                         getInternalName(MainWindow.class), guiHandler.NAME, guiHandler.DESCRIPTOR));
//$$                 //</editor-fold>
//$$                 // }
//$$
//$$                 instructions.add(endIf);
//$$                 instructions.add(new FrameNode(F_SAME, 0, null, 0, null));
//$$             }
//$$
//$$             //<editor-fold desc="// return;">
//$$             instructions.add(new InsnNode(RETURN));
//$$             //</editor-fold>
//$$
//$$             instructions.add(methodEnd);
//$$             //</editor-fold>
//$$
//$$             localVariables.add(that);
//$$             maxStack = 2;
//$$             maxLocals = 1;
//$$         }
//$$     }
//$$ }
//#endif
