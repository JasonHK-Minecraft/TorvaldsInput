package net.jasonhk.minecraft.mods.torvaldsinput.gui.handlers;

//#if MINECRAFT>=11400
import net.minecraft.client.gui.screen.EditBookScreen;
import net.minecraft.client.gui.screen.EditSignScreen;
//#else
//$$ import net.minecraft.client.gui.GuiScreenBook;
//$$ import net.minecraft.client.gui.inventory.GuiEditSign;
//#endif

import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import lombok.val;

import net.jasonhk.minecraft.mods.torvaldsinput.gui.events.TextFieldFocusChangeEvent;

public abstract class AbstractGuiHandler
{
    @SuppressWarnings("DuplicateCondition")
    @SubscribeEvent
    public final void onGuiOpen(GuiOpenEvent event)
    {
        boolean canInput;

        val gui = event.getGui();
        if (gui == null)
        {
            canInput = false;
        }
        else
        {
            //#if MINECRAFT>=11400
            canInput = ((gui instanceof EditBookScreen) || (gui instanceof EditSignScreen));
            //#else
            //$$ canInput = ((gui instanceof GuiScreenBook) || (gui instanceof GuiEditSign));
            //#endif
        }

        setInputFocus(canInput);
    }

    @SubscribeEvent
    public final void onTextFieldFocusChange(TextFieldFocusChangeEvent event)
    {
        setInputFocus(event.isFocused());
    }

    protected abstract void setInputFocus(boolean focused);
}
