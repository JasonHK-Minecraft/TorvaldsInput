package net.jasonhk.minecraft.mods.torvaldsinput.opengl;

import static net.jasonhk.minecraft.mods.torvaldsinput.core.TorvaldsInput.LOGGER;

@SuppressWarnings("JavadocReference")
public final class LinuxKeyboard
{
    static
    {
        try
        {
            LOGGER.debug("Loading natives.");
            System.loadLibrary(
                    "/media/jasonhk/STORAGE/Programming/Java/Minecraft/Tools/TorvaldsInput/1.12.2/build/runClientMod/libmain.so");
        }
        catch (UnsatisfiedLinkError ex)
        {
            LOGGER.error("Failed to load natives.");
        }
    }

    /**
     * Opens an input method, matching the current locale and modifiers specification.
     *
     * @param display The pointer to the X display connection.
     * @return The pointer to the input method.
     *
     * @see org.lwjgl.opengl.LinuxKeyboard#openIM(long)
     * @see <a href="https://www.x.org/releases/X11R7.7/doc/man/man3/XOpenIM.3.xhtml"><code>XOpenIM(Display*,
     * XrmDatabase, char*, char*)</code></a>
     */
    public static native long openIM(long display);

    /**
     * Updates the keyboard focus state the given input context.
     *
     * @param xic     The input context.
     * @param focused Whether the input context should receive keyboard focus.
     *
     * @see <a href="https://www.x.org/releases/X11R7.5/doc/man/man3/XSetICFocus.3.html"><code>XSetICFocus(XIC)</code></a>
     * @see <a href="https://www.x.org/releases/X11R7.5/doc/man/man3/XSetICFocus.3.html"><code>XUnsetICFocus(XIC)</code></a>
     */
    public static native void toggleICFocus(long xic, boolean focused);
}
