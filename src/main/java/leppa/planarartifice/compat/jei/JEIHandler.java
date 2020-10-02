package leppa.planarartifice.compat.jei;

import leppa.planarartifice.registry.PABlocks;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientBlacklist;

@JEIPlugin
public class JEIHandler implements IModPlugin {
    public void register(IModRegistry registry) {
        IIngredientBlacklist blacklist = registry.getJeiHelpers().getIngredientBlacklist();
        blacklist.addIngredientToBlacklist(PABlocks.teleporter);
    }
}
