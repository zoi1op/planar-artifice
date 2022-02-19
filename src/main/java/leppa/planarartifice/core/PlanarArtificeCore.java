package leppa.planarartifice.core;

import leppa.planarartifice.main.PlanarArtifice;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Map;

@IFMLLoadingPlugin.Name("Planar Artifice FibreSpread Hook")
@IFMLLoadingPlugin.MCVersion("1.12.2")
@IFMLLoadingPlugin.SortingIndex(1004)
@IFMLLoadingPlugin.TransformerExclusions("leppa.planarartifice.core")
// stole from thaumic augmentation -p
public class PlanarArtificeCore implements IFMLLoadingPlugin {
    static final Logger LOGGER = LogManager.getLogger(PlanarArtifice.MODID + "core");
    static Configuration config;
    public static boolean enabled;

    public PlanarArtificeCore() {
        config = new Configuration(new File("config", PlanarArtifice.MODID + ".cfg"));
        enabled = !config.getBoolean("Disable Coremod", "overhaul", false, "");
        LOGGER.info("[PA, CORE] coremod status: " + enabled);
    }

    @Override
    public String[] getASMTransformerClass() { return new String[] {"leppa.planarartifice.core.PATransformer"}; }
    public String getAccessTransformerClass() { return null; }
    public String getModContainerClass() { return null; }
    public String getSetupClass() { return null; }

    @Override
    public void injectData(Map<String, Object> data) { if (!enabled) LOGGER.info("[PA, CORE] coremod disabled"); }
}
