package net.jasonhk.minecraft.mods.torvaldsinput.input;

import com.sun.jna.Pointer;

import lombok.val;

import static com.sun.jna.platform.unix.X11.Display;
import static net.jasonhk.minecraft.mods.torvaldsinput.natives.unix.X11.XIC;

@SuppressWarnings("JavadocReference")
public final class LinuxInput
{
    private static final net.jasonhk.minecraft.mods.torvaldsinput.natives.unix.X11 X11
            = net.jasonhk.minecraft.mods.torvaldsinput.natives.unix.X11.INSTANCE;

    /**
     * Opens an X input method, matching the current locale and modifiers specification.
     *
     * @param displayPointer The pointer to an X display connection.
     * @return The pointer to an X input method.
     *
     * @see org.lwjgl.opengl.LinuxKeyboard#openIM(long)
     * @see <a href="https://www.x.org/releases/X11R7.7/doc/man/man3/XOpenIM.3.xhtml">
     *         <code>XOpenIM(Display*, XrmDatabase, char*, char*)</code></a>
     */
    public static long openIM(long displayPointer)
    {
        val display = new Display();
        display.setPointer(new Pointer(displayPointer));

        val xim = X11.XOpenIM(display, null, null, null);
        return Pointer.nativeValue(xim.getPointer());
    }

    /**
     * Updates the keyboard focus state of an X input context.
     *
     * @param ic      The pointer to an X input context.
     * @param focused Whether the X input context should receive keyboard focus.
     *
     * @see <a href="https://www.x.org/releases/X11R7.5/doc/man/man3/XSetICFocus.3.html">
     *         <code>XSetICFocus(XIC)</code></a>
     * @see <a href="https://www.x.org/releases/X11R7.5/doc/man/man3/XSetICFocus.3.html">
     *         <code>XUnsetICFocus(XIC)</code></a>
     */
    public static void toggleIcFocus(XIC ic, boolean focused)
    {
        if (focused)
        {
            X11.XSetICFocus(ic);
        }
        else
        {
            X11.XUnsetICFocus(ic);
        }
    }
}
