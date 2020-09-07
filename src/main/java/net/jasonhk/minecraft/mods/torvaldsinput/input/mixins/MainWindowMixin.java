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

import com.github.zafarkhaja.semver.Version;

import lombok.val;

import net.jasonhk.minecraft.mods.torvaldsinput.core.utilities.LwjglVersion;
import net.jasonhk.minecraft.mods.torvaldsinput.gui.events.GlfwWindowFocusChangeEvent;
import net.jasonhk.minecraft.mods.torvaldsinput.gui.handlers.AbstractGuiHandler;
import net.jasonhk.minecraft.mods.torvaldsinput.gui.handlers.X11GuiHandler;
import static net.jasonhk.minecraft.mods.torvaldsinput.natives.glfw.Glfw.AbstractGlfwWindow;
import static net.jasonhk.minecraft.mods.torvaldsinput.natives.glfw.GlfwX11.IGlfwWindow;

@Mixin(MainWindow.class)
public abstract class MainWindowMixin
{
    @Shadow
    @Final
    private long handle;

    @Unique
    private AbstractGuiHandler guiHandler;

    @Inject(method = "<init>", at = @At(value = "RETURN"))
    private void inject__init__RETURN(CallbackInfo callback)
    {
        AbstractGlfwWindow window;
        {
            val pointer = new Pointer(handle);

            if (LwjglVersion.VERSION.greaterThanOrEqualTo(Version.forIntegers(3, 2, 2)))
            {
                window = net.jasonhk.minecraft.mods.torvaldsinput.natives.glfw
                        .v3_3_0.GlfwX11.GlfwWindow.of(pointer);
            }
            else
            {
                window = net.jasonhk.minecraft.mods.torvaldsinput.natives.glfw
                        .v3_3_0_pre_2017_11_20.GlfwX11.GlfwWindow.of(pointer);
            }
        }

        window.read();

        val ic = ((IGlfwWindow) window).getX11InputContext();
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

    @Inject(method = "onWindowFocusUpdate", at = @At(value = "RETURN"))
    private void inject_onWindowFocusUpdate_RETURN(
            long windowPointer,
            boolean hasFocus,
            CallbackInfo callback)
    {
        if (windowPointer == handle)
        {
            MinecraftForge.EVENT_BUS.post(
                    new GlfwWindowFocusChangeEvent(new Pointer(windowPointer), hasFocus));
        }
    }
}
//#endif
