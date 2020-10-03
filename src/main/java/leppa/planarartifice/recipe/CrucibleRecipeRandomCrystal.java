package leppa.planarartifice.recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import com.zeitheron.hammercore.internal.init.ItemsHC;
import com.zeitheron.thaumicadditions.TAReconstructed;
import com.zeitheron.thaumicadditions.api.AspectUtil;
import com.zeitheron.thaumicadditions.init.ItemsTAR;
import com.zeitheron.thaumicadditions.init.RecipesTAR;
import com.zeitheron.thaumicadditions.items.ItemSaltEssence;
import leppa.planarartifice.compat.thaumicadditions.ThaumicAdditionsHandler;
import leppa.planarartifice.main.PAConfig;
import leppa.planarartifice.registry.PAAspects;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.CrucibleRecipe;

public class CrucibleRecipeRandomCrystal extends CrucibleRecipe {

	public static ArrayList<Aspect> yangAspects = new ArrayList<>();
	public static ArrayList<Aspect> yinAspects = new ArrayList<>();
	public Random random;
	public boolean isDark;

	public CrucibleRecipeRandomCrystal(String researchKey, Object catalyst, AspectList tags) {
		super(researchKey, ItemStack.EMPTY, catalyst, tags);
		isDark = (tags.aspects.containsKey(Aspect.FLUX));
		random = new Random();
	}

	@Override
	public ItemStack getRecipeOutput() {
		ArrayList<Aspect> listToUse = isDark ? yinAspects : yangAspects;
		if (ThaumicAdditionsHandler.extraActivated)
			return AspectUtil.salt(listToUse.get(random.nextInt(listToUse.size())), 3);
		return ThaumcraftApiHelper.makeCrystal(listToUse.get(random.nextInt(listToUse.size())), 3);
	}

	public static void registerAspectList() {
		final Aspect[] thaumYangAspects = {
				Aspect.ORDER, Aspect.EARTH, Aspect.AIR, Aspect.MAGIC, Aspect.AURA, Aspect.PROTECT, Aspect.LIGHT, Aspect.LIFE, Aspect.SENSES,
				Aspect.CRYSTAL, Aspect.EXCHANGE, Aspect.MECHANISM, Aspect.FLIGHT, Aspect.CRAFT, Aspect.TOOL, Aspect.MAN, Aspect.PLANT,
				Aspect.MOTION, Aspect.ENERGY, Aspect.METAL
		};
		final Aspect[] thaumYinAspects = {
				Aspect.ENTROPY, Aspect.FIRE, Aspect.WATER, Aspect.ALCHEMY, Aspect.FLUX, Aspect.AVERSION, Aspect.COLD, Aspect.DEATH, Aspect.SOUL,
				Aspect.DESIRE, Aspect.ELDRITCH, Aspect.TRAP, Aspect.BEAST, Aspect.DARKNESS, Aspect.VOID, Aspect.UNDEAD
		};
		yangAspects.addAll(Arrays.asList(thaumYangAspects));
		yinAspects.addAll(Arrays.asList(thaumYinAspects));
		if (!PAConfig.compat.disableAspectCompat) {
			yangAspects.add(PAAspects.TIME);
			yinAspects.add(PAAspects.DIMENSIONS);
			yangAspects.add(PAAspects.COLOR);
		}
	}
}
