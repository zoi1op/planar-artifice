package leppa.planarartifice.registry;

import static thaumcraft.api.ThaumcraftApiHelper.makeCrystal;
import static leppa.planarartifice.main.PlanarArtifice.MODID;

import java.util.ArrayList;
import java.util.List;

import com.bewitchment.common.integration.thaumcraft.ThaumcraftCompat;
import com.zeitheron.thaumicadditions.api.AspectUtil;
import com.zeitheron.thaumicadditions.init.ItemsTAR;
import com.zeitheron.thaumicadditions.init.KnowledgeTAR;
import leppa.planarartifice.compat.PACompatHandler;
import leppa.planarartifice.compat.bewitchment.BewitchmentHandler;
import leppa.planarartifice.compat.thaumicadditions.ThaumicAdditionsHandler;
import leppa.planarartifice.enchantment.EnumInfusionEnchantmentII;
import leppa.planarartifice.enchantment.InfusionEnchantmentRecipeII;
import leppa.planarartifice.main.PlanarArtifice;
import leppa.planarartifice.recipe.CrucibleRecipeRandomCrystal;
import leppa.planarartifice.recipe.RecipePotionMixer;
import leppa.planarartifice.recipe.RecipeTransmutation;
import leppa.planarartifice.util.OreUtils;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;
import soot.Registry;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.crafting.IngredientNBTTC;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.api.crafting.ShapelessArcaneRecipe;
import thaumcraft.api.items.ItemsTC;

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
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("planarartifice:alkimium_smeltery"), new ShapedArcaneRecipe(defaultGroup, "PA_ALKIMIUM_APPLICATIONS", 100, new AspectList().add(Aspect.WATER, 1), new ItemStack(PABlocks.alkimium_smeltery), "#C#", "ADA", "AAA", '#', new ItemStack(PAItems.alkimium_plate, 1, 0), 'A', "plateBrass", 'C', new ItemStack(BlocksTC.smelterBasic), 'D', new ItemStack(PABlocks.alchemical_alkimium_construct)));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("planarartifice:alkimium_smeltery_thaumium"), new ShapedArcaneRecipe(defaultGroup, "PA_ALKIMIUM_THAUMIUM_SMELTERY", 300, new AspectList().add(Aspect.WATER, 1), new ItemStack(PABlocks.alkimium_smeltery_thaumium), "#C#", "ADA", "AAA", '#', new ItemStack(PAItems.alkimium_plate), 'A', new ItemStack(ItemsTC.plate, 1, 2), 'C', new ItemStack(PABlocks.alkimium_smeltery), 'D', new ItemStack(PABlocks.alchemical_alkimium_construct)));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("planarartifice:alkimium_smeltery_void"), new ShapedArcaneRecipe(defaultGroup, "PA_ALKIMIUM_VOID_SMELTERY@2", 1000, new AspectList().add(Aspect.WATER, 1), new ItemStack(PABlocks.alkimium_smeltery_void), "#C#", "ADA", "AAA", '#', new ItemStack(PAItems.alkimium_plate, 1, 0), 'A', new ItemStack(ItemsTC.plate, 1, 3), 'C', new ItemStack(PABlocks.alkimium_smeltery_thaumium), 'D', new ItemStack(BlocksTC.metalAlchemicalAdvanced)));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("planarartifice:alchemical_alkimium_construct"), new ShapedArcaneRecipe(defaultGroup, "PA_ALKIMIUM@2", 100, new AspectList().add(Aspect.WATER, 1), new ItemStack(PABlocks.alchemical_alkimium_construct), "#v#", "pwp", "#v#", '#', PAItems.alkimium_plate, 'p', BlocksTC.tube, 'v', BlocksTC.tubeValve, 'w', BlocksTC.plankSilverwood));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("planarartifice:teleporter_matrix"), new ShapedArcaneRecipe(defaultGroup, "PA_MIRROR_TELEPORTER@2", 300, new AspectList().add(Aspect.ORDER, 1).add(Aspect.ENTROPY, 1), new ItemStack(PABlocks.teleporter_matrix), "AHA", "MDM", "AHA", 'A', BlocksTC.stoneArcaneBrick, 'M', ItemsTC.mirroredGlass, 'H', ItemsTC.alumentum, 'D', PAItems.dimensional_singularity));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("planarartifice:teleporter_matrix2"), new ShapedArcaneRecipe(defaultGroup, "PA_MIRROR_TELEPORTER@2", 300, new AspectList().add(Aspect.ORDER, 1).add(Aspect.ENTROPY, 1), new ItemStack(PABlocks.teleporter_matrix), "AHA", "MDM", "AHA", 'A', BlocksTC.stoneArcaneBrick, 'M', ItemsTC.mirroredGlass, 'H', ItemsTC.alumentum, 'D', PAItems.dimensional_curiosity));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("planarartifice:flux_scrubber"), new ShapedArcaneRecipe(defaultGroup, "PA_FLUX_SCRUBBER", 275, new AspectList().add(Aspect.ORDER, 1).add(Aspect.AIR, 1).add(Aspect.FIRE, 1).add(Aspect.WATER, 1).add(Aspect.EARTH, 1).add(Aspect.ENTROPY, 1), new ItemStack(PABlocks.flux_scrubber), "IGI", "IAI", "BQB", 'B', new ItemStack(PABlocks.bismuth_block), 'A', new ItemStack(PABlocks.alchemical_alkimium_construct), 'G', new ItemStack(ItemsTC.mechanismSimple), 'I', "plateIron", 'Q', new ItemStack(ItemsTC.alumentum)));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("planarartifice:alkimium_improved_distillation_aux_1"), new ShapelessArcaneRecipe(defaultGroup, "PA_ALKIMIUM_DISTILLATION", 70, new AspectList().add(Aspect.ORDER, 1), PABlocks.smelter_aux, new ItemStack(BlocksTC.smelterAux), new ItemStack(PABlocks.alchemical_alkimium_construct)));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("planarartifice:alkimium_improved_distillation_aux_2"), new ShapelessArcaneRecipe(defaultGroup, "PA_ALKIMIUM_DISTILLATION", 70, new AspectList().add(Aspect.ORDER, 1), PABlocks.smelter_vent, new ItemStack(BlocksTC.smelterVent), new ItemStack(PABlocks.alchemical_alkimium_construct)));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("planarartifice:alkimium_improved_distillation_aux_3"), new ShapelessArcaneRecipe(defaultGroup, "PA_ALKIMIUM_DISTILLATION", 70, new AspectList().add(Aspect.ENTROPY, 1), BlocksTC.smelterAux, new ItemStack(PABlocks.smelter_aux)));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("planarartifice:alkimium_improved_distillation_aux_4"), new ShapelessArcaneRecipe(defaultGroup, "PA_ALKIMIUM_DISTILLATION", 70, new AspectList().add(Aspect.ENTROPY, 1), BlocksTC.smelterVent, new ItemStack(PABlocks.smelter_vent)));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("planarartifice:flux_vent"), new ShapedArcaneRecipe(defaultGroup, "PA_BISMUTH_CASTERS_GAUNTLET@1", 75, new AspectList(), new ItemStack(PAItems.flux_venting_circuit), " # ", "QRV", '#', makeCrystal(Aspect.FLUX), 'Q', new ItemStack(Items.QUARTZ), 'R', new ItemStack(Items.REPEATER), 'V', new ItemStack(Blocks.REDSTONE_TORCH)));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("planarartifice:bismuth_caster"), new ShapedArcaneRecipe(defaultGroup, "PA_BISMUTH_CASTERS_GAUNTLET", 175, new AspectList().add(Aspect.ORDER, 1).add(Aspect.AIR, 1).add(Aspect.FIRE, 1).add(Aspect.WATER, 1).add(Aspect.EARTH, 1).add(Aspect.ENTROPY, 1), new ItemStack(PAItems.bismuth_caster), " VO", "MGB", "BB ", 'B', new ItemStack(PAItems.bismuth_plate), 'V', new ItemStack(PAItems.flux_venting_circuit), 'M', new ItemStack(ItemsTC.mirroredGlass), 'O', new ItemStack(ItemsTC.visResonator), 'G', new ItemStack(ItemsTC.casterBasic)));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("planarartifice:aura_meter"), new ShapedArcaneRecipe(defaultGroup, "PA_BISMUTH", 25, new AspectList().add(Aspect.ORDER, 1).add(Aspect.AIR, 1).add(Aspect.FIRE, 1).add(Aspect.WATER, 1).add(Aspect.EARTH, 1).add(Aspect.ENTROPY, 1), new ItemStack(PAItems.aura_meter), "A", "B", "C", 'A', Blocks.GLASS_PANE, 'B', makeCrystal(Aspect.AIR), 'C', PAItems.bismuth_plate));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("planarartifice:bismuth_claymore"), new ShapedArcaneRecipe(defaultGroup, "PA_BISMUTH", 0, new AspectList().add(Aspect.FIRE, 1), new ItemStack(PAItems.bismuth_claymore), " B ", "BSB", " S ", 'B', PAItems.bismuth_ingot, 'S', Items.STICK));
	}
	
	private static void registerCrucibleRecipes(Register<IRecipe> e) {
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("planarartifice:alkimium_ingot"), new CrucibleRecipe("METALLURGY@2", new ItemStack(PAItems.alkimium_ingot), new ItemStack(ItemsTC.ingots, 1, 2), new AspectList().add(Aspect.ALCHEMY, 5).add(Aspect.ORDER, 5)));
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("planarartifice:magic_apple"), new CrucibleRecipe("PA_RARE_ITEMS@4", new ItemStack(PAItems.magic_apple), new ItemStack(Items.APPLE), new AspectList().add(Aspect.MAGIC, 10).add(Aspect.LIFE, 70).add(Aspect.BEAST, 25).add(Aspect.EARTH, 15)));
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("planarartifice:alchemical_scribing_tools"), new CrucibleRecipe("PA_ALCHEMICAL_SCRIBING_TOOLS", new ItemStack(PAItems.alchemical_scribing_tools), new ItemStack(ItemsTC.scribingTools), new AspectList().add(Aspect.AURA, 15).add(Aspect.ALCHEMY, 15)));
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("planarartifice:bismuth"), new CrucibleRecipe("!Portal", new ItemStack(PAItems.bismuth_ingot), new ItemStack(ItemsTC.ingots, 1, 0), new AspectList().add(Aspect.AURA, 20).add(Aspect.ENERGY, 20)));
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("planarartifice:endereye"), new CrucibleRecipe("PA_BUSH_ALCHEMY@1", new ItemStack(Items.ENDER_EYE), new ItemStack(Items.SPIDER_EYE), new AspectList().add(PAAspects.DIMENSIONS, 10).add(Aspect.FIRE, 10).add(Aspect.ELDRITCH, 20)));
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("planarartifice:redstone"), new CrucibleRecipe("PA_BUSH_ALCHEMY@1", new ItemStack(Items.REDSTONE), new ItemStack(Items.GUNPOWDER), new AspectList().add(Aspect.ENERGY, 5)));
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("planarartifice:blaze_powder"), new CrucibleRecipe("PA_BUSH_ALCHEMY", new ItemStack(Items.BLAZE_POWDER, 2), new ItemStack(Items.GUNPOWDER), new AspectList().add(Aspect.FIRE, 14).add(Aspect.MAGIC, 5)));
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("planarartifice:condensed_crystal_cluster1"), new CrucibleRecipe("PA_BUSH_ALCHEMY_ESSENTIA@1", new ItemStack(PAItems.condensed_crystal_cluster), new ItemStack(ItemsTC.salisMundus), new AspectList().add(Aspect.FIRE, 50)));
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("planarartifice:condensed_crystal_cluster2"), new CrucibleRecipe("PA_BUSH_ALCHEMY_ESSENTIA@1", new ItemStack(PAItems.condensed_crystal_cluster), new ItemStack(ItemsTC.salisMundus), new AspectList().add(Aspect.WATER, 50)));
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("planarartifice:condensed_crystal_cluster3"), new CrucibleRecipe("PA_BUSH_ALCHEMY_ESSENTIA@1", new ItemStack(PAItems.condensed_crystal_cluster), new ItemStack(ItemsTC.salisMundus), new AspectList().add(Aspect.AIR, 50)));
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("planarartifice:condensed_crystal_cluster4"), new CrucibleRecipe("PA_BUSH_ALCHEMY_ESSENTIA@1", new ItemStack(PAItems.condensed_crystal_cluster), new ItemStack(ItemsTC.salisMundus), new AspectList().add(Aspect.EARTH, 50)));
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("planarartifice:condensed_crystal_cluster5"), new CrucibleRecipe("PA_BUSH_ALCHEMY_ESSENTIA@1", new ItemStack(PAItems.condensed_crystal_cluster), new ItemStack(ItemsTC.salisMundus), new AspectList().add(Aspect.ORDER, 50)));
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("planarartifice:condensed_crystal_cluster6"), new CrucibleRecipe("PA_BUSH_ALCHEMY_ESSENTIA@1", new ItemStack(PAItems.condensed_crystal_cluster), new ItemStack(ItemsTC.salisMundus), new AspectList().add(Aspect.ENTROPY, 50)));
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("planarartifice:random_vis_crystal1"), new CrucibleRecipeRandomCrystal("PA_BUSH_ALCHEMY_ESSENTIA@2", new ItemStack(ItemsTC.salisMundus), new AspectList().add(Aspect.FLUX, 4)));
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("planarartifice:random_vis_crystal2"), new CrucibleRecipeRandomCrystal("PA_BUSH_ALCHEMY_ESSENTIA@2", new ItemStack(ItemsTC.salisMundus), new AspectList().add(PAAspects.COLOR, 20)));
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("planarartifice:salis_mundus"), new CrucibleRecipe("PA_BUSH_ALCHEMY_ESSENTIA", new ItemStack(ItemsTC.salisMundus), new ItemStack(Items.REDSTONE), new AspectList().add(Aspect.MAGIC, 5)));
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("planarartifice:ender_pearls"), new CrucibleRecipe("PA_BUSH_ALCHEMY_CONJURING", new ItemStack(Items.ENDER_PEARL, 2), new ItemStack(Items.ENDER_PEARL), new AspectList().add(Aspect.ELDRITCH, 20)));
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("planarartifice:redstone2"), new CrucibleRecipe("PA_BUSH_ALCHEMY_CONJURING", new ItemStack(Items.REDSTONE, 2), new ItemStack(Items.REDSTONE), new AspectList().add(Aspect.ENERGY, 20)));
        AspectList metalLeveling3 = new AspectList().add(Aspect.METAL, 2).add(Aspect.DEATH, 1).add(Aspect.ALCHEMY, 1);
        AspectList metalLeveling2 = metalLeveling3.copy().add(Aspect.ORDER, 5).add(Aspect.DESIRE, 10);
        AspectList metalLeveling1 = metalLeveling2.copy().add(Aspect.MAGIC, 15).add(PAAspects.COLOR, 20);
        if (ThaumicAdditionsHandler.extraActivated) {
            metalLeveling1 = metalLeveling1.add(KnowledgeTAR.INFERNUM, 1);
            metalLeveling2 = metalLeveling2.add(KnowledgeTAR.INFERNUM, 1);
            metalLeveling3 = metalLeveling3.add(KnowledgeTAR.INFERNUM, 1);
        } else if (BewitchmentHandler.active) {
            metalLeveling1 = metalLeveling1.add(ThaumcraftCompat.DEMON, 1);
            metalLeveling2 = metalLeveling2.add(ThaumcraftCompat.DEMON, 1);
            metalLeveling3 = metalLeveling3.add(ThaumcraftCompat.DEMON, 1);
        } else {
            metalLeveling1 = metalLeveling1.add(Aspect.FIRE, 5);
            metalLeveling2 = metalLeveling2.add(Aspect.FIRE, 5);
            metalLeveling3 = metalLeveling3.add(Aspect.FIRE, 5);
        }
        AspectList metalRevert = new AspectList().add(Aspect.EARTH, 5).add(Aspect.SOUL, 2).add(Aspect.ALCHEMY, 1).add(PAAspects.TIME, 10);
        if (!OreDictionary.doesOreNameExist("ingotLead")) {
            ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("planarartifice:iron_to_gold"), new CrucibleRecipe("PA_BUSH_ALCHEMY_METAL_1", new ItemStack(Items.GOLD_INGOT), new ItemStack(Items.IRON_INGOT), metalLeveling1));
            ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("planarartifice:gold_to_iron"), new CrucibleRecipe("PA_BUSH_ALCHEMY_METAL_1", new ItemStack(Items.IRON_INGOT), new ItemStack(Items.GOLD_INGOT), metalRevert));
        } else if (OreDictionary.doesOreNameExist("ingotTin") && OreDictionary.doesOreNameExist("ingotCopper") && OreDictionary.doesOreNameExist("ingotSilver")) {
            ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("planarartifice:lead_to_tin"), new CrucibleRecipe("PA_BUSH_ALCHEMY_METAL_3", OreUtils.getFirst("ingotTin"), OreUtils.getFirst("ingotLead"), metalLeveling3));
            ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("planarartifice:tin_to_iron"), new CrucibleRecipe("PA_BUSH_ALCHEMY_METAL_3", new ItemStack(Items.IRON_INGOT), OreUtils.getFirst("ingotTin"), metalLeveling3));
            ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("planarartifice:iron_to_copper"), new CrucibleRecipe("PA_BUSH_ALCHEMY_METAL_3", OreUtils.getFirst("ingotCopper"), new ItemStack(Items.IRON_INGOT), metalLeveling3));
            AspectList goldRecipe = metalLeveling3.copy().add(Aspect.DESIRE, 10);
            AspectList silverRecipe = metalLeveling3.copy().add(Aspect.DESIRE, 5);
            if (BewitchmentHandler.active) {
                goldRecipe = goldRecipe.add(ThaumcraftCompat.SUN, 5);
                silverRecipe = silverRecipe.add(ThaumcraftCompat.MOON, 5);
            }
            ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("planarartifice:copper_to_silver"), new CrucibleRecipe("PA_BUSH_ALCHEMY_METAL_3", OreUtils.getFirst("ingotSilver"), OreUtils.getFirst("ingotCopper"), silverRecipe));
            ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("planarartifice:silver_to_gold"), new CrucibleRecipe("PA_BUSH_ALCHEMY_METAL_3", new ItemStack(Items.GOLD_INGOT), OreUtils.getFirst("ingotSilver"), goldRecipe));
            if (OreDictionary.doesOreNameExist("ingotAntimony")) {
                metalRevert = new AspectList().add(Aspect.METAL, 10).add(Aspect.DESIRE, 10).add(PAAspects.TIME, 20);
                if (BewitchmentHandler.active) metalRevert.add(ThaumcraftCompat.SUN, 5);
                ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("planarartifice:gold_to_lead"), new CrucibleRecipe("PA_BUSH_ALCHEMY_METAL_3", OreUtils.getFirst("ingotLead"), Registry.INGOT_ANTIMONY, metalRevert));
            } else {
                ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("planarartifice:gold_to_lead"), new CrucibleRecipe("PA_BUSH_ALCHEMY_METAL_3", OreUtils.getFirst("ingotLead"), new ItemStack(Items.GOLD_INGOT), metalRevert));
            }
