package leppa.planarartifice.registry;

import leppa.planarartifice.main.PlanarArtifice;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

import java.awt.*;

import static leppa.planarartifice.util.AspectUtils.*;

public class PAAspects {

	public static Aspect DIMENSIONS;
	public static Aspect TIME;
	public static Aspect COLOR;

	public static void registerAspects() {
		DIMENSIONS = new Aspect("spatio", 0x4AF755, new Aspect[]{Aspect.VOID, Aspect.ENTROPY}, new ResourceLocation(PlanarArtifice.MODID, "textures/aspects/spatium.png"), 1);
		TIME = new Aspect("tempus", 0xD6DB43, new Aspect[]{DIMENSIONS, Aspect.EXCHANGE}, new ResourceLocation(PlanarArtifice.MODID, "textures/aspects/tempus.png"), 1);
		COLOR = new Aspect("tinctura", 0xFFFFFF, new Aspect[]{Aspect.EXCHANGE, Aspect.SENSES}, new ResourceLocation(PlanarArtifice.MODID, "textures/aspects/tinctura.png"), 1) {
			@Override
			public int getColor() {
				return Color.HSBtoRGB(MinecraftServer.getCurrentTimeMillis() * 2 % 3600 / 3600F, 0.75F, 1F);
			}
		};
	}

	public static void registerItemAspects() {
		add(new ItemStack(Items.CLOCK), new AspectList().add(PAAspects.TIME, 10));
		add(new ItemStack(Items.ENDER_PEARL), new AspectList().add(PAAspects.DIMENSIONS, 10).add(PAAspects.TIME, 5));
		add(new ItemStack(Blocks.ENDER_CHEST), new AspectList().add(PAAspects.DIMENSIONS, 12));
		add(new ItemStack(PABlocks.flux_scrubber), new AspectList().add(PAAspects.DIMENSIONS, 6));
		add(new ItemStack(PAItems.dimensional_curiosity), new AspectList().add(PAAspects.DIMENSIONS, 25));
		add(new ItemStack(PAItems.bismuth_ingot), new AspectList().add(PAAspects.DIMENSIONS, 3));
		add(new ItemStack(PAItems.dimensional_singularity), new AspectList().add(PAAspects.DIMENSIONS, 30).add(PAAspects.TIME, 30));
		add(new ItemStack(Items.BONE), new AspectList().add(PAAspects.COLOR, 5));
		add(new ItemStack(Items.GLOWSTONE_DUST), new AspectList().add(PAAspects.COLOR, 5));
		add(new ItemStack(Blocks.LAPIS_BLOCK), new AspectList().add(PAAspects.COLOR, 180));
		add(new ItemStack(Blocks.LAPIS_ORE), new AspectList().add(PAAspects.COLOR, 15));
		add(new ItemStack(Blocks.RED_FLOWER), new AspectList().add(PAAspects.COLOR, 15));
		add(new ItemStack(Blocks.YELLOW_FLOWER), new AspectList().add(PAAspects.COLOR, 15));
		add(new ItemStack(Blocks.CACTUS), new AspectList().add(PAAspects.COLOR, 15));
		add(new ItemStack(Blocks.DOUBLE_PLANT), new AspectList().add(PAAspects.COLOR, 15));
		add(new ItemStack(Blocks.GLOWSTONE), new AspectList().add(PAAspects.COLOR, 15));

		add(new ItemStack(Blocks.BLACK_GLAZED_TERRACOTTA), new AspectList().add(PAAspects.COLOR, 15));
		add(new ItemStack(Blocks.WHITE_GLAZED_TERRACOTTA), new AspectList().add(PAAspects.COLOR, 15));
		add(new ItemStack(Blocks.YELLOW_GLAZED_TERRACOTTA), new AspectList().add(PAAspects.COLOR, 15));
		add(new ItemStack(Blocks.BLUE_GLAZED_TERRACOTTA), new AspectList().add(PAAspects.COLOR, 15));
		add(new ItemStack(Blocks.BROWN_GLAZED_TERRACOTTA), new AspectList().add(PAAspects.COLOR, 15));
		add(new ItemStack(Blocks.RED_GLAZED_TERRACOTTA), new AspectList().add(PAAspects.COLOR, 15));
		add(new ItemStack(Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA), new AspectList().add(PAAspects.COLOR, 15));
		add(new ItemStack(Blocks.GRAY_GLAZED_TERRACOTTA), new AspectList().add(PAAspects.COLOR, 15));
		add(new ItemStack(Blocks.CYAN_GLAZED_TERRACOTTA), new AspectList().add(PAAspects.COLOR, 15));
		add(new ItemStack(Blocks.GREEN_GLAZED_TERRACOTTA), new AspectList().add(PAAspects.COLOR, 15));
		add(new ItemStack(Blocks.LIME_GLAZED_TERRACOTTA), new AspectList().add(PAAspects.COLOR, 15));
		add(new ItemStack(Blocks.MAGENTA_GLAZED_TERRACOTTA), new AspectList().add(PAAspects.COLOR, 15));
		add(new ItemStack(Blocks.ORANGE_GLAZED_TERRACOTTA), new AspectList().add(PAAspects.COLOR, 15));
		add(new ItemStack(Blocks.PINK_GLAZED_TERRACOTTA), new AspectList().add(PAAspects.COLOR, 15));
		add(new ItemStack(Blocks.PURPLE_GLAZED_TERRACOTTA), new AspectList().add(PAAspects.COLOR, 15));
		add(new ItemStack(Blocks.SILVER_GLAZED_TERRACOTTA), new AspectList().add(PAAspects.COLOR, 15));

		for (int i = 0; i < 16; i++) {
			add(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, i), new AspectList().add(PAAspects.COLOR, 10));
			add(new ItemStack(Blocks.WOOL, 1, i), new AspectList().add(PAAspects.COLOR, 5));
			add(new ItemStack(Blocks.CONCRETE_POWDER, 1, i), new AspectList().add(PAAspects.COLOR, 5));
			add(new ItemStack(Blocks.CONCRETE, 1, i), new AspectList().add(PAAspects.COLOR, 5));
			add(new ItemStack(Items.DYE, 1, i), new AspectList().add(PAAspects.COLOR, 20));
			add(new ItemStack(Blocks.STAINED_GLASS, 1, i), new AspectList().add(PAAspects.COLOR, 15));
		}

		set(new ItemStack(PAItems.condensed_crystal_cluster), new AspectList().add(Aspect.FIRE, 7).add(Aspect.AIR, 7).add(Aspect.EARTH, 7).add(Aspect.ORDER, 7).add(Aspect.ENTROPY, 7).add(Aspect.MAGIC, 7).add(Aspect.WATER, 7));
	}
}