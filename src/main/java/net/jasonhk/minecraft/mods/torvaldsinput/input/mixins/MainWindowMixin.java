//#if MINECRAFT>=11400
package net.jasonhk.minecraft.mods.torvaldsinput.input.mixins;

import com.sun.jna.Pointer;

import net.minecraft.client.MainWindow;

import net.minecraftforge.common.MinecraftForge;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import lombok.val;

import net.jasonhk.minecraft.mods.torvaldsinput.gui.handlers.AbstractGuiHandler;
import net.jasonhk.minecraft.mods.torvaldsinput.gui.handlers.X11GuiHandler;
import static net.jasonhk.minecraft.mods.torvaldsinput.natives.glfw.v3_3.GlfwX11.GlfwWindow;

@SuppressWarnings("UnusedMixin")
@Mixin(MainWindow.class)
public abstract class MainWindowMixin
{
    @Shadow
    @Final
    private long handle;

    @Unique
    private AbstractGuiHandler guiHandler;

    @Inject(method = "<init>", at = @At(value = "RETURN"))
    private void inject_init_RETURN(CallbackInfo callback)
    {
        val window = GlfwWindow.of(new Pointer(handle));
        window.read();

        val ic = window.getX11InputContext();
        if (ic != null)
        {
            guiHandler = new X11GuiHandler(ic);
        }

        if (guiHandler != null)
        {
            MinecraftForge.EVENT_BUS.register(guiHandler);
        }
    }

    @Inject(method = "close", at = @At(value = "HEAD"))
    private void inject_close_HEAD(CallbackInfo callback)
    {
        if (guiHandler != null)
        {
            MinecraftForge.EVENT_BUS.unregister(guiHandler);
            guiHandler = null;
        }
    }
}
//#endif
