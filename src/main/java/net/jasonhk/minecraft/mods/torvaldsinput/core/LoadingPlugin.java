package net.jasonhk.minecraft.mods.torvaldsinput.core;

import java.util.Map;

import javax.annotation.Nullable;

import net.minecraft.launchwrapper.Launch;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

import net.jasonhk.minecraft.mods.torvaldsinput.core.transformers.LaunchClassLoaderTransformer;

public final class LoadingPlugin implements IFMLLoadingPlugin
{
    public LoadingPlugin()
    {
        LaunchClassLoaderTransformer.transform(Launch.classLoader);

        if (Launch.blackboard.get("fml.deobfuscatedEnvironment") != Boolean.FALSE)
        {
            MixinBootstrap.init();
        }

        Mixins.addConfiguration("torvaldsinput.mixins.input.json");
        Mixins.addConfiguration("torvaldsinput.mixins.gui.json");
    }

    @Override
    public void injectData(final Map<String, Object> map) {}

    @Nullable
    @Override
    public String getSetupClass()
    {
        return null;
    }

    @Override
    public String getModContainerClass()
    {
        return null;
    }

    @Override
    public String getAccessTransformerClass()
    {
        return null;
    }

    @Override
    public String[] getASMTransformerClass()
    {
        return new String[0];
    }
}
