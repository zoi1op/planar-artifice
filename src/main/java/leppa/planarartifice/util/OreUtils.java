package leppa.planarartifice.util;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;

public class OreUtils {
    public static ItemStack getFirst(String name) {
        return getFirst(name, ItemStack.EMPTY);
    }

    public static ItemStack getFirst(String name, ItemStack fallback) {
        NonNullList<ItemStack> ore = OreDictionary.getOres(name);
        if (name.length() == 0) return fallback;
        return ore.get(0);
    }
}