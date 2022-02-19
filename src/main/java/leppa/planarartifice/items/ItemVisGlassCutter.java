package leppa.planarartifice.items;

import leppa.planarartifice.main.PlanarArtifice;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import thaumcraft.api.items.IRechargable;
import thaumcraft.api.items.RechargeHelper;

import javax.annotation.Nonnull;

// stolen from Glassential which stole from Botania -p
@Mod.EventBusSubscriber(modid = PlanarArtifice.MODID)
public class ItemVisGlassCutter extends ItemGlassCutter implements IRechargable {
    public ItemVisGlassCutter(String name) {
        super(name);
        setMaxDamage(0);
    }

    @SuppressWarnings("deprecation")
    public EnumRarity getRarity(ItemStack itemstack) {
        return EnumRarity.RARE;
    }
    @Override
    public boolean getIsRepairable(ItemStack par1ItemStack, @Nonnull ItemStack par2ItemStack) { return false; }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase player) {
        if (!worldIn.isRemote && RechargeHelper.consumeCharge(stack, player, 1))
            return state.getMaterial() == Material.GLASS || super.onBlockDestroyedWOCheck(stack, worldIn, state, pos, player);
        else return super.onBlockDestroyedWOCheck(stack, worldIn, state, pos, player);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, IBlockState state) {
        return state.getMaterial() == Material.GLASS ? (RechargeHelper.getCharge(stack) < 1 ? 0F : 5F) : super.getDestroySpeed(stack, state);
    }

    @Override
    public int getMaxCharge(ItemStack itemStack, EntityLivingBase player) { return 150; }
    @Override
    public EnumChargeDisplay showInHud(ItemStack itemStack, EntityLivingBase player) { return EnumChargeDisplay.NORMAL; }
}
