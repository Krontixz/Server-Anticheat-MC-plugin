package net.krontixz.serveranticheat.util;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Box;

public class EnvironmentalContext {
    public static boolean isNearSulfurCube(ServerPlayerEntity player) {
        Box box = player.getBoundingBox().expand(3.0);
        for (Entity entity : player.getServerWorld().getOtherEntities(player, box)) {
            if (entity.getType().toString().contains("sulfur_cube")) {
                return true;
            }
        }
        return false;
    }
}
