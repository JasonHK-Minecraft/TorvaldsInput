package net.jasonhk.minecraft.mods.torvaldsinput.core;

import net.minecraftforge.fml.common.Mod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = ModInfo.ID,
     useMetadata = true,
     name = ModInfo.NAME,
     clientSideOnly = true)
public final class TorvaldsInput
{
    /**
     * The logger of this mod.
     */
    public static final Logger LOGGER = LogManager.getLogger(ModInfo.ID);

    @Mod.Instance(ModInfo.ID)
    public static TorvaldsInput instance;
}
