package leppa.planarartifice.recipe;

import java.util.Random;

import net.minecraft.item.ItemStack;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.CrucibleRecipe;

public class CrucibleRecipeRandomCrystal extends CrucibleRecipe {

	public CrucibleRecipeRandomCrystal(String researchKey, Object catalyst, AspectList tags) {
		super(researchKey, ItemStack.EMPTY, catalyst, tags);
	}

	@Override
	public ItemStack getRecipeOutput() {
		int i = Aspect.aspects.size();
		int aspectToUse = new Random().nextInt(i);
		return ThaumcraftApiHelper.makeCrystal((Aspect) Aspect.aspects.values().toArray()[aspectToUse], 3);
	}
}
