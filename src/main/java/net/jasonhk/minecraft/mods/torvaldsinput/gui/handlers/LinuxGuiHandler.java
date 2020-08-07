package net.jasonhk.minecraft.mods.torvaldsinput.gui.handlers;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import net.jasonhk.minecraft.mods.torvaldsinput.input.LinuxKeyboard;

@EqualsAndHashCode(callSuper = false)
@ToString
public final class LinuxGuiHandler extends AbstractGuiHandler
{
    /**
     * The pointer to an X input context.
     */
    private final long xic;

    public LinuxGuiHandler(long xic)
    {
        this.xic = xic;
    }

    @Override
    protected void setInputFocus(boolean focused)
    {
        LinuxKeyboard.toggleICFocus(xic, focused);
    }
}
