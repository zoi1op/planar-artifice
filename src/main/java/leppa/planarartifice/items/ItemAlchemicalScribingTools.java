package leppa.planarartifice.items;

import leppa.planarartifice.main.PlanarArtifice;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import thaumcraft.api.items.IRechargable;
import thaumcraft.api.items.IScribeTools;
import thaumcraft.api.items.RechargeHelper;

public class ItemAlchemicalScribingTools extends ItemPA implements IScribeTools, IRechargable {

	public ItemAlchemicalScribingTools(String name) {
		super(name);
		setMaxStackSize(1);
		setMaxDamage(200);
	}

	public void setDamage(ItemStack stack, int meta) {
		if(!(RechargeHelper.getCharge(stack) <= 0)) {
			RechargeHelper.consumeCharge(stack, null, 1);
		}
	}
	
	@Override
	public int getMaxCharge(ItemStack stack, EntityLivingBase player) {
		return 200;
	}

	@Override
	public EnumChargeDisplay showInHud(ItemStack stack, EntityLivingBase player) {
		return EnumChargeDisplay.NEVER;
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return PlanarArtifice.rarityPA;
	}
}
