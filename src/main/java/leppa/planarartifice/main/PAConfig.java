package leppa.planarartifice.main;

import net.minecraftforge.common.config.Config;

@Config(modid = PlanarArtifice.MODID)
public class PAConfig {
    @Config.Ignore
    public static final String key = PlanarArtifice.MODID + ".config.";
    public static Compat compat = new Compat();

    public static class Compat {
        @Config.Ignore
        public static final String key = PAConfig.key + "compat.";
        @Config.LangKey(key + "tacompat")
        @Config.Name("Disable Thaumic Addition Compat")
        public boolean disableTACompat = false;
        @Config.LangKey(key + "taextracompat")
        @Config.Name("Disable Thaumic Addition Extra Compat")
        public boolean disableTAExtraCompat = false;
        @Config.LangKey(key + "tinkerscompat")
        @Config.Name("Disable Tinkers Construct Compat")
        public boolean disableTinkersCompat = false;
        @Config.LangKey(key + "aspectcompat")
        @Config.Name("Disable Modded Aspect in Random Crystal Crafting")
        public boolean disableAspectCompat = false;
    }
}