package leppa.planarartifice.compat.aether;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.items.ItemsAether;
import com.legacy.lostaether.blocks.BlocksLostAether;
import com.legacy.lostaether.items.ItemsLostAether;
import leppa.planarartifice.compat.PACompatHandler;
import leppa.planarartifice.util.Aspects;
import net.minecraft.init.Items;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;

import static leppa.planarartifice.util.AspectUtils.*;

public class AetherLostContentHandler implements PACompatHandler.ICompatModule {
    @Override
    public void registerAspects() {
        set(BlocksLostAether.gale_stone, new Aspects("terra", 3, "ordo", 1).add("ventus", 1, "volatus"));
        set(BlocksLostAether.locked_gale_stone, get(BlocksLostAether.gale_stone));
        set(BlocksLostAether.gale_double_slab, get(BlocksLostAether.gale_stone));
        set(BlocksLostAether.light_gale_stone, get(BlocksLostAether.gale_stone).add("lux", 1));
        set(BlocksLostAether.locked_light_gale_stone, get(BlocksLostAether.locked_gale_stone));
        add(BlocksLostAether.crystal_sapling, new Aspects("vitreus", 1));
        set(BlocksLostAether.songstone, get(BlocksLostAether.gale_stone).add("sonus", 5, "sensus"));
        add(ItemsLostAether.phoenix_pickaxe, get(ItemsAether.skyroot_stick).multiply(2F).add(new Aspects("metallum", 10, "ignis", 5).add("sol", 15, "volatus").multiply(3F)).multiply(0.75F));
        add(ItemsLostAether.phoenix_axe, get(ItemsAether.skyroot_stick).multiply(2F).add(new Aspects("metallum", 10, "ignis", 5).add("sol", 15, "volatus").multiply(3F)).multiply(0.75F));
        add(ItemsLostAether.phoenix_shovel, get(ItemsAether.skyroot_stick).multiply(2F).add(new Aspects("metallum", 10, "ignis", 5).add("sol", 15, "volatus")).multiply(0.75F));
        add(ItemsLostAether.phoenix_sword, get(ItemsAether.skyroot_stick).add(new Aspects("metallum", 10, "ignis", 5).add("sol", 15, "volatus").multiply(2F)).multiply(0.75F));
        add(ItemsLostAether.phoenix_cape, new Aspects("metallum", 10, "ignis", 5).add("sol", 15, "volatus").multiply(6F*0.75F));
        set(ItemsLostAether.sentry_shield, get(Items.SHIELD).add(get(BlocksAether.dungeon_block).multiply(6F*0.75F)));
        add(ItemsLostAether.zanite_shield, new Aspects("praemunio", 20));
        add(ItemsLostAether.gravitite_shield, new Aspects("praemunio", 20));
        set(ItemsLostAether.jeb_shield, get(ItemsAether.repulsion_shield).add("caeles", 15).add("spatio", 10).add("tempus", 10).add("ordo", 25));
        set(ItemsLostAether.invisibility_gem, get(Items.DIAMOND).add("spiritus", 15).add("praecantatio", 10));
        set(ItemsLostAether.power_gloves, get(ItemsAether.iron_gloves).add("motus", 15).add("aversio", 10));
        set(ItemsLostAether.swetty_mask, get(ItemsAether.swetty_ball).multiply(4F*0.75F).add("praemunio", 4));
        set(ItemsLostAether.agility_boots, new Aspects("motus", 15, "volatus", 10, "aer", 25, "praemunio", 8, "praecantatio", 10));
        set(ItemsLostAether.platinum_key, new Aspects("metallum", 10, "caeles", 5, "desiderium", 5).multiply(4F*0.75F).add("imperium", 10));
    }

    @Override
    public void registerOres() {
        OreDictionary.registerOre("treeSapling", BlocksLostAether.crystal_sapling);
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        set("lost_aether:king_aerwhale", get("aether_legacy:aerwhale").multiply(2F).add("praemunio", 20));
        set("lost_aether:zephyroo", new Aspects("motus", 15, "volatus", 10, "bestia", 15, "spatio", 10));
    }
}
