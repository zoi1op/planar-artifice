package leppa.planarartifice.compat.storage;

import appeng.api.AEApi;
import appeng.api.definitions.IBlockDefinition;
import appeng.api.definitions.IDefinitions;
import appeng.api.definitions.IItemDefinition;
import leppa.planarartifice.compat.PACompatHandler;
import leppa.planarartifice.main.PlanarArtifice;
import leppa.planarartifice.util.Aspects;
import leppa.planarartifice.util.OreUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import thaumcraft.api.ThaumcraftApi;

import static leppa.planarartifice.registry.PARecipes.registerCrucibleRecipe;

public class AE2Handler implements PACompatHandler.ICompatModule {
    IDefinitions api = AEApi.instance().definitions();
    public static ItemStack get(IItemDefinition stack) { return stack.maybeStack(1).orElse(ItemStack.EMPTY); }
    public static ItemStack get(IBlockDefinition stack) { return stack.maybeStack(1).orElse(ItemStack.EMPTY); }

    @Override
    public void init(FMLInitializationEvent e) {
        ThaumcraftApi.registerResearchLocation(new ResourceLocation(PlanarArtifice.MODID, "research/compat_ae2.json"));
    }

    @Override
    public void registerRecipes(RegistryEvent.Register<IRecipe> e) {
        registerCrucibleRecipe("grow_certus", "PA_THAUMIC_FARMING_AE2", get(api.materials().purifiedCertusQuartzCrystal()), get(api.items().crystalSeed()), new Aspects("vitreus", 5, "tempus", 5));
        registerCrucibleRecipe("grow_quartz", "PA_THAUMIC_FARMING_AE2", get(api.materials().purifiedNetherQuartzCrystal()), OreUtils.nbtInteger(get(api.items().crystalSeed()), "progress", 600), new Aspects("vitreus", 5, "tempus", 5));
        registerCrucibleRecipe("grow_fluix", "PA_THAUMIC_FARMING_AE2", get(api.materials().purifiedFluixCrystal()), OreUtils.nbtInteger(get(api.items().crystalSeed()), "progress", 1200), new Aspects("vitreus", 5, "spatio", 5,  "tempus", 5));
    }
}
