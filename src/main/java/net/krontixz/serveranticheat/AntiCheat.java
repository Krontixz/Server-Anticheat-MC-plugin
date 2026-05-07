package net.krontixz.serveranticheat;

import net.fabricmc.api.ModInitializer;
import net.krontixz.serveranticheat.detections.combat.AutoClicker;
import net.minecraft.server.network.ServerPlayerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AntiCheat implements ModInitializer {
    public static final String MOD_ID = "server-anticheat";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static AutoClicker clickScanner;

    @Override
    public void onInitialize() {
        LOGGER.info("Starting ServerAntiCheat for Fabric 26.1.2");
        clickScanner = new AutoClicker();
        
        java.io.File logDir = new java.io.File("logs/anticheat");
        if (!logDir.exists()) logDir.mkdirs();
    }

    public static void kickPlayer(ServerPlayerEntity player, String reason) {
        player.networkHandler.disconnect(net.minecraft.text.Text.literal("§c[AntiCheat]§f " + reason));
    }
}
