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
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import thaumcraft.api.crafting.IDustTrigger;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.common.lib.crafting.DustTriggerOre;
import thaumcraft.common.lib.crafting.DustTriggerSimple;

import static leppa.planarartifice.main.PlanarArtifice.MODID;
import static leppa.planarartifice.util.AspectUtils.*;

public class ThaumicPotatoesHandler implements PACompatHandler.ICompatModule {
    // . //
    @Override
    public void registerAspects() {
        set(ModItems.RECORD_FAREWELL, get(Items.RECORD_11).add("herba", 5));
        set(ModItems.AMMO_NORMAL, get(Items.POTATO).add("metallum", 1));
        set(ModItems.AMMO_BAKED, get(Items.BAKED_POTATO).add("metallum", 1));
        set(ModItems.AMMO_POISONOUS, get(Items.POISONOUS_POTATO).add("metallum", 1));
        set(ModItems.PETRIFIED_POTATO, get(Items.POTATO).add("ignis", 2));
        set(ModItems.MASHEDPOTATOES, get(Items.POTATO).add("exitium", 5, "perditio"));
        set(ModItems.ELDRITCH_BLOOD, new Aspects("ordo", 15, "mortuus", 5, "vitium", 5, "alienis", 15, "praecantatio", 10));
        set(ModItems.ELDRITCH_FLESH, new Aspects("perditio", 15, "humanus", 5, "mortuus", 2, "vitium", 5, "alienis", 15));
        add(ModItems.ELDRITCH_HEART, get(Items.POTATO));
        set(ModItems.VODKA, Aspects.getPrimals());
        set(ModItems.TRUTH, get(ItemsTC.thaumonomicon).add("exitium", 15, "perditio"));
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        set("thaumicpotatoes:ammo_normal", get(ModItems.AMMO_NORMAL));
        set("thaumicpotatoes:ammo_baked", get(ModItems.AMMO_BAKED));
        set("thaumicpotatoes:ammo_poisonous", get(ModItems.AMMO_POISONOUS));
        set("thaumicpotatoes:frenchfries", new Aspects("volatus", 5, "ignis", 5, "victus", 15, "herba", 15, "terra", 5));
        set("thaumicpotatoes:potatofangs", get("evocation_fangs").add(get(Items.POTATO).multiply(2F)));
        set("thaumicpotatoes:potatoprojectile", get(Items.POTATO).multiply(2F).add("praecantatio", 5));
        set("thaumicpotatoes:ziemniak", get(Items.POTATO).multiply(3F).add("ordo", 25).add("praecantatio", 15).add("bestia", 25)); // ordo, blood
        set("thaumicpotatoes:jagaimo", get(Items.POTATO).multiply(3F).add("perditio", 25).add("praecantatio", 15).add("exanimis", 25)); // perditio, flesh
        set("thaumicpotatoes:frycook", new Aspects("humanus", 25, "terra", 25, "caeles", 25, "vitium", 25, "praecantatio", 25, "ignis", 25, "alienis", 25));
    }

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
