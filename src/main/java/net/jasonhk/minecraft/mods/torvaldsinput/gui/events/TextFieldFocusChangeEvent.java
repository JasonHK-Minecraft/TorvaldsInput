package net.jasonhk.minecraft.mods.torvaldsinput.gui.events;

import net.minecraftforge.eventbus.api.Event;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = false)
@ToString
public class TextFieldFocusChangeEvent extends Event
{
    @Getter
    private final boolean focused;

    public TextFieldFocusChangeEvent(boolean focused)
    {
        this.focused = focused;
    }
}
