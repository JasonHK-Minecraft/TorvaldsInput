package net.jasonhk.minecraft.mods.torvaldsinput.gui.events;

import net.minecraftforge.eventbus.api.Event;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = false)
@ToString
public class WindowFocusChangeEvent extends Event
{
    @Getter
    private final boolean focused;

    public WindowFocusChangeEvent(boolean focused)
    {
        this.focused = focused;
    }
}
