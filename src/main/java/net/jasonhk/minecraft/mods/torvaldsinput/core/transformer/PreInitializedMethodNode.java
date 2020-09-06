package net.jasonhk.minecraft.mods.torvaldsinput.core.transformer;

import org.objectweb.asm.tree.MethodNode;

public abstract class PreInitializedMethodNode extends MethodNode
{
    public PreInitializedMethodNode(int api)
    {
        super(api);
        tryInit();
    }

    public PreInitializedMethodNode(
            int api,
            int access,
            String name,
            String descriptor,
            String signature,
            String[] exceptions)
    {
        super(api, access, name, descriptor, signature, exceptions);
        tryInit();
    }

    protected abstract void init() throws NoSuchFieldException, NoSuchMethodException;

    private void tryInit()
    {
        try
        {
            init();
        }
        catch (NoSuchFieldException | NoSuchMethodException exception)
        {
            throw new RuntimeException(
                    String.format(
                            "An exception occurred while initializing an instance of %s.",
                            getClass().getSimpleName()),
                    exception);
        }
    }
}
