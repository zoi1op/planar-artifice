package leppa.planarartifice.client;

import leppa.planarartifice.client.render.tile.TESRAlkimiumCentrifuge;
import leppa.planarartifice.client.render.tile.TESRStarvingChest;
import leppa.planarartifice.client.render.tile.TESRTeleporter;
import leppa.planarartifice.items.ItemThaumaturgistCoat;
import leppa.planarartifice.main.CommonProxy;
import leppa.planarartifice.main.PlanarArtifice;
import leppa.planarartifice.network.MessageOpenBook;
import leppa.planarartifice.tiles.TileAlkimiumCentrifuge;
import leppa.planarartifice.tiles.TileStarvingChest;
import leppa.planarartifice.tiles.TileTeleporter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import thaumcraft.Thaumcraft;
import thaumcraft.api.items.RechargeHelper;
import thaumcraft.client.gui.GuiFocalManipulator;
import thaumcraft.client.gui.plugins.GuiSliderTC;
import thaumcraft.common.lib.SoundsTC;
import thaumcraft.common.lib.research.ResearchManager;

@EventBusSubscriber(value = Side.CLIENT)
public class ClientProxy extends CommonProxy{
	
	public static GuiSliderTC sliderRed;
	public static GuiSliderTC sliderGreen;
	public static GuiSliderTC sliderBlue;
	
	static ResourceLocation tex = new ResourceLocation(PlanarArtifice.MODID, "textures/gui/colourizer_picker.png");

	public static final KeyBinding OPEN_THAUMONOMICON = new KeyBinding("key.planarartifice.openThaumonomicon", KeyConflictContext.IN_GAME, KeyModifier.NONE, Keyboard.KEY_P, "Planar Artifice");
	
	@Override
	public void preInit(FMLPreInitializationEvent e){
		super.preInit(e);
	}
	
	@Override
	public void init(FMLInitializationEvent e){
		super.init(e);
		PlanarArtifice.LOGGER.info("[PA, CLIENT] " + OPEN_THAUMONOMICON);
		ClientRegistry.registerKeyBinding(OPEN_THAUMONOMICON);
		ClientRegistry.bindTileEntitySpecialRenderer(TileTeleporter.class, new TESRTeleporter());
		ClientRegistry.bindTileEntitySpecialRenderer(TileAlkimiumCentrifuge.class, new TESRAlkimiumCentrifuge()); // hopium
		ClientRegistry.bindTileEntitySpecialRenderer(TileStarvingChest.class, new TESRStarvingChest());
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent e) {
		super.postInit(e);
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void onKeyInput(InputEvent.KeyInputEvent e) {
		EntityPlayer player = Minecraft.getMinecraft().player;
		World world = player.world;
		if (ItemThaumaturgistCoat.isWearingThis(player) && OPEN_THAUMONOMICON.isKeyDown() && RechargeHelper.getCharge(ItemThaumaturgistCoat.findMe(player)) >= 1) {
			if (!world.isRemote) CommonProxy.network.sendToServer(new MessageOpenBook());
			world.playSound(player.posX, player.posY, player.posZ, SoundsTC.page, SoundCategory.PLAYERS, 1.0F, 1.0F, false);
			player.openGui(Thaumcraft.instance, 12, world, 0, 0, 0);
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void onGuiRender(GuiScreenEvent.DrawScreenEvent.Post event){
		if(PlanarArtifice.isSingleplayer) {
			if(event.getGui() instanceof GuiFocalManipulator) {
				if (!ResearchManager.doesPlayerHaveRequisites(event.getGui().mc.player, "PA_FOCUS_COLOUR")) return;
				GlStateManager.enableBlend();
				GlStateManager.color(255, 255, 255, 255);
				event.getGui().mc.getTextureManager().bindTexture(tex);
				Gui.drawModalRectWithCustomSizedTexture(195, 219, 0, 0, 111, 40, 111, 40);
				
				Minecraft.getMinecraft().fontRenderer.drawString("#", 200, 235, PlanarArtifice.currentColourPicked);
				
				sliderRed.drawButton(Minecraft.getMinecraft(), event.getMouseX(), event.getMouseY(), event.getRenderPartialTicks());
				Minecraft.getMinecraft().fontRenderer.drawString("R", 213, 225, (int)sliderRed.getSliderValue() << 16);
				
				sliderGreen.drawButton(Minecraft.getMinecraft(), event.getMouseX(), event.getMouseY(), event.getRenderPartialTicks());
				Minecraft.getMinecraft().fontRenderer.drawString("G", 213, 235, (int)sliderGreen.getSliderValue() << 8);
				
				sliderBlue.drawButton(Minecraft.getMinecraft(), event.getMouseX(), event.getMouseY(), event.getRenderPartialTicks());
				Minecraft.getMinecraft().fontRenderer.drawString("B", 213, 245, (int)sliderBlue.getSliderValue());
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void onGuiMouseClick(GuiScreenEvent.MouseInputEvent event){
		if(PlanarArtifice.isSingleplayer){
			if(event.getGui() instanceof GuiFocalManipulator){
				final ScaledResolution scaledresolution = new ScaledResolution(event.getGui().mc);
				final int scaledWidth = scaledresolution.getScaledWidth();
				final int scaledHeight = scaledresolution.getScaledHeight();
				int mouseX = Mouse.getX() * scaledWidth / event.getGui().mc.displayWidth;
				int mouseY = scaledHeight - Mouse.getY() * scaledHeight / event.getGui().mc.displayHeight - 1;
				
				if(Mouse.isButtonDown(0)){
					sliderRed.mousePressed(Minecraft.getMinecraft(), mouseX, mouseY);
					sliderGreen.mousePressed(Minecraft.getMinecraft(), mouseX, mouseY);
					sliderBlue.mousePressed(Minecraft.getMinecraft(), mouseX, mouseY);
				}else{
					sliderRed.mouseReleased(mouseX, mouseY);
					sliderGreen.mouseReleased(mouseX, mouseY);
					sliderBlue.mouseReleased(mouseX, mouseY);
				}
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void onGuiInit(GuiScreenEvent.InitGuiEvent event){
		if(PlanarArtifice.isSingleplayer){
			if(event.getGui() instanceof GuiFocalManipulator){
				if(sliderRed == null)
					sliderRed = new GuiSliderTC(998, 221, 225, 80, 6, "Red", 0, 255, 0, false);
				sliderRed.enabled = true;
				
				if(sliderGreen == null)
					sliderGreen = new GuiSliderTC(997, 221, 235, 80, 6, "Green", 0, 255, 0, false);
				sliderGreen.enabled = true;
				
				if(sliderBlue == null)
					sliderBlue = new GuiSliderTC(996, 221, 245, 80, 6, "Blue", 0, 255, 0, false);
				sliderBlue.enabled = true;
			}
		}
	}

	public static EntityPlayer getClientPlayer() {
		return Minecraft.getMinecraft().player;
	}
	
	@Override
	public EntityPlayer getPlayerEntityFromContext(MessageContext ctx) {
		return Minecraft.getMinecraft().player;
	}

}