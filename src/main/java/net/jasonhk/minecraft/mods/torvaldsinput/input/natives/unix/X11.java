package net.jasonhk.minecraft.mods.torvaldsinput.input.natives.unix;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.PointerType;

public interface X11 extends com.sun.jna.platform.unix.X11
{
    //#if MINECRAFT>=11200
    X11 INSTANCE = Native.loadLibrary("X11", X11.class);
    //#else
    //$$ X11 INSTANCE = (X11) Native.loadLibrary("X11", X11.class);
    //#endif

    XIM XOpenIM(Display display, XrmDatabase db, String res_name, String res_class);

    void XSetICFocus(XIC ic);

    void XUnsetICFocus(XIC ic);

    class XIC extends PointerType
    {
        public XIC() {}

        public XIC(final Pointer pointer)
        {
            super(pointer);
        }
    }

    class XIM extends PointerType {}

    class XrmDatabase extends PointerType {}
}
