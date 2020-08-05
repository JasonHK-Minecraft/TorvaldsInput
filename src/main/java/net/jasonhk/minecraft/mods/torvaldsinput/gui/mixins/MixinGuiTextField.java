package net.jasonhk.minecraft.mods.torvaldsinput.gui.mixins;

import net.minecraft.client.gui.GuiTextField;

import net.minecraftforge.common.MinecraftForge;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.jasonhk.minecraft.mods.torvaldsinput.gui.events.GuiTextFieldFocusChangeEvent;

/**
 * The mixin injector class targeting {@link GuiTextField}.
 */
@Mixin(GuiTextField.class)
public abstract class MixinGuiTextField
{
    /**
     * Injects the codes to post a {@link GuiTextFieldFocusChangeEvent}.
     *
     * @param focused  The focus state of this {@link GuiTextField}.
     * @param callback The information on the method call.
     *                 
     * @see net.minecraft.client.gui.GuiTextField#setFocused(boolean)
     */
    @Inject(method = "setFocused", at = @At(value = "RETURN"))
    private void inject_setFocused_RETURN(boolean focused, CallbackInfo callback)
    {
        MinecraftForge.EVENT_BUS.post(new GuiTextFieldFocusChangeEvent(focused));
    }
}
