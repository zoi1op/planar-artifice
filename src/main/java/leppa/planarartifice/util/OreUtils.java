package leppa.planarartifice.util;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;

import java.util.HashMap;

public class OreUtils {
    public static HashMap<String, ItemStack> cache = new HashMap<>();
    public static ItemStack getFirst(String name) {
        return getFirst(name, ItemStack.EMPTY);
    }

    public static ItemStack getFirst(String name, ItemStack fallback) {
        if (cache.containsKey(name)) {
            return cache.get(name).copy();
        }
        NonNullList<ItemStack> ore = OreDictionary.getOres(name);
        if (ore.size() == 0) return fallback;
        cache.put(name, ore.get(0));
        return ore.get(0);
    }
}