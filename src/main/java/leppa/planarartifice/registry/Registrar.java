package leppa.planarartifice.registry;

import leppa.planarartifice.compat.tconstruct.TConstructHandler;
import leppa.planarartifice.main.PlanarArtifice;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.aspects.AspectRegistryEvent;

@EventBusSubscriber(modid = PlanarArtifice.MODID)
public class Registrar {

	@SubscribeEvent
	public static void registerBlocks(Register<Block> event) {
		PlanarArtifice.LOGGER.info("[LOADER] " + Loader.isModLoaded("tconstruct"));
		if (Loader.isModLoaded("tconstruct")) TConstructHandler.registerBlocks(event);
		PABlocks.registerBlocks(event);
	}

	@SubscribeEvent
	public static void registerItems(Register<Item> event) {
		PABlocks.registerItemBlocks(event);
		PAItems.registerItems(event);
		Registrar.registerOres();
	}

	@SubscribeEvent
	public static void registerRecipes(Register<IRecipe> event) {
		PARecipes.registerRecipes(event);
		PAMultiblocks.registerMultiblocks();
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void registerModels(ModelRegistryEvent event) {
		PABlocks.registerModels();
		PAItems.registerModels();
	}

	public static void registerOres() {
		OreDictionary.registerOre("blockAlkimium", PABlocks.alkimium_block);
		OreDictionary.registerOre("ingotAlkimium", PAItems.alkimium_ingot);
		OreDictionary.registerOre("plateAlkimium", PAItems.alkimium_plate);
		OreDictionary.registerOre("nuggetAlkimium", PAItems.alkimium_nugget);

		OreDictionary.registerOre("blockBismuth", PABlocks.bismuth_block);
		OreDictionary.registerOre("ingotBismuth", PAItems.bismuth_ingot);
		OreDictionary.registerOre("plateBismuth", PAItems.bismuth_plate);
		OreDictionary.registerOre("nuggetBismuth", PAItems.bismuth_nugget);

		PABlocks.glass.oredict();
		PABlocks.glass_clear.oredict();
		PABlocks.glass_scratched.oredict();
		PABlocks.glass_crystal.oredict();
		PABlocks.glass_bright.oredict();
		PABlocks.glass_dim.oredict();
		PABlocks.glass_dark.oredict();
		PABlocks.glass_ghostly.oredict();
		PABlocks.glass_ethereal.oredict();
		PABlocks.glass_foreboding.oredict();
		PABlocks.glass_strong.oredict();
		OreDictionary.registerOre("blockGlass", PABlocks.glass_redstone);
	}

	@SubscribeEvent
	public static void registerAspects(AspectRegistryEvent event) {
		PAAspects.registerItemAspects();
	}
}
