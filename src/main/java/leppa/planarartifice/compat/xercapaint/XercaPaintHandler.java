package leppa.planarartifice.compat.xercapaint;

import leppa.planarartifice.compat.PACompatHandler;
import leppa.planarartifice.main.PAConfig;
import leppa.planarartifice.main.PlanarArtifice;
import leppa.planarartifice.registry.PAItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.GameData;
import net.minecraftforge.registries.RegistryManager;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.crafting.ShapedArcaneRecipe;

import java.util.Map;
import java.util.Set;

import static leppa.planarartifice.compat.xercapaint.RecipeXercaPaintDye.COLOR_LIST;

public class XercaPaintHandler implements PACompatHandler.ICompatModule {
    @Override
    public void preInit(FMLPreInitializationEvent e) {}

    @Override
    public void init(FMLInitializationEvent e) {
        if (!PAConfig.compat.disableXercaPaintCompat) ThaumcraftApi.registerResearchLocation(new ResourceLocation(PlanarArtifice.MODID, "research/compat_xercapaint.json"));
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {}

    public static void registerRecipes(RegistryEvent.Register<IRecipe> e) {
        // cleansing the cursed crafting recipes, i feel so wrong doing this, partially stolen from crafttweaker
        Set<Map.Entry<ResourceLocation, IRecipe>> recipes = ForgeRegistries.RECIPES.getEntries();
        for (Map.Entry<ResourceLocation, IRecipe> entry : recipes) {
            final ItemStack output = entry.getValue().getRecipeOutput();
            if (!output.isEmpty() && output.equals(new ItemStack(xerca.xercapaint.common.item.Items.ITEM_PALETTE)))
                RegistryManager.ACTIVE.getRegistry(GameData.RECIPES).remove(entry.getKey());
        }
        // no more palette
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("planarartifice:palette"), new ShapedArcaneRecipe(new ResourceLocation(""), "PA_XERCAPAINT", 300, new AspectList().add(Aspect.FIRE, 1).add(Aspect.WATER, 1).add(Aspect.EARTH, 1).add(Aspect.AIR, 1).add(Aspect.ORDER, 1).add(Aspect.ENTROPY, 1), new ItemStack(xerca.xercapaint.common.item.Items.ITEM_PALETTE), "###", " C#", " ##", '#', BlocksTC.slabGreatwood, 'C', PAItems.bismuth_nugget));
        PlanarArtifice.LOGGER.info("[PA] " + String.join(", ", COLOR_LIST));
        for (String color : COLOR_LIST) {
            PlanarArtifice.LOGGER.info("[PA] Registering color " + color);
            e.getRegistry().register(new RecipeXercaPaintDye(color));
        }
        GameRegistry.addShapedRecipe(new ResourceLocation("planarartifice:palette_fake"), new ResourceLocation(""), new ItemStack(xerca.xercapaint.common.item.Items.ITEM_PALETTE), "PD", 'P', new ItemStack(xerca.xercapaint.common.item.Items.ITEM_PALETTE), 'D', "dye");
    }
}
