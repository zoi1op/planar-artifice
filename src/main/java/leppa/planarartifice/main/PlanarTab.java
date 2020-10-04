package leppa.planarartifice.main;

import leppa.planarartifice.registry.PAItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import slimeknights.mantle.block.EnumBlockConnectedTexture;

public class PlanarTab extends CreativeTabs {

	public PlanarTab() {
		super(PlanarArtifice.MODID + ".tab.name");
		setBackgroundImageName("planar.png");
		this.setNoTitle();
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(PAItems.alkimium_goggles);
	}
	
	@Override
	public String getTranslatedTabLabel() {
		return super.getTranslatedTabLabel();
	}
}
