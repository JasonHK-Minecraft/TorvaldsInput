package net.jasonhk.minecraft.mods.torvaldsinput.input;

//#if MINECRAFT<11300
//$$ import com.sun.jna.Pointer;
//$$ import static com.sun.jna.platform.unix.X11.Display;
//#endif

//#if MINECRAFT<11300
//$$ import lombok.val;
//#endif

import static net.jasonhk.minecraft.mods.torvaldsinput.natives.unix.X11.XIC;

//#if MINECRAFT<11300
//$$ @SuppressWarnings("JavadocReference")
//#endif
public final class X11Input
{
    private static final net.jasonhk.minecraft.mods.torvaldsinput.natives.unix.X11 X11
            = net.jasonhk.minecraft.mods.torvaldsinput.natives.unix.X11.INSTANCE;

    //#if MINECRAFT<11300
    //$$ /**
    //$$  * Opens an X input method, matching the current locale and modifiers specification.
    //$$  *
    //$$  * @param displayPointer The pointer to an X display connection.
    //$$  * @return The pointer to an X input method.
    //$$  *
    //$$  * @see org.lwjgl.opengl.LinuxKeyboard#openIM(long)
    //$$  * @see <a href="https://www.x.org/releases/X11R7.7/doc/man/man3/XOpenIM.3.xhtml">
    //$$  *         <code>XOpenIM(Display*, XrmDatabase, char*, char*)</code></a>
    //$$  */
    //$$ public static long openIm(long displayPointer)
    //$$ {
    //$$     val display = new Display();
    //$$     display.setPointer(new Pointer(displayPointer));
    //$$
    //$$     val xim = X11.XOpenIM(display, null, null, null);
    //$$     return Pointer.nativeValue(xim.getPointer());
    //$$ }
    //#endif

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
