
package net.jasonhk.minecraft.mods.torvaldsinput.input;

import java.util.List;
import java.util.Set;
//#if MINECRAFT<11300
//$$ import java.util.ArrayList;
//$$ import java.util.Arrays;
//#endif

//#if MINECRAFT<11300
//$$ import net.minecraft.launchwrapper.Launch;
//$$ import net.minecraft.launchwrapper.LaunchClassLoader;
//#endif

import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

//#if MINECRAFT<11300
//$$ import lombok.SneakyThrows;
//$$ import lombok.val;
//#endif

//#if MINECRAFT>=11200
import org.objectweb.asm.tree.ClassNode;
//#else
//$$ import org.spongepowered.asm.lib.tree.ClassNode;
//#endif

public final class InputMixinConfigPlugin implements IMixinConfigPlugin
{
    //#if MINECRAFT<11300
    //$$ private static final List<String> USED_CLASS_NAMES =
    //$$         Arrays.asList("org.lwjgl.opengl.LinuxEvent",
    //$$                       "org.lwjgl.opengl.LinuxKeyboard");
    //#endif

    @Override
    public String getRefMapperConfig()
    {
        return null;
    }

    @Override
    public List<String> getMixins()
    {
        //#if MINECRAFT>=11300
        return null;
        //#else
        //$$ return Arrays.asList("LinuxEventMixin",
        //$$                      "LinuxKeyboardMixin");
        //#endif
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}

    //#if MINECRAFT<11300
    //$$ @SuppressWarnings("unchecked")
    //$$ @SneakyThrows({ IllegalAccessException.class, NoSuchFieldException.class })
    //#endif
    @Override
    public void onLoad(String mixinPackage)
    {
        //#if MINECRAFT<11300
        //$$ Set<String> classLoaderExceptions;
        //$$ {
        //$$     val field_classLoaderExceptions =
        //$$             LaunchClassLoader.class.getDeclaredField("classLoaderExceptions");
        //$$     field_classLoaderExceptions.setAccessible(true);
        //$$
        //$$     classLoaderExceptions =
        //$$             (Set<String>) field_classLoaderExceptions.get(Launch.classLoader);
        //$$ }
        //$$
        //$$ Set<String> transformerExceptions;
        //$$ {
        //$$     val field_transformerExceptions =
        //$$             LaunchClassLoader.class.getDeclaredField("transformerExceptions");
        //$$     field_transformerExceptions.setAccessible(true);
        //$$
        //$$     transformerExceptions =
        //$$             (Set<String>) field_transformerExceptions.get(Launch.classLoader);
        //$$ }
        //$$
        //$$ classLoaderExceptions.removeIf(this::shouldExceptionBeRemoved);
        //$$ transformerExceptions.removeIf(this::shouldExceptionBeRemoved);
        //#endif
    }

    //#if MINECRAFT<11300
    //$$ private boolean shouldExceptionBeRemoved(String exception)
    //$$ {
    //$$     return USED_CLASS_NAMES
    //$$             .stream()
    //$$             .anyMatch((className) ->
    //$$                       {
    //$$                           return className.startsWith(exception);
    //$$                       });
    //$$ }
    //#endif

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
