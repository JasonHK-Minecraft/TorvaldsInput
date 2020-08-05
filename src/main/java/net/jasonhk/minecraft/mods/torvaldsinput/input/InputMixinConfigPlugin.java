package net.jasonhk.minecraft.mods.torvaldsinput.input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import net.minecraft.launchwrapper.Launch;
import net.minecraft.launchwrapper.LaunchClassLoader;

import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import org.apache.logging.log4j.LogManager;

import lombok.SneakyThrows;
import lombok.val;

import org.objectweb.asm.tree.ClassNode;

public final class InputMixinConfigPlugin implements IMixinConfigPlugin
{
    @Override
    public String getRefMapperConfig()
    {
        return null;
    }

    @Override
    public List<String> getMixins()
    {
        return Arrays.asList("MixinLinuxEvent",
                             "MixinLinuxKeyboard");
    }

    @Override
    public void acceptTargets(final Set<String> myTargets, final Set<String> otherTargets) {}

    @SuppressWarnings("unchecked")
    @SneakyThrows({ IllegalAccessException.class, NoSuchFieldException.class })
    @Override
    public void onLoad(final String mixinPackage)
    {
        val field_classLoaderExceptions =
                LaunchClassLoader.class.getDeclaredField("classLoaderExceptions");
        field_classLoaderExceptions.setAccessible(true);

        val classLoaderExceptions =
                (Set<String>) field_classLoaderExceptions.get(Launch.classLoader);
        classLoaderExceptions.remove("org.lwjgl.");
    }

    @Override
    public boolean shouldApplyMixin(final String targetClassName, final String mixinClassName)
    {
        return true;
    }

    @Override
    public void preApply(
            final String targetClassName,
            final ClassNode targetClass,
            final String mixinClassName,
            final IMixinInfo mixinInfo)
    {}

    @Override
    public void postApply(
            final String targetClassName,
            final ClassNode targetClass,
            final String mixinClassName,
            final IMixinInfo mixinInfo)
    {}
}
