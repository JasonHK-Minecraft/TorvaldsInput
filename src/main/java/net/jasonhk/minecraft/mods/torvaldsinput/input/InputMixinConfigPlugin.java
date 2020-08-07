package net.jasonhk.minecraft.mods.torvaldsinput.input;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

//#if MINECRAFT<11300
//$$ import net.minecraft.launchwrapper.Launch;
//$$ import net.minecraft.launchwrapper.LaunchClassLoader;
//#endif

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
        //#if MINECRAFT>=11300
        return new ArrayList<>();
        //#else
        //$$ return Arrays.asList("MixinLinuxEvent",
        //$$                      "MixinLinuxKeyboard");
        //#endif
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}

    @SuppressWarnings("unchecked")
    @SneakyThrows({ IllegalAccessException.class, NoSuchFieldException.class })
    @Override
    public void onLoad(String mixinPackage)
    {
        //#if MINECRAFT<11300
        //$$ val field_classLoaderExceptions =
        //$$         LaunchClassLoader.class.getDeclaredField("classLoaderExceptions");
        //$$ field_classLoaderExceptions.setAccessible(true);
        //$$
        //$$ val classLoaderExceptions =
        //$$         (Set<String>) field_classLoaderExceptions.get(Launch.classLoader);
        //$$ classLoaderExceptions.remove("org.lwjgl.");
        //#endif
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
