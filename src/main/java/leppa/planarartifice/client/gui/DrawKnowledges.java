package leppa.planarartifice.client.gui;

import leppa.planarartifice.main.PAConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.capabilities.IPlayerKnowledge;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategory;
import thaumcraft.client.gui.GuiResearchPage;
import thaumcraft.client.lib.events.HudHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DrawKnowledges {
    private static final ResourceLocation tex1 = new ResourceLocation("thaumcraft", "textures/gui/gui_researchbook.png");
    private static final ResourceLocation tex2 = new ResourceLocation("planarartifice", "textures/gui/gui_researchbook_div.png");
    public static void drawKnowledges(GuiResearchPage page, int x, int y, int mx, int my, IPlayerKnowledge playerKnowledge) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        x -= 10; y -= 80;
        int cats = ResearchCategories.researchCategories.size();
        int cols = Math.min(cats, PAConfig.client.knowledgeIconsPerRow);
        int crows = Math.max(3, (int)Math.ceil((float)cats / PAConfig.client.knowledgeIconsPerRow)) + 1;
        int rows = IPlayerKnowledge.EnumKnowledgeType.values().length * crows - 1;
        int i = 0; int j;
        for (ResearchCategory cat : ResearchCategories.researchCategories.values()) {
            int dx = (int)lerp(x, x + 164.0F, (float)(i % cols) / cols);
            int dy = (int)lerp(y, y + 200.0F, (float)(i / cols) / rows);
            j = 0;
            for (IPlayerKnowledge.EnumKnowledgeType type : IPlayerKnowledge.EnumKnowledgeType.values()) {
                drawKnowledge(cat, type, playerKnowledge, page, x, y, mx, my, dx, dy + (int)lerp(0, 200.0F, (float)j * crows / rows));
                j++;
            }
            i++;
        }
        for (int k = 1; k < IPlayerKnowledge.EnumKnowledgeType.values().length; k++)
            drawDivLine(page, x, y, mx, my, (int)lerp(y, y + 200.0F, (float)(k * crows - 1) / rows), rows);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getMinecraft().renderEngine.bindTexture(tex1);
        page.drawTexturedModalRect(x + 4, y - i * 20 + 12, 24, 184, 96, 8);
    }

    private static void drawPopupAt(GuiResearchPage page, int i, int i1, int mx, int my, String s) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method popup = page.getClass().getDeclaredMethod("drawPopupAt", int.class, int.class, int.class, int.class, String.class);
        popup.setAccessible(true);
        popup.invoke(page, i, i1, mx, my, s);
    }

    private static void drawKnowledge(ResearchCategory cat, IPlayerKnowledge.EnumKnowledgeType type, IPlayerKnowledge playerKnowledge, GuiResearchPage page, int x, int y, int mx, int my, int dx, int dy) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        if (!type.hasFields() && cat != null) return;
        int amt = playerKnowledge.getKnowledge(type, cat);
        int par = playerKnowledge.getKnowledgeRaw(type, cat) % type.getProgression();
        if (amt > 0 || par > 0) {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glPushMatrix();
            Minecraft.getMinecraft().renderEngine.bindTexture(HudHandler.KNOW_TYPE[type.ordinal()]);
            GL11.glTranslatef(dx, dy, 0.0F);
            GL11.glScaled(0.0625D, 0.0625D, 0.0625D);
            page.drawTexturedModalRect(0, 0, 0, 0, 255, 255);
            if (type.hasFields() && cat != null) {
                GlStateManager.color(1.0F, 1.0F, 1.0F, 0.75F);
                Minecraft.getMinecraft().renderEngine.bindTexture(cat.icon);
                GL11.glTranslatef(0.0F, 0.0F, 1.0F);
                GL11.glScaled(0.66D, 0.66D, 0.66D);
                page.drawTexturedModalRect(66, 66, 0, 0, 255, 255);
            }
            GL11.glPopMatrix();
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glTranslatef(0.0F, 0.0F, 5.0F);
            String s = "" + amt;
            int m = Minecraft.getMinecraft().fontRenderer.getStringWidth(s);
            Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(s, dx + 16 - m, dy + 8, 16777215);
            s = I18n.translateToLocal("tc.type." + type.toString().toLowerCase());
            if (type.hasFields() && cat != null)
                s = s + ": " + ResearchCategories.getCategoryName(cat.key);
            drawPopupAt(page, dx, dy, mx, my, s);
            if (par > 0) {
                GlStateManager.color(1.0F, 1.0F, 1.0F, 0.75F);
                Minecraft.getMinecraft().renderEngine.bindTexture(tex1);
                int l = (int)((float)par / (float)type.getProgression() * 16.0F);
                page.drawTexturedModalRect(dx, dy + 17, 0, 232, l, 2);
                page.drawTexturedModalRect(dx + l, dy + 17, l, 234, 16 - l, 2);
            }
            GL11.glTranslatef(0.0F, 0.0F, -5.0F);
        }
    }

    private static void drawDivLine(GuiResearchPage page, int x, int y, int mx, int my, int dy, int rows) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glPushMatrix();
        Minecraft.getMinecraft().renderEngine.bindTexture(tex2);
        GL11.glTranslatef(x + (164.0F / 6), dy + (200.0F / (rows * 3)), 1.0F);
        GL11.glScaled(0.4D, 0.017D, 0.017D);
        page.drawTexturedModalRect(0, 0, 0, 0, 255, 255);
        GL11.glPopMatrix();
    }

    private static float lerp(float a, float b, float t) { return a + ((b - a) * t); }
}
