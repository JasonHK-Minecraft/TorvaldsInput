//#if MINECRAFT>=11400
package net.jasonhk.minecraft.mods.torvaldsinput.gui.mixins;

import net.minecraft.client.gui.widget.TextFieldWidget;

import net.minecraftforge.common.MinecraftForge;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.jasonhk.minecraft.mods.torvaldsinput.gui.events.TextFieldWidgetFocusChangeEvent;

/**
 * The mixin class targeting {@link TextFieldWidget}.
 */
@Mixin(TextFieldWidget.class)
public abstract class TextFieldWidgetMixin
{
    /**
     * Injects the codes to post a {@link TextFieldWidgetFocusChangeEvent}.
     *
     * @param focused  The focus state of this {@link TextFieldWidget}.
     * @param callback The information on the method call.
     *
     * @see net.minecraft.client.gui.widget.TextFieldWidget#setFocused2(boolean)
     */
    @Inject(method = "setFocused2", at = @At(value = "RETURN"))
    private void inject_setFocused2_RETURN(boolean focused, CallbackInfo callback)
    {
        MinecraftForge.EVENT_BUS.post(
                new TextFieldWidgetFocusChangeEvent((TextFieldWidget) (Object) this,
                                                    focused));
    }
}
//#endif
