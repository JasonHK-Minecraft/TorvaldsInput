package net.jasonhk.minecraft.mods.torvaldsinput.gui.handlers;

import net.minecraftforge.eventbus.api.SubscribeEvent;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import net.jasonhk.minecraft.mods.torvaldsinput.gui.events.WindowFocusChangeEvent;
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

    private boolean lastInputFocusState;

    public X11GuiHandler(XIC ic)
    {
        this.ic = ic;
    }

    @Override
    protected final void setInputFocus(boolean focused)
    {
        lastInputFocusState = focused;
        setInputFocusInternal(focused);
    }

    /**
     * The method that actually responsible to toggle the keyboard focus state of
     * {@linkplain #ic the X input context}.
     *
     * @param focused Whether the X input context should receive keyboard focus.
     */
    private void setInputFocusInternal(boolean focused)
    {
        X11Input.toggleIcFocus(ic, focused);
    }

    @SubscribeEvent
    public final void onWindowFocusChange(WindowFocusChangeEvent event)
    {
        if (event.isFocused())
        {
            setInputFocusInternal(lastInputFocusState);
        }
    }
}
