package leppa.planarartifice.compat.bewitchment;

import com.bewitchment.api.registry.*;
import com.bewitchment.common.integration.thaumcraft.ThaumcraftCompat;
import com.bewitchment.registry.ModObjects;
import com.bewitchment.registry.ModRecipes;
import leppa.planarartifice.compat.PACompatHandler;
import leppa.planarartifice.main.PAConfig;
import leppa.planarartifice.main.PlanarArtifice;
import leppa.planarartifice.util.AspectUtils;
import leppa.planarartifice.util.Aspects;
import leppa.planarartifice.util.OreUtils;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import thaumcraft.api.aspects.Aspect;

import java.util.HashMap;

import static leppa.planarartifice.registry.PARecipes.registerCrucibleRecipe;
import static leppa.planarartifice.util.AspectUtils.get;

public class BewitchmentHandler implements PACompatHandler.ICompatModule {
	public static boolean active = false;
	public BewitchmentHandler() {
		active = true;
	}

	public static HashMap<Integer, Aspects> chalks = new HashMap<>();

	@Override
	public void preInit(FMLPreInitializationEvent e) {
		chalks.put(-1, new Aspects());
		chalks.put(1, new Aspects("permutatio", 3));
		chalks.put(2, new Aspects("diabolus", 3));
		chalks.put(3, new Aspects("alienis", 3));
		AspectUtils.recipes.add(
			(stack, history) -> { for (CauldronRecipe recipe : ModRecipes.cauldronRecipes) {
				// input is usually main + residue, so only [0] is detected
				if (recipe.output.size() == 0 || !OreUtils.equals(recipe.output.get(0), stack)) continue;
				if (OreUtils.historyContains(history, recipe.output.get(0))) return null;
				PlanarArtifice.LOGGER.info("[PA, BEWITCHMENT] Patching for cauldron recipe " + stack.getItem().getUnlocalizedName());
				Aspects ret = new Aspects();
				for (Ingredient input : recipe.input) ret.add(get(OreUtils.ingredient(input)));
				return ret.multiply(0.75F).add("alkimia", 3).multiply(1F/Math.max(1, recipe.output.get(0).getCount()));
			} return null; }
		);
		AspectUtils.recipes.add(
			(stack, history) -> { for (OvenRecipe recipe : ModRecipes.ovenRecipes) {
				if (OreUtils.equals(recipe.output, stack)) {
					if (OreUtils.historyContains(history, recipe.output)) return null;
					PlanarArtifice.LOGGER.info("[PA, BEWITCHMENT] Patching for oven recipe " + stack.getItem().getUnlocalizedName());
					Aspects raw = new Aspects(get(recipe.input));
					Aspects ret = new Aspects();
					boolean blink = true;
					for (Aspect aspect : raw.getAspects()) {
						if (blink) ret.add(aspect, raw.getAmount(aspect));
						blink = !blink;
					}
					return ret.multiply(1.25F).add("exitium", 3, "perditio").multiply(1F/Math.max(1, recipe.output.getCount()));
				}
				if (OreUtils.equals(recipe.byproduct, stack)) {
					if (OreUtils.historyContains(history, recipe.byproduct)) return null;
					PlanarArtifice.LOGGER.info("[PA, BEWITCHMENT] Patching for oven byproduct recipe " + stack.getItem().getUnlocalizedName());
					Aspects raw = new Aspects(get(recipe.input));
					Aspects ret = new Aspects();
					boolean blink = false;
					for (Aspect aspect : raw.getAspects()) {
						if (blink) ret.add(aspect, raw.getAmount(aspect));
						blink = !blink;
					}
					return ret.multiply(1.25F/Math.max(0.01F, recipe.byproductChance)).multiply(Math.max(1, 1F/recipe.byproduct.getCount()));
				}
			} return null; }
		);
		AspectUtils.recipes.add(
			(stack, history) -> { for (Ritual recipe : ModRecipes.ritualRecipes) {
				// input is usually main + residue, so only [0] is detected
				if (!recipe.getClass().getName().equals("Ritual") || recipe.sacrificePredicate != null) continue;
				if (Loader.isModLoaded("moretweaker") && !MoreTweakerHandler.isSimpleRitual(recipe)) continue;
				if (recipe.output.size() == 0 || !OreUtils.equals(recipe.output.get(0), stack)) continue;
				if (OreUtils.historyContains(history, recipe.output.get(0))) return null;
				PlanarArtifice.LOGGER.info("[PA, BEWITCHMENT] Patching for ritual recipe " + stack.getItem().getUnlocalizedName());
				Aspects ret = new Aspects();
				for (Ingredient input : recipe.input) ret.add(get(OreUtils.ingredient(input)));
				for (int circle : recipe.circles) ret.add(chalks.get(circle));
				return ret.multiply(0.75F).add("praecantatio", MathHelper.log2(Math.max(1, recipe.runningPower * 10 + recipe.startingPower))).multiply(1F/Math.max(1, recipe.output.get(0).getCount()));
			} return null; }
		);
		AspectUtils.recipes.add(
			(stack, history) -> { for (DistilleryRecipe recipe : ModRecipes.distilleryRecipes) {
				// input is usually main + residue, so only [0] is detected
				if (recipe.output.size() == 0 || !OreUtils.equals(recipe.output.get(0), stack)) continue;
				if (OreUtils.historyContains(history, recipe.output.get(0))) return null;
				PlanarArtifice.LOGGER.info("[PA, BEWITCHMENT] Patching for distillery recipe " + stack.getItem().getUnlocalizedName());
				Aspects ret = new Aspects();
				for (Ingredient input : recipe.input) ret.add(get(OreUtils.ingredient(input)));
				return ret.multiply(0.75F).add("ordo", 3).multiply(1F/Math.max(1, recipe.output.get(0).getCount()));
			} return null; }
		);
		AspectUtils.recipes.add(
			(stack, history) -> { for (SpinningWheelRecipe recipe : ModRecipes.spinningWheelRecipes) {
				// input is usually main + residue, so only [0] is detected
				if (recipe.output.size() == 0 || !OreUtils.equals(recipe.output.get(0), stack)) continue;
				if (OreUtils.historyContains(history, recipe.output.get(0))) return null;
				PlanarArtifice.LOGGER.info("[PA, BEWITCHMENT] Patching for spinning wheel recipe " + stack.getItem().getUnlocalizedName());
				Aspects ret = new Aspects();
				for (Ingredient input : recipe.input) ret.add(get(OreUtils.ingredient(input)));
				return ret.multiply(0.75F).add("fabrico", 3).multiply(1F/Math.max(1, recipe.output.get(0).getCount()));
			} return null; }
		);
	}

