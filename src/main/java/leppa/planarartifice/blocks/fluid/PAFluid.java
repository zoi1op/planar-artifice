package leppa.planarartifice.blocks.fluid;

import net.minecraft.block.material.Material;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class PAFluid extends Fluid{
	
	int mapColor = 0xFFFFFFFF;
	static float overlayAlpha = 0.2F;
	static SoundEvent emptySound = SoundEvents.ITEM_BUCKET_EMPTY;
	static SoundEvent fillSound = SoundEvents.ITEM_BUCKET_FILL;
	static Material material = Material.WATER;
	
	public PAFluid(String fluidName, ResourceLocation still, ResourceLocation flowing){
		super(fluidName, still, flowing);
		FluidRegistry.registerFluid(this);
		FluidRegistry.addBucketForFluid(this);
		
	}
	
	@Override
	public int getColor(){
		return mapColor;
	}
	
	public PAFluid setColor(int parColor){
		mapColor = parColor;
		return this;
	}
	
	public float getAlpha(){
		return overlayAlpha;
	}
	
	public PAFluid setAlpha(float parOverlayAlpha){
		overlayAlpha = parOverlayAlpha;
		return this;
	}
	
	@Override
	public PAFluid setEmptySound(SoundEvent parSound){
		emptySound = parSound;
		return this;
	}
	
	@Override
	public SoundEvent getEmptySound(){
		return emptySound;
	}
	
	@Override
	public PAFluid setFillSound(SoundEvent parSound){
		fillSound = parSound;
		return this;
	}
	
	@Override
	public SoundEvent getFillSound(){
		return fillSound;
	}
	
	public PAFluid setMaterial(Material parMaterial){
		material = parMaterial;
		return this;
	}
	
	public Material getMaterial(){
		return material;
	}
	
	@Override
	public boolean doesVaporize(FluidStack fluidStack){
		if(block == null)
			return false;
		return block.getDefaultState().getMaterial() == getMaterial();
	}
}