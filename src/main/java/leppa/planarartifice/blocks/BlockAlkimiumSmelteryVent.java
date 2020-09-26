package leppa.planarartifice.blocks;

import java.util.List;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.common.blocks.IBlockFacingHorizontal;
import thaumcraft.common.lib.utils.BlockStateUtils;

public class BlockAlkimiumSmelteryVent extends BlockPA implements IBlockFacingHorizontal {

	public BlockAlkimiumSmelteryVent(String name) {
		super(Material.IRON, name);
		this.setSoundType(SoundType.METAL);
		this.setDefaultState(
		this.blockState.getBaseState().withProperty(IBlockFacingHorizontal.FACING, EnumFacing.NORTH));
		this.setHardness(1.0f);
		this.setResistance(10.0f);
	}

	public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side) {
		return side.getAxis().isHorizontal()
				&& worldIn.getBlockState(pos.offset(side.getOpposite())).getBlock() instanceof BlockAlkimiumSmeltery
				&& BlockStateUtils.getFacing(worldIn.getBlockState(pos.offset(side.getOpposite()))) != side;
	}

	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		tooltip.add("\u00a7Alkimium compatible");
	}

	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		IBlockState bs = this.getDefaultState();
		if(!facing.getAxis().isHorizontal()) {
			facing = EnumFacing.NORTH;
		}
		bs = bs.withProperty(IBlockFacingHorizontal.FACING, facing.getOpposite());
		return bs;
	}

	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(IBlockFacingHorizontal.FACING, EnumFacing.getHorizontal(BlockStateUtils.getFacing(meta).getHorizontalIndex()));
	}

	public int getMetaFromState(IBlockState state) {
		return state.getValue(IBlockFacingHorizontal.FACING).getIndex();
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, IBlockFacingHorizontal.FACING);
	}

	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	public boolean isFullCube(IBlockState state) {
		return false;
	}

	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing facing = BlockStateUtils.getFacing(state);
		switch(facing.ordinal()) {
		default:
			return new AxisAlignedBB(0.125, 0.125, 0.0, 0.875, 0.875, 0.5);

		case 3:
			return new AxisAlignedBB(0.125, 0.125, 0.5, 0.875, 0.875, 1.0);

		case 4:
			return new AxisAlignedBB(0.0, 0.125, 0.125, 0.5, 0.875, 0.875);

		case 5:
			return new AxisAlignedBB(0.5, 0.125, 0.125, 1.0, 0.875, 0.875);
		}
	}

}
