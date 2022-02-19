package leppa.planarartifice.compat.bewitchment;

import com.bewitchment.common.integration.thaumcraft.ThaumcraftCompat;
import com.bewitchment.registry.ModObjects;
import leppa.planarartifice.compat.PACompatHandler;
import leppa.planarartifice.main.PAConfig;
import leppa.planarartifice.util.Aspects;
import leppa.planarartifice.util.OreUtils;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;

import static leppa.planarartifice.registry.PARecipes.registerCrucibleRecipe;

public class BewitchmentHandler implements PACompatHandler.ICompatModule {
	public static boolean active = false;
	public BewitchmentHandler() {
		active = true;
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
