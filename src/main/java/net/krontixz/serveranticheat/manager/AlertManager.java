package net.krontixz.serveranticheat.manager;

import net.krontixz.serveranticheat.AntiCheat;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

public class AlertManager {
    public static void logViolation(UUID uuid, String check, double vl) {
        String message = "[Alert] Player " + uuid + " failed " + check + " (VL: " + String.format("%.2f", vl) + ")";
        AntiCheat.LOGGER.warn(message);
        
        saveToFile(message);
    }

    public static void executeAction(UUID uuid, String check) {
        AntiCheat.LOGGER.error("Action taken against " + uuid + " for " + check);
    }

    private static void saveToFile(String log) {
        try (FileWriter writer = new FileWriter("logs/anticheat/audit.log", true)) {
            writer.write(LocalDateTime.now() + " " + log + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
