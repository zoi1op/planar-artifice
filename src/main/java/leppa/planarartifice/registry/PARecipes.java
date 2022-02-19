package leppa.planarartifice.registry;

import com.bewitchment.common.integration.thaumcraft.ThaumcraftCompat;
import com.zeitheron.thaumicadditions.init.KnowledgeTAR;
import leppa.planarartifice.compat.bewitchment.BewitchmentHandler;
import leppa.planarartifice.compat.thaumicadditions.ThaumicAdditionsHandler;
import leppa.planarartifice.enchantment.EnumInfusionEnchantmentII;
import leppa.planarartifice.enchantment.InfusionEnchantmentRecipeII;
import leppa.planarartifice.main.PAConfig;
import leppa.planarartifice.recipe.CrucibleRecipeRandomCrystal;
import leppa.planarartifice.recipe.RecipePotionMixer;
import leppa.planarartifice.recipe.RecipeTransmutation;
import leppa.planarartifice.util.Aspects;
import leppa.planarartifice.util.OreUtils;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import soot.Registry;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.crafting.*;
import thaumcraft.api.items.ItemsTC;

import java.util.ArrayList;
import java.util.List;

import static leppa.planarartifice.main.PlanarArtifice.MODID;
import static thaumcraft.api.ThaumcraftApiHelper.makeCrystal;

public class PARecipes {
	
    static ResourceLocation defaultGroup = new ResourceLocation("");

    public static void registerRecipes(Register<IRecipe> e) {
    	registerArcaneRecipes(e);
    	registerCrucibleRecipes(e);
    	registerInfusionRecipes(e);
//    	registerPotionMixerRecipes(e);
    	registerTransmutationRecipes(e);
    }
    
	
	private static void registerArcaneRecipes(Register<IRecipe> e) {
        registerShapedArcaneRecipe("alkimium_smeltery", "PA_ALKIMIUM_APPLICATIONS@2", 100, new Aspects("aqua"), PABlocks.alkimium_smeltery, "#C#", "ADA", "AAA", '#', PAItems.alkimium_plate, 'A', "plateBrass", 'C', BlocksTC.smelterBasic, 'D', PABlocks.alchemical_alkimium_construct);
        registerShapedArcaneRecipe("alkimium_smeltery_thaumium", "PA_ALKIMIUM_THAUMIUM_SMELTERY", 300, new Aspects("aqua"), PABlocks.alkimium_smeltery_thaumium, "#C#", "ADA", "AAA", '#', PAItems.alkimium_plate, 'A', OreUtils.meta(ItemsTC.plate, 2), 'C', PABlocks.alkimium_smeltery, 'D', PABlocks.alchemical_alkimium_construct);
        registerShapedArcaneRecipe("alkimium_smeltery_void", "PA_ALKIMIUM_VOID_SMELTERY@2", 1000, new Aspects("aqua"), PABlocks.alkimium_smeltery_void, "#C#", "ADA", "AAA", '#', PAItems.alkimium_plate, 'A', OreUtils.meta(ItemsTC.plate, 3), 'C', PABlocks.alkimium_smeltery_thaumium, 'D', BlocksTC.metalAlchemicalAdvanced);
        registerShapedArcaneRecipe("alkimium_centrifuge", "PA_ALKIMIUM_APPLICATIONS", 100, new Aspects("perditio", "perditio"), PABlocks.alkimium_centrifuge, " P ", "C#M", " P ", '#', BlocksTC.centrifuge, 'P', BlocksTC.tube, 'C', PABlocks.alchemical_alkimium_construct, 'M', ItemsTC.mechanismComplex);
        registerShapedArcaneRecipe("alchemical_alkimium_construct", "PA_ALKIMIUM@2", 100, new Aspects("aqua"), PABlocks.alchemical_alkimium_construct, "#v#", "pwp", "#v#", '#', PAItems.alkimium_plate, 'p', BlocksTC.tube, 'v', BlocksTC.tubeValve, 'w', BlocksTC.plankSilverwood);
        registerShapedArcaneRecipe("teleporter_matrix", "PA_MIRROR_TELEPORTER@2", 300, new Aspects("ordo", "perditio"), PABlocks.teleporter_matrix, "AHA", "MDM", "AHA", 'A', BlocksTC.stoneArcaneBrick, 'M', ItemsTC.mirroredGlass, 'H', ItemsTC.alumentum, 'D', PAItems.dimensional_singularity);
//        registerShapedArcaneRecipe("teleporter_matrix2", "PA_MIRROR_TELEPORTER@2", 300, new Aspects("ordo", "perditio"), PABlocks.teleporter_matrix, "AHA", "MDM", "AHA", 'A', BlocksTC.stoneArcaneBrick, 'M', ItemsTC.mirroredGlass, 'H', ItemsTC.alumentum, 'D', PAItems.dimensional_curiosity);
        registerShapedArcaneRecipe("flux_scrubber", "PA_FLUX_SCRUBBER", 275, Aspects.getPrimals(), PABlocks.flux_scrubber, "IGI", "IAI", "BQB", 'B', PABlocks.bismuth_block, 'A', PABlocks.alchemical_alkimium_construct, 'G', ItemsTC.mechanismSimple, 'I', "plateIron", 'Q', ItemsTC.alumentum);
        registerShapelessArcaneRecipe("alkimium_improved_distillation_aux_1", "PA_ALKIMIUM_DISTILLATION", 70, new Aspects("ordo"), PABlocks.smelter_aux, BlocksTC.smelterAux, PABlocks.alchemical_alkimium_construct);
        registerShapelessArcaneRecipe("alkimium_improved_distillation_aux_2", "PA_ALKIMIUM_DISTILLATION", 70, new Aspects("ordo"), PABlocks.smelter_vent, BlocksTC.smelterVent, PABlocks.alchemical_alkimium_construct);
        registerShapelessArcaneRecipe("alkimium_improved_distillation_aux_3", "PA_ALKIMIUM_DISTILLATION", 70, new Aspects("perditio"), BlocksTC.smelterAux, PABlocks.smelter_aux);
        registerShapelessArcaneRecipe("alkimium_improved_distillation_aux_4", "PA_ALKIMIUM_DISTILLATION", 70, new Aspects("perditio"), BlocksTC.smelterVent, PABlocks.smelter_vent);
        registerShapedArcaneRecipe("flux_vent", "PA_BISMUTH_CASTERS_GAUNTLET@1", 75, new Aspects(), PAItems.flux_venting_circuit, " # ", "QRV", '#', makeCrystal(Aspect.FLUX), 'Q', Items.QUARTZ, 'R', Items.REPEATER, 'V', Blocks.REDSTONE_TORCH);
        registerShapedArcaneRecipe("bismuth_caster", "PA_BISMUTH_CASTERS_GAUNTLET", 175, Aspects.getPrimals(), PAItems.bismuth_caster, " VO", "MGB", "BB ", 'B', PAItems.bismuth_plate, 'V', PAItems.flux_venting_circuit, 'M', ItemsTC.mirroredGlass, 'O', ItemsTC.visResonator, 'G', ItemsTC.casterBasic);
        registerShapedArcaneRecipe("aura_meter", "PA_BISMUTH", 25, Aspects.getPrimals(), PAItems.aura_meter, "A", "B", "C", 'A', Blocks.GLASS_PANE, 'B', makeCrystal(Aspect.AIR), 'C', PAItems.bismuth_plate);
        registerShapedArcaneRecipe("bismuth_claymore", "PA_BISMUTH", 0, new Aspects("ignis"), PAItems.bismuth_claymore, " B ", "BSB", " S ", 'B', PAItems.bismuth_ingot, 'S', Items.STICK);
        PABlocks.glass.recipe();
        PABlocks.glass_clear.recipe();
        PABlocks.glass_scratched.recipe();
        PABlocks.glass_crystal.recipe();
        PABlocks.glass_bright.recipe();
        PABlocks.glass_dim.recipe();
        PABlocks.glass_dark.recipe();
        PABlocks.glass_ghostly.recipe();
        PABlocks.glass_ethereal.recipe();
        PABlocks.glass_foreboding.recipe();
        PABlocks.glass_strong.recipe();
        PABlocks.glass.craft(Aspect.ORDER,  "PA_GLASSWORK_COSMETIC");
        PABlocks.glass_clear.craft(Aspect.FIRE, "PA_GLASSWORK_COSMETIC@2");
        PABlocks.glass_scratched.craft(PAAspects.TIME, "PA_GLASSWORK_COSMETIC@2");
        PABlocks.glass_crystal.craft(PAAspects.DIMENSIONS, "PA_GLASSWORK_COSMETIC@3");
        PABlocks.glass_bright.craft(Aspect.LIGHT, "PA_GLASSWORK_BRIGHT");
        PABlocks.glass_dim.craft(Aspect.ENTROPY, "PA_GLASSWORK_DIM");
        PABlocks.glass_dark.craft(Aspect.DARKNESS, "PA_GLASSWORK_DARK");
        PABlocks.glass_ghostly.craft(Aspect.SOUL,  "PA_GLASSWORK_GHOST@2");
        PABlocks.glass_ethereal.craft(Aspect.TRAP,  "PA_GLASSWORK_GHOST@3");
        PABlocks.glass_foreboding.craft(Aspect.AVERSION, "PA_GLASSWORK_GHOST");
        PABlocks.glass_strong.craft(Aspect.PROTECT, "PA_GLASSWORK_STRONG");
        registerShapedArcaneRecipe("glass_cutter", "PA_GLASSWORK", 25, new Aspects("ordo"), PAItems.glass_cutter, "  B", " S ", "S  ", 'B', "nuggetQuartz", 'S', Items.STICK);
        registerShapedArcaneRecipe("glass_cutter2", "PA_GLASSWORK", 25, new Aspects("ordo"), PAItems.glass_cutter, "B  ", " S ", "  S", 'B', "nuggetQuartz", 'S', Items.STICK);
	}

