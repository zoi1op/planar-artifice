package leppa.planarartifice.compat.embers;

import leppa.planarartifice.compat.PACompatHandler;
import leppa.planarartifice.util.Aspects;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import teamroots.embers.RegistryManager;

import static leppa.planarartifice.util.AspectUtils.add;

public class EmbersHandler implements PACompatHandler.ICompatModule {
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
