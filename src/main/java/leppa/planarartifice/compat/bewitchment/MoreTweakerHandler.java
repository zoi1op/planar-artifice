package leppa.planarartifice.compat.bewitchment;

import com.bewitchment.api.registry.Ritual;
import moretweaker.bewitchment.MoreRitual;

public class MoreTweakerHandler {
    public static boolean isSimpleRitual(Ritual ritual) {
        if (ritual instanceof MoreRitual) return ritual.sacrificePredicate == null && ritual.getPreconditionMessage().equals("ritual.precondition.empty");
        return false;
    }
}
