package leppa.planarartifice.compat.astralsorcery;

import leppa.planarartifice.compat.PACompatHandler;
import leppa.planarartifice.util.Aspects;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.HashMap;

public class AstralSorceryHandler implements PACompatHandler.ICompatModule {
    public static HashMap<String, Aspects> constellation = new HashMap<>();

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        constellation.put("discidia", new Aspects("aversio", 5));
        constellation.put("armara", new Aspects("praemunio", 5));
        constellation.put("vicio", new Aspects("motus", 5));
        constellation.put("aevitas", new Aspects("herba", 5));
        constellation.put("evorsio", new Aspects().add("exitium", 5, "instrumentum"));
        constellation.put("lucerna", new Aspects("lux", 5));
        constellation.put("mineralis", new Aspects().add("visum", 5, "desiderium"));
        constellation.put("horologium", new Aspects("tempus", 5));
        constellation.put("octans", new Aspects().add("fluctus", 5, "aqua"));
        constellation.put("bootes", new Aspects("victus", 5));
        constellation.put("fornax", new Aspects().add("infernum", 5, "ignis"));
        constellation.put("pelotrio", new Aspects("spatio", 5));
        constellation.put("gelu", new Aspects("gelum", 5));
        constellation.put("ulteria", new Aspects("vinculum", 5));
        constellation.put("alcara", new Aspects().add("sonus", 5, "auram"));
        constellation.put("vorux", new Aspects().add("diabolus", 5, "bestia"));


    }

    @Override
    public void registerAspects() {
//        set(OreUtils.nbtString(ItemsAS.constellationPaper, "astralsorcery.constellationName", "astralsorcery.constellation.vorux"), constellationPaper.add("", 5));
    }
}
