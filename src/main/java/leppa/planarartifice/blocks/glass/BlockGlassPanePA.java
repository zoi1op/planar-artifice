package leppa.planarartifice.blocks.glass;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockShulkerBox;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class BlockGlassPanePA extends BlockGlassPA {
    public static final PropertyBool NORTH = PropertyBool.create("north");
    public static final PropertyBool EAST = PropertyBool.create("east");
    public static final PropertyBool SOUTH = PropertyBool.create("south");
    public static final PropertyBool WEST = PropertyBool.create("west");
    protected static final AxisAlignedBB[] AABB_BY_INDEX = new AxisAlignedBB[] {new AxisAlignedBB(0.4375D, 0.0D, 0.4375D, 0.5625D, 1.0D, 0.5625D), new AxisAlignedBB(0.4375D, 0.0D, 0.4375D, 0.5625D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.4375D, 0.5625D, 1.0D, 0.5625D), new AxisAlignedBB(0.0D, 0.0D, 0.4375D, 0.5625D, 1.0D, 1.0D), new AxisAlignedBB(0.4375D, 0.0D, 0.0D, 0.5625D, 1.0D, 0.5625D), new AxisAlignedBB(0.4375D, 0.0D, 0.0D, 0.5625D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.5625D, 1.0D, 0.5625D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.5625D, 1.0D, 1.0D), new AxisAlignedBB(0.4375D, 0.0D, 0.4375D, 1.0D, 1.0D, 0.5625D), new AxisAlignedBB(0.4375D, 0.0D, 0.4375D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.4375D, 1.0D, 1.0D, 0.5625D), new AxisAlignedBB(0.0D, 0.0D, 0.4375D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.4375D, 0.0D, 0.0D, 1.0D, 1.0D, 0.5625D), new AxisAlignedBB(0.4375D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.5625D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)};
    public BlockGlassPanePA(String name) { this(name, false, false, false); }
    public BlockGlassPanePA(String name, boolean passEntity, boolean passPlayer, boolean passHostile) {
        super(name, passEntity, passPlayer, passHostile);
        this.setDefaultState(this.blockState.getBaseState().withProperty(NORTH, Boolean.FALSE).withProperty(EAST, Boolean.FALSE).withProperty(SOUTH, Boolean.FALSE).withProperty(WEST, Boolean.FALSE));
    }

    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState)
    {
        if (passPlayer && entityIn instanceof EntityPlayer) return;
        if (passEntity && entityIn != null && !(entityIn instanceof EntityPlayer)) return;
        if (!passEntity && passHostile && entityIn instanceof IMob) return;
        if (!isActualState) state = this.getActualState(state, worldIn, pos);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_BY_INDEX[0]);
        if (state.getValue(NORTH)) addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_BY_INDEX[getBoundingBoxIndex(EnumFacing.NORTH)]);
        if (state.getValue(SOUTH)) addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_BY_INDEX[getBoundingBoxIndex(EnumFacing.SOUTH)]);
        if (state.getValue(EAST)) addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_BY_INDEX[getBoundingBoxIndex(EnumFacing.EAST)]);
        if (state.getValue(WEST)) addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_BY_INDEX[getBoundingBoxIndex(EnumFacing.WEST)]);
    }
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        state = this.getActualState(state, source, pos);
        return AABB_BY_INDEX[getBoundingBoxIndex(state)];
    }
    private static int getBoundingBoxIndex(EnumFacing face) { return 1 << face.getHorizontalIndex(); }
    private static int getBoundingBoxIndex(IBlockState state)
    {
        int i = 0;
        if (state.getValue(NORTH)) i |= getBoundingBoxIndex(EnumFacing.NORTH);
        if (state.getValue(EAST)) i |= getBoundingBoxIndex(EnumFacing.EAST);
        if (state.getValue(SOUTH)) i |= getBoundingBoxIndex(EnumFacing.SOUTH);
        if (state.getValue(WEST)) i |= getBoundingBoxIndex(EnumFacing.WEST);
        return i;
    }
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return state.withProperty(NORTH, canPaneConnectTo(worldIn, pos, EnumFacing.NORTH))
                .withProperty(SOUTH, canPaneConnectTo(worldIn, pos, EnumFacing.SOUTH))
                .withProperty(WEST,  canPaneConnectTo(worldIn, pos, EnumFacing.WEST))
                .withProperty(EAST,  canPaneConnectTo(worldIn, pos, EnumFacing.EAST));
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() { return BlockRenderLayer.CUTOUT_MIPPED; }

    public int getMetaFromState(IBlockState state)
    {
        return 0;
    }
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        switch (rot) {
            case CLOCKWISE_180: return state.withProperty(NORTH, state.getValue(SOUTH)).withProperty(EAST, state.getValue(WEST)).withProperty(SOUTH, state.getValue(NORTH)).withProperty(WEST, state.getValue(EAST));
            case COUNTERCLOCKWISE_90: return state.withProperty(NORTH, state.getValue(EAST)).withProperty(EAST, state.getValue(SOUTH)).withProperty(SOUTH, state.getValue(WEST)).withProperty(WEST, state.getValue(NORTH));
            case CLOCKWISE_90: return state.withProperty(NORTH, state.getValue(WEST)).withProperty(EAST, state.getValue(NORTH)).withProperty(SOUTH, state.getValue(EAST)).withProperty(WEST, state.getValue(SOUTH));
            default: return state;
        }
    }
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        switch (mirrorIn) {
            case LEFT_RIGHT: return state.withProperty(NORTH, state.getValue(SOUTH)).withProperty(SOUTH, state.getValue(NORTH));
            case FRONT_BACK: return state.withProperty(EAST, state.getValue(WEST)).withProperty(WEST, state.getValue(EAST));
            default: return super.withMirror(state, mirrorIn);
        }
    }
    protected BlockStateContainer createBlockState() { return new BlockStateContainer(this, NORTH, EAST, WEST, SOUTH); }
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    { return face != EnumFacing.UP && face != EnumFacing.DOWN ? BlockFaceShape.MIDDLE_POLE_THIN : BlockFaceShape.CENTER_SMALL; }

    @Override
    public boolean canBeConnectedTo(IBlockAccess world, BlockPos pos, EnumFacing facing)
    {
        BlockPos offset = pos.offset(facing);
        return attachesTo(world, world.getBlockState(offset), offset, facing.getOpposite());
    }
    public boolean canPaneConnectTo(IBlockAccess world, BlockPos pos, EnumFacing dir)
    {
        BlockPos other = pos.offset(dir);
        IBlockState state = world.getBlockState(other);
        return state.getBlock().canBeConnectedTo(world, other, dir.getOpposite()) || attachesTo(world, state, other, dir.getOpposite());
    }
    public final boolean attachesTo(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing facing)
    {
        Block block = state.getBlock();
        BlockFaceShape blockfaceshape = state.getBlockFaceShape(world, pos, facing);
        return !isExcepBlockForAttachWithPiston(block) && blockfaceshape == BlockFaceShape.SOLID || blockfaceshape == BlockFaceShape.MIDDLE_POLE_THIN;
    }
    protected static boolean isExcepBlockForAttachWithPiston(Block block) { return block instanceof BlockShulkerBox || block instanceof BlockLeaves || block == Blocks.BEACON || block == Blocks.CAULDRON || block == Blocks.GLOWSTONE || block == Blocks.ICE || block == Blocks.SEA_LANTERN || block == Blocks.PISTON || block == Blocks.STICKY_PISTON || block == Blocks.PISTON_HEAD || block == Blocks.MELON_BLOCK || block == Blocks.PUMPKIN || block == Blocks.LIT_PUMPKIN || block == Blocks.BARRIER; }
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    { return blockAccess.getBlockState(pos.offset(side)).getBlock() != this && super.shouldSideBeRendered(blockState, blockAccess, pos, side); }
}
