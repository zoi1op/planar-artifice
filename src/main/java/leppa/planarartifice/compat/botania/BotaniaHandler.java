package leppa.planarartifice.compat.botania;

import leppa.planarartifice.compat.PACompatHandler;
import leppa.planarartifice.main.PlanarArtifice;
import leppa.planarartifice.util.AspectUtils;
import leppa.planarartifice.util.Aspects;
import leppa.planarartifice.util.OreUtils;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.recipe.*;
import vazkii.botania.common.block.ModBlocks;

import java.util.HashMap;

import static leppa.planarartifice.util.AspectUtils.get;

public class BotaniaHandler implements PACompatHandler.ICompatModule {
    public static HashMap<Block, Aspects> catalysts = new HashMap<>();

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        AspectUtils.recipes.add(
            (stack, history) -> { for (RecipeRuneAltar recipe : BotaniaAPI.runeAltarRecipes) {
                if (!OreUtils.equals(recipe.getOutput(), stack)) continue;
                if (OreUtils.historyContains(history, recipe.getOutput())) return null;
                PlanarArtifice.LOGGER.info("[PA, BOTANIA] Patching for rune altar recipe " + stack.getItem().getUnlocalizedName());
                Aspects ret = new Aspects();
                for (Object inputRaw : recipe.getInputs()) {
                    ItemStack input = OreUtils.ingredient(inputRaw);
                    ret.add(get(input));
                }
                return ret.add(get(ModBlocks.livingrock)).multiply(0.75F).add("auram", MathHelper.log2(Math.max(1, recipe.getManaUsage()) / 2)).multiply(1F/Math.max(1, recipe.getOutput().getCount()));
            } return null; }
        );
        catalysts.put(null, new Aspects());
        catalysts.put(ModBlocks.alchemyCatalyst, new Aspects("permutatio", 2));
        catalysts.put(ModBlocks.conjurationCatalyst, new Aspects("praecantatio", 2));
        AspectUtils.recipes.add(
            (stack, history) -> { for (RecipeManaInfusion recipe : BotaniaAPI.manaInfusionRecipes) {
                if (!OreUtils.equals(recipe.getOutput(), stack)) continue;
                if (OreUtils.historyContains(history, recipe.getOutput())) return null;
                PlanarArtifice.LOGGER.info("[PA, BOTANIA] Patching for mana infusion recipe " + stack.getItem().getUnlocalizedName());
                Block catalyst = recipe.getCatalyst() == null ? null : recipe.getCatalyst().getBlock();
                Aspects ret;
                if (catalysts.containsKey(catalyst)) ret = catalysts.get(catalyst);
                else ret = get(catalyst).multiply(0.75F);
                return get(OreUtils.ingredient(recipe.getInput())).multiply(0.75F).add(ret).add("auram", MathHelper.log2(Math.max(1, recipe.getManaToConsume()) / 2)).multiply(1F/Math.max(1, recipe.getOutput().getCount()));
            } return null; }
        );
        AspectUtils.recipes.add(
            (stack, history) -> { for (RecipeElvenTrade recipe : BotaniaAPI.elvenTradeRecipes) {
                int outputs = 0;
                for (ItemStack output : recipe.getOutputs()) outputs += output.getCount(); // from the 3 addon + basegame, there isn't a recipe that uses more than 1 output but... this one's for you crafttweaker boys.
                for (ItemStack output : recipe.getOutputs()) {
                    if (!OreUtils.equals(output, stack)) continue;
                    if (OreUtils.historyContains(history, output)) return null;
                    PlanarArtifice.LOGGER.info("[PA, BOTANIA] Patching for elven trade recipe " + stack.getItem().getUnlocalizedName());
                    Aspects ret = new Aspects();
                    for (Object inputRaw : recipe.getInputs()) {
                        ItemStack input = OreUtils.ingredient(inputRaw);
                        ret.add(get(input));
                    }
                    return ret.multiply(0.75F).add("alienis", 2).multiply(1F / Math.max(1, outputs));
                }
            } return null; }
        );
        AspectUtils.recipes.add(
            (stack, history) -> { for (RecipePureDaisy recipe : BotaniaAPI.pureDaisyRecipes) {
                if (!OreUtils.equals(recipe.getOutputState(), stack)) continue;
                if (OreUtils.historyContains(history, recipe.getOutputState())) return null;
                PlanarArtifice.LOGGER.info("[PA, BOTANIA] Patching for pure daisy recipe " + stack.getItem().getUnlocalizedName());
                return get(OreUtils.ingredient(recipe.getInput())).multiply(0.75F).add("ordo", 3);
            } return null; }
        );
    }
}