//            ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("planarartifice:gold_to_lead"), new CrucibleRecipe("PA_BUSH_ALCHEMY_METAL_3", OreUtils.getFirst("ingotLead"), new ItemStack(Items.GOLD_INGOT), metalRevert));
        } else {
            ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("planarartifice:lead_to_gold"), new CrucibleRecipe("PA_BUSH_ALCHEMY_METAL_2", new ItemStack(Items.GOLD_INGOT), OreUtils.getFirst("ingotLead"), metalLeveling2));
            ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("planarartifice:gold_to_lead"), new CrucibleRecipe("PA_BUSH_ALCHEMY_METAL_2", OreUtils.getFirst("ingotLead"), new ItemStack(Items.GOLD_INGOT), metalRevert));
        }
	}
	
	private static void registerInfusionRecipes(Register<IRecipe> e) {
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("planarartifice:alkimium_goggles"), new InfusionRecipe("PA_ALKIMIUM_GOGGLES", new ItemStack(PAItems.alkimium_goggles), 1, new AspectList().add(Aspect.ALCHEMY, 50).add(Aspect.AURA, 25), new ItemStack(ItemsTC.goggles), new ItemStack(PAItems.alkimium_plate), new ItemStack(PAItems.alkimium_plate), new ItemStack(PAItems.alkimium_plate), makeCrystal(Aspect.AURA)));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("planarartifice:belt_of_suspension"), new InfusionRecipe("PA_BELT_OF_SUSPENSION", new ItemStack(PAItems.belt_of_suspension), 5, new AspectList().add(Aspect.AURA, 50).add(Aspect.AIR, 75).add(Aspect.MECHANISM, 15).add(Aspect.MOTION, 75).add(Aspect.ENERGY, 65).add(Aspect.FLIGHT, 125).add(Aspect.TOOL, 15), new ItemStack(ItemsTC.baubles, 1, 2), new ItemStack(Items.FEATHER), new ItemStack(ItemsTC.ringCloud), new ItemStack(Items.SUGAR), new ItemStack(ItemsTC.alumentum), new ItemStack(BlocksTC.levitator), new ItemStack(BlocksTC.pavingStoneBarrier), new ItemStack(Blocks.PISTON), new ItemStack(BlocksTC.crystalAir)));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("planarartifice:dimensional_singularity"), new InfusionRecipe("PA_DIMENSIONAL_SINGULARITY", new ItemStack(PAItems.dimensional_singularity), 5, new AspectList().add(Aspect.AURA, 75).add(Aspect.ENTROPY, 15).add(PAAspects.DIMENSIONS, 45).add(PAAspects.TIME, 10).add(Aspect.EXCHANGE, 25).add(Aspect.ENERGY, 200), new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.mirroredGlass), new ItemStack(Items.ENDER_PEARL), new ItemStack(Blocks.OBSIDIAN), new ItemStack(Blocks.GOLDEN_RAIL), new ItemStack(ItemsTC.alumentum), new ItemStack(Blocks.REDSTONE_BLOCK), new ItemStack(ItemsTC.visResonator)));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("planarartifice:mirrored_amulet"), new InfusionRecipe("PA_MIRRORED_AMULET", new ItemStack(PAItems.mirrored_amulet), 8, new AspectList().add(Aspect.AURA, 50).add(Aspect.CRYSTAL, 25).add(Aspect.ENERGY, 35).add(Aspect.TOOL, 20).add(PAAspects.DIMENSIONS, 65).add(Aspect.EXCHANGE, 64), new ItemStack(ItemsTC.baubles, 1, 4), new ItemStack(PAItems.dimensional_singularity), new ItemStack(Blocks.HOPPER), new ItemStack(BlocksTC.hungryChest), new ItemStack(BlocksTC.crystalOrder), new ItemStack(Items.NAME_TAG), new ItemStack(BlocksTC.mirror)));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("planarartifice:mirrored_amulet2"), new InfusionRecipe("PA_MIRRORED_AMULET", new ItemStack(PAItems.mirrored_amulet), 8, new AspectList().add(Aspect.AURA, 50).add(Aspect.CRYSTAL, 25).add(Aspect.ENERGY, 35).add(Aspect.TOOL, 20).add(PAAspects.DIMENSIONS, 65).add(Aspect.EXCHANGE, 64), new ItemStack(ItemsTC.baubles, 1, 4), new ItemStack(PAItems.dimensional_curiosity), new ItemStack(Blocks.HOPPER), new ItemStack(BlocksTC.hungryChest), new ItemStack(BlocksTC.crystalOrder), new ItemStack(Items.NAME_TAG), new ItemStack(BlocksTC.mirror)));

        // Infusion Enchantments
        InfusionEnchantmentRecipeII IETransmutative = new InfusionEnchantmentRecipeII(EnumInfusionEnchantmentII.TRANSMUTATIVE, new AspectList().add(Aspect.ALCHEMY, 60).add(Aspect.FLUX, 45), new IngredientNBTTC(new ItemStack(Items.ENCHANTED_BOOK)), new ItemStack(PABlocks.alkimium_block));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("planarartifice:IETransmutative"), IETransmutative);
        ItemStack recipeStack = new ItemStack(ItemsTC.thaumiumSword);
        recipeStack.setStackDisplayName(TextFormatting.RESET + recipeStack.getDisplayName() + " +" + TextFormatting.GOLD + "Transmutative");
        ThaumcraftApi.addFakeCraftingRecipe(new ResourceLocation("planarartifice:IETransmutativeFake"), new InfusionEnchantmentRecipeII(IETransmutative, recipeStack));

        InfusionEnchantmentRecipeII IEAuraInfusing = new InfusionEnchantmentRecipeII(EnumInfusionEnchantmentII.AURAINFUSING, new AspectList().add(Aspect.AURA, 50).add(Aspect.ENERGY, 60), new IngredientNBTTC(new ItemStack(Items.ENCHANTED_BOOK)), new ItemStack(ItemsTC.visResonator));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("planarartifice:IEAuraInfusing"), IEAuraInfusing);
        recipeStack = new ItemStack(ItemsTC.thaumiumSword);
        recipeStack.setStackDisplayName(TextFormatting.RESET + recipeStack.getDisplayName() + " +" + TextFormatting.GOLD + "Aura Infusing");
        ThaumcraftApi.addFakeCraftingRecipe(new ResourceLocation("planarartifice:IEAuraInfusingFake"), new InfusionEnchantmentRecipeII(IEAuraInfusing, recipeStack));

        InfusionEnchantmentRecipeII IEProjecting = new InfusionEnchantmentRecipeII(EnumInfusionEnchantmentII.PROJECTING, new AspectList().add(Aspect.TOOL, 15).add(Aspect.AVERSION, 15).add(Aspect.MOTION, 15), new IngredientNBTTC(new ItemStack(Items.ENCHANTED_BOOK)), new ItemStack(Items.ENDER_PEARL));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("planarartifice:IEProjecting"), IEProjecting);
        recipeStack = new ItemStack(ItemsTC.thaumiumSword);
        recipeStack.setStackDisplayName(TextFormatting.RESET + recipeStack.getDisplayName() + " +" + TextFormatting.GOLD + "Projecting");
        ThaumcraftApi.addFakeCraftingRecipe(new ResourceLocation("planarartifice:IEProjectingFake"), new InfusionEnchantmentRecipeII(IEProjecting, recipeStack));
	}
	
	private static void registerPotionMixerRecipes(Register<IRecipe> e) {
    	registerRecipe(e, new RecipePotionMixer(new ResourceLocation("planarartifice:mixer_piston"), new ItemStack(Blocks.PISTON), MobEffects.SLOWNESS, MobEffects.HASTE, MobEffects.JUMP_BOOST, MobEffects.LEVITATION));
    	registerRecipe(e, new RecipePotionMixer(new ResourceLocation("planarartifice:mixer_ice"), new ItemStack(Blocks.ICE), MobEffects.MINING_FATIGUE, MobEffects.RESISTANCE, MobEffects.FIRE_RESISTANCE));
    	registerRecipe(e, new RecipePotionMixer(new ResourceLocation("planarartifice:mixer_glass"), new ItemStack(Blocks.GLASS), MobEffects.INVISIBILITY, MobEffects.NIGHT_VISION, MobEffects.ABSORPTION));
    	registerRecipe(e, new RecipePotionMixer(new ResourceLocation("planarartifice:mixer_iron"), new ItemStack(Items.IRON_INGOT), MobEffects.STRENGTH, MobEffects.WEAKNESS));
    	registerRecipe(e, new RecipePotionMixer(new ResourceLocation("planarartifice:mixer_gold"), new ItemStack(Items.GOLD_INGOT), MobEffects.LUCK, MobEffects.UNLUCK));
    	registerRecipe(e, new RecipePotionMixer(new ResourceLocation("planarartifice:mixer_apple"), new ItemStack(Items.APPLE), MobEffects.INSTANT_HEALTH, MobEffects.INSTANT_DAMAGE, MobEffects.REGENERATION, MobEffects.HEALTH_BOOST, MobEffects.SATURATION));
    	registerRecipe(e, new RecipePotionMixer(new ResourceLocation("planarartifice:mixer_rotten_flesh"), new ItemStack(Items.ROTTEN_FLESH), MobEffects.POISON, MobEffects.WITHER));
    	registerRecipe(e, new RecipePotionMixer(new ResourceLocation("planarartifice:mixer_pufferfish"), new ItemStack(Items.FISH,1,2), MobEffects.NAUSEA, MobEffects.WATER_BREATHING, MobEffects.BLINDNESS, MobEffects.HUNGER));
    	registerRecipe(e, new RecipePotionMixer(new ResourceLocation("planarartifice:mixer_nitor"), new ItemStack(BlocksTC.nitor.get(EnumDyeColor.YELLOW)), MobEffects.GLOWING));

	}
	
	private static void registerTransmutationRecipes(Register<IRecipe> e) {
		
		registerRecipe(e, new RecipeTransmutation(new ResourceLocation(MODID, "transmutation_rotten_flesh"), new ItemStack(Items.ROTTEN_FLESH), new ItemStack(Items.CHICKEN)));
		registerRecipe(e, new RecipeTransmutation(new ResourceLocation(MODID, "transmutation_gunpowder"), new ItemStack(Items.GUNPOWDER), new ItemStack(Items.GLOWSTONE_DUST)));
		registerRecipe(e, new RecipeTransmutation(new ResourceLocation(MODID, "transmutation_string"), new ItemStack(Items.STRING), new ItemStack(Items.FEATHER)));
		registerRecipe(e, new RecipeTransmutation(new ResourceLocation(MODID, "transmutation_ender_pearl"), new ItemStack(Items.ENDER_PEARL), new ItemStack(Items.ENDER_EYE)));
		registerRecipe(e, new RecipeTransmutation(new ResourceLocation(MODID, "transmutation_spider_eye"), new ItemStack(Items.SPIDER_EYE), new ItemStack(Items.FERMENTED_SPIDER_EYE)));
		registerRecipe(e, new RecipeTransmutation(new ResourceLocation(MODID, "transmutation_slime_ball"), new ItemStack(Items.SLIME_BALL), new ItemStack(Items.MAGMA_CREAM)));
		registerRecipe(e, new RecipeTransmutation(new ResourceLocation(MODID, "transmutation_glowstone"), new ItemStack(Items.GLOWSTONE_DUST), new ItemStack(Items.BLAZE_POWDER)));
		registerRecipe(e, new RecipeTransmutation(new ResourceLocation(MODID, "transmutation_iron_ingot"), new ItemStack(Items.IRON_INGOT), new ItemStack(Items.GOLD_INGOT)));
		registerRecipe(e, new RecipeTransmutation(new ResourceLocation(MODID, "transmutation_gold_ingot"), new ItemStack(Items.GOLD_INGOT), new ItemStack(Items.IRON_INGOT)));
		registerRecipe(e, new RecipeTransmutation(new ResourceLocation(MODID, "transmutation_iron_nugget"), new ItemStack(Items.IRON_NUGGET), new ItemStack(Items.GOLD_NUGGET)));
		registerRecipe(e, new RecipeTransmutation(new ResourceLocation(MODID, "transmutation_gold_nugget"), new ItemStack(Items.GOLD_NUGGET), new ItemStack(Items.IRON_NUGGET)));
		registerRecipe(e, new RecipeTransmutation(new ResourceLocation(MODID, "transmutation_sugar"), new ItemStack(Items.SUGAR), new ItemStack(Items.REDSTONE)));
		registerRecipe(e, new RecipeTransmutation(new ResourceLocation(MODID, "transmutation_redstone"), new ItemStack(Items.REDSTONE), new ItemStack(ItemsTC.salisMundus)));
		registerRecipe(e, new RecipeTransmutation(new ResourceLocation(MODID, "transmutation_bone"), new ItemStack(Items.BONE), new ItemStack(Items.COAL)));
		registerRecipe(e, new RecipeTransmutation(new ResourceLocation(MODID, "transmutation_diamond"), new ItemStack(Items.DIAMOND), new ItemStack(Items.EMERALD)));
		registerRecipe(e, new RecipeTransmutation(new ResourceLocation(MODID, "transmutation_emerald"), new ItemStack(Items.EMERALD), new ItemStack(Items.DIAMOND)));
		registerRecipe(e, new RecipeTransmutation(new ResourceLocation(MODID, "transmutation_beetroot_seeds"), new ItemStack(Items.BEETROOT_SEEDS), new ItemStack(Items.WHEAT_SEEDS)));
		registerRecipe(e, new RecipeTransmutation(new ResourceLocation(MODID, "transmutation_seeds"), new ItemStack(Items.WHEAT_SEEDS), new ItemStack(Items.MELON_SEEDS)));
		
	}
	
	private static void registerRecipe(Register<IRecipe> e, IRecipe recipe) {
		e.getRegistry().register(recipe);
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
