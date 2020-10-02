package leppa.planarartifice.registry;

import leppa.planarartifice.main.PlanarArtifice;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;

@EventBusSubscriber(modid = PlanarArtifice.MODID)
public class Registrar {

	@SubscribeEvent
	public static void registerBlocks(Register<Block> event) {
		PABlocks.registerBlocks(event);
	}

	@SubscribeEvent
	public static void registerItems(Register<Item> event) {
		PABlocks.registerItemBlocks(event);
		PAItems.registerItems(event);
	}

	@SubscribeEvent
	public static void registerRecipes(Register<IRecipe> event) {
		PARecipes.registerRecipes(event);
	}

	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		PABlocks.registerModels();
		PAItems.registerModels();
	}

	public static void registerOres() {
		OreDictionary.registerOre("blockAlkimium", PABlocks.alkimium_block);
		OreDictionary.registerOre("blockBismuth", PABlocks.bismuth_block);

		OreDictionary.registerOre("ingotAlkimium", PAItems.alkimium_ingot);
		OreDictionary.registerOre("plateAlkimium", PAItems.alkimium_plate);
		OreDictionary.registerOre("nuggetAlkimium", PAItems.alkimium_nugget);
		OreDictionary.registerOre("ingotBismuth", PAItems.bismuth_ingot);
		OreDictionary.registerOre("plateBismuth", PAItems.bismuth_plate);
		OreDictionary.registerOre("nuggetBismuth", PAItems.bismuth_nugget);
	}

}
