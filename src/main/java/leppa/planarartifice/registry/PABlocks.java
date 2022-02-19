package leppa.planarartifice.registry;

import leppa.planarartifice.blocks.*;
import leppa.planarartifice.blocks.glass.*;
import leppa.planarartifice.main.PlanarArtifice;
import leppa.planarartifice.util.Aspects;
import leppa.planarartifice.util.OreUtils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.ShapedArcaneRecipe;

import java.util.ArrayList;
import java.util.Objects;

public class PABlocks {

	public static final ArrayList<Block> BLOCKS = new ArrayList<>();
	public static final ArrayList<Block> METABLOCKS = new ArrayList<>();

	public static final Block alkimium_block = new BlockMetal("alkimium_block");
	public static final Block bismuth_block = new BlockMetal("bismuth_block");
	public static final Block alchemical_alkimium_construct = new BlockPA(Material.IRON, "alchemical_alkimium_construct");
	
	public static final Block alkimium_smeltery = new BlockAlkimiumSmeltery("alkimium_smeltery", 14, 0.85F, 375);
	public static final Block alkimium_smeltery_thaumium = new BlockAlkimiumSmeltery("alkimium_smeltery_thaumium", 7, 0.85F, 375);
	public static final Block alkimium_smeltery_void = new BlockAlkimiumSmeltery("alkimium_smeltery_void", 10, 0.95F, 375);
	public static final Block alkimium_centrifuge = new BlockAlkimiumCentrifuge("alkimium_centrifuge");

	public static final Block smelter_aux = new BlockAlkimiumSmelteryAux("smelter_aux");
	public static final Block smelter_vent = new BlockAlkimiumSmelteryVent("smelter_vent");

	public static final Block teleporter = new BlockTeleporterMiddle("teleporter").setCreativeTab(null);
	public static final Block teleporter_matrix = new BlockPA(Material.ROCK,"teleporter_matrix");
	public static final Block teleporter_placeholder = new BlockTeleporterPlaceholder("teleporter_placeholder").setCreativeTab(null);
	
//	public static final Block potion_mixer = new BlockPotionMixer("potion_mixer");
	public static final Block flux_scrubber = new BlockFluxScrubber("flux_scrubber");
	public static final Block starving_chest = new BlockStarvingChest("starving_chest");

	public static final Glasses glass = new Glasses();
	public static final Glasses glass_clear = new Glasses("_clear");
	public static final Glasses glass_scratched = new Glasses("_scratched");
	public static final Glasses glass_crystal = new Glasses("_crystal");
	public static final Glasses glass_bright = new Glasses("_bright", s -> s.setLightLevel(1.0F));
	public static final Glasses glass_dim = new Glasses("_dim", s -> s.setLightOpacity(3));
	public static final Glasses glass_dark = new Glasses("_dark", s -> s.setLightOpacity(15));
	public static final Glasses glass_ghostly = new Glasses("_ghostly", true, true, true);
	public static final Glasses glass_ethereal = new Glasses("_ethereal", false, true, false);
	public static final Glasses glass_foreboding = new Glasses("_foreboding", true, true, false);
	public static final Glasses glass_strong = new Glasses("_strong", s -> s.setHardness(5).setResistance(1200));
	public static final Block glass_redstone = new BlockGlassPA("glass_redstone") {
		@Override
		@SuppressWarnings("deprecation")
		public boolean canProvidePower(IBlockState state) {
			return true;
		}

		@Override
		@SuppressWarnings("deprecation")
		public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) { return 15; }

