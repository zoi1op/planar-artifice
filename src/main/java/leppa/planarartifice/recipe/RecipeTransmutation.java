package leppa.planarartifice.recipe;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class RecipeTransmutation implements IRecipe {

	private ResourceLocation regName;
	
	private final ItemStack input;
	private final ItemStack output;

	public RecipeTransmutation(ResourceLocation name, ItemStack input, ItemStack output) {
		this.input = input;
		this.output = output;
		this.regName = name;
	}

	public boolean matches(ItemStack stack) {
		return ItemStack.areItemsEqual(stack, input);
	}

	@Override
	public IRecipe setRegistryName(ResourceLocation name) {
		this.regName = name;
		return this;
	}

	@Override
	public ResourceLocation getRegistryName() {
		return regName;
	}

	@Override
	public Class<IRecipe> getRegistryType() {
		return (Class<IRecipe>) this.getClass();
	}

	@Override
	public boolean matches(InventoryCrafting inv, World worldIn) {
		return false;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) {
		return output.copy();
	}

	@Override
	public boolean canFit(int width, int height) {
		return false;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return output.copy();
	}
	

}
