//#if MINECRAFT>=11300
// This mixin class is meant for LWJGL 2.X, which is used by Minecraft 1.12.2 or older.
//#else
//$$ package net.jasonhk.minecraft.mods.torvaldsinput.input.mixins;
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
//$$ import net.jasonhk.minecraft.mods.torvaldsinput.gui.handlers.AbstractGuiHandler;
//$$ import net.jasonhk.minecraft.mods.torvaldsinput.gui.handlers.LinuxGuiHandler;
//$$ import net.jasonhk.minecraft.mods.torvaldsinput.input.LinuxKeyboard;
//$$
//$$ /**
//$$  * The mixin class targeting {@link org.lwjgl.opengl.LinuxKeyboard}.
//$$  */
//$$ @SuppressWarnings({ "JavadocReference", "UnusedMixin" })
//$$ @Mixin(targets = "org.lwjgl.opengl.LinuxKeyboard", remap = false)
//$$ public abstract class MixinLinuxKeyboard
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
//$$      * The {@code GuiHandler} event handler.
//$$      */
//$$     @Unique
//$$     private AbstractGuiHandler guiHandler;
//$$
//$$     /**
//$$      * Redirects the invocations of {@link org.lwjgl.opengl.LinuxKeyboard#openIM(long)} to my own
//$$      * {@linkplain LinuxKeyboard#openIM(long) implementation}.
//$$      *
//$$      * @param display The pointer to an X display connection.
//$$      * @return The pointer to an X input method.
//$$      *
//$$      * @see org.lwjgl.opengl.LinuxKeyboard#LinuxKeyboard(long, long)
//$$      */
//$$     @Redirect(method = "<init>",
//$$               at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/LinuxKeyboard;openIM(J)J"))
//$$     private long redirect_init_INVOKE_openIM(long display)
//$$     {
//$$         return LinuxKeyboard.openIM(display);
//$$     }
//$$
//$$     /**
//$$      * Injects the codes to initialise and register the {@link #guiHandler GuiHandler} event handler.
//$$      *
//$$      * @param callback The information on the method call.
//$$      *
//$$      * @see org.lwjgl.opengl.LinuxKeyboard#LinuxKeyboard(long, long)
//$$      */
//$$     @Inject(method = "<init>", at = @At(value = "RETURN"))
//$$     private void inject_init_RETURN(CallbackInfo callback)
//$$     {
//$$         if (xic != 0)
//$$         {
//$$             guiHandler = new LinuxGuiHandler(xic);
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
