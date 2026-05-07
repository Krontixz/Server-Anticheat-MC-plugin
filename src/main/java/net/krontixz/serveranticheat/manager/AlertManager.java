package net.krontixz.serveranticheat.manager;

import net.krontixz.serveranticheat.AntiCheat;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

public class AlertManager {
    public static void logViolation(UUID uuid, String check, double vl) {
        String log = "[" + LocalDateTime.now() + "] Player " + uuid + " failed " + check + " (VL: " + String.format("%.2f", vl) + ")";
        AntiCheat.LOGGER.warn(log);
        saveToFile(log);
    }

    public static void executeAction(UUID uuid, String check) {
        ServerPlayerEntity player = null; 
        if (player != null) {
            AntiCheat.kickPlayer(player, "Unfair Advantage: " + check);
        }
    }

    private static void saveToFile(String content) {
        try (FileWriter writer = new FileWriter("logs/anticheat/audit.log", true)) {
            writer.write(content + "\n");
        } catch (IOException ignored) {}
    }
}
