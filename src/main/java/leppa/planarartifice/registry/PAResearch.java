package leppa.planarartifice.registry;

import leppa.planarartifice.foci.FocusEffectColourized;
import leppa.planarartifice.foci.FocusEffectPrismLight;
import leppa.planarartifice.main.PlanarArtifice;
import leppa.planarartifice.util.OreUtils;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.casters.FocusEngine;
import thaumcraft.api.golems.EnumGolemTrait;
import thaumcraft.api.golems.parts.GolemAddon;
import thaumcraft.api.golems.parts.GolemMaterial;
import thaumcraft.api.golems.parts.PartModel;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.api.research.ResearchCategory;
import thaumcraft.api.research.ScanBlock;
import thaumcraft.api.research.ScanningManager;
import thaumcraft.common.golems.client.PartModelHauler;

public class PAResearch {
	
	public static ResearchCategory catPA;

	public static void registerResearch() {
		PlanarArtifice.LOGGER.info("Research active");
		ThaumcraftApi.registerResearchLocation(new ResourceLocation("planarartifice:research/main.json"));
		if (!OreUtils.exists("ingotLead"))
			ThaumcraftApi.registerResearchLocation(new ResourceLocation(PlanarArtifice.MODID, "research/metal_1.json"));
		else if (OreUtils.exists("ingotTin") && OreUtils.exists("ingotCopper") && OreUtils.exists("ingotSilver"))
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
