package leppa.planarartifice.registry;

import com.bewitchment.common.integration.thaumcraft.ThaumcraftCompat;
import com.zeitheron.thaumicadditions.init.KnowledgeTAR;
import leppa.planarartifice.main.PAConfig;
import leppa.planarartifice.main.PlanarArtifice;
import magicbees.integration.thaumcraft.IntegrationThaumcraft;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

import java.awt.*;
import java.util.HashMap;

import static leppa.planarartifice.util.AspectUtils.*;

public class PAAspects {

	public static Aspect DIMENSIONS;
	public static Aspect TIME;
	public static Aspect COLOR;

	public static void registerAspects() {
		DIMENSIONS = new Aspect("spatio", 0x4AF755, new Aspect[]{Aspect.VOID, Aspect.ENTROPY}, new ResourceLocation(PlanarArtifice.MODID, "textures/aspects/spatio.png"), 1);
		if (Loader.isModLoaded("magicbees") && IntegrationThaumcraft.ASPECT_TIME != null) TIME = IntegrationThaumcraft.ASPECT_TIME;
		else TIME = new Aspect("tempus", 0xD6DB43, new Aspect[]{DIMENSIONS, Aspect.EXCHANGE}, new ResourceLocation(PlanarArtifice.MODID, "textures/aspects/tempus.png"), 1);
		COLOR = new Aspect("tinctura", 0xFFFFFF, new Aspect[]{Aspect.EXCHANGE, Aspect.SENSES}, new ResourceLocation(PlanarArtifice.MODID, "textures/aspects/tinctura.png"), 1) {
			@Override
			public int getColor() {
				return Color.HSBtoRGB(MinecraftServer.getCurrentTimeMillis() * 2 % 3600 / 3600F, 0.6F, 0.8F); // less saturation please
			}
		};
		if (Loader.isModLoaded("thaumadditions")) {
			TARAspects.put("CAELES", KnowledgeTAR.CAELES);
			TARAspects.put("DRACO", KnowledgeTAR.DRACO);
			TARAspects.put("EXITIUM", KnowledgeTAR.EXITIUM);
			TARAspects.put("FLUCTUS", KnowledgeTAR.FLUCTUS);
			TARAspects.put("IMPERIUM", KnowledgeTAR.IMPERIUM);
			TARAspects.put("INFERNUM", KnowledgeTAR.INFERNUM);
			TARAspects.put("SONUS", KnowledgeTAR.SONUS);
			TARAspects.put("VENTUS", KnowledgeTAR.VENTUS);
			TARAspects.put("VISUM", KnowledgeTAR.VISUM);
		} else {
			TARAspects.put("CAELES", Aspect.DESIRE);
			TARAspects.put("DRACO", Aspect.ELDRITCH);
			TARAspects.put("EXITIUM", PAAspects.TIME);
			TARAspects.put("FLUCTUS", Aspect.MOTION);
			TARAspects.put("IMPERIUM", Aspect.TOOL);
			TARAspects.put("INFERNUM", Aspect.FIRE);
			TARAspects.put("SONUS", Aspect.SENSES);
			TARAspects.put("VENTUS", Aspect.FLIGHT);
			TARAspects.put("VISUM", Aspect.SENSES);
		}
		if (Loader.isModLoaded("bewitchment")) {
			BewitchmentAspects.put("DEMON", ThaumcraftCompat.DEMON);
			BewitchmentAspects.put("STAR", ThaumcraftCompat.STAR);
			BewitchmentAspects.put("MOON", ThaumcraftCompat.MOON);
			BewitchmentAspects.put("SUN", ThaumcraftCompat.SUN);
		} else {
			BewitchmentAspects.put("DEMON", Aspect.DARKNESS);
			BewitchmentAspects.put("STAR", PAAspects.DIMENSIONS);
			BewitchmentAspects.put("MOON", Aspect.SOUL);
			BewitchmentAspects.put("SUN", Aspect.DESIRE);
		}
	}

	private static final HashMap<String, Aspect> TARAspects = new HashMap<>();
	private static final HashMap<String, Aspect> BewitchmentAspects = new HashMap<>();

