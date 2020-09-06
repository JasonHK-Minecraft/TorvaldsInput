//#if MINECRAFT>=11300
package net.jasonhk.minecraft.mods.torvaldsinput.natives.glfw.v3_3_0_pre_2017_11_20;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.val;

public interface Glfw extends net.jasonhk.minecraft.mods.torvaldsinput.natives.glfw.Glfw
{
    @SuppressWarnings("unused")
    @FieldDefaults(level = AccessLevel.PUBLIC)
    abstract class AbstractGlfwWindow
            extends net.jasonhk.minecraft.mods.torvaldsinput.natives.glfw.Glfw.AbstractGlfwWindow
    {
        // _GLFWwindow *next;

        boolean       resizable;
        boolean       decorated;
        boolean       autoIconify;
        boolean       floating;
        boolean       shouldClose;
        Pointer       userPointer;
        GlfwVideoMode videoMode;
        Pointer       monitor;
        Pointer       cursor;

        int minwidth, minheight;
        int maxwidth, maxheight;
        int numer, denom;

        boolean stickyKeys;
        boolean stickyMouseButtons;
        boolean lockKeyMods;
        int     cursorMode;
        byte[]  mouseButtons = new byte[GLFW_MOUSE_BUTTON_LAST + 1];
        byte[]  keys         = new byte[GLFW_KEY_LAST + 1];
        double  virtualCursorPosX, virtualCursorPosY;

        // _GLFWcontext context;

        Callbacks callbacks;

        // _GLFW_PLATFORM_WINDOW_STATE;

        @Override
        protected List<String> getFieldOrder(ModuleProvider provider)
        {
            return Arrays.asList("next",
                                 "resizable",
                                 "decorated",
                                 "autoIconify",
                                 "floating",
                                 "shouldClose",
                                 "userPointer",
                                 "videoMode",
                                 "monitor",
                                 "cursor",
                                 "minwidth",
                                 "minheight",
                                 "maxwidth",
                                 "maxheight",
                                 "numer",
                                 "denom",
                                 "stickyKeys",
                                 "stickyMouseButtons",
                                 "lockKeyMods",
                                 "cursorMode",
                                 "mouseButtons",
                                 "keys",
                                 "virtualCursorPosX",
                                 "virtualCursorPosY",
                                 "context",
                                 "callbacks",
                                 provider.getField());
        }
    }

    @SuppressWarnings("unused")
    @FieldDefaults(level = AccessLevel.PUBLIC)
    abstract class AbstractGlfwContext
            extends net.jasonhk.minecraft.mods.torvaldsinput.natives.glfw.Glfw.AbstractGlfwContext
    {
        int client;
        int source;
        int major, minor, revision;
        boolean forward, debug, noerror;
        int profile;
        int robustness;
        int release;

        Pointer GetStringi;
        Pointer GetIntegerv;
        Pointer GetString;

        Pointer makeCurrent;
        Pointer swapBuffers;
        Pointer swapInterval;
        Pointer extensionSupported;
        Pointer getProcAddress;
        Pointer destroy;

        // _GLFW_PLATFORM_CONTEXT_STATE;
        // _GLFW_EGL_CONTEXT_STATE;
        // _GLFW_OSMESA_CONTEXT_STATE;

        @Override
        protected List<String> getFieldOrder(ModuleProvider provider)
        {
            return getFieldOrder(provider, true);
        }

        protected List<String> getFieldOrder(ModuleProvider provider, boolean supportsEgl)
        {
            val order = Arrays.asList("client",
                                      "source",
                                      "major",
                                      "minor",
                                      "revision",
                                      "forward",
                                      "debug",
                                      "noerror",
                                      "profile",
                                      "robustness",
                                      "release",
                                      "GetStringi",
                                      "GetIntegerv",
                                      "GetString",
                                      "makeCurrent",
                                      "swapBuffers",
                                      "swapInterval",
                                      "extensionSupported",
                                      "getProcAddress",
                                      "destroy",
                                      provider.getField(),
                                      ModuleProvider.EGL.getField(),
                                      ModuleProvider.OSMESA.getField());

            if (!supportsEgl)
            {
                order.remove(ModuleProvider.EGL.getField());
            }

            return order;
        }
    }
}
//#endif
