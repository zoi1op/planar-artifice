package leppa.planarartifice.compat.twilightforest;

import leppa.planarartifice.compat.PACompatHandler;
import leppa.planarartifice.util.Aspects;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

import static leppa.planarartifice.util.AspectUtils.set;

public class TwilightForestHandler implements PACompatHandler.ICompatModule {
    @Override
    public void postInit(FMLPostInitializationEvent e) {
        set("twilightforest:adherent", new Aspects("ordo", 25, "auram", 25, "machina", 10, "spiritus", 10, "humanus", 10));
        set("twilightforest:harbinger_cube", new Aspects("ordo", 10, "auram", 5, "machina", 10, "mortuus", 10));
        set("twilightforest:roving_cube", new Aspects("machina", 5, "motus", 5));
        set("twilightforest:cube_of_annihilation", new Aspects("machina", 5, "exitium", 5, "perditio", 5));

    }
}
