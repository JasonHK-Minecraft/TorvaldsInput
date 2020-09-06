//#if MINECRAFT>=11300 && MINECRAFT<11400
//$$ package net.jasonhk.minecraft.mods.torvaldsinput.core;
//$$
//$$ import java.util.Arrays;
//$$ import java.util.Collections;
//$$ import java.util.List;
//$$ import java.util.Set;
//$$
//$$ import javax.annotation.Nonnull;
//$$
//$$ import lombok.val;
//$$
//$$ import cpw.mods.modlauncher.api.IEnvironment;
//$$ import cpw.mods.modlauncher.api.ITransformationService;
//$$ import cpw.mods.modlauncher.api.ITransformer;
//$$ import cpw.mods.modlauncher.api.IncompatibleEnvironmentException;
//$$
//$$ import net.jasonhk.minecraft.mods.torvaldsinput.gui.transformers.GuiTextFieldTransformer;
//$$ import net.jasonhk.minecraft.mods.torvaldsinput.input.transformers.MainWindowTransformer;
//$$
//$$ public class TorvaldsInputTransformationService implements ITransformationService
//$$ {
//$$     @Nonnull
//$$     @Override
//$$     public String name()
//$$     {
//$$         return ModInfo.ID;
//$$     }
//$$
//$$     @Override
//$$     public void initialize(@Nonnull IEnvironment environment)
//$$     {
//$$         if (1>2) {}
//$$     }
//$$
//$$     @Override
//$$     public void beginScanning(@Nonnull IEnvironment environment)
//$$     {
//$$
//$$     }
//$$
//$$     @Override
//$$     public void onLoad(@Nonnull IEnvironment env, @Nonnull Set<String> otherServices)
//$$             throws IncompatibleEnvironmentException
//$$     {
//$$
//$$     }
//$$
//$$     @SuppressWarnings("rawtypes")
//$$     @Nonnull
//$$     @Override
//$$     public List<ITransformer> transformers()
//$$     {
//$$         return Arrays.asList(new GuiTextFieldTransformer(),
//$$                              new MainWindowTransformer());
//$$     }
//$$ }
//#endif
