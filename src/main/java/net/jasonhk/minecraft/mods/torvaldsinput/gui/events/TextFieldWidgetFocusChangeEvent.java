//#if MINECRAFT>=11400
package net.jasonhk.minecraft.mods.torvaldsinput.gui.events;

import net.minecraft.client.gui.widget.TextFieldWidget;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString
public class TextFieldWidgetFocusChangeEvent extends TextFieldFocusChangeEvent
{
    @Getter
    private final TextFieldWidget textField;

    public TextFieldWidgetFocusChangeEvent(TextFieldWidget textField, boolean focused)
    {
        super(focused);
        this.textField = textField;
    }
}
//#endif
