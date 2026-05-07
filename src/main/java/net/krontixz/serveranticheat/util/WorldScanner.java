package net.krontixz.serveranticheat.util;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WorldScanner {
    public static boolean canSeeSky(World world, BlockPos pos) {
        return world.isSkyVisible(pos);
    }

    public static boolean isLiquid(BlockState state) {
        return state.getFluidState().isEmpty();
    }
}
