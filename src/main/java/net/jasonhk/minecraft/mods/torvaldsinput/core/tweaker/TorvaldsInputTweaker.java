package net.jasonhk.minecraft.mods.torvaldsinput.core.tweaker;

import java.io.File;
import java.util.List;

import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;

import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

import net.jasonhk.minecraft.mods.torvaldsinput.core.transformers.LaunchClassLoaderTransformer;

@SuppressWarnings("unused")
public class TorvaldsInputTweaker implements ITweaker
{
    @Override
    public void acceptOptions(
            final List<String> args,
            final File gameDir,
            final File assetsDir,
            final String profile) {}

    @Override
    public void injectIntoClassLoader(final LaunchClassLoader classLoader)
    {
        LaunchClassLoaderTransformer.transform(classLoader);

        MixinBootstrap.init();
        Mixins.addConfiguration("torvaldsinput.mixins.opengl.json");
        Mixins.addConfiguration("torvaldsinput.mixins.gui.json");
    }

    @Override
    public String getLaunchTarget()
    {
        return null;
    }

    @Override
    public String[] getLaunchArguments()
    {
        return new String[0];
    }
}
