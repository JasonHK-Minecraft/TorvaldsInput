package net.jasonhk.minecraft.mods.torvaldsinput.gui.events;

import net.minecraftforge.eventbus.api.Event;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class GuiTextFieldFocusChangeEvent extends Event
{
    private final boolean focused;
}
