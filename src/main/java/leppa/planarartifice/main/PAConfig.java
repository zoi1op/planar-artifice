package leppa.planarartifice.main;

import net.minecraftforge.common.config.Config;

@Config(modid = PlanarArtifice.MODID)
public class PAConfig {
    @Config.Ignore
    public static final String key = PlanarArtifice.MODID + ".config.";
    public static Compat compat = new Compat();
    public static Overhauls overhaul = new Overhauls();
    public static Balance balance = new Balance(); // new balance lmao
    public static Client client = new Client();

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
        @Config.LangKey(key + "xercapaintcompat")
        @Config.Name("Disable Joy of Painting Compat")
        public boolean disableXercaPaintCompat = false;
        @Config.LangKey(key + "thaumicpotatoescompat")
        @Config.Name("Disable Thaumic Potatoes Compat")
        public boolean disableThaumicPotatoesCompat = false;
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
        @Config.LangKey(key + "disable_coremod")
        @Config.Name("Disable Coremod")
        public boolean disableCoremod = false;
        @Config.LangKey(key + "gelum_is_ignis_perditio")
        @Config.Name("Disable Gelum Aspect Change")
        public boolean disableGelumAspectChange = false;
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
        @Config.LangKey(key + "taint_feature_gen")
        @Config.Name("Taint Feature Generation Rate")
        @Config.RangeDouble(min = 0, max = 1)
        public double taintFeatureGenRate = 0.05;
        @Config.LangKey(key + "centrifuge_flux_rate")
        @Config.Name("Centrifuge Flux Rate")
        @Config.RangeDouble(min = 0, max = 1)
        public double centrifugeFluxRate = 0.25;
        @Config.LangKey(key + "thaum_coat_vis_drain")
        @Config.Name("Thaumaturgist's Coat Passive Vis Drain")
        @Config.RangeInt(min = 0)
        public int thaumCoatVisDrain = 200;
    }

    public static class Client {
        @Config.LangKey(key + "knowledge_icons_per_row")
        @Config.Name("Knowledge Icons per Row")
        @Config.RangeInt(min = 1)
        public int knowledgeIconsPerRow = 7;
    }
}