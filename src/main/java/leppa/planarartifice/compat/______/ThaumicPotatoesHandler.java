package leppa.planarartifice.compat.______;

import amerifrance.guideapi.api.GuideAPI;
import com.zxc74171.thaumicpotatoes.guidebooks.GuideBookVol1;
import com.zxc74171.thaumicpotatoes.init.ModItems;
import leppa.planarartifice.compat.PACompatHandler;
import leppa.planarartifice.main.PAConfig;
import leppa.planarartifice.registry.PARecipes;
import leppa.planarartifice.util.Aspects;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import thaumcraft.api.crafting.IDustTrigger;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.common.lib.crafting.DustTriggerOre;
import thaumcraft.common.lib.crafting.DustTriggerSimple;

import static leppa.planarartifice.main.PlanarArtifice.MODID;

public class ThaumicPotatoesHandler implements PACompatHandler.ICompatModule {
    // . //

    @Override
    public void registerRecipes(RegistryEvent.Register<IRecipe> e) {
        // this is a shit way of doing it
        if (PAConfig.compat.disableThaumicPotatoesCompat) return;
        IDustTrigger.triggers.remove(IDustTrigger.triggers.get(1));
        IDustTrigger.triggers.remove(IDustTrigger.triggers.get(0));
        IDustTrigger.registerDustTrigger(new DustTriggerSimple("!gotdream", Blocks.BOOKSHELF, GuideAPI.getBookToStack().get(GuideBookVol1.guideBookv1)));
        IDustTrigger.registerDustTrigger(new DustTriggerOre("!gotdream", "bookshelf", GuideAPI.getBookToStack().get(GuideBookVol1.guideBookv1)));
        GameRegistry.addShapedRecipe(new ResourceLocation(MODID, "revive_thaumaturgy"), new ResourceLocation(""), new ItemStack(ItemsTC.thaumonomicon), "#", '#', new ItemStack(ModItems.TRUTH));
        PARecipes.registerCrucibleRecipe("potato_thaum", "PA_THAUMIC_FARMING", ModItems.THAUMIC_POTATO, Items.POTATO, new Aspects("praecantatio", 5));
    }
}
