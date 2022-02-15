package leppa.planarartifice.network;

import io.netty.buffer.ByteBuf;
import leppa.planarartifice.items.ItemThaumaturgistCoat;
import leppa.planarartifice.main.PlanarArtifice;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import thaumcraft.api.capabilities.ThaumcraftCapabilities;
import thaumcraft.api.items.RechargeHelper;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategory;
import thaumcraft.api.research.ResearchEntry;
import thaumcraft.common.lib.research.ResearchManager;

public class MessageOpenBook implements IMessage {
    public MessageOpenBook() {}

    public static class Handler implements IMessageHandler<MessageOpenBook, IMessage> {
        @Override
        public IMessage onMessage(final MessageOpenBook message, MessageContext ctx) {
            final EntityPlayer player = PlanarArtifice.proxy.getPlayerEntityFromContext(ctx);
            for (ResearchCategory cat : ResearchCategories.researchCategories.values()) {
                for (ResearchEntry ri : cat.research.values()) {
                    if (!ThaumcraftCapabilities.knowsResearch(player, ri.getKey()) || ri.getSiblings() == null) continue;
                    for (String sib : ri.getSiblings()) if (!ThaumcraftCapabilities.knowsResearch(player, sib)) ResearchManager.completeResearch(player, sib);
                }
            }
            ThaumcraftCapabilities.getKnowledge(player).sync((EntityPlayerMP)player);
            RechargeHelper.consumeCharge(ItemThaumaturgistCoat.findMe(player), player, 1);
            return null;
        }
    }

    @Override
    public void fromBytes(ByteBuf buf) {}
    @Override
    public void toBytes(ByteBuf buf) {}
}
