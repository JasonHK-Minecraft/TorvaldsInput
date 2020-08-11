//#if MINECRAFT>=11400
package net.jasonhk.minecraft.mods.torvaldsinput.input;

import static net.jasonhk.minecraft.mods.torvaldsinput.natives.glfw.Glfw.AbstractGlfwWindow;

public interface IMainWindowMixin
{
    AbstractGlfwWindow getGlfwWindow();
}
//#endif
