package net.jasonhk.minecraft.mods.torvaldsinput.exceptions;

public class WhyYouInvokeThisShadowMethodException extends IllegalStateException
{
    public WhyYouInvokeThisShadowMethodException()
    {
        super("You shouldn't invoke this @Shadow placeholder method, BUT YOU DID...for some reason.");
    }
}
