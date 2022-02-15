package leppa.planarartifice.items;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
import baubles.api.render.IRenderBauble;
import leppa.planarartifice.main.PlanarArtifice;
import leppa.planarartifice.registry.PAItems;
import leppa.planarartifice.util.LocalizationHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.api.items.IGoggles;
import thaumcraft.api.items.IRechargable;
import thaumcraft.api.items.IRevealer;
import thaumcraft.api.items.IVisDiscountGear;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ItemThaumaturgistCoat extends ItemArmor implements IRevealer, IGoggles, IVisDiscountGear, IBauble, IRenderBauble, IRechargable {
        public ItemThaumaturgistCoat(String name, ArmorMaterial mat) {
                super(mat, 3, EntityEquipmentSlot.CHEST);
                setUnlocalizedName(name);
                setRegistryName(name);
                setCreativeTab(PlanarArtifice.creativetab);
                PAItems.ITEMS.add(this);
        }

        @Override
        public BaubleType getBaubleType(ItemStack itemstack) { return BaubleType.BODY; }
        public int getVisDiscount(ItemStack itemStack, EntityPlayer entityPlayer) { return 5; }
        public EnumRarity getRarity(@Nonnull ItemStack stack) {
                return PlanarArtifice.rarityPA;
        }
        @SideOnly(Side.CLIENT)
        public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, List<String> tooltip, @Nonnull ITooltipFlag flagIn) {
                tooltip.add(TextFormatting.GREEN + LocalizationHelper.localize("planarartifice.alkimium"));
        }
        public static boolean isWearingThis(EntityPlayer player) { return player.inventory.armorInventory.get(2).getItem() == PAItems.thaum_coat || BaublesApi.isBaubleEquipped(player, PAItems.thaum_coat) != -1; }
        public static ItemStack findMe(EntityPlayer player) {
                ItemStack i = player.inventory.armorInventory.get(2);
                if (i.getItem() instanceof ItemThaumaturgistCoat) return i;
                i = BaublesApi.getBaublesHandler(player).getStackInSlot(BaubleType.BODY.ordinal());
                if (i.getItem() instanceof ItemThaumaturgistCoat) return i;
                return ItemStack.EMPTY;
        }
        // goggle
        public boolean showIngamePopups(ItemStack itemStack, EntityLivingBase entityLivingBase) { return true; }
        public boolean showNodes(ItemStack itemStack, EntityLivingBase entityLivingBase) { return true; }
        public static boolean shouldRenderHud(EntityPlayer player) { return isWearingThis(player); }
        @SideOnly(Side.CLIENT)
        @Override
        public void onPlayerBaubleRender(ItemStack stack, EntityPlayer player, RenderType type, float partialTicks) {
        }

        @Override
        public int getMaxCharge(ItemStack itemStack, EntityLivingBase entityLivingBase) { return 200; }
        @Override
        public EnumChargeDisplay showInHud(ItemStack itemStack, EntityLivingBase player) { return EnumChargeDisplay.NORMAL; }
}
