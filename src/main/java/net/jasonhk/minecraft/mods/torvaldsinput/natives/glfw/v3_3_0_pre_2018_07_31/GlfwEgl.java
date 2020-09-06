//#if MINECRAFT>=11300
package net.jasonhk.minecraft.mods.torvaldsinput.natives.glfw.v3_3_0_pre_2018_07_31;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

public interface GlfwEgl
{
    @SuppressWarnings("unused")
    @FieldDefaults(level = AccessLevel.PUBLIC)
    class GlfwContextEgl extends Structure
    {
        Pointer config;
        Pointer handle;
        Pointer surface;

        Pointer client;

        @Override
        protected List<String> getFieldOrder()
        {
            return Arrays.asList("config",
                                 "handle",
                                 "surface",
                                 "client");
        }
    }
}
//#endif
