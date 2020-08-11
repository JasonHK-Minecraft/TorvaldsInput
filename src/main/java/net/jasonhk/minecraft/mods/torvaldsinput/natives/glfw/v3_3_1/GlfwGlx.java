//#if MINECRAFT>=11300
package net.jasonhk.minecraft.mods.torvaldsinput.natives.glfw.v3_3_1;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import static com.sun.jna.platform.unix.X11.XID;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

public interface GlfwGlx extends Glfw
{
    @SuppressWarnings("unused")
    @FieldDefaults(level = AccessLevel.PUBLIC)
    class GlfwContextGlx extends Structure
    {
        Pointer handle;
        XID     window;

        @Override
        protected List<String> getFieldOrder()
        {
            return Arrays.asList("handle", "window");
        }
    }
}
//#endif
