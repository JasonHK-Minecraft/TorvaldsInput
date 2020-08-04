package net.jasonhk.minecraft.mods.torvaldsinput.gui.handlers;

import lombok.Data;
import lombok.EqualsAndHashCode;

import net.jasonhk.minecraft.mods.torvaldsinput.opengl.LinuxKeyboard;

@Data
@EqualsAndHashCode(callSuper = false)
public final class GuiHandler extends AbstractGuiHandler
{
    /**
     * The pointer to the input context of an X client.
     */
    private final long xic;

    protected void setInputFocus(boolean focused)
    {
        LinuxKeyboard.toggleICFocus(xic, focused);
    }
}
