//#if MINECRAFT<11300
//$$ package net.jasonhk.minecraft.mods.torvaldsinput.input.mixins;
//$$
//$$ import com.sun.jna.Pointer;
//$$ import com.sun.jna.platform.unix.X11;
//$$
//$$ import net.minecraftforge.common.MinecraftForge;
//$$
//$$ import org.spongepowered.asm.mixin.Final;
//$$ import org.spongepowered.asm.mixin.Mixin;
//$$ import org.spongepowered.asm.mixin.Shadow;
//$$ import org.spongepowered.asm.mixin.Unique;
//$$ import org.spongepowered.asm.mixin.injection.At;
//$$ import org.spongepowered.asm.mixin.injection.Inject;
//$$ import org.spongepowered.asm.mixin.injection.Redirect;
//$$ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//$$
//$$ import lombok.val;
//$$
//$$ import net.jasonhk.minecraft.mods.torvaldsinput.gui.handlers.X11GuiHandler;
//$$ import net.jasonhk.minecraft.mods.torvaldsinput.input.X11Input;
//$$ import static net.jasonhk.minecraft.mods.torvaldsinput.natives.unix.X11.Display;
//$$ import static net.jasonhk.minecraft.mods.torvaldsinput.natives.unix.X11.XIC;
//$$
//$$ /**
//$$  * The mixin class targeting {@link org.lwjgl.opengl.LinuxKeyboard}.
//$$  */
//$$ @SuppressWarnings({ "JavadocReference", "UnusedMixin" })
//$$ @Mixin(targets = "org.lwjgl.opengl.LinuxKeyboard", remap = false)
//$$ public abstract class LinuxKeyboardMixin
//$$ {
//$$     /**
//$$      * The pointer to the input context of an X client.
//$$      *
//$$      * @see org.lwjgl.opengl.LinuxKeyboard#xic
//$$      */
//$$     @Shadow
//$$     @Final
//$$     private long xic;
//$$
//$$     /**
//$$      * The GUI event handler.
//$$      */
//$$     @Unique
//$$     private X11GuiHandler guiHandler;
//$$
//$$     /**
//$$      * Redirects the invocations of {@link org.lwjgl.opengl.LinuxKeyboard#openIM(long)} to my own
//$$      * {@linkplain X11Input#openIm(X11.Display) implementation}.
//$$      *
//$$      * @param display The pointer to an X display connection.
//$$      * @return The pointer to an X input method.
//$$      *
//$$      * @see org.lwjgl.opengl.LinuxKeyboard#LinuxKeyboard(long, long)
//$$      */
//$$     @Redirect(method = "<init>",
//$$               at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/LinuxKeyboard;openIM(J)J"))
//$$     private long redirect__init__INVOKE_openIM(long display)
//$$     {
//$$         val im = X11Input.openIm(Display.of(new Pointer(display)));
//$$         return Pointer.nativeValue(im.getPointer());
//$$     }
//$$
//$$     /**
//$$      * Injects the codes to initialise and register the {@link #guiHandler GuiHandler} event
//$$      * handler.
//$$      *
//$$      * @param callback The information on the method call.
//$$      *
//$$      * @see org.lwjgl.opengl.LinuxKeyboard#LinuxKeyboard(long, long)
//$$      */
//$$     @Inject(method = "<init>", at = @At(value = "RETURN"))
//$$     private void inject__init__RETURN(CallbackInfo callback)
//$$     {
//$$         if (xic != 0)
//$$         {
//$$             guiHandler = new X11GuiHandler(XIC.of(new Pointer(xic)));
//$$             MinecraftForge.EVENT_BUS.register(guiHandler);
//$$         }
//$$     }
//$$
//$$     /**
//$$      * Injects the codes to unregister and remove the {@link #guiHandler GuiHandler} event handler.
//$$      *
//$$      * @param callback The information on the method call.
//$$      *
//$$      * @see org.lwjgl.opengl.LinuxKeyboard#destroy(long)
//$$      */
//$$     @Inject(method = "destroy", at = @At(value = "HEAD"))
//$$     private void inject_destroy_HEAD(CallbackInfo callback)
//$$     {
//$$         if (guiHandler != null)
//$$         {
//$$             MinecraftForge.EVENT_BUS.unregister(guiHandler);
//$$             guiHandler = null;
//$$         }
//$$     }
//$$ }
//#endif
