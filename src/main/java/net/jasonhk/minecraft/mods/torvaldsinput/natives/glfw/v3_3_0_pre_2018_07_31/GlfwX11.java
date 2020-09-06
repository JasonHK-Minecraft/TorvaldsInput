//#if MINECRAFT>=11300
package net.jasonhk.minecraft.mods.torvaldsinput.natives.glfw.v3_3_0_pre_2018_07_31;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import static com.sun.jna.platform.unix.X11.Colormap;
import static com.sun.jna.platform.unix.X11.Window;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import static net.jasonhk.minecraft.mods.torvaldsinput.natives.glfw.GlfwX11.IGlfwWindow;
import static net.jasonhk.minecraft.mods.torvaldsinput.natives.unix.X11.Time;
import static net.jasonhk.minecraft.mods.torvaldsinput.natives.unix.X11.XIC;

public interface GlfwX11 extends Glfw, GlfwEgl, GlfwGlx, GlfwOsMesa
{
    @SuppressWarnings("unused")
    @FieldDefaults(level = AccessLevel.PUBLIC)
    class GlfwWindow extends AbstractGlfwWindow implements IGlfwWindow
    {
        GlfwWindow    next;
        GlfwContext   context;
        GlfwWindowX11 x11;

        public static GlfwWindow of(Pointer pointer)
        {
            return (GlfwWindow) newInstance(GlfwWindow.class, pointer);
        }

        @Override
        public XIC getX11InputContext()
        {
            return x11.ic;
        }

        @Override
        protected List<String> getFieldOrder()
        {
            return getFieldOrder(ModuleProvider.X11);
        }
    }

    @SuppressWarnings("unused")
    @FieldDefaults(level = AccessLevel.PUBLIC)
    class GlfwWindowX11 extends Structure implements Structure.ByValue
    {
        Colormap colormap;
        Window   handle;
        XIC      ic;

        boolean overrideRedirect;
        boolean iconified;
        boolean maximized;

        boolean transparent;

        int width, height;
        int xpos, ypos;

        int lastCursorPosX, lastCursorPosY;
        int warpCursorPosX, warpCursorPosY;

        Time lastKeyTime;

        @Override
        protected List<String> getFieldOrder()
        {
            return Arrays.asList("colormap",
                                 "handle",
                                 "ic",
                                 "overrideRedirect",
                                 "iconified",
                                 "maximized",
                                 "transparent",
                                 "width",
                                 "height",
                                 "xpos",
                                 "ypos",
                                 "lastCursorPosX",
                                 "lastCursorPosY",
                                 "warpCursorPosX",
                                 "warpCursorPosY",
                                 "lastKeyTime");
        }
    }

    @SuppressWarnings("unused")
    @FieldDefaults(level = AccessLevel.PUBLIC)
    class GlfwContext extends AbstractGlfwContext
    {
        GlfwContextGlx glx;
        GlfwContextEgl egl;
        GlfwContextOsMesa osmesa;

        @Override
        protected List<String> getFieldOrder()
        {
            return getFieldOrder(ModuleProvider.GLX);
        }
    }
}
//#endif
