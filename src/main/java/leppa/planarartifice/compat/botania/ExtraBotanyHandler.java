package leppa.planarartifice.compat.botania;

import com.meteor.extrabotany.api.ExtraBotanyAPI;
import com.meteor.extrabotany.common.block.ModBlocks;
import com.meteor.extrabotany.common.crafting.recipe.RecipePedestal;
import leppa.planarartifice.compat.PACompatHandler;
import leppa.planarartifice.main.PlanarArtifice;
import leppa.planarartifice.util.AspectUtils;
import leppa.planarartifice.util.Aspects;
import leppa.planarartifice.util.OreUtils;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static leppa.planarartifice.util.AspectUtils.get;

public class ExtraBotanyHandler implements PACompatHandler.ICompatModule {
    @Override
    public void preInit(FMLPreInitializationEvent e) {
        BotaniaHandler.catalysts.put(ModBlocks.dimensioncatalyst, new Aspects("spatio", 2));
        AspectUtils.recipes.add(
            (stack, history) -> { for (RecipePedestal recipe : ExtraBotanyAPI.pedestalRecipes) {
                if (!OreUtils.equals(recipe.getOutput(), stack)) continue;
                if (OreUtils.historyContains(history, recipe.getOutput())) return null;
                PlanarArtifice.LOGGER.info("[PA, BOTANIA] Patching for pedestal recipe " + stack.getItem().getUnlocalizedName());
                return get(OreUtils.ingredient(recipe.getInput())).multiply(0.75F).add("exitium", 3, "tempus").add("perditio", 1).multiply(1F/Math.max(1, recipe.getOutput().getCount()));
            } return null; }
        );
    }
}
