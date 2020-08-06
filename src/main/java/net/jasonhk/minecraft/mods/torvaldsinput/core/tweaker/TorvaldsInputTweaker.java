//#if MINECRAFT<11300
//$$ package net.jasonhk.minecraft.mods.torvaldsinput.core.tweaker;
//$$
//$$ import java.io.File;
//$$ import java.util.List;
//$$
//$$ import net.minecraft.launchwrapper.ITweaker;
//$$ import net.minecraft.launchwrapper.LaunchClassLoader;
//$$
//$$ import org.spongepowered.asm.launch.MixinBootstrap;
//$$ import org.spongepowered.asm.mixin.Mixins;
//$$
//$$ @SuppressWarnings("unused")
//$$ public final class TorvaldsInputTweaker implements ITweaker
//$$ {
//$$     @Override
//$$     public void acceptOptions(
//$$             final List<String> args,
//$$             final File gameDir,
//$$             final File assetsDir,
//$$             final String profile)
//$$     {}
//$$
//$$     @Override
//$$     public void injectIntoClassLoader(LaunchClassLoader classLoader)
//$$     {
//$$         MixinBootstrap.init();
//$$         Mixins.addConfiguration("torvaldsinput.mixins.input.json");
//$$         Mixins.addConfiguration("torvaldsinput.mixins.gui.json");
//$$     }
//$$
//$$     @Override
//$$     public String getLaunchTarget()
//$$     {
//$$         return null;
//$$     }
//$$
//$$     @Override
//$$     public String[] getLaunchArguments()
//$$     {
//$$         return new String[0];
//$$     }
//$$ }
//#endif
