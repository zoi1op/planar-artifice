package leppa.planarartifice.event;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import leppa.planarartifice.enchantment.EnumInfusionEnchantmentII;
import leppa.planarartifice.recipe.RecipeTransmutation;
import leppa.planarartifice.registry.PARecipes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.aura.AuraHelper;

public class EntityEventHandler {

	@SubscribeEvent
	public static void onLivingDropsInfusion(LivingDropsEvent e) {
		if(e.getSource().getTrueSource() instanceof EntityPlayer) {
			ItemStack item = ((EntityPlayer) e.getSource().getTrueSource()).getHeldItem(EnumHand.MAIN_HAND);
			int level = EnumInfusionEnchantmentII.getInfusionEnchantmentLevel(item,
					EnumInfusionEnchantmentII.TRANSMUTATIVE);
			if(level > 0) {
				ArrayList<ItemStack> items = new ArrayList<>();

				List<RecipeTransmutation> recipes = PARecipes.getRecipesOfType(RecipeTransmutation.class);

				recipes.forEach(r -> {
					ListIterator<EntityItem> iter = e.getDrops().listIterator();
					while(iter.hasNext()) {
						EntityItem ei = iter.next();
						ItemStack it = ei.getItem();

						if(r.matches(it)) {
							ItemStack output = r.getRecipeOutput();
							output.setCount(it.getCount());
							items.add(output);
						}

						else {
							items.add(it);
						}

					}});

					for(int i = 0; i < e.getDrops().size(); i++) {
						e.getDrops().get(i).setItem(items.get(i));
					}

			}

		}
	}

	@SubscribeEvent
	public static void onLivingExperienceDrop(LivingExperienceDropEvent event) {
		if(event.getAttackingPlayer() != null) {
			event.getAttackingPlayer().getHeldItemMainhand();
			EntityPlayer p = event.getAttackingPlayer();
			ItemStack item = p.getHeldItemMainhand();
			int level = EnumInfusionEnchantmentII.getInfusionEnchantmentLevel(item,
					EnumInfusionEnchantmentII.AURAINFUSING);
			if(level > 0) {
				int amount = event.getOriginalExperience();
				AuraHelper.addVis(p.getEntityWorld(), p.getPosition(), (float) (0.5 * amount));
				event.setCanceled(true);
			}
		}
	}

}
