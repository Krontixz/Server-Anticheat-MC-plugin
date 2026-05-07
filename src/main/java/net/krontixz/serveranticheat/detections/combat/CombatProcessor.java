package net.krontixz.serveranticheat.detections.combat;

import net.krontixz.serveranticheat.manager.ViolationManager;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CombatProcessor {
    private static final Map<UUID, List<Long>> clickData = new ConcurrentHashMap<>();

    public static void processClick(UUID uuid) {
        long now = System.currentTimeMillis();
        List<Long> times = clickData.computeIfAbsent(uuid, k -> Collections.synchronizedList(new ArrayList<>()));
        
        times.add(now);
        if (times.size() > 50) times.remove(0);

        if (times.size() == 50) {
            analyze(uuid, times);
        }
    }

    private static void analyze(UUID uuid, List<Long> times) {
        double totalDeviance = 0;
        for (int i = 1; i < times.size(); i++) {
            totalDeviance += (times.get(i) - times.get(i - 1));
        }
        
        double average = totalDeviance / (times.size() - 1);
        if (average < 40) {
            ViolationManager.handleViolation(uuid, "HighCPS", 2.0);
        }
    }
}
