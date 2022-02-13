package leppa.planarartifice.registry;

import leppa.planarartifice.api.event.EventFibreSpread;
import leppa.planarartifice.compat.tconstruct.TConstructHandler;
import leppa.planarartifice.main.PAConfig;
import leppa.planarartifice.main.PlanarArtifice;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.aspects.AspectRegistryEvent;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.common.blocks.IBlockFacing;

import java.util.ArrayList;

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

	@SubscribeEvent
	public static void onTaintSpread(EventFibreSpread event) {
		if (!event.checkDefaultCancel()) return;
		IBlockState bs = event.world.getBlockState(event.pos);
		if (event.world.rand.nextFloat() < PAConfig.balance.taintFeatureGenRate && bs.getBlock() == BlocksTC.taintLog) {
			ArrayList<BlockPos> fib = new ArrayList<>();
			ArrayList<EnumFacing> face = new ArrayList<>();
			if (event.world.isAirBlock(event.pos.up())) { fib.add(event.pos.up()); face.add(EnumFacing.UP); }
			if (event.world.isAirBlock(event.pos.north())) { fib.add(event.pos.north()); face.add(EnumFacing.NORTH); }
			if (event.world.isAirBlock(event.pos.south())) { fib.add(event.pos.south()); face.add(EnumFacing.SOUTH); }
			if (event.world.isAirBlock(event.pos.east())) { fib.add(event.pos.east()); face.add(EnumFacing.EAST); }
			if (event.world.isAirBlock(event.pos.west())) { fib.add(event.pos.west()); face.add(EnumFacing.WEST); }
			if (fib.size() == 0) return;
			int meta = event.world.rand.nextInt(fib.size());
			event.world.setBlockState(fib.get(meta), BlocksTC.taintFeature.getDefaultState().withProperty(IBlockFacing.FACING, face.get(meta)));
//			PlanarArtifice.LOGGER.info("Spawned TaintFeature at " + event.pos + " on " + face.get(meta) + "!");
		}
	}
}