	public static void registerIngot(String ore, Aspect[] aspects) { registerIngot(ore, aspects, false); }
	public static void registerIngot(String ore, Aspect[] aspects, boolean isGem) {
		if (PAConfig.overhaul.disableIngotReaspect) return;
		AspectList aspectBase = new AspectList();
		String prefix = "ingot";
		if (isGem) {
			aspectBase.add(Aspect.CRYSTAL, 10);
			prefix = "gem";
		}
		else aspectBase.add(Aspect.METAL, 10);
		for (Aspect a : aspects) aspectBase.add(a, 5);
		if (!OreDictionary.doesOreNameExist(prefix + ore)) return;
		setOreDict(prefix + ore, aspectBase);
		PlanarArtifice.LOGGER.info("Ingot overhaul: " + ore);
		registerIngotDerivative("block", ore, aspectBase, 9.0F, new AspectList().add(PAAspects.DIMENSIONS, 5));
		registerIngotDerivative("nugget", ore, aspectBase, 1.0F/9.0F, null);
		registerIngotDerivative("dust", ore, aspectBase, 1.0F, new AspectList().add(PAAspects.TIME, 1).add(TARAspects.get("EXITIUM"), 1));
		registerIngotDerivative("dustDirty", ore, aspectBase, 0.8F, new AspectList().add(PAAspects.TIME, 1).add(TARAspects.get("EXITIUM"), 1));
		registerIngotDerivative("crystal", ore, aspectBase, 1.0F, new AspectList().add(Aspect.CRYSTAL, 5).add(Aspect.DESIRE, 2));
		registerIngotDerivative("shard", ore, aspectBase, 1.0F, new AspectList().add(Aspect.CRYSTAL, 3));
		registerIngotDerivative("clump", ore, aspectBase, 0.8F, new AspectList().add(PAAspects.DIMENSIONS, 1));
		registerIngotDerivative("plate", ore, aspectBase, 1.0F, new AspectList().add(Aspect.TOOL, 2));
		registerIngotDerivative("gear", ore, aspectBase, 4.0F, new AspectList().add(Aspect.MECHANISM, 5));
		registerIngotDerivative("rod", ore, aspectBase, 2.0F, new AspectList().add(Aspect.CRAFT, 3));
	}
	public static void registerIngotDerivative(String prefix, String ore, AspectList base, float scale, AspectList extra) {
		if (!OreDictionary.doesOreNameExist(prefix + ore)) return;
		AspectList list = new AspectList();
		for (Aspect a : base.getAspects()) list.add(a, (int)Math.floor(base.getAmount(a) * scale * 0.75)); // base scale
		if (extra != null) list.add(extra);
		setOreDict(prefix + ore, list);
	}


