package leppa.planarartifice.blocks.glass;

import leppa.planarartifice.blocks.BlockPA;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class BlockGlassPA extends BlockPA {
    protected final boolean passEntity;
    protected final boolean passPlayer;
    protected final boolean passHostile;

    public BlockGlassPA(String name) { this(name, false, false, false); }
    public BlockGlassPA(String name, boolean passEntity, boolean passPlayer, boolean passHostile) {
        super(Material.GLASS, name);
        this.passEntity = passEntity;
        this.passPlayer = passPlayer;
        this.passHostile = passHostile;
        setHardness(0.3F);
        setSoundType(SoundType.GLASS);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState) {
        if (passPlayer && entityIn instanceof EntityPlayer) return;
        if (passEntity && entityIn != null && !(entityIn instanceof EntityPlayer)) return;
        if (!passEntity && passHostile && entityIn instanceof IMob) return;
        super.addCollisionBoxToList(state, worldIn, pos, entityBox, collidingBoxes, entityIn, isActualState);
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() { return BlockRenderLayer.CUTOUT; }

    public boolean isFullCube(IBlockState state) { return false; }
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
    public int quantityDropped(Random random)
    {
        return 0;
    }
    public boolean canSilkHarvest() { return true; }

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
        Block block = iblockstate.getBlock();
        if (blockState != iblockstate) { return true; }
        if (block == this) { return false; }
        return super.shouldSideBeRendered(blockState, blockAccess, pos, side);
    }

}
