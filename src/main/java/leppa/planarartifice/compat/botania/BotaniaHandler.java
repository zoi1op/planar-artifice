package leppa.planarartifice.compat.botania;

import leppa.planarartifice.compat.PACompatHandler;
import leppa.planarartifice.util.AspectUtils;
import leppa.planarartifice.util.Aspects;
import leppa.planarartifice.util.OreUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.recipe.RecipeRuneAltar;
import vazkii.botania.common.block.ModBlocks;

import static leppa.planarartifice.util.AspectUtils.get;

public class BotaniaHandler implements PACompatHandler.ICompatModule {
    @Override
    public void preInit(FMLPreInitializationEvent e) {
        AspectUtils.recipes.add(
            (stack, history) -> { for (RecipeRuneAltar recipe : BotaniaAPI.runeAltarRecipes) {
                if (recipe.getOutput() != stack) continue;
                Aspects ret = new Aspects();
                for (Object inputRaw : recipe.getInputs()) {
                    ItemStack input = OreUtils.ingredient(inputRaw);
                    ret.add(get(input));
                }
                return ret.add(get(ModBlocks.livingrock)).multiply(0.75F).add("auram", MathHelper.log2(recipe.getManaUsage()) / 2);
            } return null; }
        );
    }
}
