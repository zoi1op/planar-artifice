package leppa.planarartifice.blocks.glass;

import net.minecraft.block.BlockDirectional;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGlassRedstoneDirectionalPA extends BlockGlassPA {
    public static final PropertyDirection FACING = BlockDirectional.FACING;
    public BlockGlassRedstoneDirectionalPA(String name) { this(name, false, false, false); }
    public BlockGlassRedstoneDirectionalPA(String name, boolean passEntity, boolean passPlayer, boolean passHostile) {
        super(name, passEntity, passPlayer, passHostile);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    { return this.getDefaultState().withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer)); }
    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    { worldIn.setBlockState(pos, state.withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer)), 2); }
    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot)
    { return state.withProperty(FACING, rot.rotate(state.getValue(FACING))); }
    @Override
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    { return state.withRotation(mirrorIn.toRotation(state.getValue(FACING))); }
    @Override
    protected BlockStateContainer createBlockState()
    { return new BlockStateContainer(this, FACING); }
    @Override
    public IBlockState getStateFromMeta(int meta)  { return this.getDefaultState().withProperty(FACING, EnumFacing.getFront(meta & 7)); }
    @Override
    public int getMetaFromState(IBlockState state) { return state.getValue(FACING).getIndex(); }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public boolean canProvidePower(IBlockState state) {
        return true;
    }

    @Override
    public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        if (blockState.getValue(FACING) == side.getOpposite()) return 15;
        else return 0;
    }
}
