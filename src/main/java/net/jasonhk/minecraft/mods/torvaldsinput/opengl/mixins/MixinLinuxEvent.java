package net.jasonhk.minecraft.mods.torvaldsinput.opengl.mixins;

import java.nio.ByteBuffer;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.jasonhk.minecraft.mods.torvaldsinput.exceptions.WhyYouInvokeThisShadowMethodException;

/**
 * The mixin injector class targeting {@link org.lwjgl.opengl.LinuxEvent}.
 */
@SuppressWarnings("JavadocReference")
@Mixin(targets = "org.lwjgl.opengl.LinuxEvent", remap = false)
public abstract class MixinLinuxEvent
{
    @Shadow
    @Final
    private ByteBuffer event_buffer;

    private static boolean enableIME = true;

    private boolean finalEventFiltered = false;

    /**
     * @author Jason Kwok
     * @reason The logics inside this method needs to be changed completely.
     */
    @Overwrite
    public boolean filterEvent(long window)
    {
        return finalEventFiltered;
    }

    @SuppressWarnings("SameParameterValue")
    private boolean filterEventInternal(long window)
    {
        return (enableIME && nFilterEvent(event_buffer, window));
    }

    @Shadow
    private static boolean nFilterEvent(ByteBuffer event_buffer, long window)
    {
        throw new WhyYouInvokeThisShadowMethodException();
    }

    @Inject(method = "nextEvent", at = @At(value = "RETURN"))
    private void inject_nextEvent_RETURN(long display, CallbackInfo callback)
    {
        finalEventFiltered = filterEventInternal(0L);
    }
}
