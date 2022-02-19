package leppa.planarartifice.compat.xercapaint;

import leppa.planarartifice.compat.PACompatHandler;
import leppa.planarartifice.main.PAConfig;
import leppa.planarartifice.main.PlanarArtifice;
import leppa.planarartifice.registry.PAItems;
import leppa.planarartifice.util.Aspects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.GameData;
import net.minecraftforge.registries.RegistryManager;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import xerca.xercapaint.common.item.Items;

import java.util.Map;
import java.util.Set;

import static leppa.planarartifice.compat.xercapaint.RecipeXercaPaintDye.COLOR_LIST;
import static leppa.planarartifice.util.AspectUtils.add;

public class XercaPaintHandler implements PACompatHandler.ICompatModule {
    @Override
    public void preInit(FMLPreInitializationEvent e) {}

    @Override
    public void init(FMLInitializationEvent e) {
        if (PAConfig.compat.disableXercaPaintCompat) return;
        ThaumcraftApi.registerResearchLocation(new ResourceLocation(PlanarArtifice.MODID, "research/compat_xercapaint.json"));
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {}

    @Override
    public void registerRecipes(RegistryEvent.Register<IRecipe> e) {
        if (PAConfig.compat.disableXercaPaintCompat) return;
        // cleansing the cursed crafting recipes, i feel so wrong doing this, partially stolen from crafttweaker
        Set<Map.Entry<ResourceLocation, IRecipe>> recipes = ForgeRegistries.RECIPES.getEntries();
        for (Map.Entry<ResourceLocation, IRecipe> entry : recipes) {
            final ItemStack output = entry.getValue().getRecipeOutput();
            if (!output.isEmpty() && output.equals(new ItemStack(xerca.xercapaint.common.item.Items.ITEM_PALETTE)))
                RegistryManager.ACTIVE.getRegistry(GameData.RECIPES).remove(entry.getKey());
        }
        // no more palette
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("planarartifice:palette"), new ShapedArcaneRecipe(new ResourceLocation(""), "PA_XERCAPAINT", 300, Aspects.getPrimals(), new ItemStack(xerca.xercapaint.common.item.Items.ITEM_PALETTE), "###", " C#", " ##", '#', BlocksTC.slabGreatwood, 'C', PAItems.bismuth_nugget));
        PlanarArtifice.LOGGER.info("[PA] " + String.join(", ", COLOR_LIST));
        for (String color : COLOR_LIST) {
            PlanarArtifice.LOGGER.info("[PA] Registering color " + color);
            e.getRegistry().register(new RecipeXercaPaintDye(color));
        }
        GameRegistry.addShapedRecipe(new ResourceLocation("planarartifice:palette_fake"), new ResourceLocation(""), new ItemStack(xerca.xercapaint.common.item.Items.ITEM_PALETTE), "PD", 'P', new ItemStack(xerca.xercapaint.common.item.Items.ITEM_PALETTE), 'D', "dye");
    }

    public static void registerOresLate() {
        if (PAConfig.compat.disableXercaPaintCompat) return;
        for (String color : RecipeXercaPaintDye.COLOR_LIST)
            for (ItemStack stack : OreDictionary.getOres("dye" + color)) {
                PlanarArtifice.LOGGER.info("[PA, ORE] Registering " + stack.getItem() + " to " + stack.getMetadata());
                if (!stack.isEmpty()) OreDictionary.registerOre("dye", stack);
            }
    }

    @Override
    public void registerAspects() {
        add(Items.ITEM_PALETTE, new Aspects("tinctura", 25));
        add(Items.ITEM_CANVAS, new Aspects("tinctura", 10));
    }
}
