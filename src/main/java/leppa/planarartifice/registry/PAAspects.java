package leppa.planarartifice.registry;

import leppa.planarartifice.main.PAConfig;
import leppa.planarartifice.main.PlanarArtifice;
import leppa.planarartifice.util.Aspects;
import leppa.planarartifice.util.OreUtils;
import magicbees.integration.thaumcraft.IntegrationThaumcraft;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.items.ItemsTC;

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
		for (Aspect a : Aspect.aspects.values()) Aspects.register(a);
		Aspects.register(DIMENSIONS, TIME, COLOR);

		// repl
		aspectRepl.put("caeles", Aspect.DESIRE);
		aspectRepl.put("draco", Aspect.ELDRITCH);
		aspectRepl.put("exitium", PAAspects.TIME);
		aspectRepl.put("fluctus", Aspect.MOTION);
		aspectRepl.put("imperium", Aspect.TOOL);
		aspectRepl.put("infernum", Aspect.FIRE);
		aspectRepl.put("sonus", Aspect.SENSES);
		aspectRepl.put("ventus", Aspect.FLIGHT);
		aspectRepl.put("visum", Aspect.SENSES);
		aspectRepl.put("diabolus", Aspect.DARKNESS);
		aspectRepl.put("stellae", PAAspects.DIMENSIONS);
		aspectRepl.put("luna", Aspect.SOUL);
		aspectRepl.put("sol", Aspect.DESIRE);
	}

	private static final HashMap<String, Aspect> aspectRepl = new HashMap<>();

	public static void registerIngot(String ore, String ...aspects) { registerIngot(ore, false, aspects); }
	public static void registerIngot(String ore, boolean isGem, String ...aspects) { registerIngot(ore, isGem, false, aspects); }
	public static void registerIngot(String ore, boolean isGem, boolean isFour, String... aspects) {
		if (aspects.length == 0) Aspects.ingotAspects.put(ore, new Aspect[]{isGem ? Aspect.CRYSTAL : Aspect.METAL});
		else Aspects.ingotAspects.put(ore, new Aspects(aspects, aspectRepl).getAspects());
		if (PAConfig.overhaul.disableIngotReaspect) return;
		Aspects aspectBase; String prefix;
		if (isGem) { aspectBase = new Aspects("vitreus", 10);
			if (OreUtils.exists("gem" + ore)) prefix = "gem";
			else if (OreUtils.exists("crystal" + ore)) prefix = "crystal";
			else return;
		}
		else { aspectBase = new Aspects("metallum", 10); prefix = "ingot"; }
		if (!OreUtils.exists(prefix + ore)) return;
		for (String a : aspects) {
			if (Aspects.exists(a)) aspectBase.add(a, 5);
			else if (aspectRepl.containsKey(a)) aspectBase.add(aspectRepl.get(a), 5);
		}
		setOreDict(prefix + ore, aspectBase);
		PlanarArtifice.LOGGER.info("[PA, ORE] Ingot overhaul: " + prefix + ore + ", " + aspectBase);
		registerIngotDerivative("ore", ore, aspectBase, 1.0F/0.75F, new Aspects("terra", 5));
		registerIngotDerivative("cluster", ore, aspectBase, 1.0F/0.75F, new Aspects("terra", 5, "ordo", 5));
		registerIngotDerivative("clusterEldritch", ore, aspectBase, 1.0F/0.75F, new Aspects("terra", 5, "vitium", 10));
		registerIngotDerivative("block", ore, aspectBase, isFour ? 4.0F :9.0F, new Aspects("spatio", 5));
		registerIngotDerivative("nugget", ore, aspectBase, 1.0F/9.0F, null);
		registerIngotDerivative("dust", ore, aspectBase, 1.0F, new Aspects("tempus", "exitium"));
		registerIngotDerivative("dustDirty", ore, aspectBase, 0.8F, new Aspects("tempus", "exitium"));
		registerIngotDerivative("crystal", ore, aspectBase, 1.0F, new Aspects("vitreus", 5, "desiderium", 2));
		registerIngotDerivative("shard", ore, aspectBase, 1.0F, new Aspects("vitreus", 3));
		registerIngotDerivative("clump", ore, aspectBase, 0.8F, new Aspects("spatio"));
		registerIngotDerivative("plate", ore, aspectBase, 1.0F, new Aspects("instrumentum", 2));
		registerIngotDerivative("gear", ore, aspectBase, 4.0F, new Aspects("machina", 5));
		registerIngotDerivative("rod", ore, aspectBase, 2.0F, new Aspects("fabrico", 3));
	}
	public static void registerIngotDerivative(String prefix, String ore, Aspects base, float scale, AspectList extra) {
		if (!OreUtils.exists(prefix + ore)) return;
		setOreDict(prefix + ore, base.copy().multiply(scale).add(extra));
	}


	public static void registerItemAspects() {
		PlanarArtifice.LOGGER.info("[PA, ASPECT] Start registering Aspects");

		add(Items.ENDER_PEARL, new Aspects("spatio", 10, "tempus", 5));
		add(Blocks.ENDER_CHEST, new Aspects("spatio", 12));
		add(PABlocks.flux_scrubber, new Aspects("spatio", 6));
		add(PAItems.dimensional_curiosity, new Aspects("spatio", 25));
		add(PAItems.bismuth_ingot, new Aspects("spatio", 3));
		add(PAItems.dimensional_singularity, new Aspects("spatio", 30, "tempus", 30));
		add(Items.BONE, new Aspects("tinctura", 5));
		add(Items.GLOWSTONE_DUST, new Aspects("tinctura", 5));
		add(Blocks.LAPIS_BLOCK, new Aspects("tinctura", 180));
		add(Blocks.LAPIS_ORE, new Aspects("tinctura", 15));
		add(Blocks.YELLOW_FLOWER, new Aspects("tinctura", 15));
		add(Blocks.RED_FLOWER, new Aspects("tinctura", 15));
		add(OreUtils.meta(Blocks.RED_FLOWER, 1), new Aspects("tinctura", 15));
		add(OreUtils.meta(Blocks.RED_FLOWER, 2), new Aspects("tinctura", 15));
		add(OreUtils.meta(Blocks.RED_FLOWER, 3), new Aspects("tinctura", 15));
		add(OreUtils.meta(Blocks.RED_FLOWER, 4), new Aspects("tinctura", 15));
		add(OreUtils.meta(Blocks.RED_FLOWER, 5), new Aspects("tinctura", 15));
		add(OreUtils.meta(Blocks.RED_FLOWER, 6), new Aspects("tinctura", 15));
		add(OreUtils.meta(Blocks.RED_FLOWER, 7), new Aspects("tinctura", 15));
		add(OreUtils.meta(Blocks.RED_FLOWER, 8), new Aspects("tinctura", 15));
		add(Blocks.CACTUS, new Aspects("tinctura", 15));
		add(Blocks.DOUBLE_PLANT, new Aspects("tinctura", 15));
		add(OreUtils.meta(Blocks.DOUBLE_PLANT, 1), new Aspects("tinctura", 15));
		add(OreUtils.meta(Blocks.DOUBLE_PLANT, 4), new Aspects("tinctura", 15));
		add(OreUtils.meta(Blocks.DOUBLE_PLANT, 5), new Aspects("tinctura", 15));
		add(Blocks.GLOWSTONE, new Aspects("tinctura", 15));

		add(Blocks.BLACK_GLAZED_TERRACOTTA, new Aspects("tinctura", 15));
		add(Blocks.WHITE_GLAZED_TERRACOTTA, new Aspects("tinctura", 15));
		add(Blocks.YELLOW_GLAZED_TERRACOTTA, new Aspects("tinctura", 15));
		add(Blocks.BLUE_GLAZED_TERRACOTTA, new Aspects("tinctura", 15));
		add(Blocks.BROWN_GLAZED_TERRACOTTA, new Aspects("tinctura", 15));
		add(Blocks.RED_GLAZED_TERRACOTTA, new Aspects("tinctura", 15));
		add(Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA, new Aspects("tinctura", 15));
		add(Blocks.GRAY_GLAZED_TERRACOTTA, new Aspects("tinctura", 15));
		add(Blocks.CYAN_GLAZED_TERRACOTTA, new Aspects("tinctura", 15));
		add(Blocks.GREEN_GLAZED_TERRACOTTA, new Aspects("tinctura", 15));
		add(Blocks.LIME_GLAZED_TERRACOTTA, new Aspects("tinctura", 15));
		add(Blocks.MAGENTA_GLAZED_TERRACOTTA, new Aspects("tinctura", 15));
		add(Blocks.ORANGE_GLAZED_TERRACOTTA, new Aspects("tinctura", 15));
		add(Blocks.PINK_GLAZED_TERRACOTTA, new Aspects("tinctura", 15));
		add(Blocks.PURPLE_GLAZED_TERRACOTTA, new Aspects("tinctura", 15));
		add(Blocks.SILVER_GLAZED_TERRACOTTA, new Aspects("tinctura", 15));

		for (int i = 0; i < 16; i++) {
			add(OreUtils.meta(Blocks.STAINED_HARDENED_CLAY, i), new Aspects("tinctura", 10));
			add(OreUtils.meta(Blocks.WOOL, i), new Aspects("tinctura", 5));
			add(OreUtils.meta(Blocks.CONCRETE_POWDER, i), new Aspects("tinctura", 5));
			add(OreUtils.meta(Blocks.CONCRETE, i), new Aspects("tinctura", 5));
			add(OreUtils.meta(Items.DYE, i), new Aspects("tinctura", 20));
			add(OreUtils.meta(Blocks.STAINED_GLASS, i), new Aspects("tinctura", 15));
		}

		set(PAItems.condensed_crystal_cluster, Aspects.getPrimals().multiply(7));
		addOreDict("sand", new Aspects("tempus", 1));
		addByRegex(".*hour.*", new Aspects("tempus", 5));
		addByRegex("old.*", new Aspects("tempus", 10));
		addByRegex(".*ancient.*", new Aspects("tempus", 5));
		addByRegex(".*futur.*", new Aspects("tempus", 5));
		addByRegex(".*time.*", new Aspects("tempus", 4));
		addByRegex(".*spatial.*", new Aspects("spatio", 8));
		addByRegex(".*compass.*", new Aspects("spatio", 6));
		addByRegex(".*dimens.*", new Aspects("spatio", 4));
		addByRegex(".*chest(?!nut)(?!plate).*", new Aspects("spatio", 6));
		addByRegex(".*map(?!le).*", new Aspects("spatio", 5));
		addByRegex(".*clock.*", new Aspects("tempus", 10));
		addByRegex(".*temporal.*", new Aspects("tempus", 10));
		addByRegex(".*portal.*", new Aspects("spatio", 16));
		addByRegex(".*color.*", new Aspects("tinctura", 15));
		addByRegex(".*chroma.*", new Aspects("tinctura", 15));
		addByRegex(".*mossy.*", new Aspects("tempus", 2));
		add(OreUtils.meta(Blocks.STONEBRICK, 1), new Aspects("tempus", 2));
		addByRegex(".*cracked.*", new Aspects("tempus", 2));
		add(OreUtils.meta(Blocks.STONEBRICK, 2), new Aspects("tempus", 2));

		add(Items.ARMOR_STAND, new Aspects("praemunio", 5, "imperium", 5));
		add(Blocks.COMMAND_BLOCK, new Aspects("machina", 25, "praecantatio", 25).add("caeles", 25, "desiderium").add("imperium", 25, "instrumentum"));
		add(Blocks.STRUCTURE_BLOCK, new Aspects("machina", 25, "praecantatio", 25, "spatio", 25).add("caeles", 25, "desiderium"));
		add(Blocks.CHAIN_COMMAND_BLOCK, get(Blocks.COMMAND_BLOCK));
		add(Blocks.REPEATING_COMMAND_BLOCK, get(Blocks.COMMAND_BLOCK));
		add(Items.COMMAND_BLOCK_MINECART, get(Blocks.COMMAND_BLOCK).add(get(Items.MINECART)).multiply(0.75F));
		add(Items.KNOWLEDGE_BOOK, get(Items.BOOK).add("cognitio", 25).add("sensus", 25));
		add(Items.WRITTEN_BOOK, get(Items.WRITABLE_BOOK).add("tinctura", 10).add("tempus", 5));
		add(Blocks.BARRIER, new Aspects("auram", 25, "vacuos", 25, "spiritus", 25, "spatio", 25));
		add(Blocks.STRUCTURE_VOID, new Aspects("spatio", 25, "vacuos", 25, "praecantatio", 25, "imperium", 25));
		set(Items.ITEM_FRAME, new Aspects("fabrico", 5, "sensus", 5, "herba", 5));
		set(Items.PAINTING, new Aspects("fabrico", 5, "sensus", 10, "tinctura", 10));
		addByRegex(".*sign", new Aspects("sensus", 4));
		add(Blocks.HOPPER, new Aspects("spatio", 4));
		add(Blocks.GRAVEL, new Aspects("tempus", 1));

		PlanarArtifice.LOGGER.info("[PA, ASPECT] Start registering Ingot Overhauls");

		registerIngot("Iron");
		registerIngot("Gold", "desiderium", "sol");
		registerIngot("Quartz", true, true);
		registerIngot("Lapis", true, "tinctura", "tinctura", "tinctura", "tinctura", "sensus", "desiderium");
		registerIngot("Diamond", true, "desiderium", "desiderium");
		registerIngot("Emerald", true, "desiderium", "humanus");
		registerIngot("Ruby", true, "desiderium", "exanimis");
		registerIngot("Sapphire", true, "desiderium", "cognitio");
		registerIngot("Amber", true, true, "vinculum", "vinculum");
		registerIngot("Apatite", true, "herba");
		registerIngot("Amethyst", true, "alkimia", "victus");
		registerIngot("Garnet", true, "praemunio", "stellae");
		registerIngot("Opal", true, "praecantatio", "luna");
		registerIngot("Copper", "permutatio");
		registerIngot("Tin", "vitreus");
		registerIngot("Silver", "desiderium", "luna");
		registerIngot("Lead", "ordo");
		registerIngot("Aluminum", "aer");
		registerIngot("Nickel", "fabrico");
		registerIngot("Bronze", "instrumentum");
		registerIngot("Copper", "permutatio");
		registerIngot("AstralStarmetal", "stellae");
		registerIngot("Manasteel", "praecantatio");
		registerIngot("ElvenElementium", "alienis");
		registerIngot("Dawnstone", "machina", "ignis");
		registerIngot("Draconium", "draco");
		registerIngot("Netherite", "infernum");
		registerIngot("Uranium","potentia", "mortuus");
		registerIngot("Alkimium", "alkimia");
		registerIngot("Bismuth","tinctura");
		registerIngot("Cobalt", "tempus");
		registerIngot("Ardite", "spatio");
		registerIngot("Osmium", "ventus");
		registerIngot("ColdIron", "gelum");
		registerIngot("Antimony", "vacuos");
		registerIngot("Iridium", "motus", "machina");
		registerIngot("Platinum", "caeles", "desiderium");
		registerIngot("Mithril", "praecantatio");
		registerIngot("Titanium", "praemunio");
		registerIngot("Tungsten", "aversio");
		registerIngot("Endorium", "motus", "alienis");
		registerIngot("Ambrosium", true, "lux", "ordo");
		registerIngot("Zanite", true, "motus", "metallum");
		registerIngot("CertusQuartz", true, true, "tempus");
		registerIngot("RadiantQuartzRaw", true, "praecantatio", "lux");
		if (PAConfig.overhaul.disableAlloyReaspect) return;
		registerIngot("Steel", "instrumentum", "ordo");
		registerIngot("Electrum","potentia", "desiderium");
		registerIngot("DraconiumAwakened", "draco", "caeles");
		registerIngot("Unstable", "exitium");
		registerIngot("DemonicMetal", "diabolus", "infernum");
		registerIngot("EnchantedMetal", "praecantatio", "vitreus");
		registerIngot("Shadowium", "tenebrae");
		registerIngot("Photonium", "lux");
		registerIngot("Manyullyn", "diabolus");
		registerIngot("Constantan", "sonus");
		registerIngot("Signalum", "fluctus", "desiderium");
		registerIngot("Lumium", "lux", "desiderium");
		registerIngot("Alubrass", "visum");
		registerIngot("Knightslime", "aversio", "praemunio");
		registerIngot("Pigiron", "bestia");
		registerIngot("Valkyrie", "aversio", "aer");
		registerIngot("QuartzEnrichedIron", "vitreus");
		registerIngot("Fluix", true, true, "tempus", "spatio");
		registerIngot("RadiantQuartzShaped", true, "praecantatio", "lux", "spatio");
	}

	public static void registerEntityAspects() {
		PlanarArtifice.LOGGER.info("[PA, ASPECT] Start registering Entity Aspects");
		// only EntityLiving can be sacrificed
		remove("chicken", new Aspects("volatus", 999));
		add("creeper", new Aspects("exitium", 10));
		set("ender_dragon", get("enderman").multiply(2).add("draco", 50));
		add("llama", new Aspects("spatio", 5));
		add("polar_bear", new Aspects("victus", 10));
		set("rabbit", new Aspects("motus", 5, "bestia", 5, "tempus", 5));
		// alt forms
		set("elder_guardian", get("guardian").multiply(1.5F));
		set("zombie_villager", get("zombie").add("humanus", 5));
		// cow
		add("cow", new Aspects("victus", 5));
		set("mooshroom", get("cow").add("tenebrae", 4).add("herba", 15));
		// tinctura
		add("sheep", new Aspects("tinctura", 5));
		add("squid", new Aspects("tinctura", 5));
		// horse
		set("donkey", new Aspects("herba", 15, "terra", 5, "bestia", 15));
		set("mule", new Aspects("spatio", 15, "terra", 5, "bestia", 15));
		set("horse", new Aspects("motus", 15, "terra", 5, "bestia", 15));
		// infernum for the netherdwellers
		add("blaze", new Aspects("infernum", 5));
		add("ghast", new Aspects("spiritus", 10, "infernum", 5));
		add("magma_cube", new Aspects("infernum", 5, "alienis", 10));
		add("zombie_pigman", new Aspects("infernum", 5));
		// you're literally thaumcraft??? why don't you have aspects???
		set("thaumcraft:brainyzombie", getRaw("Thaumcraft.BrainyZombie"));
		set("thaumcraft:giantbrainyzombie", getRaw("Thaumcraft.GiantBrainyZombie"));
		set("thaumcraft:cultistcleric", get(OreUtils.meta(ItemsTC.curio, 6)).add("auram", 10));
		set("thaumcraft:cultistknight", get(OreUtils.meta(ItemsTC.curio, 6)).add("aversio", 10));
		set("thaumcraft:cultistleader", get(OreUtils.meta(ItemsTC.curio, 6)).add("praemunio", 10).multiply(1.25F));
		set("thaumcraft:cultistportalgreater", get(OreUtils.meta(ItemsTC.curio, 6)).add("spatio", 16).multiply(2F));
		set("thaumcraft:cultistportallesser", get(OreUtils.meta(ItemsTC.curio, 6)).add("spatio", 16).multiply(1.5F));
		set("thaumcraft:eldritchcrab", new Aspects("praemunio", 10, "alienis", 10, "bestia", 10));
		set("thaumcraft:eldritchgolem", new Aspects("praemunio", 25, "alienis", 25, "vitium", 25, "machina", 25, "praecantatio", 25));
		set("thaumcraft:eldritchguardian", new Aspects("spiritus", 25, "alienis", 25, "vitium", 25, "cognitio", 25, "praecantatio", 25));
		set("thaumcraft:eldritchwarden", new Aspects("tenebrae", 25, "alienis", 25, "vitium", 25, "exanimis", 25, "praecantatio", 25));
		set("thaumcraft:firebat", getRaw("Thaumcraft.Firebat").add("infernum", 10));
		// flux rift is made of pure impetus, no essentia is registered
		set("thaumcraft:inhabitedzombie", get("zombie").add("vitium", 15).add("alienis", 10).add("praecantatio", 10).add("praemunio", 10));
		set("thaumcraft:mindspider", get("spider").add("spiritus", 5).add("cognitio", 10).add("praecantatio", 5).add("alienis", 5));
		set("thaumcraft:pech", getRaw("Thaumcraft.Pech", new ThaumcraftApi.EntityTagsNBT("PechType", 0)), new ThaumcraftApi.EntityTagsNBT("PechType", 0));
		set("thaumcraft:pech", getRaw("Thaumcraft.Pech", new ThaumcraftApi.EntityTagsNBT("PechType", 1)), new ThaumcraftApi.EntityTagsNBT("PechType", 1));
		set("thaumcraft:pech", getRaw("Thaumcraft.Pech", new ThaumcraftApi.EntityTagsNBT("PechType", 2)), new ThaumcraftApi.EntityTagsNBT("PechType", 2));
		set("thaumcraft:taintacle", new Aspects("vitium", 25, "alienis", 10, "fluctus", 10));
		set("thaumcraft:taintaclegiant", get("thaumcraft:taintacle").multiply(2F));
		set("thaumcraft:taintacletiny", get("thaumcraft:taintacle").multiply(0.5F));
		set("thaumcraft:taintcrawler", get("silverfish").add("vitium", 5));
		set("thaumcraft:taintseed", new Aspects("vitium", 25, "alienis", 10, "praecantatio", 5));
		set("thaumcraft:taintseedprime", get("thaumcraft:taintseed").multiply(2F));
		set("thaumcraft:thaumslime", getRaw("Thaumcraft.ThaumSlime"));
		set("thaumcraft:taintswarm", getRaw("Thaumcraft.TaintSwarm"));
		set("thaumcraft:golem", getRaw("Thaumcraft.Golem").add("imperium", 10));
		for (Aspect aspect : Aspect.aspects.values())
			set("thaumcraft:wisp", new Aspects("sol", 5, "auram", 5, "volatus", 5).add(aspect, 2), new ThaumcraftApi.EntityTagsNBT("Type", aspect.getTag()));
		// ITEM->ENTITY
		set("armor_stand", get(Items.ARMOR_STAND));
		set("boat", get(Items.BOAT));
		set("ender_crystal", get(Items.END_CRYSTAL));
		set("fireball", get(Items.FIRE_CHARGE));
		set("fireworks_rocket", get(Items.FIREWORKS));
		set("item_frame", get(Items.ITEM_FRAME));
		set("painting", get(Items.PAINTING));
		set("tnt", get(Blocks.TNT));
		set("evocation_fangs", new Aspects("bestia", 5, "praecantatio", 5));
		set("thaumcraft:taintswarm", new Aspects("vitium", 5, "praecantatio", 5));
		set("thaumcraft:turretbasic", get(ItemsTC.turretPlacer));
		set("thaumcraft:turretadvanced", get(OreUtils.meta(ItemsTC.turretPlacer, 1)));
		set("thaumcraft:arcanebore", get(OreUtils.meta(ItemsTC.turretPlacer, 2)));
		// MINECARTS
		set("minecart", get(Items.MINECART));
		set("commandblock_minecart", get(Items.COMMAND_BLOCK_MINECART));
		set("tnt_minecart", get(Items.TNT_MINECART));
		set("chest_minecart", get(Items.CHEST_MINECART));
		set("furnace_minecart", get(Items.FURNACE_MINECART));
		set("hopper_minecart", get(Items.HOPPER_MINECART));
		set("spawner_minecart", get(Blocks.MOB_SPAWNER).multiply(0.75F).add("metallum", 42));
		// ITEM->ENTITY, PROJECTILE
		set("shulker_bullet", new Aspects("volatus", 5, "alienis", 5));
		set("llama_spit", new Aspects("aqua", 5, "aversio", 5));
		// add("lightning_bolt", new Aspects("potentia", 25, "lux", 10).add("exitium", 10, "perditio"));
		set("fireball", get(Items.FIRE_CHARGE));
		set("small_fireball", get(Items.FIRE_CHARGE).multiply(0.5F));
		set("dragon_fireball", get(Items.FIRE_CHARGE).add("draco", 25).add("tenebrae", 10));
		set("egg", get(Items.EGG));
		set("snowball", get(Items.SNOWBALL));
		set("ender_pearl", get(Items.ENDER_PEARL));
		set("eye_of_ender_signal", get(Items.ENDER_EYE));
		set("arrow", get(Items.ARROW));
		set("spectral_arrow", get(Items.SPECTRAL_ARROW));
		set("wither_skull", get(OreUtils.meta(Blocks.SKULL, 1)));
		set("xp_bottle", get(Items.EXPERIENCE_BOTTLE));
		set("thaumcraft:alumentum", get(ItemsTC.alumentum));
		set("thaumcraft:bottletaint", get(ItemsTC.bottleTaint));
		set("thaumcraft:causalitycollapser", get(ItemsTC.causalityCollapser));
		set("thaumcraft:eldritchorb", new Aspects("vitium", 5, "tenebrae", 5));
	}
}