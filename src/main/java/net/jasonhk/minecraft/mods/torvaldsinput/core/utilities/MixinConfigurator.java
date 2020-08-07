package net.jasonhk.minecraft.mods.torvaldsinput.core.utilities;

import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

//import org.apache.logging.log4j.Logger;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MixinConfigurator
{
//    private final Logger LOGGER = LoggerUtility.getLogger();

    public void initialize()
    {
        MixinBootstrap.init();
    }

    public void configure()
    {
//        LOGGER.debug("Adding mixin configurations.");

        Mixins.addConfiguration("torvaldsinput.mixins.input.json");
        Mixins.addConfiguration("torvaldsinput.mixins.gui.json");
    }
}
