package leppa.planarartifice.client.render.tile;

import leppa.planarartifice.blocks.BlockStarvingChest;
import leppa.planarartifice.tiles.TileStarvingChest;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nonnull;

@SideOnly(Side.CLIENT)
public class TESRStarvingChest extends TileEntitySpecialRenderer<TileStarvingChest> {
    private ModelChest chestModel = new ModelChest();
    private static final ResourceLocation[] textures = new ResourceLocation[] {
        new ResourceLocation("planarartifice", "textures/models/starving_chest_1.png"),
            new ResourceLocation("planarartifice", "textures/models/starving_chest_2.png"),
            new ResourceLocation("planarartifice", "textures/models/starving_chest_3.png"),
            new ResourceLocation("planarartifice", "textures/models/starving_chest_4.png")
    };

    public TESRStarvingChest() {
    }

    public void renderTileEntityChestAt(TileStarvingChest chest, double x, double y, double z, float partialTicks, int destroyStage) {
        int meta = 0;
        int meta2 = 3;
        if (chest.hasWorld()) {
            meta = chest.getBlockMetadata();
            IBlockState state = chest.getWorld().getBlockState(chest.getPos());
            if (state.getPropertyKeys().contains(BlockStarvingChest.FACING)) meta2 = state.getValue(BlockStarvingChest.FACING).getIndex();
        }

        ModelChest chestModel = this.chestModel;
        if (destroyStage >= 0) {
            this.bindTexture(DESTROY_STAGES[destroyStage]);
            GlStateManager.matrixMode(5890);
            GlStateManager.pushMatrix();
            GlStateManager.scale(4.0F, 4.0F, 1.0F);
            GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
            GlStateManager.matrixMode(5888);
        } else {
            this.bindTexture(textures[meta]);
        }

        GL11.glPushMatrix();
        GL11.glEnable(32826);
        if (destroyStage < 0) {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        }

        GL11.glTranslatef((float)x, (float)y + 1.0F, (float)z + 1.0F);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        short rotate = 0;
        if (meta2 == 2) {
            rotate = 180;
        }

        if (meta2 == 4) {
            rotate = 90;
        }

        if (meta2 == 5) {
            rotate = -90;
        }

        GL11.glRotatef(rotate, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        float rotateLid = chest.prevLidAngle + (chest.lidAngle - chest.prevLidAngle) * partialTicks;
        rotateLid = 1.0F - rotateLid;
        rotateLid = 1.0F - rotateLid * rotateLid * rotateLid;
        chestModel.chestLid.rotateAngleX = -(rotateLid * 3.1415927F / 2.0F);
        chestModel.renderAll();
        GL11.glDisable(32826);
        GL11.glPopMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        if (destroyStage >= 0) {
            GlStateManager.matrixMode(5890);
            GlStateManager.popMatrix();
            GlStateManager.matrixMode(5888);
        }

    }

    public void render(@Nonnull TileStarvingChest te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        this.renderTileEntityChestAt(te, x, y, z, partialTicks, destroyStage);
    }
}
