package leppa.planarartifice.compat.aether;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.items.ItemsAether;
import leppa.planarartifice.compat.PACompatHandler;
import leppa.planarartifice.util.Aspects;
import leppa.planarartifice.util.OreUtils;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.items.ItemsTC;

import static leppa.planarartifice.util.AspectUtils.*;

public class AetherHandler implements PACompatHandler.ICompatModule {
    @Override
    public void registerAspects() {
        // register items
        set(BlocksAether.aether_grass, get(Blocks.GRASS).add("aer", 2));
        set(BlocksAether.enchanted_aether_grass, get(Blocks.GRASS).add("praecantatio", 5).add("aer", 5));
        set(BlocksAether.aether_dirt, get(Blocks.DIRT).add("aer", 2));
        set(BlocksAether.holystone, get(Blocks.STONE).add("aer", 2).add("ordo", 1));
        add(BlocksAether.mossy_holystone, get(Blocks.MOSSY_COBBLESTONE).add("aer", 2).add("ordo", 1));
        add(BlocksAether.aercloud, new Aspects("gelum", 5, "perditio", 5, "aer", 5));
        add(OreUtils.meta(BlocksAether.aercloud, 1), new Aspects("motus", 5, "perditio", 5, "aer", 5));
        add(OreUtils.meta(BlocksAether.aercloud, 2), new Aspects("desiderium", 5, "perditio", 5, "aer", 5));
        add(OreUtils.meta(BlocksAether.aercloud, 3), new Aspects("victus", 5, "perditio", 5, "aer", 5));
        add(BlocksAether.quicksoil, get(Blocks.SAND).add("motus", 5));
        add(BlocksAether.icestone, get(Blocks.GLOWSTONE).remove("lux", 999).add("gelum", 30));
        add(BlocksAether.aether_leaves, new Aspects("aer", 1));
        add(OreUtils.meta(BlocksAether.aether_leaves, 1), new Aspects("desiderium", 1));
        add(BlocksAether.aether_log, new Aspects("aer", 5));
        add(OreUtils.meta(BlocksAether.aether_log, 1), new Aspects("desiderium", 5));
        add(BlocksAether.quicksoil_glass, new Aspects("motus", 5));
        set(BlocksAether.aerogel, new Aspects("aer", 5, "gelum", 5, "ordo", 1));
        set(BlocksAether.gravitite_ore, new Aspects("spatio", 5, "metallum", 10, "terra", 5));
        set(BlocksAether.enchanted_gravitite, new Aspects("spatio", 5, "metallum", 10, "desiderium", 10));
        set(BlocksAether.berry_bush, new Aspects("herba", 5, "victus", 5));
        set(BlocksAether.berry_bush_stem, new Aspects("herba", 3));
        set(BlocksAether.dungeon_block, new Aspects("terra", 3, "ordo", 1));
        set(OreUtils.meta(BlocksAether.dungeon_block, 2), get(BlocksAether.dungeon_block).add("aversio", 1));
        set(OreUtils.meta(BlocksAether.dungeon_block, 3), get(OreUtils.meta(BlocksAether.dungeon_block, 2)).add("lux", 1));
        set(OreUtils.meta(BlocksAether.dungeon_block, 4), get(BlocksAether.dungeon_block).add("sol", 1, "ignis"));
        set(OreUtils.meta(BlocksAether.dungeon_block, 5), get(OreUtils.meta(BlocksAether.dungeon_block, 4)).add("lux", 1));
        add(BlocksAether.dungeon_block, new Aspects("metallum", 1));
        set(OreUtils.meta(BlocksAether.dungeon_block, 1), get(BlocksAether.dungeon_block).add("lux", 1));
        for (int i = 0; i < 6; i++) {
            set(OreUtils.meta(BlocksAether.locked_dungeon_block, i), get(OreUtils.meta(BlocksAether.dungeon_block, i)));
            set(OreUtils.meta(BlocksAether.dungeon_trap, i), get(OreUtils.meta(BlocksAether.dungeon_block, i)).add("vinculum", 3));
        }
        add(BlocksAether.treasure_chest, get(BlocksAether.holystone).multiply(8F*0.75F));
        set(BlocksAether.purple_flower, get(Blocks.YELLOW_FLOWER));
        set(BlocksAether.white_flower, get(Blocks.YELLOW_FLOWER));
        set(BlocksAether.skyroot_sapling, get(Blocks.SAPLING).add("aer", 1));
        set(BlocksAether.golden_oak_sapling, get(Blocks.SAPLING).add("desiderium", 1));
        add(BlocksAether.crystal_leaves, new Aspects("vitreus", 1));
        add(OreUtils.meta(BlocksAether.crystal_leaves, 1), new Aspects("vitreus", 1, "victus", 1));
        add(BlocksAether.holiday_leaves, new Aspects("tinctura", 1));
        add(OreUtils.meta(BlocksAether.holiday_leaves, 1), new Aspects("tinctura", 1, "victus", 1));
        set(BlocksAether.present, get(Blocks.CHEST).remove("herba", 999).add("tinctura", 5));
        set(BlocksAether.pillar, get(OreUtils.meta(Blocks.QUARTZ_BLOCK, 1)).add("aer", 5));
        set(BlocksAether.pillar_top, get(OreUtils.meta(Blocks.QUARTZ_BLOCK, 2)).add("aer", 5));
        set(BlocksAether.sun_altar, get(BlocksAether.enchanter).add("caeles", 10).add("ignis", 10).add("sol", 25));
        set(ItemsAether.golden_amber, get(Items.GOLD_INGOT).add(get(ItemsTC.amber)).multiply(0.75F));
        add(ItemsAether.aechor_petal, new Aspects("herba", 10, "mortuus", 5, "aversio", 5));
        set(ItemsAether.swetty_ball, get(Items.SLIME_BALL).add("ventus", 2, "aer"));
        registerImaginaryArmor(new Aspects("metallum", 10, "aer", 5, "aversio", 5), ItemsAether.valkyrie_helmet, ItemsAether.valkyrie_chestplate, ItemsAether.valkyrie_leggings, ItemsAether.valkyrie_boots, ItemsAether.valkyrie_gloves);
        registerImaginaryArmor(new Aspects("metallum", 10, "aqua", 5).add("fluctus", 5, "terra"), ItemsAether.neptune_helmet, ItemsAether.neptune_chestplate, ItemsAether.neptune_leggings, ItemsAether.neptune_boots, ItemsAether.neptune_gloves);
        registerImaginaryArmor(new Aspects("metallum", 10, "ignis", 5).add("sol", 15, "volatus"), ItemsAether.phoenix_helmet, ItemsAether.phoenix_chestplate, ItemsAether.phoenix_leggings, ItemsAether.phoenix_boots, ItemsAether.phoenix_gloves);
        registerImaginaryArmor(get(Blocks.OBSIDIAN), ItemsAether.obsidian_helmet, ItemsAether.obsidian_chestplate, ItemsAether.obsidian_leggings, ItemsAether.obsidian_boots, ItemsAether.obsidian_gloves);
        set(ItemsAether.valkyrie_pickaxe, get(ItemsAether.skyroot_stick).multiply(2F).add(new Aspects("metallum", 10, "aer", 5, "aversio", 5).multiply(3F)).multiply(0.75F));
        set(ItemsAether.valkyrie_axe, get(ItemsAether.skyroot_stick).multiply(2F).add(new Aspects("metallum", 10, "aer", 5, "aversio", 5).multiply(3F)).multiply(0.75F));
        set(ItemsAether.valkyrie_shovel, get(ItemsAether.skyroot_stick).multiply(2F).add(new Aspects("metallum", 10, "aer", 5, "aversio", 5)).multiply(0.75F));
        set(ItemsAether.valkyrie_lance, get(ItemsAether.skyroot_stick).add(new Aspects("metallum", 10, "aer", 5, "aversio", 5).multiply(2F)).multiply(0.75F));
        set(ItemsAether.valkyrie_cape, new Aspects("metallum", 10, "aer", 5, "aversio", 5).multiply(6F*0.75F));
        set(ItemsAether.phoenix_bow, get(Items.STRING).multiply(3F).add(new Aspects("metallum", 10, "ignis", 5).add("sol", 15, "volatus").multiply(3F)).multiply(0.75F));
        set(ItemsAether.ice_pendant, get(BlocksAether.icestone).multiply(0.75F).add(get(ItemsAether.iron_pendant)));
        set(ItemsAether.ice_ring, get(BlocksAether.icestone).multiply(0.75F).add(get(ItemsAether.iron_ring)));
        set(ItemsAether.swet_cape, get(ItemsAether.swetty_ball).multiply(6F*0.75F));
        set(ItemsAether.sentry_boots, get(BlocksAether.dungeon_block).multiply(6F*0.75F).add("spatio", 5).add("motus", 5));
        set(ItemsAether.flaming_sword, get(ItemsAether.holystone_sword).add("ignis", 15).add("sol", 15, "potentia"));
        set(ItemsAether.lightning_sword, get(ItemsAether.holystone_sword).add("aer", 15).add("potentia", 15));
        set(ItemsAether.holy_sword, get(ItemsAether.holystone_sword).add("ordo", 15).add("auram", 15));
        set(ItemsAether.vampire_blade, get(ItemsAether.holystone_sword).add("mortuus", 15).add("spiritus", 15));
        set(ItemsAether.pig_slayer, get(ItemsAether.holystone_sword).add("mortuus", 15).add("bestia", 15));
        set(ItemsAether.lightning_knife, get(ItemsAether.lightning_sword).multiply(0.125F));
        set(ItemsAether.moa_egg, get(Items.EGG).add("volatus", 5).add("tinctura", 5));
        set(ItemsAether.blue_berry, new Aspects("herba", 3, "victus", 3));
        set(ItemsAether.enchanted_blueberry, new Aspects("herba", 3, "praecantatio", 5));
        set(ItemsAether.white_apple, get(Items.APPLE).add("praecantatio", 5));
        set(ItemsAether.healing_stone, get(BlocksAether.holystone).multiply(0.75F).add("victus", 5));
        set(ItemsAether.candy_cane, new Aspects("tinctura", 5, "desiderium", 5));
        add(ItemsAether.candy_cane_sword, get(ItemsAether.candy_cane).multiply(2F).add(get(ItemsAether.skyroot_stick)).multiply(0.75F));
        set(ItemsAether.ginger_bread_man, get(ItemsAether.candy_cane).add("humanus", 3));
        set(ItemsAether.victory_medal, new Aspects("desiderium", "aer", "aversio"));
        add(ItemsAether.skyroot_bucket, new Aspects("vacuos", 5));
        set(OreUtils.meta(ItemsAether.skyroot_bucket, 1), get(ItemsAether.skyroot_bucket).add("aqua", 20).add("fluctus", 4));
        set(OreUtils.meta(ItemsAether.skyroot_bucket, 2), get(ItemsAether.skyroot_bucket).add("mortuus", 20).add("fluctus", 4));
        set(OreUtils.meta(ItemsAether.skyroot_bucket, 3), get(ItemsAether.skyroot_bucket).add("victus", 10).add("praecantatio", 5));
        set(OreUtils.meta(ItemsAether.skyroot_bucket, 4), get(ItemsAether.skyroot_bucket).add("victus", 10).add("bestia", 5).add("aqua", 5));
        add(ItemsAether.dart, new Aspects("aversio", 10, "volatus", 5));
        merge(OreUtils.meta(ItemsAether.dart, 1), new Aspects("aversio", 10, "volatus", 5));
        set(OreUtils.meta(ItemsAether.dart, 2), get(OreUtils.meta(ItemsAether.dart, 1)).add("ordo", 10).add("praecantatio", 5));
        add(ItemsAether.notch_hammer, new Aspects("praecantatio", 10, "tinctura", 20, "spatio", 10, "tempus", 10, "ordo", 25, "caeles", 15));
        set(ItemsAether.agility_cape, get(ItemsAether.white_cape).add("motus", 50));
        set(ItemsAether.invisibility_cape, get(ItemsAether.white_cape).add("spiritus", 50));
        set(ItemsAether.golden_feather, get(Items.FEATHER).add("desiderium", 10).add("sol", 5).add("volatus", 10));
        set(ItemsAether.regeneration_stone, get(Items.DIAMOND).add("victus", 10).add("luna", 5).add("auram", 10));
        set(ItemsAether.iron_bubble, get(Items.IRON_INGOT).add("aqua", 10).add("fluctus", 5).add("tenebrae", 10));
        set(ItemsAether.life_shard, get(Items.DIAMOND).add("victus", 25).add("praecantatio", 10));
        set(ItemsAether.cloud_staff, new Aspects("volatus", 25).add("praemunio", 10).add("praecantatio", 10).add("ventus", 5).add("aer", 15));
        add(ItemsAether.nature_staff, new Aspects("bestia", 10).add("praecantatio", 10));
        set(ItemsAether.repulsion_shield, get(Items.SHIELD).add("motus", 15).add("praecantatio", 15));
        set(ItemsAether.dungeon_key, new Aspects("metallum", 10, "instrumentum", 5).multiply(4F*0.75F).add("imperium", 10));
        set(OreUtils.meta(ItemsAether.dungeon_key, 1), new Aspects("metallum", 10).add("luna", 5, "spiritus").multiply(4F*0.75F).add("imperium", 10));
        set(OreUtils.meta(ItemsAether.dungeon_key, 2), new Aspects("metallum", 10).add("sol", 5, "desiderium").multiply(4F*0.75F).add("imperium", 10));
        add(ItemsAether.lore_book, new Aspects("aer", 5));
        set(ItemsAether.gummy_swet, get(ItemsAether.swetty_ball).multiply(3F*0.75F).add("victus", 10));
        set(OreUtils.meta(ItemsAether.gummy_swet, 1), get(ItemsAether.gummy_swet).add("desiderium", 5));
        add(BlocksAether.aether_portal, new Aspects("aer", 10));
        set(ItemsAether.aether_portal_frame, get(BlocksAether.aether_portal).multiply(6).add(get(Blocks.GLOWSTONE).multiply(14)).multiply(0.75F));
        set(ItemsAether.developer_stick, get(ItemsAether.skyroot_stick).add("imperium", 25).add("praecantatio", 25).add("caeles", 25).add("machina", 25));
        set(ItemsAether.aether_tune, get(Items.RECORD_11).add("volatus", 5));
        set(ItemsAether.ascending_dawn, get(Items.RECORD_11).add("aversio", 5));
        set(ItemsAether.welcoming_skies, get(Items.RECORD_11).add("ventus", 5, "tinctura"));
        set(ItemsAether.legacy, get(Items.RECORD_11).add("tempus", 5));
        set(BlocksAether.aerogel_double_slab, get(BlocksAether.aerogel));
        set(BlocksAether.skyroot_double_slab, get(BlocksAether.skyroot_plank));
        set(BlocksAether.carved_double_slab, get(BlocksAether.dungeon_block));
        set(BlocksAether.angelic_double_slab, get(OreUtils.meta(BlocksAether.dungeon_block, 2)));
        set(BlocksAether.hellfire_double_slab, get(OreUtils.meta(BlocksAether.dungeon_block, 4)));
        set(BlocksAether.holystone_double_slab, get(BlocksAether.holystone));
        set(BlocksAether.holystone_brick_double_slab, get(BlocksAether.holystone_brick));
        set(BlocksAether.mossy_holystone_double_slab, get(BlocksAether.mossy_holystone));
        addByRegex(".*cape.*", new Aspects("volatus", 6, "praemunio", 18));
        addByRegex(".*gloves.*", new Aspects("praemunio", 6));
        addByRegex(".*ring.*", new Aspects("praemunio", 3));
        addByRegex(".*pendant.*", new Aspects("praemunio", 3));
        add(ItemsAether.chain_gloves, get(Items.IRON_INGOT).multiply(2F*0.75F*0.75F));
    }

