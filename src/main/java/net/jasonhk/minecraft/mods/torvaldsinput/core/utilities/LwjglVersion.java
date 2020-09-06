package net.jasonhk.minecraft.mods.torvaldsinput.core.utilities;

import static org.lwjgl.Version.VERSION_MAJOR;
import static org.lwjgl.Version.VERSION_MINOR;
import static org.lwjgl.Version.VERSION_REVISION;

import lombok.experimental.UtilityClass;

import com.github.zafarkhaja.semver.Version;

@UtilityClass
public class LwjglVersion
{
    public final int MAJOR = VERSION_MAJOR;
    public final int MINOR = VERSION_MINOR;
    public final int REVISION = VERSION_REVISION;

    public final Version VERSION = Version.forIntegers(MAJOR, MINOR, REVISION);
}
