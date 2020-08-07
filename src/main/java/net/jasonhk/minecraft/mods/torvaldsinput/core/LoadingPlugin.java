//#if MINECRAFT<11300
//$$ package net.jasonhk.minecraft.mods.torvaldsinput.core;
//$$
//$$ import java.util.Map;
//$$
//$$ import javax.annotation.Nullable;
//$$
//$$ import net.minecraft.launchwrapper.Launch;
//$$
//$$ import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
//$$
//$$ import org.spongepowered.asm.launch.MixinBootstrap;
//$$ import org.spongepowered.asm.mixin.Mixins;
//$$
//$$ import net.jasonhk.minecraft.mods.torvaldsinput.core.utilities.MixinConfigurator;
//$$
//$$ public final class LoadingPlugin implements IFMLLoadingPlugin
//$$ {
//$$     public LoadingPlugin()
//$$     {
//$$         if (Launch.blackboard.get("fml.deobfuscatedEnvironment") != Boolean.FALSE)
//$$         {
//$$             MixinConfigurator.initialize();
//$$         }
//$$
//$$         MixinConfigurator.configure();
//$$     }
//$$
//$$     @Override
//$$     public void injectData(Map<String, Object> map) {}
//$$
//$$     @Nullable
//$$     @Override
//$$     public String getSetupClass()
//$$     {
//$$         return null;
//$$     }
//$$
//$$     @Override
//$$     public String getModContainerClass()
//$$     {
//$$         return null;
//$$     }
//$$
//$$     @Override
//$$     public String getAccessTransformerClass()
//$$     {
//$$         return null;
//$$     }
//$$
//$$     @Override
//$$     public String[] getASMTransformerClass()
//$$     {
//$$         return new String[0];
//$$     }
//$$ }
//#endif
