package leppa.planarartifice.compat.aether;

import com.gildedgames.the_aether.addon.blocks.BlocksAetherAddon;
import com.gildedgames.the_aether.addon.items.ItemsAetherAddon;
import leppa.planarartifice.compat.PACompatHandler;
import leppa.planarartifice.util.Aspects;

import static leppa.planarartifice.util.AspectUtils.*;

public class AetherContinuationHandler implements PACompatHandler.ICompatModule {
    @Override
    public void registerAspects() {
        set(ItemsAetherAddon.aetherium_core, new Aspects("aer", 25, "volatus", 25));
        add(BlocksAetherAddon.skyroot_workbench, new Aspects("fabrico", 20));
        add(BlocksAetherAddon.skyroot_chest, new Aspects("vacuos", 6));
        add(BlocksAetherAddon.aetherion_chest, new Aspects("vacuos", 6));
        add(BlocksAetherAddon.aether_lever, new Aspects("imperium", 5, "machina", 5));
        add(BlocksAetherAddon.holystone_button, new Aspects("imperium", 5, "machina", 5));
        add(BlocksAetherAddon.skyroot_button, new Aspects("imperium", 5, "machina", 5));
        add(BlocksAetherAddon.holystone_pressure_plate, new Aspects("machina", 5));
        add(BlocksAetherAddon.skyroot_pressure_plate, new Aspects("machina", 5));
        add(BlocksAetherAddon.zanite_pressure_plate, new Aspects("machina", 5));
        add(BlocksAetherAddon.skyroot_trapdoor, new Aspects("motus", 5));
        add(BlocksAetherAddon.zanite_trapdoor, new Aspects("motus", 5));
        add(ItemsAetherAddon.skyroot_door, new Aspects("machina", 5, "vinculum", 5));
        add(ItemsAetherAddon.zanite_door, new Aspects("machina", 5, "vinculum", 5));
        set(ItemsAetherAddon.cockatrice, new Aspects("bestia", 5, "victus", 5, "volatus", 5));
        set(ItemsAetherAddon.burnt_cockatrice, get(ItemsAetherAddon.cockatrice).add("ignis", 5).add("perditio", 5));
        set(ItemsAetherAddon.enchanted_cockatrice, get(ItemsAetherAddon.cockatrice).add("praecantatio", 5));
        set(ItemsAetherAddon.cooked_enchanted_cockatrice, get(ItemsAetherAddon.cockatrice).add("praecantatio", 10).add("ignis", 5));
        set(BlocksAetherAddon.skyroot_door, get(ItemsAetherAddon.skyroot_door));
        set(BlocksAetherAddon.zanite_door, get(ItemsAetherAddon.zanite_door));
        set(BlocksAetherAddon.skyroot_standing_sign, get(ItemsAetherAddon.skyroot_sign));
        set(BlocksAetherAddon.skyroot_wall_sign, get(ItemsAetherAddon.skyroot_sign));
    }
}
