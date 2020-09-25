package leppa.planarartifice.items;

import java.util.List;

import javax.annotation.Nullable;

import leppa.planarartifice.main.PlanarArtifice;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.capabilities.IPlayerKnowledge.EnumKnowledgeType;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.common.lib.SoundsTC;

public class ItemDimensionalCuriosity extends ItemPA {

	public ItemDimensionalCuriosity(String name) {
		super(name);
	}

	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

		if(!worldIn.isRemote) {

			if(!playerIn.capabilities.isCreativeMode)
				playerIn.getHeldItem(handIn).shrink(1);

			worldIn.playSound((EntityPlayer) null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundsTC.learn, SoundCategory.NEUTRAL, 0.5f, 0.4f / (itemRand.nextFloat() * 0.4f + 0.8f));

			playerIn.addStat(StatList.getObjectUseStats(this));

			ThaumcraftApi.internalMethods.addKnowledge(playerIn, EnumKnowledgeType.THEORY, ResearchCategories.getResearchCategory("PLANARARTIFICE"), 33);
		}

		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

	public EnumRarity getRarity(ItemStack itemstack) {
		return PlanarArtifice.rarityPA;
	}

	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("Space warps around this.");
	}
}
