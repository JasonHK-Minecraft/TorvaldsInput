package net.jasonhk.minecraft.mods.torvaldsinput.input.natives.common;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@SuppressWarnings("unused")
public interface GLFW
{
    int GLFW_KEY_MENU = 348;

    int GLFW_KEY_LAST = GLFW_KEY_MENU;

    int GLFW_MOUSE_BUTTON_1      = 0;
    int GLFW_MOUSE_BUTTON_2      = 1;
    int GLFW_MOUSE_BUTTON_3      = 2;
    int GLFW_MOUSE_BUTTON_4      = 3;
    int GLFW_MOUSE_BUTTON_5      = 4;
    int GLFW_MOUSE_BUTTON_6      = 5;
    int GLFW_MOUSE_BUTTON_7      = 6;
    int GLFW_MOUSE_BUTTON_8      = 7;
    int GLFW_MOUSE_BUTTON_LAST   = GLFW_MOUSE_BUTTON_8;
    int GLFW_MOUSE_BUTTON_LEFT   = GLFW_MOUSE_BUTTON_1;
    int GLFW_MOUSE_BUTTON_RIGHT  = GLFW_MOUSE_BUTTON_2;
    int GLFW_MOUSE_BUTTON_MIDDLE = GLFW_MOUSE_BUTTON_3;

    @FieldDefaults(level = AccessLevel.PUBLIC)
    abstract class AbstractGLFWcontext extends Structure
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

        GLFWcontextEGL    egl;
        GLFWcontextOSMesa osmesa;

        protected List<String> getFieldOrder(final String platform)
        {
            return Arrays.asList("client",
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
                                 platform,
                                 "egl",
                                 "osmesa");
        }
    }

    @FieldDefaults(level = AccessLevel.PUBLIC)
    class GLFWcontextEGL extends Structure implements Structure.ByValue
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

    @FieldDefaults(level = AccessLevel.PUBLIC)
    class GLFWcontextOSMesa extends Structure implements Structure.ByValue
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

    @FieldDefaults(level = AccessLevel.PUBLIC)
    abstract class AbstractGLFWwindow<GLFWcontext extends AbstractGLFWcontext & Structure.ByValue, GLFWwindow extends AbstractGLFWwindow<GLFWcontext, ?>>
            extends Structure
    {
        GLFWwindow next;

        boolean             resizable;
        boolean             decorated;
        boolean             autoIconify;
        boolean             floating;
        boolean             focusOnShow;
        boolean             mousePassthrough;
        boolean             shouldClose;
        Pointer             userPointer;
        GLFWvidmode.ByValue videoMode;
        Pointer             monitor;
        Pointer             cursor;

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
        boolean rawMouseMotion;

        GLFWcontext context;

        Callbacks callbacks;

        protected List<String> getFieldOrder(final String platform)
        {
            return Arrays.asList("next",
                                 "resizable",
                                 "decorated",
                                 "autoIconify",
                                 "floating",
                                 "focusOnShow",
                                 "mousePassthrough",
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
                                 "rawMouseMotion",
                                 "context",
                                 "callbacks",
                                 platform);
        }

        @FieldDefaults(level = AccessLevel.PUBLIC)
        public static class Callbacks extends Structure implements Structure.ByValue
        {
            Pointer pos;
            Pointer size;
            Pointer close;
            Pointer refresh;
            Pointer focus;
            Pointer iconify;
            Pointer maximize;
            Pointer fbsize;
            Pointer scale;
            Pointer mouseButton;
            Pointer cursorPos;
            Pointer cursorEnter;
            Pointer scroll;
            Pointer key;
            Pointer character;
            Pointer charmods;
            Pointer drop;

            @Override
            protected List<String> getFieldOrder()
            {
                return Arrays.asList("pos",
                                     "size",
                                     "close",
                                     "refresh",
                                     "focus",
                                     "iconify",
                                     "maximize",
                                     "fbsize",
                                     "scale",
                                     "mouseButton",
                                     "cursorPos",
                                     "cursorEnter",
                                     "scroll",
                                     "key",
                                     "character",
                                     "charmods",
                                     "drop");
            }
        }
    }

    @FieldDefaults(level = AccessLevel.PUBLIC)
    class GLFWvidmode extends Structure
    {
        int width;
        int height;
        int redBits;
        int greenBits;
        int blueBits;
        int refreshRate;

        @Override
        protected List<String> getFieldOrder()
        {
            return Arrays.asList("width",
                                 "height",
                                 "redBits",
                                 "greenBits",
                                 "blueBits",
                                 "refreshRate");
        }

        public static class ByValue extends GLFWvidmode implements Structure.ByValue {}
    }
}
