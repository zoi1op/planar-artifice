package leppa.planarartifice.compat.aether;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.virtualesence.aetheradditions.init.ModBlocks;
import com.virtualesence.aetheradditions.init.ModItems;
import leppa.planarartifice.compat.PACompatHandler;
import leppa.planarartifice.util.Aspects;
import net.minecraft.init.Items;

import static leppa.planarartifice.util.AspectUtils.*;

public class AetherAdditionsHandler implements PACompatHandler.ICompatModule {
    @Override
    public void registerAspects() {
        add(ModItems.BRUH, new Aspects("caeles", 500, "humanus", 500, "spatio", 500, "tempus", 500, "tinctura", 500, "ordo", 500));
        add(ModItems.SKYROOT_BOWL, new Aspects("vacuos", 5));
        add(ModItems.ICESTONE_BOWL, new Aspects("vacuos", 5));
        add(ModItems.HOLYSTONE_STICK, get(BlocksAether.holystone).multiply(0.5F*0.75F));
        set(ModItems.HOLYSTONE_PEBBLE, new Aspects("terra"));
        set(ModItems.QUICKSOIL_DUST, new Aspects("terra", "perditio"));
        set(ModItems.GILDED_COOKIE, get(Items.COOKIE).add("desiderium", 15).add("aer", 15));
        set(ModItems.GODLEN_SWET_JELLY, get(ModItems.BLUE_SWET_JELLY).add("desiderium", 1));
        set(ModItems.DARK_SWET_JELLY, get(ModItems.BLUE_SWET_JELLY).add("vitium", 1));
        set(ModItems.GREEN_SWET_JELLY, get(ModItems.BLUE_SWET_JELLY).add("permutatio", 1));
        add(ModItems.PINK_BABY_SWET, new Aspects("bestia", 5));
        set(ModBlocks.BLOOD_MOSS_HOLYSTONE, get(BlocksAether.mossy_holystone).add("mortuus", 1));
        set(ModBlocks.OBSERVING_GLASS, get(BlocksAether.quicksoil_glass).add("visum", 5, "sensus"));
    }
}
