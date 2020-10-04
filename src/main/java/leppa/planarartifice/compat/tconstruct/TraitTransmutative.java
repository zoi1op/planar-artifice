package leppa.planarartifice.compat.tconstruct;

import leppa.planarartifice.recipe.RecipeTransmutation;
import leppa.planarartifice.registry.PARecipes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.capability.projectile.CapabilityTinkerProjectile;
import slimeknights.tconstruct.library.capability.projectile.ITinkerProjectile;
import slimeknights.tconstruct.library.modifiers.IToolMod;
import slimeknights.tconstruct.library.modifiers.ModifierNBT;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.utils.TinkerUtil;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.TinkerTraits;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class TraitTransmutative extends AbstractTrait {

	public TraitTransmutative() {
		super("transmutative", 0xFF0DCE53);
	}

	@Override
	public boolean canApplyTogether(IToolMod toolmod) {
		// Incompatible with Squeaky, Silk Touch, and Autosmelt
		return !toolmod.getIdentifier().equals(TinkerTraits.squeaky.getIdentifier())
				&& !toolmod.getIdentifier().equals(TinkerModifiers.modSilktouch.getIdentifier())
				&& !toolmod.getIdentifier().equals(TinkerTraits.autosmelt.getIdentifier());
	}

	@SubscribeEvent
	public void onLivingDrops(LivingDropsEvent e) {
		if(e.getSource().getTrueSource() instanceof EntityPlayer) {
			ItemStack item = CapabilityTinkerProjectile.getTinkerProjectile(e.getSource())
					.map(ITinkerProjectile::getItemStack)
					.orElse(((EntityPlayer) e.getSource().getTrueSource()).getHeldItem(EnumHand.MAIN_HAND));
			NBTTagCompound tag = TinkerUtil.getModifierTag(item, "transmutative");
			int level = ModifierNBT.readTag(tag).level;
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

					}
				});

				for(int i = 0; i < e.getDrops().size(); i++) {
					e.getDrops().get(i).setItem(items.get(i));
				}
			}
		}
	}

}