	public static void registerItemAspects() {
		add(new ItemStack(Items.CLOCK), new AspectList().add(PAAspects.TIME, 10));
		add(new ItemStack(Items.ENDER_PEARL), new AspectList().add(PAAspects.DIMENSIONS, 10).add(PAAspects.TIME, 5));
		add(new ItemStack(Blocks.ENDER_CHEST), new AspectList().add(PAAspects.DIMENSIONS, 12));
		add(new ItemStack(PABlocks.flux_scrubber), new AspectList().add(PAAspects.DIMENSIONS, 6));
		add(new ItemStack(PAItems.dimensional_curiosity), new AspectList().add(PAAspects.DIMENSIONS, 25));
		add(new ItemStack(PAItems.bismuth_ingot), new AspectList().add(PAAspects.DIMENSIONS, 3));
		add(new ItemStack(PAItems.dimensional_singularity), new AspectList().add(PAAspects.DIMENSIONS, 30).add(PAAspects.TIME, 30));
		add(new ItemStack(Items.BONE), new AspectList().add(PAAspects.COLOR, 5));
		add(new ItemStack(Items.GLOWSTONE_DUST), new AspectList().add(PAAspects.COLOR, 5));
		add(new ItemStack(Blocks.LAPIS_BLOCK), new AspectList().add(PAAspects.COLOR, 180));
		add(new ItemStack(Blocks.LAPIS_ORE), new AspectList().add(PAAspects.COLOR, 15));
		add(new ItemStack(Blocks.RED_FLOWER), new AspectList().add(PAAspects.COLOR, 15));
		add(new ItemStack(Blocks.YELLOW_FLOWER), new AspectList().add(PAAspects.COLOR, 15));
		add(new ItemStack(Blocks.CACTUS), new AspectList().add(PAAspects.COLOR, 15));
		add(new ItemStack(Blocks.DOUBLE_PLANT), new AspectList().add(PAAspects.COLOR, 15));
		add(new ItemStack(Blocks.GLOWSTONE), new AspectList().add(PAAspects.COLOR, 15));

		add(new ItemStack(Blocks.BLACK_GLAZED_TERRACOTTA), new AspectList().add(PAAspects.COLOR, 15));
		add(new ItemStack(Blocks.WHITE_GLAZED_TERRACOTTA), new AspectList().add(PAAspects.COLOR, 15));
		add(new ItemStack(Blocks.YELLOW_GLAZED_TERRACOTTA), new AspectList().add(PAAspects.COLOR, 15));
		add(new ItemStack(Blocks.BLUE_GLAZED_TERRACOTTA), new AspectList().add(PAAspects.COLOR, 15));
		add(new ItemStack(Blocks.BROWN_GLAZED_TERRACOTTA), new AspectList().add(PAAspects.COLOR, 15));
		add(new ItemStack(Blocks.RED_GLAZED_TERRACOTTA), new AspectList().add(PAAspects.COLOR, 15));
		add(new ItemStack(Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA), new AspectList().add(PAAspects.COLOR, 15));
		add(new ItemStack(Blocks.GRAY_GLAZED_TERRACOTTA), new AspectList().add(PAAspects.COLOR, 15));
		add(new ItemStack(Blocks.CYAN_GLAZED_TERRACOTTA), new AspectList().add(PAAspects.COLOR, 15));
		add(new ItemStack(Blocks.GREEN_GLAZED_TERRACOTTA), new AspectList().add(PAAspects.COLOR, 15));
		add(new ItemStack(Blocks.LIME_GLAZED_TERRACOTTA), new AspectList().add(PAAspects.COLOR, 15));
		add(new ItemStack(Blocks.MAGENTA_GLAZED_TERRACOTTA), new AspectList().add(PAAspects.COLOR, 15));
		add(new ItemStack(Blocks.ORANGE_GLAZED_TERRACOTTA), new AspectList().add(PAAspects.COLOR, 15));
		add(new ItemStack(Blocks.PINK_GLAZED_TERRACOTTA), new AspectList().add(PAAspects.COLOR, 15));
		add(new ItemStack(Blocks.PURPLE_GLAZED_TERRACOTTA), new AspectList().add(PAAspects.COLOR, 15));
		add(new ItemStack(Blocks.SILVER_GLAZED_TERRACOTTA), new AspectList().add(PAAspects.COLOR, 15));

		for (int i = 0; i < 16; i++) {
			add(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, i), new AspectList().add(PAAspects.COLOR, 10));
			add(new ItemStack(Blocks.WOOL, 1, i), new AspectList().add(PAAspects.COLOR, 5));
			add(new ItemStack(Blocks.CONCRETE_POWDER, 1, i), new AspectList().add(PAAspects.COLOR, 5));
			add(new ItemStack(Blocks.CONCRETE, 1, i), new AspectList().add(PAAspects.COLOR, 5));
			add(new ItemStack(Items.DYE, 1, i), new AspectList().add(PAAspects.COLOR, 20));
			add(new ItemStack(Blocks.STAINED_GLASS, 1, i), new AspectList().add(PAAspects.COLOR, 15));
		}

		set(new ItemStack(PAItems.condensed_crystal_cluster), new AspectList().add(Aspect.FIRE, 7).add(Aspect.AIR, 7).add(Aspect.EARTH, 7).add(Aspect.ORDER, 7).add(Aspect.ENTROPY, 7).add(Aspect.MAGIC, 7).add(Aspect.WATER, 7));
		addOreDict("sand", new AspectList().add(PAAspects.TIME, 1));
		addByRegex(".*hour.*", new AspectList().add(PAAspects.TIME, 5));
		addByRegex(".*spatial.*", new AspectList().add(PAAspects.DIMENSIONS, 8));
		addByRegex(".*dimens.*", new AspectList().add(PAAspects.DIMENSIONS, 4));

