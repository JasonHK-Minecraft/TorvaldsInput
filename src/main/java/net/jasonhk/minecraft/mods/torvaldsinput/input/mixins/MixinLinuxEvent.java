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
    /**
     * The {@code ByteBuffer} storing an X event.
     *
     * @see org.lwjgl.opengl.LinuxEvent#event_buffer
     */
    @Shadow
    @Final
    private ByteBuffer event_buffer;

    private static boolean enableIME = true;

    /**
     * Whether {@linkplain #event_buffer this X event} was filtered by some X input method.
     */
    private boolean eventFiltered = false;

    /**
     * Checks whether some X input method has filtered {@linkplain #event_buffer this X event}.
     *
     * @param window The pointer to an X window for which the filter is to be applied.
     * @return Returns {@code true} if some X input method has filtered the X event, {@code false}
     *         otherwise.
     */
    @SuppressWarnings("SameParameterValue")
    private boolean filterEventInternal(final long window)
    {
        return (enableIME && nFilterEvent(event_buffer, window));
    }

    /**
     * Checks whether some X input method has filtered the given X event.
     *
     * @param event_buffer The X event to filter.
     * @param window       The pointer to an X window for which the filter is to be applied.
     * @return Returns {@code true} if some X input method has filtered the given X event,
     *         {@code false} otherwise.
     *
     * @see org.lwjgl.opengl.LinuxEvent#nFilterEvent(ByteBuffer, long)
     * @see <a href="https://www.x.org/releases/current/doc/man/man3/XFilterEvent.3.xhtml">
     *         <code>XFilterEvent(XEvent*, Window)</code></a>
     */
    @Shadow
    private static boolean nFilterEvent(ByteBuffer event_buffer, long window)
    {
        throw new WhyYouInvokeThisShadowMethodException();
    }

    /**
     * Injects the codes to override the return value of this method.
     *
     * @param callback The information on the method call.
     *
     * @see org.lwjgl.opengl.LinuxEvent#filterEvent(long)
     */
    @Inject(method = "filterEvent", at = @At(value = "HEAD"), cancellable = true)
    private void inject_filterEvent_HEAD(final CallbackInfoReturnable<Boolean> callback)
    {
        callback.setReturnValue(eventFiltered);
    }

    /**
     * Injects the codes to check earlier whether {@linkplain #event_buffer this X event} was
     * filtered.
     *
     * @param callback The information on the method call.
     *
     * @see org.lwjgl.opengl.LinuxEvent#nextEvent(long)
     */
    @Inject(method = "nextEvent", at = @At(value = "RETURN"))
    private void inject_nextEvent_RETURN(final CallbackInfo callback)
    {
        eventFiltered = filterEventInternal(0L);
    }
}
