package net.jasonhk.minecraft.mods.torvaldsinput.gui.handlers;

import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiScreenBook;
import net.minecraft.client.gui.inventory.GuiEditSign;

import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import lombok.Data;
import lombok.val;

import net.jasonhk.minecraft.mods.torvaldsinput.gui.events.GuiTextFieldFocusChangeEvent;
import net.jasonhk.minecraft.mods.torvaldsinput.opengl.LinuxKeyboard;

@Data
public class GuiHandler
{
    private final long xic;

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

        setICFocus(canInput);
    }

    @SubscribeEvent
    public void onGuiTextFieldFocusChange(GuiTextFieldFocusChangeEvent event)
    {
        setICFocus(event.isFocused());
    }

    private void setICFocus(boolean focus)
    {
        LinuxKeyboard.toggleICFocus(xic, focus);
    }
}
