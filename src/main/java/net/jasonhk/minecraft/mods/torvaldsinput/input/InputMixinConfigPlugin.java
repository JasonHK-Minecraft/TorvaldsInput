package net.jasonhk.minecraft.mods.torvaldsinput.input;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import net.minecraft.launchwrapper.Launch;
import net.minecraft.launchwrapper.LaunchClassLoader;

import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import lombok.SneakyThrows;
import lombok.val;

//#if MINECRAFT>=11200
import org.objectweb.asm.tree.ClassNode;
//#else
//$$ import org.spongepowered.asm.lib.tree.ClassNode;
//#endif

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
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}

    @SuppressWarnings("unchecked")
    @SneakyThrows({ IllegalAccessException.class, NoSuchFieldException.class })
    @Override
    public void onLoad(String mixinPackage)
    {
        val field_classLoaderExceptions =
                LaunchClassLoader.class.getDeclaredField("classLoaderExceptions");
        field_classLoaderExceptions.setAccessible(true);

        val classLoaderExceptions =
                (Set<String>) field_classLoaderExceptions.get(Launch.classLoader);
        classLoaderExceptions.remove("org.lwjgl.");
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName)
    {
        return true;
    }

    @Override
    public void preApply(
            String targetClassName,
            ClassNode targetClass,
            String mixinClassName,
            IMixinInfo mixinInfo)
    {}

    @Override
    public void postApply(
            String targetClassName,
            ClassNode targetClass,
            String mixinClassName,
            IMixinInfo mixinInfo)
    {}
}
