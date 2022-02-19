package leppa.planarartifice.client.render.tile;

import leppa.planarartifice.tiles.TileTeleporter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.ForgeHooksClient;
import org.lwjgl.opengl.GL11;

public class TESRTeleporter extends TileEntitySpecialRenderer<TileTeleporter>{
	
	@Override
	public void render(TileTeleporter te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		ItemStack stack = te.crystal.getStackInSlot(0);
		GlStateManager.enableRescaleNormal();
		GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1f);
		GlStateManager.enableBlend();
		RenderHelper.enableStandardItemLighting();
		GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
		GlStateManager.pushMatrix();
		float offset = (te.getWorld().getTotalWorldTime() - te.lastChangeTime + partialTicks) / 8.0F;
		float offsetX = MathHelper.cos(offset) / 1.5F;
		float offsetZ = MathHelper.sin(offset) / 1.5F;
		GlStateManager.translate(x + 0.5 + offsetX, y + 0.5, z + 0.5 + offsetZ);
		GlStateManager.rotate((te.getWorld().getTotalWorldTime() + partialTicks) * 4, 0, 1, 0);
		IBakedModel model = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(stack, te.getWorld(),  null);
		model = ForgeHooksClient.handleCameraTransforms(model, ItemCameraTransforms.TransformType.GROUND, false);
		Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		Minecraft.getMinecraft().getRenderItem().renderItem(stack, model);
		GlStateManager.popMatrix();
		GlStateManager.disableRescaleNormal();
		GlStateManager.disableBlend();
	}
}