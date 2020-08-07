package net.jasonhk.minecraft.mods.torvaldsinput.core.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lombok.experimental.UtilityClass;

import net.jasonhk.minecraft.mods.torvaldsinput.core.ModInfo;

@UtilityClass
public class LoggerUtility
{
    public Logger getLogger()
    {
        return LogManager.getLogger(ModInfo.ID);
    }
}
