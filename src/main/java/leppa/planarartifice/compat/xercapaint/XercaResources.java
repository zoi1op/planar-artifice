package leppa.planarartifice.compat.xercapaint;

import com.google.common.collect.ImmutableSet;
import leppa.planarartifice.main.PlanarArtifice;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.AbstractResourcePack;
import net.minecraft.client.resources.IResourcePack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

// also stole from magicalpsi -p
public class XercaResources extends AbstractResourcePack {
    public static final String[] DEFAULT_RESOURCE_PACKS = new String[] { "aD", "field_110449_ao", "defaultResourcePacks" };
    private static final Set<String> RESOURCE_DOMAINS = ImmutableSet.of("xercapaint", "minecraft");
    private static final String PACK_META = "pack.mcmeta";
    private static final String PROXYPACK_META = "/proxypack.mcmeta";
    private static final HashMap<String, Boolean> found = new HashMap<>();

    public static void init() {
        List<IResourcePack> resourcePacks = ReflectionHelper.getPrivateValue(Minecraft.class, Minecraft.getMinecraft(), DEFAULT_RESOURCE_PACKS);
        IResourcePack resources = new XercaResources();
        resourcePacks.add(resources);
        System.out.println("Hooked Proxy Resource Pack");
    }

    @Override
    protected InputStream getInputStreamByName(String name) throws IOException { return stream(name); }

    @Override
    protected boolean hasResourceName(String name) {
        if (name.startsWith("assets/minecraft") && !name.contains("creative_inventory")) return false;
        if (!found.containsKey(name)) locate(name);
        return found.get(name);
    }

    private void locate(String name) {
        InputStream stream = stream(name);
        found.put(name, stream != null);
    }

    private InputStream stream(String name) {
        if (name.equals(PACK_META)) name = PROXYPACK_META;
        if (!name.startsWith("/")) name = "/" + name;
        name = name.replaceAll("\\/assets\\/xercapaint", "/assets/pa_xercapaint");
        return PlanarArtifice.class.getResourceAsStream(name);
    }

    private XercaResources() { super(Loader.instance().activeModContainer().getSource()); }
    @Override
    public Set<String> getResourceDomains() { return RESOURCE_DOMAINS; }
    @Override
    protected void logNameNotLowercase(String name) {}
    @Override
    public String getPackName() { return "planar-artifice-xercapaint-compat"; }
}
