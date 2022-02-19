package leppa.planarartifice.items.baubles;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import leppa.planarartifice.items.ItemPA;
import leppa.planarartifice.main.PlanarArtifice;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import thaumcraft.api.items.IVisDiscountGear;

public class ItemMirroredAmulet extends ItemPA implements IBauble, IVisDiscountGear {
	
	public ItemMirroredAmulet(String name) {
		super(name);
		this.setMaxStackSize(1);

	}

	@Override
	public int getVisDiscount(ItemStack var1, EntityPlayer var2){
		return 3;
	}
	
	@Override
	public BaubleType getBaubleType(ItemStack arg0){
		return BaubleType.AMULET;
	}

	@SuppressWarnings("deprecation")
	public EnumRarity getRarity(ItemStack stack){
		return PlanarArtifice.rarityPA;
	}
	
}