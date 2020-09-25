package leppa.planarartifice.compat.tconstruct;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.modifiers.ModifierNBT;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.utils.TinkerUtil;
import thaumcraft.api.aura.AuraHelper;

public class TraitAuraInfusing extends AbstractTrait {

	public TraitAuraInfusing() {
		super("aurainfusing", 0xFFF442F1);
	}

	@SubscribeEvent
	public void onLivingExperienceDrop(LivingExperienceDropEvent e) {
		if(e.getAttackingPlayer() == null || e.getAttackingPlayer() instanceof FakePlayer || e.isCanceled())
			return;
		
		EntityPlayer p = e.getAttackingPlayer();
		NBTTagCompound tag = TinkerUtil.getModifierTag(p.getHeldItemMainhand(), "aurainfusing");
		int level = ModifierNBT.readTag(tag).level;
		if(level > 0) {
			int amount = e.getOriginalExperience();
			AuraHelper.addVis(p.getEntityWorld(), p.getPosition(), (0.5F * amount));
			e.setCanceled(true);
		}
	}

}
