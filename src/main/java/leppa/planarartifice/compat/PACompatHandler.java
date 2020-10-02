package leppa.planarartifice.compat;

import java.util.ArrayList;
import java.util.HashMap;

import leppa.planarartifice.compat.jei.JEIHandler;
import leppa.planarartifice.compat.tconstruct.TConstructHandler;
import leppa.planarartifice.compat.thaumicadditions.ThaumicAdditionsHandler;
import leppa.planarartifice.main.PAConfig;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class PACompatHandler {

	public static final HashMap<String, Class<? extends ICompatModule>> CLASSES = new HashMap<>();
	public static final ArrayList<ICompatModule> MODULES = new ArrayList<ICompatModule>();

	public static void setup() {
		if (!PAConfig.compat.disableTinkersCompat) CLASSES.put("tconstruct", TConstructHandler.class);
		if (!PAConfig.compat.disableTACompat) CLASSES.put("thaumadditions", ThaumicAdditionsHandler.class);
		// jei

		CLASSES.forEach((modid, clazz) -> {
			if(Loader.isModLoaded(modid))
				try {
					MODULES.add(clazz.newInstance());
				} catch(Exception e) {
					e.printStackTrace();
				}
		});
	}

	public static void preInit(FMLPreInitializationEvent e) {
		MODULES.forEach(p -> p.preInit(e));
	}

	public static void init(FMLInitializationEvent e) {
		MODULES.forEach(p -> p.init(e));
	}

	public static void postInit(FMLPostInitializationEvent e) {
		MODULES.forEach(p -> p.postInit(e));
	}

	public interface ICompatModule {

		void preInit(FMLPreInitializationEvent e);

		void init(FMLInitializationEvent e);

		void postInit(FMLPostInitializationEvent e);
		
		default ItemStack getModItem(String name, int amount, int meta) {
			return GameRegistry.makeItemStack(name, meta, amount, null);
		}

	}

}
