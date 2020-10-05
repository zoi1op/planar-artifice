package leppa.planarartifice.registry;

import leppa.planarartifice.foci.FocusEffectColourized;
import leppa.planarartifice.foci.FocusEffectPrismLight;
import leppa.planarartifice.main.PlanarArtifice;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.casters.FocusEngine;
import thaumcraft.api.golems.EnumGolemTrait;
import thaumcraft.api.golems.parts.GolemAddon;
import thaumcraft.api.golems.parts.GolemMaterial;
import thaumcraft.api.golems.parts.PartModel;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategory;
import thaumcraft.api.research.ScanBlock;
import thaumcraft.api.research.ScanningManager;
import thaumcraft.common.golems.client.PartModelHauler;

public class PAResearch {
	
	public static final ResearchCategory catPA = ResearchCategories.registerCategory("PLANARARTIFICE", null,
			new AspectList().add(Aspect.AURA, 1),
			new ResourceLocation("planarartifice:textures/meta/logo_icon.png"),
			new ResourceLocation("planarartifice:textures/research/gui_research_back_2.jpg"));

	public static void registerResearch() {
		PlanarArtifice.LOGGER.info("Research active");
		ThaumcraftApi.registerResearchLocation(new ResourceLocation("planarartifice:research/main.json"));
		if (!OreDictionary.doesOreNameExist("ingotLead"))
			ThaumcraftApi.registerResearchLocation(new ResourceLocation(PlanarArtifice.MODID, "research/metal_1.json"));
		else if (OreDictionary.doesOreNameExist("ingotTin") && OreDictionary.doesOreNameExist("ingotCopper") && OreDictionary.doesOreNameExist("ingotSilver"))
			ThaumcraftApi.registerResearchLocation(new ResourceLocation(PlanarArtifice.MODID, "research/metal_3.json"));
		else ThaumcraftApi.registerResearchLocation(new ResourceLocation(PlanarArtifice.MODID, "research/metal_2.json"));

        // Scannables
        ScanningManager.addScannableThing(new ScanBlock("!Portal", Blocks.PORTAL));

        // Caster
        FocusEngine.registerElement(FocusEffectPrismLight.class, new ResourceLocation("planarartifice", "textures/foci/prism.png"), 0xff00ff);
        FocusEngine.registerElement(FocusEffectColourized.class, new ResourceLocation("planarartifice", "textures/foci/colourizer.png"), 0xffffff);

        // Golem Material
        GolemMaterial.register(new GolemMaterial("ALKIMIUM", new String[]{"PA_MATSTUDY_ALKIMIUM"}, new ResourceLocation("planarartifice", "textures/models/golem/alkimium_golem.png"), 0x4CD482, 13, 12, 4, new ItemStack(PAItems.alkimium_ingot), new ItemStack(ItemsTC.mechanismSimple), new EnumGolemTrait[]{EnumGolemTrait.BLASTPROOF, EnumGolemTrait.LIGHT, EnumGolemTrait.FIREPROOF, EnumGolemTrait.FRAGILE}));

        // Golem Addon
        GolemAddon.register(new GolemAddon("TELEPORT_PACK", new String[]{"PA_MATSTUDY_ALKIMIUM"}, new ResourceLocation("planarartifice", "textures/misc/golem/addon_teleport_pack.png"), new PartModelHauler(new ResourceLocation("thaumcraft", "models/obj/golem_hauler.obj"), new ResourceLocation("thaumcraft", "textures/entity/golems/golem_hauler.png"), PartModel.EnumAttachPoint.BODY), new Object[]{new ItemStack(PAItems.dimensional_singularity), new ItemStack(Blocks.OBSIDIAN)}, new EnumGolemTrait[]{PlanarArtifice.golemTraitTeleporter}));
  
		
	}

}
