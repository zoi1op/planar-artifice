package leppa.planarartifice.main;

import leppa.planarartifice.registry.PAItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class PlanarTab extends CreativeTabs {

	public PlanarTab() {
		super(PlanarArtifice.MODID + ".tab.name");
		setBackgroundImageName("planar.png");
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(PAItems.alkimium_goggles);
	}
}
