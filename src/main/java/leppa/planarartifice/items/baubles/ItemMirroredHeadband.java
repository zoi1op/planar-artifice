package leppa.planarartifice.items.baubles;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import baubles.api.render.IRenderBauble;
import leppa.planarartifice.items.ItemPA;
import leppa.planarartifice.main.PlanarArtifice;
import leppa.planarartifice.registry.PAItems;
import leppa.planarartifice.util.LocalizationHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.api.items.IVisDiscountGear;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.items.baubles.ItemCuriosityBand;

import javax.annotation.Nullable;
import java.util.List;

public class ItemMirroredHeadband extends ItemPA implements IBauble, IRenderBauble, IVisDiscountGear {
    ResourceLocation tex = new ResourceLocation("planarartifice", "textures/items/mirromirous_headband_worn.png");

    public ItemMirroredHeadband() { this("mirromirous_headband"); }
    public ItemMirroredHeadband(String name) {
        super(name);
        this.maxStackSize = 1;
        this.canRepair = false;
        this.setMaxDamage(0);

        PAItems.ITEMS.add(this);
    }

    @Override
    public EnumRarity getRarity(ItemStack stack){
        return PlanarArtifice.rarityPA;
    }

    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.HEAD;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onPlayerBaubleRender(ItemStack stack, EntityPlayer player, RenderType type, float ticks) {
        if (type == RenderType.HEAD) {
            boolean armor = !player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).isEmpty();
            Minecraft.getMinecraft().renderEngine.bindTexture(this.tex);
            Helper.translateToHeadLevel(player);
            Helper.translateToFace();
            Helper.defaultTransforms();
            GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.translate(-0.5D, -0.5D, armor ? 0.11999999731779099D : 0.0D);
            UtilsFX.renderTextureIn3D(0.0F, 0.0F, 1.0F, 1.0F, 16, 26, 0.1F);
        }
    }

    @Override
    public int getVisDiscount(ItemStack itemStack, EntityPlayer entityPlayer) {
        return 3;
    }
}
