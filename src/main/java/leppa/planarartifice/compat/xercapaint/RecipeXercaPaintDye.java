package leppa.planarartifice.compat.xercapaint;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import xerca.xercapaint.common.item.ItemPalette;
import xerca.xercapaint.common.item.Items;

import javax.annotation.Nonnull;
import java.util.Arrays;

public class RecipeXercaPaintDye extends ShapelessOreRecipe {
    private final String ore;
    public static final String[] COLOR_LIST = {"Black", "Red", "Green", "Brown", "Blue", "Purple", "Cyan", "LightGray", "Gray", "Pink", "Lime", "Yellow", "LightBlue", "Magenta", "Orange", "White"};
    public RecipeXercaPaintDye(String ore) {
        super(new ResourceLocation(""), ItemStack.EMPTY, "dye" + ore, new ItemStack(Items.ITEM_PALETTE));
        setRegistryName("planarartifice:palette_fill_" + ore.toLowerCase());
        this.ore = ore;
    }

    @Nonnull
    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        ItemStack palette = null;
        ItemStack dye = null;
        for (int i = 0; i < inv.getSizeInventory(); i++) {
            if (palette == null && inv.getStackInSlot(i).getItem() instanceof ItemPalette) palette = inv.getStackInSlot(i);
            if (dye == null && OreDictionary.getOres("dye" + ore).contains(inv.getStackInSlot(i))) dye = inv.getStackInSlot(i);
        }
        if (palette == null || dye == null) return ItemStack.EMPTY;
        NBTTagCompound tag = palette.getTagCompound();
        if (tag == null) tag = new NBTTagCompound();
        else tag = tag.copy();
        byte[] colors;
        if (tag.hasKey("basic")) colors = tag.getByteArray("basic");
        else colors = new byte[16];
        int target = Arrays.binarySearch(COLOR_LIST, ore);
        if (target >= 0 && colors[target] > 0) return ItemStack.EMPTY;
        else colors[target] = 1;
        tag.setByteArray("basic", colors);
        ItemStack res = new ItemStack(Items.ITEM_PALETTE);
        res.setTagCompound(tag);
        return res;
    }

    @Override
    public boolean isDynamic() { return true; }
}
