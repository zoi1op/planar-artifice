package leppa.planarartifice.items;

import java.lang.reflect.Method;

import leppa.planarartifice.registry.PAItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import thaumcraft.api.items.ItemsTC;

public class ItemThaumostaticJetHarness extends ItemArmor{
	
	public ItemThaumostaticJetHarness(String name, ArmorMaterial mat) {
		super(mat, 3, EntityEquipmentSlot.CHEST);
		
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		
		PAItems.ITEMS.add(this);
		
	}
	
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair){
        return repair.getItem() == ItemsTC.ingots && repair.getItemDamage() == 2;
    }

    /**
     * Called when the equipped item is right clicked.
     */
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand){
        EntityEquipmentSlot entityequipmentslot = EntityLiving.getSlotForItemStack(itemStackIn);
        ItemStack itemstack = playerIn.getItemStackFromSlot(entityequipmentslot);
        
        if (itemstack.isEmpty()){
            playerIn.setItemStackToSlot(entityequipmentslot, itemStackIn.copy());
            itemStackIn.shrink(1);;
            return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
        }else{
            return new ActionResult(EnumActionResult.FAIL, itemStackIn);
        }
    }
    
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack){
    	if(!world.isRemote){
    		serverTick(world, player, itemStack);
    	}
    }
    
    private void serverTick(World world, EntityPlayer player, ItemStack itemStack){
    	try{
			Method m = Entity.class.getDeclaredMethod("setFlag", int.class, boolean.class);
			m.setAccessible(true);
			m.invoke(player, 7, true);
			m.setAccessible(false);
		}catch(Exception e){
			System.out.println("Thaumostatic Jet Harness just died. Welp.");
			e.printStackTrace();
		}
    }
}