package leppa.planarartifice.registry;

import leppa.planarartifice.main.PlanarArtifice;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.ThaumcraftApi.BluePrint;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.crafting.IDustTrigger;
import thaumcraft.api.crafting.Part;
import thaumcraft.common.lib.crafting.DustTriggerMultiblock;

import java.util.Arrays;

public class PAMultiblocks{
	public static BluePrint mirromirous_teleporter;
	public static void registerMultiblocks() {
		//To name a Part, put where it is + what it turns into.
		Part TP = new Part(PABlocks.teleporter_matrix, PABlocks.teleporter_placeholder);
		Part MT = new Part(BlocksTC.stoneArcaneBrick, PABlocks.teleporter);
		Part BP = new Part(BlocksTC.stoneArcaneBrick, PABlocks.teleporter_placeholder);
		mirromirous_teleporter = new BluePrint("PA_MIRROR_TELEPORTER", new Part[][][] {{{TP}}, {{MT}}, {{BP}}}, new ItemStack(PABlocks.teleporter_matrix), new ItemStack(BlocksTC.stoneArcaneBrick, 2));
		IDustTrigger.registerDustTrigger(new DustTriggerMultiblock("PA_MIRROR_TELEPORTER", mirromirous_teleporter.getParts()));
		ThaumcraftApi.addMultiblockRecipeToCatalog(new ResourceLocation("planarartifice:mirror_teleporter"), mirromirous_teleporter);
		PlanarArtifice.LOGGER.info("[PA, MULTIBLOCK] Mirromirous Waystone: " + Arrays.deepToString(mirromirous_teleporter.getParts()));
	}
}