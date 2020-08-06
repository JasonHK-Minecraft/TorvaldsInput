package dev.jasonhk.gradle.minecraft.mod.variant.extensions

import dev.jasonhk.gradle.minecraft.mod.variant.ModLoader

fun String.toModLoader(): ModLoader
{
    return ModLoader.valueOf(this)
}
