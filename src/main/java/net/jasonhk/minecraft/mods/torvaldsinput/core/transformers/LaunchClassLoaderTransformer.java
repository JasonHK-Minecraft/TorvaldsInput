package net.jasonhk.minecraft.mods.torvaldsinput.core.transformers;

import java.util.Set;

import net.minecraft.launchwrapper.LaunchClassLoader;

import lombok.val;

public final class LaunchClassLoaderTransformer
{
    public static void transform(final LaunchClassLoader classLoader)
    {
        try
        {
            val field_classLoaderExceptions = LaunchClassLoader.class.getDeclaredField(
                    "classLoaderExceptions");
            field_classLoaderExceptions.setAccessible(true);

            @SuppressWarnings("unchecked")
            val classLoaderExceptions = (Set<String>) field_classLoaderExceptions.get(classLoader);
            classLoaderExceptions.remove("org.lwjgl.");
        }
        catch (IllegalAccessException | NoSuchFieldException e)
        {
            e.printStackTrace();
        }
    }
}
