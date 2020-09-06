//#if MINECRAFT>=11300
package net.jasonhk.minecraft.mods.torvaldsinput.natives.glfw.v3_3_0_pre_2018_07_31;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

public interface GlfwOsMesa
{
    @SuppressWarnings("unused")
    @FieldDefaults(level = AccessLevel.PUBLIC)
    class GlfwContextOsMesa extends Structure
    {
        Pointer handle;
        int     width;
        int     height;
        Pointer buffer;

        @Override
        protected List<String> getFieldOrder()
        {
            return Arrays.asList("handle",
                                 "width",
                                 "height",
                                 "buffer");
        }
    }
}
//#endif
