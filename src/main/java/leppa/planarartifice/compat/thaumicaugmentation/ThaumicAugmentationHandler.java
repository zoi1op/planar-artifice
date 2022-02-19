package leppa.planarartifice.compat.thaumicaugmentation;

import leppa.planarartifice.compat.PACompatHandler;
import leppa.planarartifice.util.Aspects;
import leppa.planarartifice.util.OreUtils;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import thecodex6824.thaumicaugmentation.api.TABlocks;
import thecodex6824.thaumicaugmentation.api.TAItems;

import static leppa.planarartifice.util.AspectUtils.*;

public class ThaumicAugmentationHandler implements PACompatHandler.ICompatModule {
    @Override
    public void registerAspects() {
        add(OreUtils.meta(TABlocks.STONE, 3), new Aspects("tempus", 5));
        add(OreUtils.meta(TABlocks.STONE, 5), new Aspects("tempus", 5));
        add(OreUtils.meta(TABlocks.STONE, 8), new Aspects("tempus", 5));
        add(OreUtils.meta(TABlocks.STONE, 9), new Aspects("tempus", 5));
        add(OreUtils.meta(TABlocks.STONE, 10), new Aspects("tempus", 5));
        add(TABlocks.BARS, new Aspects("tempus", 5));
        add(OreUtils.meta(TABlocks.CAPSTONE, 1), new Aspects("tempus", 5));
        add(OreUtils.meta(TABlocks.CAPSTONE, 3), new Aspects("tempus", 5));
        add(TAItems.FOCUS_ANCIENT, new Aspects("tempus", 5));
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        set("thaumicaugmentation:eldritch_golem", get("thaumcraft.eldritchgolem"));
        set("thaumicaugmentation:eldritch_guardian", get("thaumcraft.eldritchguardian"));
        set("thaumicaugmentation:eldritch_warden", get("thaumcraft.eldritchwarden"));
        set("thaumicaugmentation:golem_orb", new Aspects("potentia", 5, "praecantatio", 5));
    }
}
