package net.jasonhk.minecraft.mods.torvaldsinput.gui.events;

import com.sun.jna.Pointer;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = false)
@ToString
public class GlfwWindowFocusChangeEvent extends WindowFocusChangeEvent
{
    @Getter
    private final Pointer window;

    public GlfwWindowFocusChangeEvent(Pointer window, boolean focused)
    {
        super(focused);
        this.window = window;
    }
}
