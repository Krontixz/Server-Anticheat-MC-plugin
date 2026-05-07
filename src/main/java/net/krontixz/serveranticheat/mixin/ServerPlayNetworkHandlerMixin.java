package net.krontixz.serveranticheat.mixin;

import net.krontixz.serveranticheat.manager.ViolationManager;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandlerMixin {
    @Shadow
    public ServerPlayerEntity player;

    @Inject(method = "onPlayerMove", at = @At("HEAD"))
    private void onMove(PlayerMoveC2SPacket packet, CallbackInfo ci) {
        double x = packet.getX(player.getX());
        double y = packet.getY(player.getY());
        double z = packet.getZ(player.getZ());

        double deltaX = x - player.getX();
        double deltaZ = z - player.getZ();
        double distance = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);

        double maxSpeed = 0.7; 
        
        if (player.isFallFlying()) {
            maxSpeed = 2.5;
        }

        if (distance > maxSpeed && !player.isCreative()) {
            ViolationManager.handleViolation(player.getUuid(), "Movement", 1.0);
        }
    }
}
