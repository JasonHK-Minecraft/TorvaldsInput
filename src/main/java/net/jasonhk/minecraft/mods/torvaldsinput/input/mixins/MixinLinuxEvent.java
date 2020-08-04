package net.jasonhk.minecraft.mods.torvaldsinput.input.mixins;

import java.nio.ByteBuffer;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

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

    private boolean eventFiltered = false;

    @SuppressWarnings("SameParameterValue")
    private boolean filterEventInternal(final long window)
    {
        return (enableIME && nFilterEvent(event_buffer, window));
    }

    @Shadow
    private static boolean nFilterEvent(ByteBuffer event_buffer, long window)
    {
        throw new WhyYouInvokeThisShadowMethodException();
    }

    @Inject(method = "filterEvent", at = @At(value = "HEAD"), cancellable = true)
    private void inject_filterEvent_HEAD(final CallbackInfoReturnable<Boolean> callback)
    {
        callback.setReturnValue(eventFiltered);
    }

    @Inject(method = "nextEvent", at = @At(value = "RETURN"))
    private void inject_nextEvent_RETURN(final CallbackInfo callback)
    {
        eventFiltered = filterEventInternal(0L);
    }
}
