package leppa.planarartifice.compat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Supplier;

import leppa.planarartifice.compat.tconstruct.TConstructHandler;
import leppa.planarartifice.compat.thaumicadditions.ThaumicAdditionsHandler;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class PACompatHandler {

	public static final HashMap<String, Supplier<ICompatModule>> SUPPLIERS = new HashMap<>();
	public static final ArrayList<ICompatModule> MODULES = new ArrayList<ICompatModule>();

	static {
		SUPPLIERS.put("tconstruct", TConstructHandler::new);
		SUPPLIERS.put("thaumadditions", ThaumicAdditionsHandler::new);

	}

	public static void setup() {
		SUPPLIERS.forEach((modid, sup) -> {
			if(Loader.isModLoaded(modid))
				MODULES.add(sup.get());
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