	@Override
	public void registerRecipes(RegistryEvent.Register<IRecipe> e) {
		if(!PAConfig.compat.disableAspectCompat) {
			Aspects.yinAspects.add(ThaumcraftCompat.DEMON);
			Aspects.yinAspects.add(ThaumcraftCompat.MOON);
			Aspects.yangAspects.add(ThaumcraftCompat.SUN);
			Aspects.yangAspects.add(ThaumcraftCompat.STAR);
		}
		registerCrucibleRecipe("aconitum_to_seed", "PA_THAUMIC_FARMING@2", OreUtils.count(ModObjects.aconitum_seeds, 4), ModObjects.aconitum, new Aspects("herba", 5, "tempus", 5));
		registerCrucibleRecipe("seed_to_aconitum", "PA_THAUMIC_FARMING@3", OreUtils.count(ModObjects.aconitum, 4), ModObjects.aconitum_seeds, new Aspects("mortuus", 5, "tempus", 5));
		registerCrucibleRecipe("belladonna_to_seed", "PA_THAUMIC_FARMING@2", OreUtils.count(ModObjects.belladonna_seeds, 4), ModObjects.belladonna, new Aspects("herba", 5, "tempus", 5));
		registerCrucibleRecipe("seed_to_belladonna", "PA_THAUMIC_FARMING@3", OreUtils.count(ModObjects.belladonna, 4), ModObjects.belladonna_seeds, new Aspects("praecantatio", 5, "tempus", 5));
		registerCrucibleRecipe("garlic_to_seed", "PA_THAUMIC_FARMING@2", OreUtils.count(ModObjects.garlic_seeds, 4), ModObjects.garlic, new Aspects("herba", 5, "tempus", 5));
		registerCrucibleRecipe("seed_to_garlic", "PA_THAUMIC_FARMING@3", OreUtils.count(ModObjects.garlic, 4), ModObjects.garlic_seeds, new Aspects("sensus", 5, "tempus", 5));
		registerCrucibleRecipe("hellebore_to_seed", "PA_THAUMIC_FARMING@2", OreUtils.count(ModObjects.hellebore_seeds, 4), ModObjects.hellebore, new Aspects("herba", 5, "tempus", 5));
		registerCrucibleRecipe("seed_to_hellebore", "PA_THAUMIC_FARMING@3", OreUtils.count(ModObjects.hellebore, 4), ModObjects.hellebore_seeds, new Aspects("diabolus", 5, "tempus", 5));
		registerCrucibleRecipe("mandrake_to_seed", "PA_THAUMIC_FARMING@2", OreUtils.count(ModObjects.mandrake_seeds, 4), ModObjects.mandrake_root, new Aspects("herba", 5, "tempus", 5));
		registerCrucibleRecipe("seed_to_mandrake", "PA_THAUMIC_FARMING@3", OreUtils.count(ModObjects.mandrake_root, 4), ModObjects.mandrake_seeds, new Aspects("terra", 5, "tempus", 5));
		registerCrucibleRecipe("white_sage_to_seed", "PA_THAUMIC_FARMING@2", OreUtils.count(ModObjects.white_sage_seeds, 4), ModObjects.white_sage, new Aspects("herba", 5, "tempus", 5));
		registerCrucibleRecipe("seed_to_white_sage", "PA_THAUMIC_FARMING@3", OreUtils.count(ModObjects.white_sage, 4), ModObjects.white_sage_seeds, new Aspects("auram", 5, "tempus", 5));
		registerCrucibleRecipe("wormwood_to_seed", "PA_THAUMIC_FARMING@2", OreUtils.count(ModObjects.wormwood_seeds, 4), ModObjects.wormwood, new Aspects("herba", 5, "tempus", 5));
		registerCrucibleRecipe("seed_to_wormwood", "PA_THAUMIC_FARMING@3", OreUtils.count(ModObjects.wormwood, 4), ModObjects.wormwood_seeds, new Aspects("spiritus", 5, "tempus", 5));
	}
}
