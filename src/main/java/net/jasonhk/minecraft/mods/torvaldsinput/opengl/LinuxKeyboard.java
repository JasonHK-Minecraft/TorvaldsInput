package net.jasonhk.minecraft.mods.torvaldsinput.opengl;

import lombok.val;

import com.sun.jna.Pointer;

import net.jasonhk.minecraft.mods.torvaldsinput.opengl.natives.unix.X11;
import static net.jasonhk.minecraft.mods.torvaldsinput.opengl.natives.unix.X11.Display;
import static net.jasonhk.minecraft.mods.torvaldsinput.opengl.natives.unix.X11.XIC;

@SuppressWarnings("JavadocReference")
public final class LinuxKeyboard
{
    private static final X11 _X11 = X11.INSTANCE;

    /**
     * Opens an input method, matching the current locale and modifiers specification.
     *
     * @param displayPointer The pointer to the X display connection.
     * @return The pointer to the input method.
     *
     * @see org.lwjgl.opengl.LinuxKeyboard#openIM(long)
     * @see <a href="https://www.x.org/releases/X11R7.7/doc/man/man3/XOpenIM.3.xhtml"><code>XOpenIM(Display*,
     * XrmDatabase, char*, char*)</code></a>
     */
    public static long openIM(final long displayPointer)
    {
        val display = new Display();
        display.setPointer(new Pointer(displayPointer));

        val xim = _X11.XOpenIM(display, null, null, null);
        return Pointer.nativeValue(xim.getPointer());
    }

    /**
     * Updates the keyboard focus state the given input context.
     *
     * @param xicPointer The pointer to the input context.
     * @param focused    Whether the input context should receive keyboard focus.
     *
     * @see <a href="https://www.x.org/releases/X11R7.5/doc/man/man3/XSetICFocus.3.html"><code>XSetICFocus(XIC)</code></a>
     * @see <a href="https://www.x.org/releases/X11R7.5/doc/man/man3/XSetICFocus.3.html"><code>XUnsetICFocus(XIC)</code></a>
     */
    public static void toggleICFocus(final long xicPointer, final boolean focused)
    {
        val xic = new XIC(Pointer.createConstant(xicPointer));

        if (focused)
        {
            _X11.XSetICFocus(xic);
        }
        else
        {
            _X11.XUnsetICFocus(xic);
        }
    }
}
