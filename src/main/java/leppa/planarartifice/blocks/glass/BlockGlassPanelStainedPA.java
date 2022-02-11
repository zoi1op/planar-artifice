package leppa.planarartifice.blocks.glass;

import leppa.planarartifice.registry.PABlocks;
import net.minecraft.block.BlockBeacon;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class BlockGlassPanelStainedPA extends BlockGlassPanelPA {
    public static final PropertyEnum<EnumDyeColor> COLOR = PropertyEnum.create("color", EnumDyeColor.class);

    public BlockGlassPanelStainedPA(String name) { this(name, false, false, false); }
    public BlockGlassPanelStainedPA(String name, boolean passEntity, boolean passPlayer, boolean passHostile) {
        super(name, passEntity, passPlayer, passHostile);
        this.setDefaultState(this.blockState.getBaseState().withProperty(COLOR, EnumDyeColor.WHITE));
        PABlocks.BLOCKS.remove(this);
        PABlocks.METABLOCKS.add(this);
    }

    public int damageDropped(IBlockState state)
    {
        return state.getValue(COLOR).getMetadata();
    }
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    { for (int i = 0; i < EnumDyeColor.values().length; ++i) items.add(new ItemStack(this, 1, i)); }
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    { return MapColor.getBlockColor(state.getValue(COLOR)); }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }

    public IBlockState getStateFromMeta(int meta)
    { return this.getDefaultState().withProperty(COLOR, EnumDyeColor.byMetadata(meta)); }
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(COLOR).getMetadata();
    }
    protected BlockStateContainer createBlockState()
    { return new BlockStateContainer(this, COLOR); }
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    { if (!worldIn.isRemote) BlockBeacon.updateColorAsync(worldIn, pos); }
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    { if (!worldIn.isRemote) BlockBeacon.updateColorAsync(worldIn, pos); }
    @Nonnull
    @Override
    public ItemStack getPickBlock(@Nonnull IBlockState state, RayTraceResult target, @Nonnull World world, @Nonnull BlockPos pos, EntityPlayer player)
    { return new ItemStack(this, 1, getMetaFromState(state)); }
    @Override
    @SideOnly(Side.CLIENT)
    public void registerModels() {
        for (int i = 0; i < EnumDyeColor.values().length; ++i)
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), EnumDyeColor.values()[i].getMetadata(), new ModelResourceLocation(this.getRegistryName(), "color=" + EnumDyeColor.values()[i].getDyeColorName()));
    }
}
