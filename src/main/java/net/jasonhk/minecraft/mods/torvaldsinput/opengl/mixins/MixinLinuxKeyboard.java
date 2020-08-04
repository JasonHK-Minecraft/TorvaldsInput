package net.jasonhk.minecraft.mods.torvaldsinput.opengl.mixins;

import net.minecraftforge.common.MinecraftForge;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.jasonhk.minecraft.mods.torvaldsinput.gui.handlers.GuiHandler;
import net.jasonhk.minecraft.mods.torvaldsinput.opengl.LinuxKeyboard;

/**
 * The mixin injector class targeting {@link org.lwjgl.opengl.LinuxKeyboard}.
 */
@SuppressWarnings("JavadocReference")
@Mixin(targets = "org.lwjgl.opengl.LinuxKeyboard", remap = false)
public abstract class MixinLinuxKeyboard
{
    /**
     * The pointer to the input context of an X client.
     *
     * @see org.lwjgl.opengl.LinuxKeyboard#xic
     */
    @Shadow
    @Final
    private long xic;

    /**
     * The {@code GuiHandler} event handler.
     */
    @Unique
    private GuiHandler guiHandler;

    /**
     * Redirects the invocations of {@link org.lwjgl.opengl.LinuxKeyboard#openIM(long)} to my own
     * {@linkplain LinuxKeyboard#openIM(long) implementation}.
     *
     * @param display The pointer to an X display connection.
     * @return The pointer to the input method.
     *
     * @see org.lwjgl.opengl.LinuxKeyboard#LinuxKeyboard(long, long)
     */
    @Redirect(method = "<init>",
              at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/LinuxKeyboard;openIM(J)J"))
    private long redirect_init_INVOKE_openIM(final long display)
    {
        return LinuxKeyboard.openIM(display);
    }

    /**
     * Injects the codes to initialise and register the {@link GuiHandler} event handler.
     *
     * @param callback The information on the method call.
     *
     * @see org.lwjgl.opengl.LinuxKeyboard#LinuxKeyboard(long, long)
     */
    @Inject(method = "<init>", at = @At(value = "RETURN"))
    private void inject_init_RETURN(final CallbackInfo callback)
    {
        if (xic != 0)
        {
            guiHandler = new GuiHandler(xic);
            MinecraftForge.EVENT_BUS.register(guiHandler);
        }
    }

    /**
     * Injects the codes to unregister and remove the {@link GuiHandler} event handler.
     *
     * @param callback The information on the method call.
     *
     * @see org.lwjgl.opengl.LinuxKeyboard#destroy(long)
     */
    @Inject(method = "destroy", at = @At(value = "HEAD"))
    private void inject_destroy_HEAD(final CallbackInfo callback)
    {
        if (guiHandler != null)
        {
            MinecraftForge.EVENT_BUS.unregister(guiHandler);
            guiHandler = null;
        }
    }
}
