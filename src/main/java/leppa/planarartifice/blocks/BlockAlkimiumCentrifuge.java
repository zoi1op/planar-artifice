package leppa.planarartifice.blocks;

import leppa.planarartifice.tiles.TileAlkimiumCentrifuge;
import leppa.planarartifice.util.LocalizationHelper;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.common.blocks.IBlockEnabled;
import thaumcraft.common.blocks.IBlockFacingHorizontal;
import thaumcraft.common.lib.utils.BlockStateUtils;

import javax.annotation.Nullable;
import java.util.List;

public class BlockAlkimiumCentrifuge extends BlockPA implements IBlockEnabled, IBlockFacingHorizontal {
    public BlockAlkimiumCentrifuge(String name) {
        super(Material.WOOD, name);;
        this.setHardness(2.0F);
        this.setResistance(20.0F);
    }

    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileAlkimiumCentrifuge();
    }

    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public boolean isFullCube(IBlockState state) {
        return false;
    }

    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.INVISIBLE;
    }

    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
        return BlockFaceShape.UNDEFINED;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
        tooltip.add(TextFormatting.GREEN + LocalizationHelper.localize("planarartifice.alkimium"));
    }

    public void updateFacing(World world, BlockPos pos, EnumFacing face) {
        if(face != BlockStateUtils.getFacing(world.getBlockState(pos)) && face.getHorizontalIndex() != 0)
            world.setBlockState(pos, world.getBlockState(pos).withProperty(IBlockFacingHorizontal.FACING, face), 3);

    }
}
