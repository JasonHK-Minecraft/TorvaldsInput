package dev.jasonhk.gradle.minecraft.mod.variant

import kotlin.IllegalArgumentException

import dev.jasonhk.gradle.minecraft.mod.variant.extensions.isSizeInRange

/**
 * Represents the version code of a Minecraft stable release.
 */
@Suppress("unused", "MemberVisibilityCanBePrivate")
class MinecraftVersion
{
    val major: Int
    val minor: Int
    val patch: Int

    val string: String
        get() = "${major}.${minor}${(if (patch == 0) "" else ".${patch}")}"

    val integer: Int
        get() = ((major * 10000) + (minor * 100) + patch)

    constructor(version: String)
    {
        val fragments = version.split(".").map { it.toInt() }
        if (!fragments.isSizeInRange(2, 3))
        {
            throw IllegalArgumentException()
        }

        major = fragments[0]
        minor = fragments[1]
        patch = fragments.getOrNull(2) ?: 0
    }

    constructor(version: Int)
    {
        major = (version / 10000)
        minor = ((version % 10000) / 100)
        patch = (version % 100)
    }

    constructor(major: Int, minor: Int, patch: Int = 0)
    {
        this.major = major
        this.minor = minor
        this.patch = patch
    }
}
