package net.jasonhk.minecraft.mods.torvaldsinput.exceptions;

/**
 * Well, I don't know what on Earth to motivate you to invoke a method annotated with
 * {@link org.spongepowered.asm.mixin.Shadow @Shadow}...love or responsibility?
 */
public class WhyYouInvokeThisShadowMethodException extends IllegalStateException
{
    /**
     * Obtains an instance of {@code WhyYouInvokeThisShadowMethodException}.
     */
    public WhyYouInvokeThisShadowMethodException()
    {
        super("You shouldn't invoke this @Shadow placeholder method, BUT YOU DID...for some reason.");
    }
}