    public static void registerShapedArcaneRecipe(String resource, String research, int vis, Aspects aspects, Item stack, Object ...recipes) { registerShapedArcaneRecipe(resource, research, vis, aspects, new ItemStack(stack), recipes); }
    public static void registerShapedArcaneRecipe(String resource, String research, int vis, Aspects aspects, Block stack, Object ...recipes) { registerShapedArcaneRecipe(resource, research, vis, aspects, new ItemStack(stack), recipes); }
    public static void registerShapedArcaneRecipe(String resource, String research, int vis, Aspects aspects, ItemStack stack, Object ...recipes) {
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(MODID, resource), new ShapedArcaneRecipe(defaultGroup, research, vis, aspects, stack, CraftingHelper.parseShaped(recipes)));
    }

    public static void registerShapelessArcaneRecipe(String resource, String research, int vis, Aspects aspects, Item stack, Object ...recipes) { registerShapelessArcaneRecipe(resource, research, vis, aspects, new ItemStack(stack), recipes); }
    public static void registerShapelessArcaneRecipe(String resource, String research, int vis, Aspects aspects, Block stack, Object ...recipes) { registerShapelessArcaneRecipe(resource, research, vis, aspects, new ItemStack(stack), recipes); }
    public static void registerShapelessArcaneRecipe(String resource, String research, int vis, Aspects aspects, ItemStack stack, Object ...recipes) {
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(MODID, resource), new ShapelessArcaneRecipe(defaultGroup, research, vis, aspects, stack, recipes));
    }

	private static void registerCrucibleRecipes(Register<IRecipe> e) {
        registerCrucibleRecipe("alkimium_ingot", "METALLURGY@2", PAItems.alkimium_ingot, OreUtils.meta(ItemsTC.ingots, 2), new Aspects("alkimia", 5, "ordo",  5));
        registerCrucibleRecipe("magic_apple", "PA_RARE_ITEMS@4", PAItems.magic_apple, Items.APPLE, new Aspects("praecantatio", 10, "victus", 70, "bestia",  25, "terra",  15));
        registerCrucibleRecipe("alchemical_scribing_tools", "PA_ALCHEMICAL_SCRIBING_TOOLS", PAItems.alchemical_scribing_tools, ItemsTC.scribingTools, new Aspects("auram",  15, "alkimia", 15));
        registerCrucibleRecipe("bismuth", "!Portal", PAItems.bismuth_ingot, ItemsTC.ingots, new Aspects("auram",  20, "potentia", 20));
        registerCrucibleRecipe("endereye", "PA_BUSH_ALCHEMY@1", Items.ENDER_EYE, Items.SPIDER_EYE, new Aspects("spatio", 10, "ignis", 10, "alienis", 20));
        registerCrucibleRecipe("redstone", "PA_BUSH_ALCHEMY@1", Items.REDSTONE, Items.GUNPOWDER, new Aspects("potentia", 5));
        registerCrucibleRecipe("blaze_powder", "PA_BUSH_ALCHEMY@2", new ItemStack(Items.BLAZE_POWDER, 2), Items.GUNPOWDER, new Aspects("ignis", 14, "praecantatio", 5));
        registerCrucibleRecipe("amber_alchemy", "PA_BUSH_ALCHEMY", new ItemStack(ItemsTC.amber, 1), ItemsTC.quicksilver, new Aspects("vinculum",  10));
        registerCrucibleRecipe("quicksilver_alchemy", "PA_BUSH_ALCHEMY", new ItemStack(ItemsTC.quicksilver, 1), ItemsTC.amber, new Aspects("alkimia", 10));
        registerCrucibleRecipe("condensed_crystal_cluster1", "PA_BUSH_ALCHEMY_ESSENTIA@1", PAItems.condensed_crystal_cluster, ItemsTC.salisMundus, new Aspects("ignis", 50));
        registerCrucibleRecipe("condensed_crystal_cluster2", "PA_BUSH_ALCHEMY_ESSENTIA@1", PAItems.condensed_crystal_cluster, ItemsTC.salisMundus, new Aspects(Aspect.WATER, 50));
        registerCrucibleRecipe("condensed_crystal_cluster3", "PA_BUSH_ALCHEMY_ESSENTIA@1", PAItems.condensed_crystal_cluster, ItemsTC.salisMundus, new Aspects("aer", 50));
        registerCrucibleRecipe("condensed_crystal_cluster4", "PA_BUSH_ALCHEMY_ESSENTIA@1", PAItems.condensed_crystal_cluster, ItemsTC.salisMundus, new Aspects("terra",  50));
        registerCrucibleRecipe("condensed_crystal_cluster5", "PA_BUSH_ALCHEMY_ESSENTIA@1", PAItems.condensed_crystal_cluster, ItemsTC.salisMundus, new Aspects("ordo",  50));
        registerCrucibleRecipe("condensed_crystal_cluster6", "PA_BUSH_ALCHEMY_ESSENTIA@1", PAItems.condensed_crystal_cluster, ItemsTC.salisMundus, new Aspects("perditio", 50));
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("random_vis_crystal1"), new CrucibleRecipeRandomCrystal("PA_BUSH_ALCHEMY_ESSENTIA@2", ItemsTC.salisMundus, new Aspects("vitium", 4)));
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("random_vis_crystal2"), new CrucibleRecipeRandomCrystal("PA_BUSH_ALCHEMY_ESSENTIA@2", ItemsTC.salisMundus, new Aspects("tinctura", 20)));
        registerCrucibleRecipe("salis_mundus", "PA_BUSH_ALCHEMY_ESSENTIA", ItemsTC.salisMundus, Items.REDSTONE, new Aspects("praecantatio", 5));
        registerCrucibleRecipe("sugar_conjure", "PA_BUSH_ALCHEMY_CONJURING@1", new ItemStack(Items.SUGAR, 2), Items.SUGAR, new Aspects("aer", PAConfig.balance.duplicationCost));
        registerCrucibleRecipe("ender_pearls", "PA_BUSH_ALCHEMY_CONJURING@1", new ItemStack(Items.ENDER_PEARL, 2), Items.ENDER_PEARL, new Aspects("alienis", PAConfig.balance.duplicationCost));
        registerCrucibleRecipe("redstone2", "PA_BUSH_ALCHEMY_CONJURING@1", new ItemStack(Items.REDSTONE, 2), Items.REDSTONE, new Aspects("potentia", PAConfig.balance.duplicationCost));
        registerCrucibleRecipe("lapis_conjure", "PA_BUSH_ALCHEMY_CONJURING@2", new ItemStack(Items.DYE, 2, 4), OreUtils.meta(Items.DYE, 4), new Aspects("sensus", PAConfig.balance.duplicationCost));
        registerCrucibleRecipe("coal_conjure", "PA_BUSH_ALCHEMY_CONJURING@2", new ItemStack(Items.COAL, 2), Items.COAL, new Aspects("ignis", PAConfig.balance.duplicationCost));
        registerCrucibleRecipe("quartz_conjure", "PA_BUSH_ALCHEMY_CONJURING@2", new ItemStack(Items.QUARTZ, 2), Items.QUARTZ, new Aspects("vitreus", PAConfig.balance.duplicationCost));
        registerCrucibleRecipe("amber_conjure", "PA_BUSH_ALCHEMY_CONJURING", new ItemStack(ItemsTC.amber, 2), ItemsTC.amber, new Aspects("vinculum",  PAConfig.balance.duplicationCost, "vitreus", PAConfig.balance.duplicationCost));
        registerCrucibleRecipe("blaze_conjure", "PA_BUSH_ALCHEMY_CONJURING", new ItemStack(Items.BLAZE_POWDER, 2), Items.BLAZE_POWDER, new Aspects("ignis", PAConfig.balance.duplicationCost, "alkimia", PAConfig.balance.duplicationCost));
        registerCrucibleRecipe("chorus_conjure", "PA_BUSH_ALCHEMY_CONJURING", new ItemStack(Items.CHORUS_FRUIT, 2), Items.CHORUS_FRUIT, new Aspects("alienis", PAConfig.balance.duplicationCost, "herba", PAConfig.balance.duplicationCost));
        registerCrucibleRecipe("flesh_conjure", "PA_BUSH_ALCHEMY_CONJURING", new ItemStack(Items.ROTTEN_FLESH, 2), Items.ROTTEN_FLESH, new Aspects("humanus", PAConfig.balance.duplicationCost, "victus", PAConfig.balance.duplicationCost));
        registerCrucibleRecipe("bone_conjure", "PA_BUSH_ALCHEMY_CONJURING", new ItemStack(Items.BONE, 2), Items.BONE, new Aspects("mortuus", PAConfig.balance.duplicationCost, "victus", PAConfig.balance.duplicationCost));
        registerCrucibleRecipe("cactus_conjure", "PA_BUSH_ALCHEMY_CONJURING", new ItemStack(Blocks.CACTUS, 2), Blocks.CACTUS, new Aspects("tinctura", PAConfig.balance.duplicationCost, "herba", PAConfig.balance.duplicationCost));
        registerCrucibleRecipe("mushroom_conjure", "PA_BUSH_ALCHEMY_CONJURING", new ItemStack(Blocks.BROWN_MUSHROOM, 2), Blocks.BROWN_MUSHROOM, new Aspects("terra",  PAConfig.balance.duplicationCost, "tenebrae", PAConfig.balance.duplicationCost));
        registerCrucibleRecipe("mushroom_conjure2", "PA_BUSH_ALCHEMY_CONJURING", new ItemStack(Blocks.RED_MUSHROOM, 2), Blocks.RED_MUSHROOM, new Aspects("ignis", PAConfig.balance.duplicationCost, "tenebrae", PAConfig.balance.duplicationCost));
        Aspects gunpowderDupe = new Aspects();
        gunpowderDupe.add("exitium", PAConfig.balance.duplicationCost, "ignis").add("perditio", PAConfig.balance.duplicationCost);
        registerCrucibleRecipe("gunpowder_conjure", "PA_BUSH_ALCHEMY_CONJURING", new ItemStack(Items.GUNPOWDER, 2), Items.GUNPOWDER, gunpowderDupe);
        Aspects metalLeveling3 = new Aspects("metallum",  2, "mortuus", 1, "alkimia",  1).add("infernum", 1, "ignis", 2).add("diabolus", 1, "ignis", 2);
        Aspects metalLeveling2 = metalLeveling3.copy().add("ordo",  5).add("desiderium",  10);
        Aspects metalLeveling1 = metalLeveling2.copy().add("praecantatio", 15).add("tinctura", 20);
        Aspects metalRevert = new Aspects("terra",  5, "spiritus",  2, "alkimia",  1, "tempus", 10);
        if (!OreUtils.exists("ingotLead")) {
            registerCrucibleRecipe("iron_to_gold", "PA_BUSH_ALCHEMY_METAL_1", Items.GOLD_INGOT, Items.IRON_INGOT, metalLeveling1);
            registerCrucibleRecipe("gold_to_iron", "PA_BUSH_ALCHEMY_METAL_1", Items.IRON_INGOT, Items.GOLD_INGOT, metalRevert);
        } else if (OreUtils.exists("ingotTin") && OreUtils.exists("ingotCopper") && OreUtils.exists("ingotSilver")) {
            registerCrucibleRecipe("lead_to_tin", "PA_BUSH_ALCHEMY_METAL_3", OreUtils.getFirst("ingotTin"), OreUtils.getFirst("ingotLead"), metalLeveling3);
            registerCrucibleRecipe("tin_to_iron", "PA_BUSH_ALCHEMY_METAL_3", Items.IRON_INGOT, OreUtils.getFirst("ingotTin"), metalLeveling3);
            registerCrucibleRecipe("iron_to_copper", "PA_BUSH_ALCHEMY_METAL_3", OreUtils.getFirst("ingotCopper"), Items.IRON_INGOT, metalLeveling3);
            Aspects goldRecipe = metalLeveling3.copy().add("desiderium",  10).add("sol", 5, null);
            Aspects silverRecipe = metalLeveling3.copy().add("desiderium",  5).add("luna", 5, null);
            if (BewitchmentHandler.active) {
                goldRecipe = goldRecipe.add(ThaumcraftCompat.SUN, 5);
                silverRecipe = silverRecipe.add(ThaumcraftCompat.MOON, 5);
            }
            registerCrucibleRecipe("copper_to_silver", "PA_BUSH_ALCHEMY_METAL_3", OreUtils.getFirst("ingotSilver"), OreUtils.getFirst("ingotCopper"), silverRecipe);
            registerCrucibleRecipe("silver_to_gold", "PA_BUSH_ALCHEMY_METAL_3", Items.GOLD_INGOT, OreUtils.getFirst("ingotSilver"), goldRecipe);
            if (Loader.isModLoaded("soot")) {
                metalRevert = new Aspects("metallum",  10, "desiderium",  10, "tempus", 20);
                if (BewitchmentHandler.active) metalRevert.add(ThaumcraftCompat.SUN, 5);
                registerCrucibleRecipe("gold_to_lead", "PA_BUSH_ALCHEMY_METAL_3", OreUtils.getFirst("ingotLead"), Registry.INGOT_ANTIMONY, metalRevert);
            } else {
                registerCrucibleRecipe("gold_to_lead", "PA_BUSH_ALCHEMY_METAL_3", OreUtils.getFirst("ingotLead"), Items.GOLD_INGOT, metalRevert);
            }
//            registerCrucibleRecipe("gold_to_lead", "PA_BUSH_ALCHEMY_METAL_3", OreUtils.getFirst("ingotLead"), Items.GOLD_INGOT, metalRevert));
        } else {
            registerCrucibleRecipe("lead_to_gold", "PA_BUSH_ALCHEMY_METAL_2", Items.GOLD_INGOT, OreUtils.getFirst("ingotLead"), metalLeveling2);
            registerCrucibleRecipe("gold_to_lead", "PA_BUSH_ALCHEMY_METAL_2", OreUtils.getFirst("ingotLead"), Items.GOLD_INGOT, metalRevert);
        }
        registerCrucibleRecipe("bismuth_to_iron", "PA_BUSH_ALCHEMY_METAL_BISMUTH", Items.IRON_INGOT, PAItems.bismuth_ingot, new Aspects("metallum",  PAConfig.balance.bismuthCrashCost));
        registerCrucibleRecipe("bismuth_to_gold", "PA_BUSH_ALCHEMY_METAL_BISMUTH", Items.GOLD_INGOT, PAItems.bismuth_ingot, new Aspects("desiderium",  PAConfig.balance.bismuthCrashCost));
        if (OreUtils.exists("ingotCopper")) registerCrucibleRecipe("bismuth_to_copper", "PA_BUSH_ALCHEMY_METAL_BISMUTH", OreUtils.getFirst("ingotCopper"), PAItems.bismuth_ingot, new Aspects("permutatio", PAConfig.balance.bismuthCrashCost));
        if (OreUtils.exists("ingotTin")) registerCrucibleRecipe("bismuth_to_tin", "PA_BUSH_ALCHEMY_METAL_BISMUTH", OreUtils.getFirst("ingotTin"), PAItems.bismuth_ingot, new Aspects("vitreus", PAConfig.balance.bismuthCrashCost));
        if (OreUtils.exists("ingotLead")) registerCrucibleRecipe("bismuth_to_lead", "PA_BUSH_ALCHEMY_METAL_BISMUTH", OreUtils.getFirst("ingotLead"), PAItems.bismuth_ingot, new Aspects("ordo",  PAConfig.balance.bismuthCrashCost));
        if (OreUtils.exists("ingotSilver")) {
            if (BewitchmentHandler.active) registerCrucibleRecipe("bismuth_to_silver", "PA_BUSH_ALCHEMY_METAL_BISMUTH", OreUtils.getFirst("ingotSilver"), PAItems.bismuth_ingot, new Aspects(ThaumcraftCompat.MOON, PAConfig.balance.bismuthCrashCost));
            else registerCrucibleRecipe("bismuth_to_silver", "PA_BUSH_ALCHEMY_METAL_BISMUTH", OreUtils.getFirst("ingotSilver"), PAItems.bismuth_ingot, new Aspects("spiritus",  PAConfig.balance.bismuthCrashCost));
        }
        if (OreUtils.exists("ingotNickel")) registerCrucibleRecipe("bismuth_to_nickel", "PA_BUSH_ALCHEMY_METAL_BISMUTH", OreUtils.getFirst("ingotNickel"), PAItems.bismuth_ingot, new Aspects("fabrico", PAConfig.balance.bismuthCrashCost));
        if (OreUtils.exists("ingotAluminium")) registerCrucibleRecipe("bismuth_to_aluminium", "PA_BUSH_ALCHEMY_METAL_BISMUTH", OreUtils.getFirst("ingotAluminium"), PAItems.bismuth_ingot, new Aspects("aer", PAConfig.balance.bismuthCrashCost));
        if (OreUtils.exists("ingotArdite")) registerCrucibleRecipe("bismuth_to_ardite", "PA_BUSH_ALCHEMY_METAL_BISMUTH", OreUtils.getFirst("ingotArdite"), PAItems.bismuth_ingot, new Aspects("spatio", PAConfig.balance.bismuthCrashCost));
        if (OreUtils.exists("ingotCobalt")) registerCrucibleRecipe("bismuth_to_cobalt", "PA_BUSH_ALCHEMY_METAL_BISMUTH", OreUtils.getFirst("ingotCobalt"), PAItems.bismuth_ingot, new Aspects("tempus", PAConfig.balance.bismuthCrashCost));
        if (OreUtils.exists("ingotIridium")) registerCrucibleRecipe("bismuth_to_iridium", "PA_BUSH_ALCHEMY_METAL_BISMUTH", OreUtils.getFirst("ingotIridium"), PAItems.bismuth_ingot, new Aspects("machina", PAConfig.balance.bismuthCrashCost));
        if (OreUtils.exists("ingotPlatinum")) {
            if (ThaumicAdditionsHandler.extraActivated) registerCrucibleRecipe("bismuth_to_platinum", "PA_BUSH_ALCHEMY_METAL_BISMUTH", OreUtils.getFirst("ingotPlatinum"), PAItems.bismuth_ingot, new Aspects(KnowledgeTAR.CAELES, PAConfig.balance.bismuthCrashCost));
            else registerCrucibleRecipe("bismuth_to_platinum", "PA_BUSH_ALCHEMY_METAL_BISMUTH", OreUtils.getFirst("ingotPlatinum"), PAItems.bismuth_ingot, new Aspects("humanus", PAConfig.balance.bismuthCrashCost));
        }
        if (OreUtils.exists("ingotOsmium")) {
            if (ThaumicAdditionsHandler.extraActivated) registerCrucibleRecipe("bismuth_to_osmium", "PA_BUSH_ALCHEMY_METAL_BISMUTH", OreUtils.getFirst("ingotOsmium"), PAItems.bismuth_ingot, new Aspects(KnowledgeTAR.VENTUS, PAConfig.balance.bismuthCrashCost));
            else registerCrucibleRecipe("bismuth_to_osmium", "PA_BUSH_ALCHEMY_METAL_BISMUTH", OreUtils.getFirst("ingotOsmium"), PAItems.bismuth_ingot, new Aspects("vacuos", PAConfig.balance.bismuthCrashCost));
        }
        if (OreUtils.exists("ingotUranium")) registerCrucibleRecipe("bismuth_to_uranium", "PA_BUSH_ALCHEMY_METAL_BISMUTH", OreUtils.getFirst("ingotUranium"), PAItems.bismuth_ingot, new Aspects("mortuus", PAConfig.balance.bismuthCrashCost));
        if (OreUtils.exists("ingotDraconium")) {
            if (ThaumicAdditionsHandler.extraActivated) registerCrucibleRecipe("bismuth_to_draconium", "PA_BUSH_ALCHEMY_METAL_BISMUTH", OreUtils.getFirst("ingotDraconium"), PAItems.bismuth_ingot, new Aspects(KnowledgeTAR.DRACO, PAConfig.balance.bismuthCrashCost));
            else registerCrucibleRecipe("bismuth_to_draconium", "PA_BUSH_ALCHEMY_METAL_BISMUTH", OreUtils.getFirst("ingotDraconium"), PAItems.bismuth_ingot, new Aspects("alienis", PAConfig.balance.bismuthCrashCost));
        }
        if (OreUtils.exists("ingotMithril")) registerCrucibleRecipe("bismuth_to_mithril", "PA_BUSH_ALCHEMY_METAL_BISMUTH", OreUtils.getFirst("ingotMithril"), PAItems.bismuth_ingot, new Aspects("praecantatio", PAConfig.balance.bismuthCrashCost));
        if (OreUtils.exists("ingotTitanium")) registerCrucibleRecipe("bismuth_to_titanium", "PA_BUSH_ALCHEMY_METAL_BISMUTH", OreUtils.getFirst("ingotTitanium"), PAItems.bismuth_ingot, new Aspects("praemunio", PAConfig.balance.bismuthCrashCost));
        if (OreUtils.exists("ingotTungsten")) registerCrucibleRecipe("bismuth_to_tungsten", "PA_BUSH_ALCHEMY_METAL_BISMUTH", OreUtils.getFirst("ingotTungsten"), PAItems.bismuth_ingot, new Aspects("aversio", PAConfig.balance.bismuthCrashCost));
        if (Loader.isModLoaded("soot")) registerCrucibleRecipe("bismuth_to_antimony", "PA_BUSH_ALCHEMY_METAL_BISMUTH", Registry.INGOT_ANTIMONY, PAItems.bismuth_ingot, new Aspects("vacuos", PAConfig.balance.bismuthCrashCost));
        registerCrucibleRecipe("glass_potentia", "PA_GLASSWORK_REDSTONE@2", PABlocks.glass_redstone, "blockGlass", new Aspects("potentia", 10));
        registerCrucibleRecipe("glass_machina", "PA_GLASSWORK_REDSTONE", PABlocks.glass_redstone_directional, "blockGlass", new Aspects("machina", 10, "potentia", 10));
        registerCrucibleRecipe("wheat_to_seed", "PA_THAUMIC_FARMING@2", OreUtils.count(Items.WHEAT_SEEDS, 4), Items.WHEAT, new Aspects("herba", 5, "tempus", 5));
        registerCrucibleRecipe("seed_to_wheat", "PA_THAUMIC_FARMING@3", Items.WHEAT, Items.WHEAT_SEEDS, new Aspects("victus", 5, "tempus", 5));
        registerCrucibleRecipe("beet_to_seed", "PA_THAUMIC_FARMING@2", OreUtils.count(Items.BEETROOT_SEEDS, 4), Items.BEETROOT, new Aspects("herba", 5, "tempus", 5));
        registerCrucibleRecipe("seed_to_beet", "PA_THAUMIC_FARMING@3", Items.BEETROOT, Items.BEETROOT_SEEDS, new Aspects("victus", 5, "tempus", 5));
        registerCrucibleRecipe("melon_to_seed", "PA_THAUMIC_FARMING@2", OreUtils.count(Items.MELON_SEEDS, 4), Items.MELON, new Aspects("herba", 5, "tempus", 5));
        registerCrucibleRecipe("seed_to_melon", "PA_THAUMIC_FARMING@3", OreUtils.count(Items.MELON, 4), Items.MELON_SEEDS, new Aspects("victus", 5, "tempus", 5));
        registerCrucibleRecipe("pumpkin_to_seed", "PA_THAUMIC_FARMING@2", OreUtils.count(Items.PUMPKIN_SEEDS, 16), Blocks.PUMPKIN, new Aspects("herba", 10, "tempus", 10));
        registerCrucibleRecipe("seed_to_pumpkin", "PA_THAUMIC_FARMING@3", Blocks.PUMPKIN, Items.PUMPKIN_SEEDS, new Aspects("victus", 10, "tempus", 10));
        registerCrucibleRecipe("potato_1", "PA_THAUMIC_FARMING", Items.POISONOUS_POTATO, Items.POTATO, new Aspects("tempus", 1));
        registerCrucibleRecipe("potato_2", "PA_THAUMIC_FARMING", Items.POTATO, Items.POISONOUS_POTATO, new Aspects("tempus", 1));
	}

    public static void registerCrucibleRecipe(String resource, String research, Item output, Object catalyst, AspectList tags) { registerCrucibleRecipe(resource, research, new ItemStack(output), catalyst, tags); }
    public static void registerCrucibleRecipe(String resource, String research, Block output, Object catalyst, AspectList tags) { registerCrucibleRecipe(resource, research, new ItemStack(output), catalyst, tags); }
    public static void registerCrucibleRecipe(String resource, String research, ItemStack output, Object catalyst, AspectList tags) {
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(MODID, resource), new CrucibleRecipe(research, output, catalyst, tags));
    }
	
	private static void registerInfusionRecipes(Register<IRecipe> e) {
        registerInfusionRecipe("alkimium_goggles", "PA_ALKIMIUM_GOGGLES", PAItems.alkimium_goggles, 1, new Aspects("alkimia",  50, "auram",  25), ItemsTC.goggles, PAItems.alkimium_plate, PAItems.alkimium_plate, PAItems.alkimium_plate, makeCrystal(Aspect.AURA));
        registerInfusionRecipe("belt_of_suspension", "PA_BELT_OF_SUSPENSION", PAItems.belt_of_suspension, 5, new Aspects("auram",  50, "aer", 75, "machina", 15, "motus", 75, "potentia", 65, "volatus", 125, "instrumentum", 15), OreUtils.meta(ItemsTC.baubles, 2), Items.FEATHER, ItemsTC.ringCloud, Items.SUGAR, ItemsTC.alumentum, BlocksTC.levitator, BlocksTC.pavingStoneBarrier, Blocks.PISTON, BlocksTC.crystalAir);
        registerInfusionRecipe("dimensional_singularity", "PA_DIMENSIONAL_SINGULARITY", PAItems.dimensional_singularity, 5, new Aspects("auram",  75, "perditio", 15, "spatio", 45, "tempus", 10, "permutatio", 25, "potentia", 200), ItemsTC.salisMundus, ItemsTC.mirroredGlass, Items.ENDER_PEARL, Blocks.OBSIDIAN, Blocks.GOLDEN_RAIL, ItemsTC.alumentum, Blocks.REDSTONE_BLOCK, ItemsTC.visResonator);
        registerInfusionRecipe("mirrored_amulet", "PA_MIRRORED_AMULET", PAItems.mirrored_amulet, 8, new Aspects("auram",  50, "vitreus", 25, "potentia", 35, "instrumentum", 20, "spatio", 65, "permutatio", 64), OreUtils.meta(ItemsTC.baubles, 4), PAItems.dimensional_singularity, Blocks.HOPPER, BlocksTC.hungryChest, BlocksTC.crystalOrder, Items.NAME_TAG, BlocksTC.mirror);
        registerInfusionRecipe("mirrored_amulet2", "PA_MIRRORED_AMULET", PAItems.mirrored_amulet, 8, new Aspects("auram",  50, "vitreus", 25, "potentia", 35, "instrumentum", 20, "spatio", 65, "permutatio", 64), OreUtils.meta(ItemsTC.baubles, 4), PAItems.dimensional_curiosity, Blocks.HOPPER, BlocksTC.hungryChest, BlocksTC.crystalOrder, Items.NAME_TAG, BlocksTC.mirror);
        registerInfusionRecipe("mirromirous_headband", "PA_MIRROMIROUS_HEADBAND", PAItems.mirromirous_headband, 7, new Aspects("cognitio", 175, "vitreus", 50, "vinculum",  125), ItemsTC.bandCuriosity, PAItems.bismuth_plate, Items.ENCHANTED_BOOK, PAItems.bismuth_plate, Items.ENCHANTED_BOOK, PAItems.bismuth_plate, Items.ENCHANTED_BOOK, PAItems.bismuth_plate, Items.ENCHANTED_BOOK);
        registerInfusionEnchantment("IETransmutative", "TRANSMUTATIVE", EnumInfusionEnchantmentII.TRANSMUTATIVE, new Aspects("alkimia",  60, "vitium", 45), ingredientNBT(Items.ENCHANTED_BOOK), PABlocks.alkimium_block);
        registerInfusionEnchantment("IEAuraInfusing", "AURAINFUSING", EnumInfusionEnchantmentII.AURAINFUSING, new Aspects("auram",  50, "potentia", 60), ingredientNBT(Items.ENCHANTED_BOOK), ItemsTC.visResonator);
        registerInfusionEnchantment("IEProjecting", "PROJECTING", EnumInfusionEnchantmentII.PROJECTING, new Aspects("instrumentum", 15, "aversio", 15, "motus", 15), ingredientNBT(Items.ENCHANTED_BOOK), Items.ENDER_PEARL);
        registerInfusionEnchantment("IECurious", "CURIOUS", EnumInfusionEnchantmentII.CURIOUS, new Aspects("cognitio", 30, "instrumentum", 30), OreUtils.meta(ItemsTC.curio, 1), Items.EXPERIENCE_BOTTLE);
        registerInfusionRecipe("vis_glass_cutter", "PA_GLASSWORK_CUTTER", PAItems.vis_glass_cutter, 2, new Aspects("auram",  25, "vitreus", 25), PAItems.glass_cutter, ItemsTC.amber, PAItems.bismuth_nugget, ItemsTC.amber, PAItems.bismuth_nugget);
        registerInfusionRecipe("starving_chest_1", "PA_STARVING_CHEST", PABlocks.starving_chest, 3, new Aspects("vinculum",  25, "spatio", 25), BlocksTC.hungryChest, Blocks.HOPPER, ItemsTC.filter, Blocks.HOPPER, ItemsTC.filter);
        registerInfusionRecipe("starving_chest_2", "PA_STARVING_CHEST", OreUtils.meta(PABlocks.starving_chest, 1), 6, new Aspects("vinculum",  50, "spatio", 50), PABlocks.starving_chest, Blocks.HOPPER, ItemsTC.filter, Blocks.HOPPER, ItemsTC.filter);
        registerInfusionRecipe("starving_chest_3", "PA_STARVING_CHEST", OreUtils.meta(PABlocks.starving_chest, 2), 9, new Aspects("vinculum",  75, "spatio", 75), OreUtils.meta(PABlocks.starving_chest, 1), Blocks.HOPPER, ItemsTC.filter, Blocks.HOPPER, ItemsTC.filter);
        registerInfusionRecipe("starving_chest_4", "PA_STARVING_CHEST", OreUtils.meta(PABlocks.starving_chest, 3), 12, new Aspects("vinculum",  99, "spatio", 99), OreUtils.meta(PABlocks.starving_chest, 2), Blocks.HOPPER, ItemsTC.filter, Blocks.HOPPER, ItemsTC.filter);
        registerInfusionRecipe("thaum_coat", "PA_THAUM_COAT", PAItems.thaum_coat, 15, new Aspects("spatio", 100, "instrumentum", 150, "sensus", 150), ItemsTC.fabric, ItemsTC.thaumonomicon, PAItems.bismuth_caster, PAItems.alkimium_goggles, BlocksTC.visBattery);
	}
    public static void registerInfusionRecipe(String resource, String research, Item stack, int inst, Aspects aspects, Object catalyst, Object ...recipe) { registerInfusionRecipe(resource, research, new ItemStack(stack), inst, aspects, catalyst, recipe); }
    public static void registerInfusionRecipe(String resource, String research, Block stack, int inst, Aspects aspects, Object catalyst, Object ...recipe) { registerInfusionRecipe(resource, research, new ItemStack(stack), inst, aspects, catalyst, recipe); }
	public static void registerInfusionRecipe(String resource, String research, ItemStack stack, int inst, Aspects aspects, Object catalyst, Object ...recipe) {
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(MODID, resource), new InfusionRecipe(research, stack, inst, aspects, catalyst, recipe));
    }
    public static void registerInfusionEnchantment(String resource, String text, EnumInfusionEnchantmentII ench, Aspects aspects, Object ...recipe) {
        InfusionEnchantmentRecipeII IEEnchantment = new InfusionEnchantmentRecipeII(ench, aspects, recipe);
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(MODID, resource), IEEnchantment);
        ItemStack recipeStack = new ItemStack(ItemsTC.thaumiumSword);
        recipeStack.setStackDisplayName(TextFormatting.RESET + recipeStack.getDisplayName() + " +" + TextFormatting.GOLD + I18n.translateToLocal("enchantment.infusion." + text));
        ThaumcraftApi.addFakeCraftingRecipe(new ResourceLocation(MODID, resource + "Fake"), new InfusionEnchantmentRecipeII(IEEnchantment, recipeStack));
    }
    public static IngredientNBTTC ingredientNBT(Item stack) { return ingredientNBT(new ItemStack(stack)); }
    public static IngredientNBTTC ingredientNBT(Block stack) { return ingredientNBT(new ItemStack(stack)); }
    public static IngredientNBTTC ingredientNBT(ItemStack stack) { return new IngredientNBTTC(stack); }
	
	private static void registerPotionMixerRecipes(Register<IRecipe> e) {
    	registerPotionMixerRecipe(e, "mixer_piston", Blocks.PISTON, MobEffects.SLOWNESS, MobEffects.HASTE, MobEffects.JUMP_BOOST, MobEffects.LEVITATION);
    	registerPotionMixerRecipe(e, "mixer_ice", Blocks.ICE, MobEffects.MINING_FATIGUE, MobEffects.RESISTANCE, MobEffects.FIRE_RESISTANCE);
    	registerPotionMixerRecipe(e, "mixer_glass", Blocks.GLASS, MobEffects.INVISIBILITY, MobEffects.NIGHT_VISION, MobEffects.ABSORPTION);
    	registerPotionMixerRecipe(e, "mixer_iron", Items.IRON_INGOT, MobEffects.STRENGTH, MobEffects.WEAKNESS);
    	registerPotionMixerRecipe(e, "mixer_gold", Items.GOLD_INGOT, MobEffects.LUCK, MobEffects.UNLUCK);
    	registerPotionMixerRecipe(e, "mixer_apple", Items.APPLE, MobEffects.INSTANT_HEALTH, MobEffects.INSTANT_DAMAGE, MobEffects.REGENERATION, MobEffects.HEALTH_BOOST, MobEffects.SATURATION);
    	registerPotionMixerRecipe(e, "mixer_rotten_flesh", Items.ROTTEN_FLESH, MobEffects.POISON, MobEffects.WITHER);
    	registerPotionMixerRecipe(e, "mixer_pufferfish", OreUtils.meta(Items.FISH, 2), MobEffects.NAUSEA, MobEffects.WATER_BREATHING, MobEffects.BLINDNESS, MobEffects.HUNGER);
    	registerPotionMixerRecipe(e, "mixer_nitor", BlocksTC.nitor.get(EnumDyeColor.YELLOW), MobEffects.GLOWING);
	}
    public static void registerPotionMixerRecipe(Register<IRecipe> e, String resource, Item stack, Potion ...potion) { registerPotionMixerRecipe(e, resource, new ItemStack(stack), potion); }
    public static void registerPotionMixerRecipe(Register<IRecipe> e, String resource, Block stack, Potion ...potion) { registerPotionMixerRecipe(e, resource, new ItemStack(stack), potion); }
    public static void registerPotionMixerRecipe(Register<IRecipe> e, String resource, ItemStack stack, Potion ...potion) {
        e.getRegistry().register(new RecipePotionMixer(new ResourceLocation(MODID, resource), stack, potion));
    }
	
	private static void registerTransmutationRecipes(Register<IRecipe> e) {
		registerTransmutationRecipe(e, "transmutation_rotten_flesh", Items.ROTTEN_FLESH, Items.CHICKEN);
		registerTransmutationRecipe(e, "transmutation_gunpowder", Items.GUNPOWDER, Items.GLOWSTONE_DUST);
		registerTransmutationRecipe(e, "transmutation_string", Items.STRING, Items.FEATHER);
		registerTransmutationRecipe(e, "transmutation_ender_pearl", Items.ENDER_PEARL, Items.ENDER_EYE);
		registerTransmutationRecipe(e, "transmutation_spider_eye", Items.SPIDER_EYE, Items.FERMENTED_SPIDER_EYE);
		registerTransmutationRecipe(e, "transmutation_slime_ball", Items.SLIME_BALL, Items.MAGMA_CREAM);
		registerTransmutationRecipe(e, "transmutation_glowstone", Items.GLOWSTONE_DUST, Items.BLAZE_POWDER);
		registerTransmutationRecipe(e, "transmutation_iron_ingot", Items.IRON_INGOT, Items.GOLD_INGOT);
		registerTransmutationRecipe(e, "transmutation_gold_ingot", Items.GOLD_INGOT, Items.IRON_INGOT);
		registerTransmutationRecipe(e, "transmutation_iron_nugget", Items.IRON_NUGGET, Items.GOLD_NUGGET);
		registerTransmutationRecipe(e, "transmutation_gold_nugget", Items.GOLD_NUGGET, Items.IRON_NUGGET);
		registerTransmutationRecipe(e, "transmutation_sugar", Items.SUGAR, Items.REDSTONE);
		registerTransmutationRecipe(e, "transmutation_redstone", Items.REDSTONE, ItemsTC.salisMundus);
		registerTransmutationRecipe(e, "transmutation_bone", Items.BONE, Items.COAL);
		registerTransmutationRecipe(e, "transmutation_diamond", Items.DIAMOND, Items.EMERALD);
		registerTransmutationRecipe(e, "transmutation_emerald", Items.EMERALD, Items.DIAMOND);
		registerTransmutationRecipe(e, "transmutation_beetroot_seeds", Items.BEETROOT_SEEDS, Items.WHEAT_SEEDS);
		registerTransmutationRecipe(e, "transmutation_seeds", Items.WHEAT_SEEDS, Items.MELON_SEEDS);
	}
    public static void registerTransmutationRecipe(Register<IRecipe> e, String resource, ItemStack stack, Item stack2) { registerTransmutationRecipe(e, resource, stack, new ItemStack(stack2)); }
    public static void registerTransmutationRecipe(Register<IRecipe> e, String resource, ItemStack stack, Block stack2) { registerTransmutationRecipe(e, resource, stack, new ItemStack(stack2)); }
    public static void registerTransmutationRecipe(Register<IRecipe> e, String resource, Item stack, ItemStack stack2) { registerTransmutationRecipe(e, resource, new ItemStack(stack), stack2); }
    public static void registerTransmutationRecipe(Register<IRecipe> e, String resource, Block stack, ItemStack stack2) { registerTransmutationRecipe(e, resource, new ItemStack(stack), stack2); }
    public static void registerTransmutationRecipe(Register<IRecipe> e, String resource, Item stack, Item stack2) { registerTransmutationRecipe(e, resource, new ItemStack(stack), new ItemStack(stack2)); }
    public static void registerTransmutationRecipe(Register<IRecipe> e, String resource, Item stack, Block stack2) { registerTransmutationRecipe(e, resource, new ItemStack(stack), new ItemStack(stack2)); }
    public static void registerTransmutationRecipe(Register<IRecipe> e, String resource, Block stack, Item stack2) { registerTransmutationRecipe(e, resource, new ItemStack(stack), new ItemStack(stack2)); }
    public static void registerTransmutationRecipe(Register<IRecipe> e, String resource, Block stack, Block stack2) { registerTransmutationRecipe(e, resource, new ItemStack(stack), new ItemStack(stack2)); }
    public static void registerTransmutationRecipe(Register<IRecipe> e, String resource, ItemStack stack, ItemStack stack2) {
        e.getRegistry().register(new RecipeTransmutation(new ResourceLocation(MODID, resource), stack, stack2));
    }
	
	public static <T> List<T> getRecipesOfType(Class<T> clazz) {
		ArrayList<T> recipes = new ArrayList<>();
		ForgeRegistries.RECIPES.forEach(r -> {
			if(r.getClass() == clazz)
				recipes.add((T) r);
		});
		return recipes;
	}

}
