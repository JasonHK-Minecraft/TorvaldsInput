//#if MINECRAFT>=11300 && MINECRAFT<11400
//$$ package net.jasonhk.minecraft.mods.torvaldsinput.gui.transformers;
//$$
//$$ import java.util.Arrays;
//$$ import java.util.Collections;
//$$ import java.util.Set;
//$$
//$$ import javax.annotation.Nonnull;
//$$
//$$ import net.minecraft.client.gui.GuiTextField;
//$$
//$$ import net.minecraftforge.common.MinecraftForge;
//$$ import net.minecraftforge.eventbus.api.Event;
//$$ import net.minecraftforge.eventbus.api.IEventBus;
//$$
//$$ import org.apache.logging.log4j.Logger;
//$$
//$$ import lombok.SneakyThrows;
//$$ import lombok.val;
//$$
//$$ import org.objectweb.asm.tree.ClassNode;
//$$ import org.objectweb.asm.tree.InsnList;
//$$ import org.objectweb.asm.tree.InsnNode;
//$$ import org.objectweb.asm.tree.LabelNode;
//$$ import org.objectweb.asm.tree.LocalVariableNode;
//$$ import org.objectweb.asm.tree.MethodNode;
//$$ import org.objectweb.asm.tree.VarInsnNode;
//$$ import static org.objectweb.asm.Opcodes.*;
//$$ import static org.objectweb.asm.Type.BOOLEAN_TYPE;
//$$ import static org.objectweb.asm.Type.VOID_TYPE;
//$$ import static org.objectweb.asm.Type.getMethodDescriptor;
//$$
//$$ import cpw.mods.modlauncher.api.ITransformer;
//$$ import cpw.mods.modlauncher.api.ITransformerVotingContext;
//$$ import cpw.mods.modlauncher.api.TransformerVoteResult;
//$$
//$$ import net.jasonhk.minecraft.mods.torvaldsinput.core.transformer.PreInitializedMethodNode;
//$$ import net.jasonhk.minecraft.mods.torvaldsinput.core.utilities.LoggerUtility;
//$$ import net.jasonhk.minecraft.mods.torvaldsinput.gui.events.GuiTextFieldFocusChangeEvent;
//$$ import static net.jasonhk.minecraft.mods.torvaldsinput.core.utilities.TransformerUtility.createFieldInsnNode;
//$$ import static net.jasonhk.minecraft.mods.torvaldsinput.core.utilities.TransformerUtility.createLocalVariableNode;
//$$ import static net.jasonhk.minecraft.mods.torvaldsinput.core.utilities.TransformerUtility.createMethodInsnNode;
//$$ import static net.jasonhk.minecraft.mods.torvaldsinput.core.utilities.TransformerUtility.createTarget;
//$$ import static net.jasonhk.minecraft.mods.torvaldsinput.core.utilities.TransformerUtility.createTypeInsnNode;
//$$ import static net.jasonhk.minecraft.mods.torvaldsinput.core.utilities.TransformerUtility.createVarInsnNode;
//$$ import static net.jasonhk.minecraft.mods.torvaldsinput.core.utilities.TransformerUtility.getInjectedMemberName;
//$$
//$$ public class GuiTextFieldTransformer implements ITransformer<ClassNode>
//$$ {
//$$     private static final Logger LOGGER = LoggerUtility.getLogger();
//$$
//$$     @Nonnull
//$$     @Override
//$$     public Set<Target> targets()
//$$     {
//$$         return Collections.singleton(createTarget(GuiTextField.class));
//$$     }
//$$
//$$     @Nonnull
//$$     @Override
//$$     public TransformerVoteResult castVote(@Nonnull ITransformerVotingContext context)
//$$     {
//$$         return TransformerVoteResult.YES;
//$$
//$$     }
//$$
//$$     @Nonnull
//$$     @Override
//$$     public ClassNode transform(
//$$             @Nonnull ClassNode input,
//$$             @Nonnull ITransformerVotingContext context)
//$$     {
//$$         val inject_setFocused_RETURN = new inject_setFocused_RETURN();
//$$         input.methods.add(inject_setFocused_RETURN);
//$$
//$$         for (val method : input.methods)
//$$         {
//$$             if (method.name.equals("setFocused"))
//$$             {
//$$                 val that        = 0;
//$$                 val isFocusedIn = 1;
//$$
//$$                 val instructions = method.instructions;
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
//$$                         insnList.add(new VarInsnNode(ALOAD, that));
//$$                         insnList.add(new VarInsnNode(ILOAD, isFocusedIn));
//$$                         insnList.add(createMethodInsnNode(
//$$                                 INVOKEVIRTUAL, input, inject_setFocused_RETURN));
//$$
//$$                         instructions.insert(instruction.getPrevious(), insnList);
//$$                     }
//$$                 }
//$$             }
//$$         }
//$$
//$$         return input;
//$$     }
//$$
//$$     private static final class inject_setFocused_RETURN extends PreInitializedMethodNode
//$$     {
//$$         private static final String NAME       = getInjectedMemberName(inject_setFocused_RETURN.class);
//$$         private static final String DESCRIPTOR = getMethodDescriptor(VOID_TYPE, BOOLEAN_TYPE);
//$$
//$$         private inject_setFocused_RETURN()
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
//$$                     GuiTextField.class, null,
//$$                     methodStart, methodEnd,
//$$                     0);
//$$
//$$             val focused = createLocalVariableNode(
//$$                     "focused",
//$$                     boolean.class, null,
//$$                     methodStart, methodEnd,
//$$                     1);
//$$             //</editor-fold>
//$$
//$$             //<editor-fold desc="Instructions">
//$$             instructions.add(methodStart);
//$$
//$$             //<editor-fold desc="MinecraftForge.EVENT_BUS.post(new TextFieldWidgetFocusChangeEvent(this, focused));">
//$$             instructions.add(createFieldInsnNode(
//$$                     GETSTATIC,
//$$                     MinecraftForge.class.getField("EVENT_BUS")));
//$$             instructions.add(createTypeInsnNode(NEW, GuiTextFieldFocusChangeEvent.class));
//$$             instructions.add(new InsnNode(DUP));
//$$             instructions.add(createVarInsnNode(ALOAD, that));
//$$             instructions.add(createVarInsnNode(ILOAD, focused));
//$$             instructions.add(createMethodInsnNode(
//$$                     INVOKESPECIAL,
//$$                     GuiTextFieldFocusChangeEvent.class.getConstructor(
//$$                             GuiTextField.class, boolean.class)));
//$$             instructions.add(createMethodInsnNode(
//$$                     INVOKEINTERFACE,
//$$                     IEventBus.class.getMethod("post", Event.class)));
//$$             instructions.add(new InsnNode(POP));
//$$             //</editor-fold>
//$$             //<editor-fold desc="return;">
//$$             instructions.add(new InsnNode(RETURN));
//$$             //</editor-fold>
//$$
//$$             instructions.add(methodEnd);
//$$             //</editor-fold>
//$$
//$$             localVariables.addAll(Arrays.asList(that, focused));
//$$             maxStack = 5;
//$$             maxLocals = 2;
//$$         }
//$$     }
//$$ }
//#endif
