package leppa.planarartifice.client.render.tile;

import leppa.planarartifice.tiles.TileAlkimiumCentrifuge;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.renderers.models.block.ModelCentrifuge;
import thaumcraft.common.tiles.essentia.TileCentrifuge;

public class TESRAlkimiumCentrifuge extends TileEntitySpecialRenderer<TileAlkimiumCentrifuge> {
    private ModelCentrifuge model = new ModelCentrifuge();
    private static final ResourceLocation TEX = new ResourceLocation("planarartifice", "textures/models/block/alkimium_centrifuge.png");

    public TESRAlkimiumCentrifuge() {
    }

    public void renderEntityAt(TileCentrifuge cf, double x, double y, double z, float fq, int destroyStage) {
        this.bindTexture(TEX);
        GL11.glPushMatrix();
        if (destroyStage >= 0) {
            this.bindTexture(DESTROY_STAGES[destroyStage]);
            GlStateManager.matrixMode(5890);
            GlStateManager.pushMatrix();
            GlStateManager.scale(4.0F, 4.0F, 1.0F);
            GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
            GlStateManager.matrixMode(5888);
        }

        GL11.glTranslated(x + 0.5D, y + 0.5D, z + 0.5D);
        this.model.renderBoxes();
        GL11.glRotated((double)cf.rotation, 0.0D, 1.0D, 0.0D);
        this.model.renderSpinnyBit();
        if (destroyStage >= 0) {
            GlStateManager.matrixMode(5890);
            GlStateManager.popMatrix();
            GlStateManager.matrixMode(5888);
        }

        GL11.glPopMatrix();
    }

    @Override
    public void render(TileAlkimiumCentrifuge te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        super.render(te, x, y, z, partialTicks, destroyStage, alpha);
        this.renderEntityAt(te, x, y, z, partialTicks, destroyStage);
    }
}
