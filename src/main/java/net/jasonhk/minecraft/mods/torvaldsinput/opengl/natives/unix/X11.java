package net.jasonhk.minecraft.mods.torvaldsinput.opengl.natives.unix;

import lombok.val;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.PointerType;
import com.sun.jna.Structure;

public interface X11 extends com.sun.jna.platform.unix.X11
{
    X11 INSTANCE = Native.loadLibrary("X11", X11.class);

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
