package net.krontixz.serveranticheat.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ViolationManager {
    private static final Map<UUID, Double> violations = new HashMap<>();

    public static void handleViolation(UUID uuid, String checkName, double amount) {
        double currentVL = violations.getOrDefault(uuid, 0.0) + amount;
        violations.put(uuid, currentVL);

        AlertManager.logViolation(uuid, checkName, currentVL);

        if (currentVL >= 100.0) {
            AlertManager.executeAction(uuid, checkName);
        }
    }

    public static double getVL(UUID uuid) {
        return violations.getOrDefault(uuid, 0.0);
    }

    public static void reset(UUID uuid) {
        violations.remove(uuid);
    }
}