    @Override
    public void registerOres() {
        OreDictionary.registerOre("record", ItemsAether.legacy);
    }

    private void registerImaginaryArmor(Aspects ingot, Item helm, Item chest, Item legs, Item boots, Item gloves) {
        set(helm, ingot.copy().multiply(5*0.75F));
        set(chest, ingot.copy().multiply(8*0.75F));
        set(legs, ingot.copy().multiply(7*0.75F));
        set(boots, ingot.copy().multiply(4*0.75F));
        set(gloves, ingot.copy().multiply(2*0.75F));
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        // register entities
        set("aether_legacy:aechor_plant", new Aspects("herba", 15, "mortuus", 10, "aversio", 5));
        set("aether_legacy:aerbunny", get("rabbit").add("aer", 10));
        set("aether_legacy:aerwhale", new Aspects("ventus", 15, "volatus", 15, "aer", 15, "aqua", 10, "bestia", 20));
        set("aether_legacy:cockatrice", new Aspects("mortuus", 10, "volatus", 15, "bestia", 10));
        set("aether_legacy:flying_cow", get("cow").add("ventus", 10, "volatus"));
        Aspects moa = new Aspects("tinctura", 10, "volatus", 15, "bestia", 10);
        set("aether_legacy:moa", moa.copy().add("fluctus", 5, "gelum"), new ThaumcraftApi.EntityTagsNBT("typeId", 0));
        set("aether_legacy:moa", moa.copy().add("alkimia", 5), new ThaumcraftApi.EntityTagsNBT("typeId", 1));
        set("aether_legacy:moa", moa, new ThaumcraftApi.EntityTagsNBT("typeId", 2));
        set("aether_legacy:moa", moa.copy().add("terra", 5), new ThaumcraftApi.EntityTagsNBT("typeId", 3));
        set("aether_legacy:moa", moa.copy().add("auram", 5), new ThaumcraftApi.EntityTagsNBT("typeId", 4));
        set("aether_legacy:moa", moa.copy().add("infernum", 5, "ignis"), new ThaumcraftApi.EntityTagsNBT("typeId", 5));
        set("aether_legacy:moa", moa.copy().add("tempus", 5), new ThaumcraftApi.EntityTagsNBT("typeId", 6));
        set("aether_legacy:moa", moa.copy().add("tenebrae", 5), new ThaumcraftApi.EntityTagsNBT("typeId", 7));
        set("aether_legacy:moa", moa.copy().add("sonus", 5, "cognitio"), new ThaumcraftApi.EntityTagsNBT("typeId", 8));
        set("aether_legacy:moa", moa.copy().add("aqua", 5), new ThaumcraftApi.EntityTagsNBT("typeId", 9));
        set("aether_legacy:moa", moa.copy().add("spiritus", 5), new ThaumcraftApi.EntityTagsNBT("typeId", 10));
        set("aether_legacy:moa", moa.copy().add("praecantatio", 5), new ThaumcraftApi.EntityTagsNBT("typeId", 11));
        set("aether_legacy:phyg", get("pig").add("volatus", 10));
        set("aether_legacy:sheepuff", get("sheep").add("aer", 5).add("ventus", 5));
        set("aether_legacy:swet", get("slime").remove("aqua", 999).add("aer", 5).add("tinctura", 5));
        set("aether_legacy:whirlwind", new Aspects("vinculum", 10, "aer", 5, "volatus", 5));
        set("aether_legacy:zephyr", new Aspects("aer", 10, "potentia", 10, "ventus", 10));
        set("aether_legacy:mini_cloud", get("aether_legacy:zephyr").multiply(0.5F));
        // dungeon
        set("aether_legacy:sentry", new Aspects("machina", 5, "terra", 5, "ordo", 5, "victus", 10));
        set("aether_legacy:slider", get("aether_legacy:sentry").multiply(5F));
        set("aether_legacy:valkyrie", new Aspects("humanus", 10, "aversio", 10, "desiderium", 10, "volatus", 10));
        set("aether_legacy:valkyrie_queen", get("aether_legacy:valkyrie").multiply(2.5F));
        set("aether_legacy:fire_minion", new Aspects("sol", 25, "ignis", 10, "humanus", 10, "diabolus", 5, "caeles", 1));
        set("aether_legacy:sun_spirit", get("aether_legacy:fire_minion").add("caeles", 4).multiply(2F));
        // entityitems
        set("mimic", get(BlocksAether.chest_mimic));
        set("aether_legacy:poison_needle", get(ItemsAether.dart));
        set("aether_legacy:poison_dart", get(OreUtils.meta(ItemsAether.dart, 1)));
        set("aether_legacy:golden_dart", get(ItemsAether.dart));
        set("aether_legacy:enchanted_dart", get(OreUtils.meta(ItemsAether.dart, 2)));
        set("aether_legacy:parachute", get(ItemsAether.cloud_parachute));
        set("aether_legacy:hammer_projectile", get(ItemsAether.notch_hammer));
        set("aether_legacy:lightning_knife", get(ItemsAether.lightning_knife));
        set("aether_legacy:phoenix_arrow", get(Items.ARROW).add("ignis", 5));
        set("aether_legacy:tipped_phoenix_arrow", get(Items.ARROW).add("ignis", 5));
        set("aether_legacy:tnt_present", get(Blocks.TNT).add(get(BlocksAether.present)));
        set("aether_legacy:fire_ball", new Aspects("potentia", 10, "ignis", 10));
        set("aether_legacy:thunder_ball", new Aspects("potentia", 10, "aer", 10));
        set("aether_legacy:ice_ball", new Aspects("potentia", 10, "gelum", 10));
        set("aether_legacy:zephyr_snowball", new Aspects("potentia", 5, "gelum", 5));
    }
}
