package dev.jasonhk.gradle.minecraft.mod.variant

import kotlin.IllegalArgumentException

import dev.jasonhk.gradle.minecraft.mod.variant.extensions.isSizeInRange
import dev.jasonhk.gradle.minecraft.mod.variant.extensions.toModLoader

@Suppress("unused", "MemberVisibilityCanBePrivate")
class ModVariant
{
    val version: MinecraftVersion
    val loader: ModLoader

    val isLoaderForge: Boolean
        get() = (loader == ModLoader.FORGE)

    val isLoaderFabric: Boolean
        get() = (loader == ModLoader.FABRIC)

    constructor(notations: String)
    {
        val fragments = notations.split("-")
        if (!fragments.isSizeInRange(1, 2))
        {
            throw IllegalArgumentException()
        }

        version = MinecraftVersion(fragments[0])
        loader = getModLoader(fragments.getOrNull(1)?.toUpperCase()?.toModLoader(), version)
    }

    constructor(version: MinecraftVersion, loader: ModLoader?)
    {
        this.version = version
        this.loader = getModLoader(loader, version)
    }

    private companion object
    {
        fun getModLoader(loader: ModLoader?, version: MinecraftVersion): ModLoader
        {
            return if (version.integer >= 11400)
            {
                if (loader == null)
                {
                    throw IllegalArgumentException("Mod loader is required by Minecraft 1.14 or newer")
                }

                loader
            }
            else
            {
                if ((loader != ModLoader.FORGE) && (loader != null))
                {
                    throw IllegalArgumentException("Only Forge is supported by Minecraft 1.14 older.")
                }

                loader ?: ModLoader.FORGE
            }
        }
    }
}
