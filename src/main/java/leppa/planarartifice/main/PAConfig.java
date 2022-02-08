package leppa.planarartifice.main;

import net.minecraftforge.common.config.Config;

@Config(modid = PlanarArtifice.MODID)
public class PAConfig {
    @Config.Ignore
    public static final String key = PlanarArtifice.MODID + ".config.";
    public static Compat compat = new Compat();
    public static Overhauls overhaul = new Overhauls();
    public static Balance balance = new Balance(); // new balance lmao

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

    public static class Overhauls {
        @Config.Ignore
        public static final String key = PAConfig.key + "overhaul.";
        @Config.LangKey(key + "ingot_re_aspect")
        @Config.Name("Disable Aspect Overhaul for Ingot Types")
        public boolean disableIngotReaspect = false;
        @Config.LangKey(key + "alloy_re_aspect")
        @Config.Name("Disable Aspect Overhaul for Alloy Types")
        public boolean disableAlloyReaspect = false;
    }

    public static class Balance {
        @Config.Ignore
        public static final String key = PAConfig.key + "balance.";
        @Config.LangKey(key + "bismuth_crash_cost")
        @Config.Name("Bismuth Crash Cost")
        @Config.RangeInt(min = 1)
        public int bismuthCrashCost = 10;
        @Config.LangKey(key + "duplication_cost")
        @Config.Name("Duplication Cost")
        @Config.RangeInt(min = 1)
        public int duplicationCost = 20;
    }
}