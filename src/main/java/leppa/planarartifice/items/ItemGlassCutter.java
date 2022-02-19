package leppa.planarartifice.items;

import leppa.planarartifice.main.PlanarArtifice;
import leppa.planarartifice.util.OreUtils;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

// stolen from Glassential which stole from Botania -p
@Mod.EventBusSubscriber(modid = PlanarArtifice.MODID)
public class ItemGlassCutter extends ItemPA {
    public ItemGlassCutter(String name) {
        super(name);
        setMaxStackSize(1);
        setMaxDamage(100);
    }

    @SubscribeEvent
    public static void onBlockDrops(BlockEvent.HarvestDropsEvent event) {
        if (event.getHarvester() != null
                && event.getState() != null
                && event.getDrops() != null
                && event.getDrops().isEmpty()
                && !event.getHarvester().getHeldItemMainhand().isEmpty()
                && event.getHarvester().getHeldItemMainhand().getItem() instanceof ItemGlassCutter
                && event.getState().getMaterial() == Material.GLASS
                && event.getState().getBlock().canSilkHarvest(event.getWorld(), event.getPos(), event.getState(), event.getHarvester()))
            event.getDrops().add(OreUtils.meta(event.getState().getBlock(), event.getState().getBlock().getMetaFromState(event.getState())));
    }

    @Override
    public boolean getIsRepairable(ItemStack par1ItemStack, @Nonnull ItemStack par2ItemStack) {
        return par2ItemStack.getItem() == Items.QUARTZ || super.getIsRepairable(par1ItemStack, par2ItemStack);
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
        if (!worldIn.isRemote) stack.damageItem(1, entityLiving);
        return state.getMaterial() == Material.GLASS || super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving);
    }
    protected boolean onBlockDestroyedWOCheck(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
        return super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving);
    }

    @Override
    public boolean canHarvestBlock(IBlockState blockIn) {
        return blockIn.getMaterial() == Material.GLASS;
    }

    @Override
    public float getDestroySpeed(ItemStack stack, IBlockState state) {
        return state.getMaterial() == Material.GLASS ? 5.0F : super.getDestroySpeed(stack, state);
    }
}