		registerIngot("Iron", new Aspect[]{});
		registerIngot("Gold", new Aspect[]{Aspect.DESIRE, BewitchmentAspects.get("SUN")});
		registerIngot("Diamond", new Aspect[]{Aspect.DESIRE, Aspect.DESIRE}, true);
		registerIngot("Emerald", new Aspect[]{Aspect.DESIRE, Aspect.MAN}, true);
		registerIngot("Ruby", new Aspect[]{Aspect.DESIRE, Aspect.UNDEAD}, true);
		registerIngot("Sapphire", new Aspect[]{Aspect.DESIRE, Aspect.MIND}, true);
		registerIngot("Amber", new Aspect[]{Aspect.TRAP, Aspect.TRAP}, true);
		registerIngot("Apatite", new Aspect[]{Aspect.PLANT}, true);
		registerIngot("Amethyst", new Aspect[]{Aspect.ALCHEMY, Aspect.LIFE}, true);
		registerIngot("Garnet", new Aspect[]{Aspect.PROTECT, BewitchmentAspects.get("STAR")}, true);
		registerIngot("Opal", new Aspect[]{Aspect.MAGIC, BewitchmentAspects.get("MOON")}, true);
		registerIngot("Copper", new Aspect[]{Aspect.EXCHANGE});
		registerIngot("Tin", new Aspect[]{Aspect.CRYSTAL});
		registerIngot("Silver", new Aspect[]{Aspect.DESIRE, BewitchmentAspects.get("MOON")});
		registerIngot("Lead", new Aspect[]{Aspect.ORDER});
		registerIngot("Aluminum", new Aspect[]{Aspect.AIR});
		registerIngot("Nickel", new Aspect[]{Aspect.CRAFT});;
		registerIngot("Bronze", new Aspect[]{Aspect.TOOL});
		registerIngot("Copper", new Aspect[]{Aspect.EXCHANGE});
		registerIngot("AstralStarmetal", new Aspect[]{BewitchmentAspects.get("STAR")});
		registerIngot("Manasteel", new Aspect[]{Aspect.MAGIC});
		registerIngot("ElvenElementium", new Aspect[]{Aspect.ELDRITCH});
		registerIngot("Dawnstone", new Aspect[]{Aspect.MECHANISM, Aspect.FIRE});
		registerIngot("Draconium", new Aspect[]{TARAspects.get("DRACO")});
		registerIngot("Netherite", new Aspect[]{TARAspects.get("INFERNUM")});
		registerIngot("Uranium", new Aspect[]{Aspect.ENERGY, Aspect.DEATH});
		registerIngot("Alkimium", new Aspect[]{Aspect.ALCHEMY});
		registerIngot("Bismuth", new Aspect[]{PAAspects.COLOR});
		registerIngot("Cobalt", new Aspect[]{PAAspects.TIME});
		registerIngot("Ardite", new Aspect[]{PAAspects.DIMENSIONS});
		registerIngot("Osmium", new Aspect[]{TARAspects.get("VENTUS")});
		registerIngot("ColdIron", new Aspect[]{Aspect.COLD});
		registerIngot("Antimony", new Aspect[]{Aspect.VOID});
		registerIngot("Iridium", new Aspect[]{Aspect.MOTION, Aspect.MECHANISM});
		registerIngot("Platinum", new Aspect[]{TARAspects.get("CAELES"), Aspect.DESIRE});
		registerIngot("Mithril", new Aspect[]{Aspect.MAGIC});
		registerIngot("Titanium", new Aspect[]{Aspect.PROTECT});
		registerIngot("Tungsten", new Aspect[]{Aspect.AVERSION});
		registerIngot("Endorium", new Aspect[]{Aspect.MOTION, Aspect.ELDRITCH});

		if (!PAConfig.overhaul.disableAlloyReaspect) {
			registerIngot("Steel", new Aspect[]{Aspect.TOOL, Aspect.ORDER});
			registerIngot("Electrum", new Aspect[]{Aspect.ENERGY, Aspect.DESIRE});
			registerIngot("DraconiumAwakened", new Aspect[]{TARAspects.get("DRACO"), TARAspects.get("CAELES")});
			registerIngot("Unstable", new Aspect[]{TARAspects.get("EXITIUM")});
			registerIngot("DemonicMetal", new Aspect[]{BewitchmentAspects.get("DEMON"), TARAspects.get("INFERNUM")});
			registerIngot("EnchantedMetal", new Aspect[]{Aspect.MAGIC, Aspect.CRYSTAL});
			registerIngot("Shadowium", new Aspect[]{Aspect.DARKNESS});
			registerIngot("Photonium", new Aspect[]{Aspect.LIGHT});
			registerIngot("Manyullyn", new Aspect[]{BewitchmentAspects.get("DEMON")});
			registerIngot("Constantan", new Aspect[]{TARAspects.get("SONUS")});
			registerIngot("Signalum", new Aspect[]{TARAspects.get("FLUCTUS"), Aspect.DESIRE});
			registerIngot("Lumium", new Aspect[]{Aspect.LIGHT, Aspect.DESIRE});
			registerIngot("Alubrass", new Aspect[]{TARAspects.get("VISUM")});
			registerIngot("Knightslime", new Aspect[]{Aspect.AVERSION, Aspect.PROTECT});
			registerIngot("Pigiron", new Aspect[]{Aspect.BEAST});
			registerIngot("Valkyrie", new Aspect[]{Aspect.AVERSION, Aspect.AIR});
		}
	}
}