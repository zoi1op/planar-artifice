package leppa.planarartifice.compat.jei;

import leppa.planarartifice.registry.PABlocks;
import leppa.planarartifice.registry.PAItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class JEIHandler implements IModPlugin {
	public void register(IModRegistry registry) {
		IIngredientBlacklist blacklist = registry.getJeiHelpers().getIngredientBlacklist();
		blacklist.addIngredientToBlacklist(new ItemStack(PABlocks.teleporter));
		blacklist.addIngredientToBlacklist(new ItemStack(PAItems.alchemical_universe));
	}
}
