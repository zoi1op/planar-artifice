package leppa.planarartifice.util;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.AspectEventProxy;
import thaumcraft.api.aspects.AspectHelper;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.internal.CommonInternals;

import java.util.ArrayList;
import java.util.Arrays;

public class AspectUtils {
    public static AspectEventProxy event;
    public static ArrayList<AspectListFunction> recipes = new ArrayList<>();
    public interface AspectListFunction { AspectList run(ItemStack stack, ArrayList<String> history); }

    public static Aspects getEntityAspect(String name, ThaumcraftApi.EntityTagsNBT... nbt) {
        for (ThaumcraftApi.EntityTags et : CommonInternals.scanEntities) {
            if (et.entityName == null) continue;
            if (et.entityName.equalsIgnoreCase(getOldName(name)) && Arrays.equals(et.nbts, nbt != null ? nbt : new ThaumcraftApi.EntityTagsNBT[0])) {
                return new Aspects(et.aspects);
            }
        }
        return new Aspects();
    }

    public static Aspects get(Item stack) { return get(new ItemStack(stack)); }
    public static Aspects get(Block stack) { return get(new ItemStack(stack)); }
    public static Aspects get(ItemStack stack) { return new Aspects(AspectHelper.getObjectAspects(stack)); }
    public static Aspects get(String stack, ThaumcraftApi.EntityTagsNBT... nbt) { return new Aspects(getEntityAspect(stack, nbt)); }
    public static Aspects get(Entity stack) { return new Aspects(AspectHelper.getEntityAspects(stack)); }
    public static Aspects getOre(String stack) { if (!OreUtils.exists(stack)) return new Aspects(); return get(OreUtils.getFirst(stack)); }
    public static Aspects getRaw(String stack, ThaumcraftApi.EntityTagsNBT... nbt) {
        return CommonInternals.scanEntities.stream().filter(s -> s.entityName.equalsIgnoreCase(stack) && Arrays.equals(s.nbts, nbt != null ? nbt : new ThaumcraftApi.EntityTagsNBT[0])).findAny().map(entityTags -> new Aspects(entityTags.aspects)).orElseGet(Aspects::new);
    }

    public static void add(Item stack, AspectList aspects) { add(new ItemStack(stack), aspects); }
    public static void add(Block stack, AspectList aspects) { add(new ItemStack(stack), aspects); }
    public static void add(ItemStack stack, AspectList aspects) {
        event.registerObjectTag(stack, AspectHelper.getObjectAspects(stack).add(aspects));
    }
    public static void add(String entityName, AspectList aspects, ThaumcraftApi.EntityTagsNBT... nbt) { set(entityName, getEntityAspect(entityName, nbt).add(aspects), nbt); }

    public static void set(Item stack, AspectList aspects) { set(new ItemStack(stack), aspects); }
    public static void set(Block stack, AspectList aspects) { set(new ItemStack(stack), aspects); }
    public static void set(ItemStack stack, AspectList aspects) {
        event.registerObjectTag(stack, aspects);
    }
    @SuppressWarnings("deprecation")
    public static void set(String entityName, AspectList aspects, ThaumcraftApi.EntityTagsNBT... nbt) {
        if (getOldName(entityName) == null) return;
        ThaumcraftApi.registerEntityTag(getOldName(entityName), aspects, nbt != null ? nbt : new ThaumcraftApi.EntityTagsNBT[0]);
    }

    public static void merge(Item stack, AspectList aspects) { set(new ItemStack(stack), aspects); }
    public static void merge(Block stack, AspectList aspects) { set(new ItemStack(stack), aspects); }
    public static void merge(ItemStack stack, AspectList aspects) {
        event.registerObjectTag(stack, AspectHelper.getObjectAspects(stack).merge(aspects));
    }
    public static void merge(String entityName, AspectList aspects, ThaumcraftApi.EntityTagsNBT... nbt) { set(entityName, getEntityAspect(entityName, nbt).merge(aspects), nbt); }

    public static void remove(Item stack, AspectList aspects) { set(new ItemStack(stack), aspects); }
    public static void remove(Block stack, AspectList aspects) { set(new ItemStack(stack), aspects); }
    public static void remove(ItemStack stack, AspectList aspects) {
        event.registerObjectTag(stack, AspectHelper.getObjectAspects(stack).remove(aspects));
    }
    public static void remove(String entityName, AspectList aspects, ThaumcraftApi.EntityTagsNBT... nbt) { set(entityName, getEntityAspect(entityName, nbt).remove(aspects), nbt); }

    public static void addOreDict(String ore, AspectList aspects) {
        event.registerObjectTag(ore, AspectHelper.getObjectAspects(OreDictionary.getOres(ore).get(0)).add(aspects));
    }
    public static void setOreDict(String ore, AspectList aspects) {
        event.registerObjectTag(ore, aspects);
    }
    public static void mergeOreDict(String ore, AspectList aspects) {
        event.registerObjectTag(ore, AspectHelper.getObjectAspects(OreUtils.getFirst(ore)).add(aspects));
    }
    public static void removeOreDict(String ore, AspectList aspects) {
        event.registerObjectTag(ore, AspectHelper.getObjectAspects(OreUtils.getFirst(ore)).add(aspects));
    }

    public static void addById(String id, AspectList aspects) {
        ItemStack stack = new ItemStack(Item.getByNameOrId(id));
        add(stack, aspects);
    }
    public static void setById(String id, AspectList aspects) {
        ItemStack stack = new ItemStack(Item.getByNameOrId(id));
        set(stack, aspects);
    }
    public static void mergeById(String id, AspectList aspects) {
        ItemStack stack = new ItemStack(Item.getByNameOrId(id));
        merge(stack, aspects);
    }
    public static void removeById(String id, AspectList aspects) {
        ItemStack stack = new ItemStack(Item.getByNameOrId(id));
        remove(stack, aspects);
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
    public static void mergeByRegex(String regex, AspectList aspects) {
        // probably will be less efficient? don't use this unless necessary?
        for (Item item : ForgeRegistries.ITEMS.getValuesCollection()) {
            if (item.getRegistryName().toString().matches(regex)) merge(new ItemStack(item), aspects);
        }
    }
    public static void removeByRegex(String regex, AspectList aspects) {
        // probably will be less efficient? don't use this unless necessary?
        for (Item item : ForgeRegistries.ITEMS.getValuesCollection()) {
            if (item.getRegistryName().toString().matches(regex)) remove(new ItemStack(item), aspects);
        }
    }

    private static String getOldName(String newName) { return EntityList.getTranslationName(new ResourceLocation(newName)); }

    public static AspectList generateTagsFromRecipes(ItemStack stack, ArrayList<String> history) {
        AspectList ret;
        for (AspectListFunction fn : recipes) {
            ret = fn.run(stack, history);
            if (ret != null) return ret;
        }
        return null;
    }
}