package leppa.planarartifice.blocks.glass;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockShulkerBox;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGlassPanelPA extends BlockGlassPA {
    protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.0D, 0.4375D, 0.0D, 1.0D, 0.5625D, 1.0D);
    public BlockGlassPanelPA(String name) { this(name, false, false, false); }
    public BlockGlassPanelPA(String name, boolean passEntity, boolean passPlayer, boolean passHostile) {
        super(name, passEntity, passPlayer, passHostile);
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) { return AABB; }

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    { return blockAccess.getBlockState(pos.offset(side)).getBlock() != this && super.shouldSideBeRendered(blockState, blockAccess, pos, side); }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    @Override
    public boolean canBeConnectedTo(IBlockAccess world, BlockPos pos, EnumFacing facing)
    {
        BlockPos offset = pos.offset(facing);
        return attachesTo(world, world.getBlockState(offset), offset, facing.getOpposite());
    }

    public final boolean attachesTo(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing facing)
    {
        Block block = state.getBlock();
        BlockFaceShape blockfaceshape = state.getBlockFaceShape(worldIn, pos, facing);
        return !isExcepBlockForAttachWithPiston(block) && blockfaceshape == BlockFaceShape.SOLID || blockfaceshape == BlockFaceShape.MIDDLE_POLE_THIN;
    }

    protected static boolean isExcepBlockForAttachWithPiston(Block block)
    { return block instanceof BlockShulkerBox || block instanceof BlockLeaves || block == Blocks.BEACON || block == Blocks.CAULDRON || block == Blocks.GLOWSTONE || block == Blocks.ICE || block == Blocks.SEA_LANTERN || block == Blocks.PISTON || block == Blocks.STICKY_PISTON || block == Blocks.PISTON_HEAD || block == Blocks.MELON_BLOCK || block == Blocks.PUMPKIN || block == Blocks.LIT_PUMPKIN || block == Blocks.BARRIER; }
}
