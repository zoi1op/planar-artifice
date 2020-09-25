package leppa.planarartifice.items.baubles;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import leppa.planarartifice.items.ItemPA;
import leppa.planarartifice.main.PlanarArtifice;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import thaumcraft.api.items.IVisDiscountGear;

public class ItemBeltOfSuspension extends ItemPA implements IBauble, IVisDiscountGear {

	public ItemBeltOfSuspension(String name) {
		super(name);
		this.setMaxStackSize(1);
	}

	@Override
	public int getVisDiscount(ItemStack var1, EntityPlayer var2) {
		return 5;
	}

	@Override
	public BaubleType getBaubleType(ItemStack arg0) {
		return BaubleType.BELT;
	}

	public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
		player.fallDistance = 0;
		if(player.world.isRemote) {
			boolean isJumpPressed = Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown();
			boolean isSneakPressed = Minecraft.getMinecraft().gameSettings.keyBindSneak.isKeyDown();
			if(isJumpPressed && player.motionY < 0) {
				player.motionY = 0;
				player.velocityChanged = true;
			}
			if(isSneakPressed) {
				player.motionY = 0;
				player.velocityChanged = true;
			}
		}
	}

	public EnumRarity getRarity(ItemStack stack) {
		return PlanarArtifice.rarityPA;
	}
}
