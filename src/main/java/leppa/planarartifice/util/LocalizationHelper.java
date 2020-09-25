package leppa.planarartifice.util;

import net.minecraft.client.resources.I18n;

public class LocalizationHelper {
	
	public static String localize(String s, Object... args){
		return I18n.format(s, args);
	}

}
