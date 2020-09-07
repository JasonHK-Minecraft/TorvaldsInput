package net.jasonhk.minecraft.mods.torvaldsinput.input;

//#if MINECRAFT<11300
//$$ import static com.sun.jna.platform.unix.X11.Display;
//#endif

import lombok.experimental.UtilityClass;

import net.jasonhk.minecraft.mods.torvaldsinput.natives.unix.X11;
import static net.jasonhk.minecraft.mods.torvaldsinput.natives.unix.X11.XIC;
//#if MINECRAFT<11300
//$$ import net.jasonhk.minecraft.mods.torvaldsinput.natives.unix.X11.XIM;
//#endif

//#if MINECRAFT<11300
//$$ @SuppressWarnings("JavadocReference")
//#endif
@UtilityClass
public class X11Input
{
    private final X11 X11 = net.jasonhk.minecraft.mods.torvaldsinput.natives.unix.X11.INSTANCE;

    //#if MINECRAFT<11300
    //$$ /**
    //$$  * Opens an X input method, matching the current locale and modifiers specification.
    //$$  *
    //$$  * @param display The pointer to an X display connection.
    //$$  * @return The pointer to an X input method.
    //$$  *
    //$$  * @see org.lwjgl.opengl.LinuxKeyboard#openIM(long)
    //$$  * @see <a href="https://www.x.org/releases/X11R7.7/doc/man/man3/XOpenIM.3.xhtml">
    //$$  *         <code>XOpenIM(Display*, XrmDatabase, char*, char*)</code></a>
    //$$  */
    //$$ public XIM openIm(Display display)
    //$$ {
    //$$     return X11.XOpenIM(display, null, null, null);
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
    public void toggleIcFocus(XIC ic, boolean focused)
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
