package leppa.planarartifice.compat.botania;

import leppa.planarartifice.compat.PACompatHandler;
import leppa.planarartifice.util.Aspects;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import tk.zeitheron.botanicadds.init.BlocksBA;

public class BotanicAdditionsHandler implements PACompatHandler.ICompatModule {
    @Override
    public void preInit(FMLPreInitializationEvent e) {
        BotaniaHandler.catalysts.put(BlocksBA.TERRA_CATALYST, new Aspects("alienis", 2));
    }
}
