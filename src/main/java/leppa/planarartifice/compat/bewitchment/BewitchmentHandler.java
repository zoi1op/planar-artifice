package leppa.planarartifice.compat.bewitchment;

import com.bewitchment.common.integration.thaumcraft.ThaumcraftCompat;
import leppa.planarartifice.compat.PACompatHandler;
import leppa.planarartifice.main.PAConfig;
import leppa.planarartifice.recipe.CrucibleRecipeRandomCrystal;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class BewitchmentHandler implements PACompatHandler.ICompatModule {
    @Override
    public void preInit(FMLPreInitializationEvent e) {}

    @Override
    public void init(FMLInitializationEvent e) {
        if (!PAConfig.compat.disableAspectCompat) {
            CrucibleRecipeRandomCrystal.yinAspects.add(ThaumcraftCompat.DEMON);
            CrucibleRecipeRandomCrystal.yinAspects.add(ThaumcraftCompat.MOON);
            CrucibleRecipeRandomCrystal.yangAspects.add(ThaumcraftCompat.SUN);
            CrucibleRecipeRandomCrystal.yangAspects.add(ThaumcraftCompat.STAR);
        }
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {}
}