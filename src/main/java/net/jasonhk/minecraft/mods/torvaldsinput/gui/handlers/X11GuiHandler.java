package net.jasonhk.minecraft.mods.torvaldsinput.gui.handlers;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import net.jasonhk.minecraft.mods.torvaldsinput.input.X11Input;
import static net.jasonhk.minecraft.mods.torvaldsinput.natives.unix.X11.XIC;

@EqualsAndHashCode(callSuper = false)
@ToString
public final class X11GuiHandler extends AbstractGuiHandler
{
    /**
     * The pointer to an X input context.
     */
    private final XIC ic;

    public X11GuiHandler(XIC ic)
    {
        this.ic = ic;
    }

    @Override
    protected void setInputFocus(boolean focused)
    {
        X11Input.toggleIcFocus(ic, focused);
    }
}
