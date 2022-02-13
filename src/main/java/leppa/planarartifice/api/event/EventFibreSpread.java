package leppa.planarartifice.api.event;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.Event;

// yes!    yes!
public class EventFibreSpread extends Event {
    public final World world;
    public final BlockPos source;
    public final BlockPos pos;
    public EventFibreSpread(World world, BlockPos source, BlockPos pos) {
        this.world = world;
        this.source = source;
        this.pos = pos;
    }

    public boolean checkDefaultCancel() {
        if (pos.equals(source)) return false;
        IBlockState bs = world.getBlockState(pos);
        float bh = bs.getBlockHardness(world, pos);
        if (bh < 0.0F || bh > 10.0F) { return false; }
        return true;
    }
}
