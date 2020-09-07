package net.jasonhk.minecraft.mods.torvaldsinput.natives.unix;

import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.PointerType;

import lombok.val;

public interface X11 extends com.sun.jna.platform.unix.X11
{
    //#if MINECRAFT>=11200
    X11 INSTANCE = Native.loadLibrary("X11", X11.class);
    //#else
    //$$ X11 INSTANCE = (X11) Native.loadLibrary("X11", X11.class);
    //#endif

    //#if MINECRAFT>=11300
    @SuppressWarnings("unused")
    //#endif
    XIM XOpenIM(
            com.sun.jna.platform.unix.X11.Display display,
            XrmDatabase db,
            String res_name,
            String res_class);

    void XSetICFocus(XIC ic);

    void XUnsetICFocus(XIC ic);

    class Display extends com.sun.jna.platform.unix.X11.Display
    {
        public static Display of(Pointer pointer)
        {
            val display = new Display();
            display.setPointer(pointer);

            return display;
        }
    }

    @SuppressWarnings("unused")
    class Time extends NativeLong
    {
        public Time()
        {
            this(0);
        }

        public Time(long value)
        {
            //#if MINECRAFT>=11300
            super(value, true);
            //#else
            //$$ super(value);
            //#endif
        }
    }

    class XIC extends PointerType
    {
        public static XIC of(Pointer pointer)
        {
            val xic = new XIC();
            xic.setPointer(pointer);

            return xic;
        }
    }

    class XIM extends PointerType {}

    class XrmDatabase extends PointerType {}
}
