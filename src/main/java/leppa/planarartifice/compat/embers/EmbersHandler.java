package leppa.planarartifice.compat.embers;

import leppa.planarartifice.compat.PACompatHandler;
import leppa.planarartifice.main.PlanarArtifice;
import leppa.planarartifice.util.AspectUtils;
import leppa.planarartifice.util.Aspects;
import leppa.planarartifice.util.OreUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import teamroots.embers.RegistryManager;
import teamroots.embers.recipe.AlchemyRecipe;
import teamroots.embers.recipe.DawnstoneAnvilRecipe;
import teamroots.embers.recipe.RecipeRegistry;

import java.util.HashMap;

import static leppa.planarartifice.util.AspectUtils.add;
import static leppa.planarartifice.util.AspectUtils.get;

public class EmbersHandler implements PACompatHandler.ICompatModule {
    public static HashMap<String, Aspects> embersAspect = new HashMap<>();

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        embersAspect.put("iron", new Aspects("metallum"));
        embersAspect.put("dawnstone", new Aspects("sol", "machina", "ignis"));
        embersAspect.put("copper", new Aspects("permutatio"));
        embersAspect.put("silver", new Aspects("desiderium", "luna"));
        embersAspect.put("lead", new Aspects("ordo"));
        AspectUtils.recipes.add(

            (stack, history) -> { for (AlchemyRecipe recipe : RecipeRegistry.alchemyRecipes) {
                if (!OreUtils.equals(recipe.result, stack)) continue;
                if (OreUtils.historyContains(history, recipe.result)) return null;
                PlanarArtifice.LOGGER.info("[PA, EMBERS] Patching for alchemy recipe " + stack.getItem().getUnlocalizedName());
                Aspects ret = new Aspects();
                for (Ingredient ii : recipe.outsideIngredients) ret.add(get(OreUtils.ingredient(ii)));
                Aspects ret2 = new Aspects();
                for (String aspect : embersAspect.keySet())
                    ret2.add(embersAspect.get(aspect).multiply((recipe.aspectRange.getMax(aspect) - recipe.aspectRange.getMin(aspect)) / 16F));
                return ret.multiply(0.75F).add(ret2).add(get(OreUtils.ingredient(recipe.centerIngredient))).multiply(1F/Math.max(1, recipe.result.getCount()));
            } return null; }
        );
        AspectUtils.recipes.add(
            (stack, history) -> { for (DawnstoneAnvilRecipe recipe : RecipeRegistry.dawnstoneAnvilRecipes) {
                if (recipe.result.size() == 0 || !OreUtils.equals(recipe.result.get(0), stack)) continue;
                if (OreUtils.historyContains(history, recipe.result.get(0))) return null;
                PlanarArtifice.LOGGER.info("[PA, EMBERS] Patching for dawnstone anvil recipe " + stack.getItem().getUnlocalizedName());
                Aspects ret = new Aspects();
                for (ItemStack ii : recipe.getTopInputs()) ret.add(get(ii));
                Aspects ret2 = new Aspects();
                for (ItemStack ii : recipe.getBottomInputs()) ret.add(get(ii));
                return ret.multiply(0.75F).add(ret2).add("ignis", 3).add("sol", 3).multiply(1F/Math.max(1, recipe.result.get(0).getCount()));
            } return null; }
        );
    }

    @Override
    public void registerAspects() {
        add(RegistryManager.codex, new Aspects("tempus", 15));
        add(RegistryManager.golems_eye, new Aspects("tempus", 15));
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        add("embers:ancient_golem", new Aspects("tempus", 15));
    }
}