		@SideOnly(Side.CLIENT)
		public BlockRenderLayer getBlockLayer() { return BlockRenderLayer.TRANSLUCENT; }
	};
	public static final Block glass_redstone_directional = new BlockGlassRedstoneDirectionalPA("glass_redstone_directional") {
		@SideOnly(Side.CLIENT)
		public BlockRenderLayer getBlockLayer()
		{
			return BlockRenderLayer.TRANSLUCENT;
		}
	};

	public static void registerBlocks(RegistryEvent.Register<Block> event)
	{
		BLOCKS.forEach(b -> event.getRegistry().register(b));
		METABLOCKS.forEach(b -> event.getRegistry().register(b));
	}

	public static void registerItemBlocks(RegistryEvent.Register<Item> event)
	{
		BLOCKS.forEach(b -> event.getRegistry().register(new ItemBlock(b).setRegistryName(b.getRegistryName())));
		METABLOCKS.forEach(b -> { if (b instanceof BlockPA) event.getRegistry().register(new ItemMultiTexture(b, null, ((BlockPA)b).variantNames).setRegistryName(b.getRegistryName())); });
	}

	@SideOnly(Side.CLIENT)
	public static void registerModels() {
		BLOCKS.forEach(PABlocks::registerRender);
		METABLOCKS.forEach(b -> { if (b instanceof BlockPA) ((BlockPA)b).registerModels(); });
	}

	@SideOnly(Side.CLIENT)
	public static void registerRender(Block block) {
		if(block instanceof BlockFluidBase)
			ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(BlockFluidBase.LEVEL).build());
		else ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
	}

	public static class Glasses {
		private final String[] COLORS = {"White", "Orange", "Magenta", "LightBlue", "Yellow", "Lime", "Pink", "Gray", "LightGray", "Cyan", "Purple", "Blue", "Brown", "Green", "Red", "Black"};
		private String name;
		public Block BLOCK;
		public Block BLOCK_STAINED;
		public Block BLOCK_RAINBOW;
		public Block PANE;
		public Block PANE_STAINED;
		public Block PANE_RAINBOW;
		public Block PANEL;
		public Block PANEL_STAINED;
		public Block PANEL_RAINBOW;

		public Glasses(String name) { this(name, false, false, false, (s) -> s); }
		public Glasses(String name, boolean passEntity, boolean passPlayer, boolean passHostile) { this(name, passEntity, passPlayer, passHostile, (s) -> s); }
		public Glasses(String name, BlockFunction fn) { this(name, false, false, false, fn); }
		public Glasses(String name, boolean passEntity, boolean passPlayer, boolean passHostile, BlockFunction fn) {
			this.name = name;
			PlanarArtifice.LOGGER.info("Registering glass type " + name);
			if (Objects.equals(name, "_crystal")) {
				BLOCK = fn.run(new BlockGlassPA("glass" + name, passEntity, passPlayer, passHostile) {
					@SideOnly(Side.CLIENT)
					public BlockRenderLayer getBlockLayer() {
						return BlockRenderLayer.TRANSLUCENT;
					}
				});
				BLOCK_STAINED = fn.run(new BlockGlassStainedPA("stained_glass" + name, passEntity, passPlayer, passHostile));
			} else if (!Objects.equals(name, "_clear") || !Loader.isModLoaded("tconstruct")) {
				BLOCK = fn.run(new BlockGlassPA("glass" + name, passEntity, passPlayer, passHostile));
				BLOCK_STAINED = fn.run(new BlockGlassStainedPA("stained_glass" + name, passEntity, passPlayer, passHostile));
			}
			BLOCK_RAINBOW = fn.run(new BlockGlassPA("glass" + name + "_rainbow", passEntity, passPlayer, passHostile) {
				@SideOnly(Side.CLIENT)
				public BlockRenderLayer getBlockLayer() { return BlockRenderLayer.TRANSLUCENT; }
			});
			PANE = fn.run(new BlockGlassPanePA("glass_pane" + name, passEntity, passPlayer, passHostile));
			PANE_STAINED = fn.run(new BlockGlassPaneStainedPA("stained_glass_pane" + name, passEntity, passPlayer, passHostile));
			PANE_RAINBOW = fn.run(new BlockGlassPanePA("glass_pane" + name + "_rainbow", passEntity, passPlayer, passHostile) {
				@SideOnly(Side.CLIENT)
				public BlockRenderLayer getBlockLayer() { return BlockRenderLayer.TRANSLUCENT; }
			});
			PANEL = fn.run(new BlockGlassPanelPA("glass_panel" + name, passEntity, passPlayer, passHostile));
			PANEL_STAINED = fn.run(new BlockGlassPanelStainedPA("stained_glass_panel" + name, passEntity, passPlayer, passHostile));
			PANEL_RAINBOW = fn.run(new BlockGlassPanelPA("glass_panel" + name + "_rainbow", passEntity, passPlayer, passHostile) {
				@SideOnly(Side.CLIENT)
				public BlockRenderLayer getBlockLayer() { return BlockRenderLayer.TRANSLUCENT; }
			});
		}
		// for vanilla
		public Glasses() {
			this.name = "";
			PlanarArtifice.LOGGER.info("Registering glass type null");
			BLOCK = Blocks.GLASS;
			BLOCK_STAINED = Blocks.STAINED_GLASS;
			BLOCK_RAINBOW = new BlockGlassPA("glass_rainbow") {
				@SideOnly(Side.CLIENT)
				public BlockRenderLayer getBlockLayer()
				{
					return BlockRenderLayer.TRANSLUCENT;
				}
			};
			PANE = Blocks.GLASS_PANE;
			PANE_STAINED = Blocks.STAINED_GLASS_PANE;
			PANE_RAINBOW = new BlockGlassPanePA("glass_pane_rainbow") {
				@SideOnly(Side.CLIENT)
				public BlockRenderLayer getBlockLayer()
				{
					return BlockRenderLayer.TRANSLUCENT;
				}
			};
			PANEL = new BlockGlassPanelPA("glass_panel");
			PANEL_STAINED = new BlockGlassPanelStainedPA("stained_glass_panel");
			PANEL_RAINBOW = new BlockGlassPanelPA("glass_panel_rainbow") {
				@SideOnly(Side.CLIENT)
				public BlockRenderLayer getBlockLayer()
				{
					return BlockRenderLayer.TRANSLUCENT;
				}
			};
		}

		public void oredict() {
			String type;
			if (name.isEmpty()) type = null;
			else type = name.substring(1, 2).toUpperCase() + name.substring(2);
			if (type != null) PlanarArtifice.LOGGER.info("Registering oredict for glass type " + type);
			else PlanarArtifice.LOGGER.info("Registering oredict for glass type null");
			OreDictionary.registerOre("blockGlass", BLOCK);
			OreDictionary.registerOre("blockGlass", OreUtils.meta(BLOCK_STAINED, OreDictionary.WILDCARD_VALUE));
			OreDictionary.registerOre("blockGlass", BLOCK_RAINBOW);
			OreDictionary.registerOre("paneGlass", PANE);
			OreDictionary.registerOre("paneGlass", OreUtils.meta(PANE_STAINED, OreDictionary.WILDCARD_VALUE));
			OreDictionary.registerOre("paneGlass", PANE_RAINBOW);
			OreDictionary.registerOre("panelGlass", PANEL);
			OreDictionary.registerOre("panelGlass", OreUtils.meta(PANEL_STAINED, OreDictionary.WILDCARD_VALUE));
			OreDictionary.registerOre("panelGlass", PANEL_RAINBOW);
			if (type != null) {
				OreDictionary.registerOre("blockGlass" + type, BLOCK);
				OreDictionary.registerOre("blockGlass" + type, OreUtils.meta(BLOCK_STAINED, OreDictionary.WILDCARD_VALUE));
				OreDictionary.registerOre("blockGlass" + type, BLOCK_RAINBOW);
				OreDictionary.registerOre("paneGlass" + type, PANE);
				OreDictionary.registerOre("paneGlass" + type, OreUtils.meta(PANE_STAINED, OreDictionary.WILDCARD_VALUE));
				OreDictionary.registerOre("paneGlass" + type, PANE_RAINBOW);
				OreDictionary.registerOre("panelGlass" + type, PANEL);
				OreDictionary.registerOre("panelGlass" + type, OreUtils.meta(PANEL_STAINED, OreDictionary.WILDCARD_VALUE));
				OreDictionary.registerOre("panelGlass" + type, PANEL_RAINBOW);
			}
			OreDictionary.registerOre("blockGlassColorless", BLOCK);
			OreDictionary.registerOre("blockGlassRainbow", BLOCK_RAINBOW);
			OreDictionary.registerOre("paneGlassColorless", PANE);
			OreDictionary.registerOre("paneGlassRainbow", PANE_RAINBOW);
			OreDictionary.registerOre("panelGlassColorless", PANEL);
			OreDictionary.registerOre("panelGlassRainbow", PANEL_RAINBOW);
			for (int i = 0; i < 16; i++) {
				OreDictionary.registerOre("blockGlass" + COLORS[i], OreUtils.meta(BLOCK_STAINED, i));
				OreDictionary.registerOre("paneGlass" + COLORS[i], OreUtils.meta(PANE_STAINED, i));
				OreDictionary.registerOre("panelGlass" + COLORS[i], OreUtils.meta(PANEL_STAINED, i));
			}
		}

		public void recipe() {
			// panes
			GameRegistry.addShapedRecipe(new ResourceLocation("planarartifice:glass_pane" + name), new ResourceLocation(""), new ItemStack(PANE, 16), "###", "###", '#', BLOCK);
			GameRegistry.addShapedRecipe(new ResourceLocation("planarartifice:glass_pane2" + name), new ResourceLocation(""), new ItemStack(PANEL, 16), "##", "##", "##", '#', BLOCK);
			GameRegistry.addShapedRecipe(new ResourceLocation("planarartifice:glass_pane3" + name), new ResourceLocation(""), new ItemStack(PANEL, 1), "#", '#', PANE);
			GameRegistry.addShapedRecipe(new ResourceLocation("planarartifice:glass_pane4" + name), new ResourceLocation(""), new ItemStack(PANE, 1), "#", '#', PANEL);
			GameRegistry.addShapedRecipe(new ResourceLocation("planarartifice:glass_pane" + name + "_rainbow"), new ResourceLocation(""), new ItemStack(PANE_RAINBOW, 16), "###", "###", '#', BLOCK_RAINBOW);
			GameRegistry.addShapedRecipe(new ResourceLocation("planarartifice:glass_pane2" + name + "_rainbow"), new ResourceLocation(""), new ItemStack(PANEL_RAINBOW, 16), "##", "##", "##", '#', BLOCK_RAINBOW);
			GameRegistry.addShapedRecipe(new ResourceLocation("planarartifice:glass_pane3" + name + "_rainbow"), new ResourceLocation(""), new ItemStack(PANEL_RAINBOW, 1), "#", '#', PANE_RAINBOW);
			GameRegistry.addShapedRecipe(new ResourceLocation("planarartifice:glass_pane4" + name + "_rainbow"), new ResourceLocation(""), new ItemStack(PANE_RAINBOW, 1), "#", '#', PANEL_RAINBOW);
			// undye / rainbow
			ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("planarartifice:glass_aqua" + name), new ShapedArcaneRecipe(PARecipes.defaultGroup, "PA_GLASSWORK_COSMETIC", 5, null, new ItemStack(BLOCK, 8), "###", "#C#", "###", '#', BLOCK, 'C', ThaumcraftApiHelper.makeCrystal(Aspect.WATER)));
			ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("planarartifice:pane_aqua" + name), new ShapedArcaneRecipe(PARecipes.defaultGroup, "PA_GLASSWORK_COSMETIC", 5, null, new ItemStack(PANE, 8), "###", "#C#", "###", '#', PANE, 'C', ThaumcraftApiHelper.makeCrystal(Aspect.WATER)));
			ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("planarartifice:panel_aqua" + name), new ShapedArcaneRecipe(PARecipes.defaultGroup, "PA_GLASSWORK_COSMETIC", 5, null, new ItemStack(PANEL, 8), "###", "#C#", "###", '#', PANEL, 'C', ThaumcraftApiHelper.makeCrystal(Aspect.WATER)));
			ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("planarartifice:glass_bismuth" + name), new CrucibleRecipe("PA_GLASSWORK_RAINBOW", new ItemStack(BLOCK_RAINBOW), BLOCK, new Aspects("tinctura", 5)));
			ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("planarartifice:pane_bismuth" + name), new CrucibleRecipe("PA_GLASSWORK_RAINBOW", new ItemStack(PANE_RAINBOW), PANE, new Aspects("tinctura", 5)));
			ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("planarartifice:panel_bismuth" + name), new CrucibleRecipe("PA_GLASSWORK_RAINBOW", new ItemStack(PANEL_RAINBOW), PANEL, new Aspects("tinctura", 5)));
			ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("planarartifice:glass_rebismuth" + name), new CrucibleRecipe("PA_GLASSWORK_RAINBOW", new ItemStack(BLOCK_RAINBOW), new ItemStack(BLOCK_STAINED, OreDictionary.WILDCARD_VALUE), new Aspects("tinctura", 5)));
			ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("planarartifice:pane_rebismuth" + name), new CrucibleRecipe("PA_GLASSWORK_RAINBOW", new ItemStack(PANE_RAINBOW), new ItemStack(PANE_STAINED, OreDictionary.WILDCARD_VALUE), new Aspects("tinctura", 5)));
			ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("planarartifice:panel_rebismuth" + name), new CrucibleRecipe("PA_GLASSWORK_RAINBOW", new ItemStack(PANEL_RAINBOW), new ItemStack(PANEL_STAINED, OreDictionary.WILDCARD_VALUE), new Aspects("tinctura", 5)));
			for (int i = 0; i < 16; i++) {
				// dye / redye
				GameRegistry.addShapedRecipe(new ResourceLocation("planarartifice:glass_dye" + name + "_" + i), new ResourceLocation(""), new ItemStack(BLOCK_STAINED, 8, i), "###", "#C#", "###", '#', BLOCK, 'C', "dye" + COLORS[i]);
				GameRegistry.addShapedRecipe(new ResourceLocation("planarartifice:glass_redye" + name + "_" + i), new ResourceLocation(""), new ItemStack(BLOCK_STAINED, 8, i), "###", "#C#", "###", '#', OreUtils.meta(BLOCK_STAINED, OreDictionary.WILDCARD_VALUE), 'C', "dye" + COLORS[i]);
				GameRegistry.addShapedRecipe(new ResourceLocation("planarartifice:glass_redye2" + name + "_" + i), new ResourceLocation(""), new ItemStack(BLOCK_STAINED, 8, i), "###", "#C#", "###", '#', BLOCK_RAINBOW, 'C', "dye" + COLORS[i]);
				GameRegistry.addShapedRecipe(new ResourceLocation("planarartifice:pane_dye" + name + "_" + i), new ResourceLocation(""), new ItemStack(PANE_STAINED, 8, i), "###", "#C#", "###", '#', PANE, 'C', "dye" + COLORS[i]);
				GameRegistry.addShapedRecipe(new ResourceLocation("planarartifice:pane_redye" + name + "_" + i), new ResourceLocation(""), new ItemStack(PANE_STAINED, 8, i), "###", "#C#", "###", '#', OreUtils.meta(PANE_STAINED, OreDictionary.WILDCARD_VALUE), 'C', "dye" + COLORS[i]);
				GameRegistry.addShapedRecipe(new ResourceLocation("planarartifice:pane_redye2" + name + "_" + i), new ResourceLocation(""), new ItemStack(PANE_STAINED, 8, i), "###", "#C#", "###", '#', PANE_RAINBOW, 'C', "dye" + COLORS[i]);
				GameRegistry.addShapedRecipe(new ResourceLocation("planarartifice:panel_dye" + name + "_" + i), new ResourceLocation(""), new ItemStack(PANEL_STAINED, 8, i), "###", "#C#", "###", '#', PANEL, 'C', "dye" + COLORS[i]);
				GameRegistry.addShapedRecipe(new ResourceLocation("planarartifice:panel_redye" + name + "_" + i), new ResourceLocation(""), new ItemStack(PANEL_STAINED, 8, i), "###", "#C#", "###", '#', OreUtils.meta(PANEL_STAINED, OreDictionary.WILDCARD_VALUE), 'C', "dye" + COLORS[i]);
				GameRegistry.addShapedRecipe(new ResourceLocation("planarartifice:panel_redye2" + name + "_" + i), new ResourceLocation(""), new ItemStack(PANEL_STAINED, 8, i), "###", "#C#", "###", '#', PANEL_RAINBOW, 'C', "dye" + COLORS[i]);
				// panes
				GameRegistry.addShapedRecipe(new ResourceLocation("planarartifice:glass_pane" + name + "_" + i), new ResourceLocation(""), new ItemStack(PANE_STAINED, 16, i), "###", "###", '#', OreUtils.meta(BLOCK_STAINED, i));
				GameRegistry.addShapedRecipe(new ResourceLocation("planarartifice:glass_pane2" + name + "_" + i), new ResourceLocation(""), new ItemStack(PANEL_STAINED, 16, i), "##", "##", "##", '#', OreUtils.meta(BLOCK_STAINED, i));
				GameRegistry.addShapedRecipe(new ResourceLocation("planarartifice:glass_pane3" + name + "_" + i), new ResourceLocation(""), OreUtils.meta(PANEL_STAINED, i), "#", '#', OreUtils.meta(PANE_STAINED, i));
				GameRegistry.addShapedRecipe(new ResourceLocation("planarartifice:glass_pane4" + name + "_" + i), new ResourceLocation(""), OreUtils.meta(PANE_STAINED, i), "#", '#', OreUtils.meta(PANEL_STAINED, i));
			}
		}

		public void craft(Aspect aspect, String research) {
			ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("planarartifice:glass_" + aspect.getTag()), new ShapedArcaneRecipe(PARecipes.defaultGroup, research, 5, null, new ItemStack(BLOCK, 8), "###", "#C#", "###", '#', "blockGlassColorless", 'C', ThaumcraftApiHelper.makeCrystal(aspect)));
			ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("planarartifice:glass_" + aspect.getTag() + "_rainbow"), new ShapedArcaneRecipe(PARecipes.defaultGroup, research, 5, null, new ItemStack(BLOCK_RAINBOW, 8), "###", "#C#", "###", '#', "blockGlassRainbow", 'C', ThaumcraftApiHelper.makeCrystal(aspect)));
			ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("planarartifice:pane_" + aspect.getTag()), new ShapedArcaneRecipe(PARecipes.defaultGroup, research, 5, null, new ItemStack(PANE, 8), "###", "#C#", "###", '#', "paneGlassColorless", 'C', ThaumcraftApiHelper.makeCrystal(aspect)));
			ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("planarartifice:pane_" + aspect.getTag() + "_rainbow"), new ShapedArcaneRecipe(PARecipes.defaultGroup, research, 5, null, new ItemStack(PANE_RAINBOW, 8), "###", "#C#", "###", '#', "paneGlassRainbow", 'C', ThaumcraftApiHelper.makeCrystal(aspect)));
			ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("planarartifice:panel_" + aspect.getTag()), new ShapedArcaneRecipe(PARecipes.defaultGroup, research, 5, null, new ItemStack(PANEL, 8), "###", "#C#", "###", '#', "panelGlassColorless", 'C', ThaumcraftApiHelper.makeCrystal(aspect)));
			ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("planarartifice:panel_" + aspect.getTag() + "_rainbow"), new ShapedArcaneRecipe(PARecipes.defaultGroup, research, 5, null, new ItemStack(PANEL_RAINBOW, 8), "###", "#C#", "###", '#', "panelGlassRainbow", 'C', ThaumcraftApiHelper.makeCrystal(aspect)));
			for (int i = 0; i < 16; i++) {
				ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("planarartifice:glass_" + aspect.getTag() + "_" + i), new ShapedArcaneRecipe(PARecipes.defaultGroup, research, 5, null, new ItemStack(BLOCK_STAINED, 8, i), "###", "#C#", "###", '#', "blockGlass" + COLORS[i], 'C', ThaumcraftApiHelper.makeCrystal(aspect)));
				ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("planarartifice:pane_" + aspect.getTag() + "_" + i), new ShapedArcaneRecipe(PARecipes.defaultGroup, research, 5, null, new ItemStack(PANE_STAINED, 8, i), "###", "#C#", "###", '#', "paneGlass" + COLORS[i], 'C', ThaumcraftApiHelper.makeCrystal(aspect)));
				ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("planarartifice:panel_" + aspect.getTag() + "_" + i), new ShapedArcaneRecipe(PARecipes.defaultGroup, research, 5, null, new ItemStack(PANEL_STAINED, 8, i), "###", "#C#", "###", '#', "panelGlass" + COLORS[i], 'C', ThaumcraftApiHelper.makeCrystal(aspect)));
			}
		}
	}
	public interface BlockFunction { Block run(Block block); }
}
