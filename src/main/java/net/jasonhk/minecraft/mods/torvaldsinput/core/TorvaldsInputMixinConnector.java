//#if MINECRAFT>=11400
package net.jasonhk.minecraft.mods.torvaldsinput.core;

import org.spongepowered.asm.mixin.connect.IMixinConnector;

import net.jasonhk.minecraft.mods.torvaldsinput.core.utilities.MixinConfigurator;

public class TorvaldsInputMixinConnector implements IMixinConnector
{
    @Override
    public void connect()
    {
        MixinConfigurator.configure();
    }
}
//#endif
