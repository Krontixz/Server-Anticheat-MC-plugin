package net.krontixz.serveranticheat.detections.combat;

import net.krontixz.serveranticheat.manager.ViolationManager;
import java.util.*;

public class AutoClicker {
    private final Map<UUID, List<Long>> clickHistory = new HashMap<>();

    public void check(UUID uuid) {
        long now = System.currentTimeMillis();
        List<Long> clicks = clickHistory.computeIfAbsent(uuid, k -> new ArrayList<>());
        
        clicks.add(now);
        if (clicks.size() > 20) clicks.remove(0);

        if (clicks.size() == 20) {
            double entropy = calculateEntropy(clicks);
            if (entropy < 0.05) {
                ViolationManager.handleViolation(uuid, "AutoClicker (Consistency)", 5.0);
            }
        }
    }

    private double calculateEntropy(List<Long> clicks) {
        List<Long> deltas = new ArrayList<>();
        for (int i = 1; i < clicks.size(); i++) {
            deltas.add(clicks.get(i) - clicks.get(i - 1));
        }
        
        double avg = deltas.stream().mapToLong(Long::longValue).average().orElse(0);
        double variance = deltas.stream().mapToDouble(d -> Math.pow(d - avg, 2)).average().orElse(0);
        return Math.sqrt(variance) / avg;
    }
}
