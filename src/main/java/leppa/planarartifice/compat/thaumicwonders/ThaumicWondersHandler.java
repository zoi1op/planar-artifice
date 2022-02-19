package leppa.planarartifice.compat.thaumicwonders;

import com.verdantartifice.thaumicwonders.common.blocks.BlocksTW;
import com.verdantartifice.thaumicwonders.common.items.ItemsTW;
import leppa.planarartifice.compat.PACompatHandler;
import leppa.planarartifice.util.Aspects;
import leppa.planarartifice.util.OreUtils;
import net.minecraft.init.Items;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;

import static leppa.planarartifice.util.AspectUtils.get;
import static leppa.planarartifice.util.AspectUtils.set;

public class ThaumicWondersHandler implements PACompatHandler.ICompatModule {
    @Override
    public void registerOres() {
        OreDictionary.registerOre("clusterEldritchIron", ItemsTW.ELDRITCH_CLUSTER);
        OreDictionary.registerOre("clusterEldritchGold", OreUtils.meta(ItemsTW.ELDRITCH_CLUSTER, 1));
        OreDictionary.registerOre("clusterEldritchCopper", OreUtils.meta(ItemsTW.ELDRITCH_CLUSTER, 2));
        OreDictionary.registerOre("clusterEldritchTin", OreUtils.meta(ItemsTW.ELDRITCH_CLUSTER, 3));
        OreDictionary.registerOre("clusterEldritchSilver", OreUtils.meta(ItemsTW.ELDRITCH_CLUSTER, 4));
        OreDictionary.registerOre("clusterEldritchLead", OreUtils.meta(ItemsTW.ELDRITCH_CLUSTER, 5));
        OreDictionary.registerOre("clusterEldritchCinnabar", OreUtils.meta(ItemsTW.ELDRITCH_CLUSTER, 6));
        OreDictionary.registerOre("clusterEldritchQuartz", OreUtils.meta(ItemsTW.ELDRITCH_CLUSTER, 7));
        OreDictionary.registerOre("clusterEldritchVoid", OreUtils.meta(ItemsTW.ELDRITCH_CLUSTER, 8));
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        set("thaumicwonders:corruption_avatar", new Aspects("tenebrae", 25, "alienis", 25, "vitium", 25, "imperium", 25, "praecantatio", 25));
        set("thaumicwonders:flux_fireball", get(Items.FIRE_CHARGE).add("vitium", 25).add("tenebrae", 10));
        set("thaumicwonders:flying_carpet", get(ItemsTW.FLYING_CARPET));
        set("thaumicwonders:hexamite_primed", get(BlocksTW.HEXAMITE));
        set("thaumicwonders:primal_arrow", get(Items.ARROW));
        set("thaumicwonders:void_portal", get("thaumcraft.cultistportalgreater"));
    }
}
