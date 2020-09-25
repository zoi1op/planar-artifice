package leppa.planarartifice.main;

import leppa.planarartifice.containers.ContainerFluxScrubber;
import leppa.planarartifice.containers.ContainerPotionMixer;
import leppa.planarartifice.containers.gui.GuiAlkimiumSmeltery;
import leppa.planarartifice.containers.gui.GuiFluxScrubber;
import leppa.planarartifice.containers.gui.GuiPotionMixer;
import leppa.planarartifice.tiles.TileAlkimiumSmeltery;
import leppa.planarartifice.tiles.TileFluxScrubber;
import leppa.planarartifice.tiles.TilePotionMixer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import thaumcraft.common.container.ContainerSmelter;

public class PAGuiHandler implements IGuiHandler {
		
	public static final int ID_ALKIMIUM_SMELTERY = 0;	
	public static final int ID_FLUX_SCRUBBER = 1;
	public static final int ID_POTION_MIXER = 2;
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z){
		
		switch(ID) {
			case ID_ALKIMIUM_SMELTERY:
				return new ContainerSmelter(player.inventory, (TileAlkimiumSmeltery) world.getTileEntity(new BlockPos(x, y, z)));
			case ID_FLUX_SCRUBBER:
				return new ContainerFluxScrubber(player.inventory, (TileFluxScrubber) world.getTileEntity(new BlockPos(x, y, z)));
			case ID_POTION_MIXER:
				return new ContainerPotionMixer(player.inventory, (TilePotionMixer) world.getTileEntity(new BlockPos(x, y, z)));
		}
		
		return null;
		
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z){
		
		switch(ID) {
			case ID_ALKIMIUM_SMELTERY:
				return new GuiAlkimiumSmeltery(player.inventory, (TileAlkimiumSmeltery) world.getTileEntity(new BlockPos(x, y, z)));
			case ID_FLUX_SCRUBBER:
				return new GuiFluxScrubber(player.inventory, (TileFluxScrubber) world.getTileEntity(new BlockPos(x, y, z)));
			case ID_POTION_MIXER:
				return new GuiPotionMixer(player.inventory, (TilePotionMixer) world.getTileEntity(new BlockPos(x, y, z)));
		}
	
		return null;
	}
}
