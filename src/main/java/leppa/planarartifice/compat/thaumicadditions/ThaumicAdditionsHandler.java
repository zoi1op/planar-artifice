package leppa.planarartifice.compat.thaumicadditions;

import com.zeitheron.thaumicadditions.init.KnowledgeTAR;
import leppa.planarartifice.blocks.BlockAlkimiumSmeltery;
import leppa.planarartifice.compat.PACompatHandler.ICompatModule;
import leppa.planarartifice.main.PAConfig;
import leppa.planarartifice.main.PlanarArtifice;
import leppa.planarartifice.recipe.CrucibleRecipeRandomCrystal;
import leppa.planarartifice.registry.PABlocks;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.crafting.ShapedArcaneRecipe;

import java.util.Arrays;

public class ThaumicAdditionsHandler implements ICompatModule {

	public static boolean extraActivated = false;

	public static final Block alkimium_smeltery_mithrillium = new BlockAlkimiumSmeltery("alkimium_smeltery_mithrillium", 15, 1F, 1000).setCreativeTab(PlanarArtifice.creativetab);
	public static final Block alkimium_smeltery_adaminite = new BlockAlkimiumSmeltery("alkimium_smeltery_adaminite", 10, 1.25F, 2000).setCreativeTab(PlanarArtifice.creativetab);
	public static final Block alkimium_smeltery_mithminite = new BlockAlkimiumSmeltery("alkimium_smeltery_mithminite", 3, 1.5F, 4000).setCreativeTab(PlanarArtifice.creativetab);

	static ResourceLocation defaultGroup = new ResourceLocation("");

	public ThaumicAdditionsHandler() {
		extraActivated = !PAConfig.compat.disableTAExtraCompat;
	}

	@Override
	public void preInit(FMLPreInitializationEvent e) {

	}

	@Override
	public void init(FMLInitializationEvent e) {
		if (!PAConfig.compat.disableTACompat) {
			ThaumcraftApi.registerResearchLocation(new ResourceLocation(PlanarArtifice.MODID, "research/compat_ta.json"));
			ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(PlanarArtifice.MODID, "alkimium_smeltery_mithrillium"), new ShapedArcaneRecipe(defaultGroup, "PA_ALKIMIUM_MITHRILLIUM_SMELTERY@2", 1250, new AspectList().add(Aspect.FIRE, 9).add(Aspect.WATER, 3), new ItemStack(alkimium_smeltery_mithrillium), "#C#", "ADA", "AAA", '#', "plateAlkimium", 'A', "plateMithrillium", 'C', new ItemStack(PABlocks.alkimium_smeltery_void), 'D', new ItemStack(BlocksTC.metalAlchemicalAdvanced)));
			ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(PlanarArtifice.MODID, "alkimium_smeltery_adaminite"), new ShapedArcaneRecipe(defaultGroup, "PA_ALKIMIUM_ADAMINITE_SMELTERY@2", 1600, new AspectList().add(Aspect.FIRE, 18).add(Aspect.WATER, 9), new ItemStack(alkimium_smeltery_adaminite), "#C#", "ADA", "AAA", '#', "plateAlkimium", 'A', "plateAdaminite", 'C', new ItemStack(alkimium_smeltery_mithrillium), 'D', new ItemStack(BlocksTC.metalAlchemicalAdvanced)));
			ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(PlanarArtifice.MODID, "alkimium_smeltery_mithminite"), new ShapedArcaneRecipe(defaultGroup, "PA_ALKIMIUM_MITHMINITE_SMELTERY@2", 2000, new AspectList().add(Aspect.FIRE, 36).add(Aspect.WATER, 18), new ItemStack(alkimium_smeltery_mithminite), "#C#", "ADA", "AAA", '#', "plateAlkimium", 'A', "plateMithminite", 'C', new ItemStack(alkimium_smeltery_adaminite), 'D', new ItemStack(BlocksTC.metalAlchemicalAdvanced)));
		}

		if (!PAConfig.compat.disableAspectCompat) {
			final Aspect[] taYangAspects = {KnowledgeTAR.CAELES, KnowledgeTAR.FLUCTUS, KnowledgeTAR.SONUS, KnowledgeTAR.VENTUS, KnowledgeTAR.VISUM};
			CrucibleRecipeRandomCrystal.yangAspects.addAll(Arrays.asList(taYangAspects));
			final Aspect[] taYinAspects = {KnowledgeTAR.DRACO, KnowledgeTAR.EXITIUM, KnowledgeTAR.IMPERIUM, KnowledgeTAR.INFERNUM};
			CrucibleRecipeRandomCrystal.yinAspects.addAll(Arrays.asList(taYinAspects));
		}
	}

	@Override
	public void postInit(FMLPostInitializationEvent e) {

	}

}