//#if MINECRAFT>=11300
package net.jasonhk.minecraft.mods.torvaldsinput.natives.glfw;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@SuppressWarnings({ "unused", "SameParameterValue" })
public interface Glfw
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

    int GLFW_JOYSTICK_1    = 0;
    int GLFW_JOYSTICK_2    = 1;
    int GLFW_JOYSTICK_3    = 2;
    int GLFW_JOYSTICK_4    = 3;
    int GLFW_JOYSTICK_5    = 4;
    int GLFW_JOYSTICK_6    = 5;
    int GLFW_JOYSTICK_7    = 6;
    int GLFW_JOYSTICK_8    = 7;
    int GLFW_JOYSTICK_9    = 8;
    int GLFW_JOYSTICK_10   = 9;
    int GLFW_JOYSTICK_11   = 10;
    int GLFW_JOYSTICK_12   = 11;
    int GLFW_JOYSTICK_13   = 12;
    int GLFW_JOYSTICK_14   = 13;
    int GLFW_JOYSTICK_15   = 14;
    int GLFW_JOYSTICK_16   = 15;
    int GLFW_JOYSTICK_LAST = GLFW_JOYSTICK_16;

    enum ModuleProvider
    {
        NULL("null"),
        COCOA("ns"),
        EGL("egl"),
        GLX("glx"),
        LINUX_JOYSTICK("linjs"),
        NSGL("nsgl"),
        OSMESA("osmesa"),
        POSIX("posix"),
        WAYLAND("wl"),
        WIN32("win32"),
        X11("x11");

        @Getter
        private final String field;

        ModuleProvider(String field)
        {
            this.field = field;
        }
    }

    //<editor-fold desc="GlfwWindow">
    abstract class AbstractGlfwWindow extends Structure implements Structure.ByReference
    {
        protected abstract List<String> getFieldOrder(ModuleProvider provider);

        @FieldDefaults(level = AccessLevel.PUBLIC)
        public static class Callbacks extends Structure
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
    class GlfwVideoMode extends Structure
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
    }
    //</editor-fold>

    abstract class AbstractGlfwContext extends Structure
    {
        protected abstract List<String> getFieldOrder(ModuleProvider provider);
    }
}
//#endif
