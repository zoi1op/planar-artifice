package leppa.planarartifice.util;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.AspectHelper;
import thaumcraft.api.aspects.AspectList;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class AspectUtils {

    public static void add(ItemStack stack, AspectList aspects) {
        ThaumcraftApi.registerObjectTag(stack, AspectHelper.getObjectAspects(stack).add(aspects));
    }

    public static void set(ItemStack stack, AspectList aspects) {
        ThaumcraftApi.registerObjectTag(stack, aspects);
    }

    public static void addOreDict(String ore, AspectList aspects) {
        ThaumcraftApi.registerObjectTag(ore, AspectHelper.getObjectAspects(OreDictionary.getOres(ore).get(0)).add(aspects));
    }

    public static void setOreDict(String ore, AspectList aspects) {
        ThaumcraftApi.registerObjectTag(ore, aspects);
    }

    public static void addById(String id, AspectList aspects) {
        ItemStack stack = new ItemStack(Item.getByNameOrId(id));
        add(stack, aspects);
    }

    public static void setById(String id, AspectList aspects) {
        ItemStack stack = new ItemStack(Item.getByNameOrId(id));
        set(stack, aspects);
    }

    public static void addByRegex(String regex, AspectList aspects) {
        // probably will be less efficient? don't use this unless necessary?
        for (Item item : ForgeRegistries.ITEMS.getValuesCollection()) {
            if (item.getRegistryName().toString().matches(regex)) add(new ItemStack(item), aspects);
        }
    }

    public static void setByRegex(String regex, AspectList aspects) {
        // probably will be less efficient? don't use this unless necessary?
        for (Item item : ForgeRegistries.ITEMS.getValuesCollection()) {
            if (item.getRegistryName().toString().matches(regex)) set(new ItemStack(item), aspects);
        }
    }
}