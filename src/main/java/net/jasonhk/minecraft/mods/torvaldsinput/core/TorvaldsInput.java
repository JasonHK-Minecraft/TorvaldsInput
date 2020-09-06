package net.jasonhk.minecraft.mods.torvaldsinput.core;

import net.minecraftforge.fml.common.Mod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The main entry point of this mod.
 */
//#if MINECRAFT>=11300
@Mod(ModInfo.ID)
//#else
//$$ @Mod(modid = ModInfo.ID,
//$$      useMetadata = true,
//$$      name = ModInfo.NAME,
//$$      clientSideOnly = true)
//#endif
public final class TorvaldsInput
{
    /**
     * The logger of this mod.
     */
    public static final Logger LOGGER = LogManager.getLogger(ModInfo.ID);

    /**
     * The singleton instance of this mod.
     */
    //#if MINECRAFT<11300
    //$$ @Mod.Instance(ModInfo.ID)
    //#endif
    public static TorvaldsInput instance;

    //#if MINECRAFT>=11300
    {
        instance = this;
    }
    //#endif
}
