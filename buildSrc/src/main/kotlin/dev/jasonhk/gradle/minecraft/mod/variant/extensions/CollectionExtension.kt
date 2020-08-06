package dev.jasonhk.gradle.minecraft.mod.variant.extensions

fun <E> Collection<E>.isSizeInRange(lower: Int, upper: Int, inclusive: Boolean = true): Boolean
{
    return this.isSizeInRange(lower, upper, inclusive, inclusive)
}

fun <E> Collection<E>.isSizeInRange(
        lower: Int,
        upper: Int,
        lowerInclusive: Boolean = true,
        upperInclusive: Boolean = true): Boolean
{
    val size = this.size
    return if (lowerInclusive && upperInclusive)
    {
        ((size >= lower) && (size <= upper))
    }
    else if (lowerInclusive && !upperInclusive)
    {
        ((size >= lower) && (size < upper))
    }
    else if (!lowerInclusive && upperInclusive)
    {
        ((size > lower) && (size <= upper))
    }
    else
    {
        ((size > lower) && (size < upper))
    }
}
