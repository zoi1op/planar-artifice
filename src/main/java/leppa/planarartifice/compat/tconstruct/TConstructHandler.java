package leppa.planarartifice.compat.tconstruct;

import static slimeknights.tconstruct.library.utils.HarvestLevels.*;

import leppa.planarartifice.blocks.fluid.PAFluid;
import leppa.planarartifice.blocks.fluid.PAFluidBlock;
import leppa.planarartifice.compat.PACompatHandler.ICompatModule;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import slimeknights.tconstruct.library.MaterialIntegration;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.client.MaterialRenderInfo;
import slimeknights.tconstruct.library.materials.BowMaterialStats;
import slimeknights.tconstruct.library.materials.ExtraMaterialStats;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TConstructHandler implements ICompatModule {
	
	public static final Fluid fluidAlkimium = new PAFluid("molten_alkimium", new ResourceLocation("tconstruct:blocks/fluids/molten_metal"), new ResourceLocation("tconstruct:blocks/fluids/molten_metal_flow")).setColor(0xFF0DCE53).setLuminosity(15).setTemperature(1700);;
	public static final Fluid fluidBismuth = new PAFluid("molten_bismuth", new ResourceLocation("tconstruct:blocks/fluids/molten_metal"), new ResourceLocation("tconstruct:blocks/fluids/molten_metal_flow")).setColor(0xFFF0FFF0).setLuminosity(15).setTemperature(2500);;
				
	public static final Block alkimiumFluidBlock = new PAFluidBlock(fluidAlkimium, net.minecraft.block.material.Material.LAVA);
	public static final Block bismuthFluidBlock = new PAFluidBlock(fluidBismuth, net.minecraft.block.material.Material.LAVA);
	
	public static final AbstractTrait traitTransmutative = new TraitTransmutative();
	public static final AbstractTrait traitAuraInfusing = new TraitAuraInfusing();
	
	@Override
	public void preInit(FMLPreInitializationEvent e){
		
		System.out.println("HELLO");
		
		
		MinecraftForge.EVENT_BUS.register(traitTransmutative);
		MinecraftForge.EVENT_BUS.register(traitAuraInfusing);
		
		Material alkimium = new Material("alkimium", 0xFF0DCE53);
		alkimium.setCraftable(false).setCastable(true);
		alkimium.setFluid(fluidAlkimium);
		setMetalMaterialRenderInfo(alkimium, 0xFF0DCE53, 0.7f, 0f, 0.1f);
		
		alkimium.addTrait(traitTransmutative);
		
		TinkerRegistry.addMaterialStats(alkimium, new HeadMaterialStats(302, 9, 6f, OBSIDIAN), new HandleMaterialStats(1.3f, 24), new ExtraMaterialStats(92), new BowMaterialStats(0.6f, 1.7f, 11));
		
		MaterialIntegration mi = new MaterialIntegration(alkimium, fluidAlkimium);
		mi.oreSuffix = "Alchemical";
		mi.toolforge();
		mi.setRepresentativeItem("ingotAlchemical");
		TinkerRegistry.integrate(mi).preInit();
		
		Material bismuth = new Material("bismuth", 0xFFFF0000);
		bismuth.setCraftable(false).setCastable(true);
		bismuth.setFluid(fluidBismuth);
		setMetalMaterialRenderInfo(bismuth, 0xFFC05353, 0.3f, 0.2f, 36f);
		
		bismuth.addTrait(traitAuraInfusing);
		
		TinkerRegistry.addMaterialStats(bismuth, new HeadMaterialStats(450, 7.5f, 7f, COBALT), new HandleMaterialStats(1.1f, 55), new ExtraMaterialStats(125), new BowMaterialStats(0.7f, 1.9f, 8));
		
		MaterialIntegration mib = new MaterialIntegration(bismuth, fluidBismuth);
		mib.oreSuffix = "Bismuth";
		mib.setRepresentativeItem("ingotBismuth");
		TinkerRegistry.integrate(mib).preInit();
		
		TinkerRegistry.registerSmelteryFuel(new FluidStack(fluidAlkimium, 50), 120);
		TinkerRegistry.registerSmelteryFuel(new FluidStack(fluidBismuth, 50), 150);
	}
	
	@Override
	public void init(FMLInitializationEvent e) {
		
	}

	@Override
	public void postInit(FMLPostInitializationEvent e) {
		
	}
	
	public static Material setMetalMaterialRenderInfo(Material material, int colour, float shinyness, float brightness, float hueshift){
		material.setRenderInfo(new MaterialRenderInfo.Metal(colour, 0.7f, 0f, 0.1f));
		return material;
	}


}