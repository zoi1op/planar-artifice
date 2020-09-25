package leppa.planarartifice.recipe;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry.Impl;

public class RecipePotionMixer extends Impl<IRecipe> implements IRecipe {
	
	private final ItemStack input;
	private final Potion[] potions;

	public RecipePotionMixer(ResourceLocation name, ItemStack input, Potion... potions) {
		this.input = input;
		this.potions = potions;
		
		this.setRegistryName(name);
	}

	public boolean matches(ItemStack stack) {
		return ItemStack.areItemsEqual(stack, input);
	}

	public Potion[] getPotions() {
		return potions;
	}


	@Override
	public boolean matches(InventoryCrafting inv, World worldIn) {
		return false;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) {
		return ItemStack.EMPTY;
	}

	@Override
	public boolean canFit(int width, int height) {
		return false;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return ItemStack.EMPTY;
	}

}
