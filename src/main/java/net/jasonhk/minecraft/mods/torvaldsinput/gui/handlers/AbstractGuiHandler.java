package net.jasonhk.minecraft.mods.torvaldsinput.gui.handlers;

import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiScreenBook;
import net.minecraft.client.gui.inventory.GuiEditSign;

import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import lombok.val;

import net.jasonhk.minecraft.mods.torvaldsinput.gui.events.GuiTextFieldFocusChangeEvent;

public abstract class AbstractGuiHandler
{
    @SubscribeEvent
    public void onGuiOpen(GuiOpenEvent event)
    {
        boolean canInput;

        val gui = event.getGui();
        if (gui == null)
        {
            canInput = false;
        }
        else if (gui instanceof GuiChat)
        {
            return;
        }
        else
        {
            //noinspection DuplicateCondition
            canInput = ((gui instanceof GuiScreenBook) || (gui instanceof GuiEditSign));
        }

        setInputFocus(canInput);
    }

    @SubscribeEvent
    public void onGuiTextFieldFocusChange(GuiTextFieldFocusChangeEvent event)
    {
        setInputFocus(event.isFocused());
    }

    protected abstract void setInputFocus(boolean focused);
}
