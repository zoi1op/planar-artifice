package leppa.planarartifice.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;

import java.util.HashMap;
import java.util.UUID;

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

    public static boolean exists(String name) {
        return OreDictionary.doesOreNameExist(name) && OreUtils.getFirst(name) != ItemStack.EMPTY;
    }

    public static ItemStack meta(Item stack, int meta) { return new ItemStack(stack, 1, meta); }
    public static ItemStack meta(Block stack, int meta) { return new ItemStack(stack, 1, meta); }
    public static ItemStack meta(ItemStack stack, int meta) {
        ItemStack e = new ItemStack(stack.getItem(), stack.getCount(), meta);
        e.setTagCompound(stack.getTagCompound());
        return e;
    }
    public static ItemStack count(Item stack, int count) { if (count <= 0) return ItemStack.EMPTY; return new ItemStack(stack, count); }
    public static ItemStack count(Block stack, int count) { if (count <= 0) return ItemStack.EMPTY; return new ItemStack(stack, count); }
    public static ItemStack count(ItemStack stack, int count) {
        if (count <= 0) return ItemStack.EMPTY;
        stack.setCount(count);
        return stack;
    }
    public static ItemStack nbtByte(Item stack, String key, byte e) { return nbtByte(new ItemStack(stack), key, e); }
    public static ItemStack nbtByte(Block stack, String key, byte e) { return nbtByte(new ItemStack(stack), key, e); }
    public static ItemStack nbtByte(ItemStack stack, String key, byte e) {
        NBTTagCompound tag = stack.getTagCompound();
        if (tag == null) tag = new NBTTagCompound();
        tag = tag.copy();
        tag.setByte(key, e);
        stack.setTagCompound(tag);
        return stack;
    }
    public static ItemStack nbtByteArray(Item stack, String key, byte[] e) { return nbtByteArray(new ItemStack(stack), key, e); }
    public static ItemStack nbtByteArray(Block stack, String key, byte[] e) { return nbtByteArray(new ItemStack(stack), key, e); }
    public static ItemStack nbtByteArray(ItemStack stack, String key, byte[] e) {
        NBTTagCompound tag = stack.getTagCompound();
        if (tag == null) tag = new NBTTagCompound();
        tag = tag.copy();
        tag.setByteArray(key, e);
        stack.setTagCompound(tag);
        return stack;
    }
    public static ItemStack nbtBoolean(Item stack, String key, boolean e) { return nbtBoolean(new ItemStack(stack), key, e); }
    public static ItemStack nbtBoolean(Block stack, String key, boolean e) { return nbtBoolean(new ItemStack(stack), key, e); }
    public static ItemStack nbtBoolean(ItemStack stack, String key, boolean e) {
        NBTTagCompound tag = stack.getTagCompound();
        if (tag == null) tag = new NBTTagCompound();
        tag = tag.copy();
        tag.setBoolean(key, e);
        stack.setTagCompound(tag);
        return stack;
    }
    public static ItemStack nbtTag(Item stack, String key, NBTBase e) { return nbtTag(new ItemStack(stack), key, e); }
    public static ItemStack nbtTag(Block stack, String key, NBTBase e) { return nbtTag(new ItemStack(stack), key, e); }
    public static ItemStack nbtTag(ItemStack stack, String key, NBTBase e) {
        NBTTagCompound tag = stack.getTagCompound();
        if (tag == null) tag = new NBTTagCompound();
        tag = tag.copy();
        tag.setTag(key, e);
        stack.setTagCompound(tag);
        return stack;
    }
    public static ItemStack nbtDouble(Item stack, String key, double e) { return nbtDouble(new ItemStack(stack), key, e); }
    public static ItemStack nbtDouble(Block stack, String key, double e) { return nbtDouble(new ItemStack(stack), key, e); }
    public static ItemStack nbtDouble(ItemStack stack, String key, double e) {
        NBTTagCompound tag = stack.getTagCompound();
        if (tag == null) tag = new NBTTagCompound();
        tag = tag.copy();
        tag.setDouble(key, e);
        stack.setTagCompound(tag);
        return stack;
    }
    public static ItemStack nbtFloat(Item stack, String key, float e) { return nbtFloat(new ItemStack(stack), key, e); }
    public static ItemStack nbtFloat(Block stack, String key, float e) { return nbtFloat(new ItemStack(stack), key, e); }
    public static ItemStack nbtFloat(ItemStack stack, String key, float e) {
        NBTTagCompound tag = stack.getTagCompound();
        if (tag == null) tag = new NBTTagCompound();
        tag = tag.copy();
        tag.setFloat(key, e);
        stack.setTagCompound(tag);
        return stack;
    }
    public static ItemStack nbtInteger(Item stack, String key, int e) { return nbtInteger(new ItemStack(stack), key, e); }
    public static ItemStack nbtInteger(Block stack, String key, int e) { return nbtInteger(new ItemStack(stack), key, e); }
    public static ItemStack nbtInteger(ItemStack stack, String key, int e) {
        NBTTagCompound tag = stack.getTagCompound();
        if (tag == null) tag = new NBTTagCompound();
        tag = tag.copy();
        tag.setInteger(key, e);
        stack.setTagCompound(tag);
        return stack;
    }
    public static ItemStack nbtIntArray(Item stack, String key, int[] e) { return nbtIntArray(new ItemStack(stack), key, e); }
    public static ItemStack nbtIntArray(Block stack, String key, int[] e) { return nbtIntArray(new ItemStack(stack), key, e); }
    public static ItemStack nbtIntArray(ItemStack stack, String key, int[] e) {
        NBTTagCompound tag = stack.getTagCompound();
        if (tag == null) tag = new NBTTagCompound();
        tag = tag.copy();
        tag.setIntArray(key, e);
        stack.setTagCompound(tag);
        return stack;
    }
    public static ItemStack nbtLong(Item stack, String key, long e) { return nbtLong(new ItemStack(stack), key, e); }
    public static ItemStack nbtLong(Block stack, String key, long e) { return nbtLong(new ItemStack(stack), key, e); }
    public static ItemStack nbtLong(ItemStack stack, String key, long e) {
        NBTTagCompound tag = stack.getTagCompound();
        if (tag == null) tag = new NBTTagCompound();
        tag = tag.copy();
        tag.setLong(key, e);
        stack.setTagCompound(tag);
        return stack;
    }
    public static ItemStack nbtShort(Item stack, String key, short e) { return nbtShort(new ItemStack(stack), key, e); }
    public static ItemStack nbtShort(Block stack, String key, short e) { return nbtShort(new ItemStack(stack), key, e); }
    public static ItemStack nbtShort(ItemStack stack, String key, short e) {
        NBTTagCompound tag = stack.getTagCompound();
        if (tag == null) tag = new NBTTagCompound();
        tag = tag.copy();
        tag.setShort(key, e);
        stack.setTagCompound(tag);
        return stack;
    }
    public static ItemStack nbtString(Item stack, String key, String e) { return nbtString(new ItemStack(stack), key, e); }
    public static ItemStack nbtString(Block stack, String key, String e) { return nbtString(new ItemStack(stack), key, e); }
    public static ItemStack nbtString(ItemStack stack, String key, String e) {
        NBTTagCompound tag = stack.getTagCompound();
        if (tag == null) tag = new NBTTagCompound();
        tag = tag.copy();
        tag.setString(key, e);
        stack.setTagCompound(tag);
        return stack;
    }
    public static ItemStack nbtUniqueId(Item stack, String key, UUID e) { return nbtUniqueId(new ItemStack(stack), key, e); }
    public static ItemStack nbtUniqueId(Block stack, String key, UUID e) { return nbtUniqueId(new ItemStack(stack), key, e); }
    public static ItemStack nbtUniqueId(ItemStack stack, String key, UUID e) {
        NBTTagCompound tag = stack.getTagCompound();
        if (tag == null) tag = new NBTTagCompound();
        tag = tag.copy();
        tag.setUniqueId(key, e);
        stack.setTagCompound(tag);
        return stack;
    }
    public static ItemStack ingredient(Object stack) {
        if (stack instanceof String) return OreUtils.getFirst((String)stack);
        if (stack instanceof Item) return new ItemStack((Item)stack);
        if (stack instanceof Block) return new ItemStack((Block)stack);
        if (stack instanceof ItemStack) {
            if (((ItemStack) stack).getMetadata() == OreDictionary.WILDCARD_VALUE) return OreUtils.meta((ItemStack) stack, 0);
            return (ItemStack)stack;
        }
        return ItemStack.EMPTY;
    }
}