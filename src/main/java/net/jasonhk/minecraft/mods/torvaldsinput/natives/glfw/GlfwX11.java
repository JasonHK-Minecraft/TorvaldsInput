//#if MINECRAFT>=11300
package net.jasonhk.minecraft.mods.torvaldsinput.natives.glfw;

import static net.jasonhk.minecraft.mods.torvaldsinput.natives.unix.X11.XIC;

public interface GlfwX11 extends Glfw
{
    interface IGlfwWindow
    {
        XIC getX11InputContext();
    }
}
//#endif
