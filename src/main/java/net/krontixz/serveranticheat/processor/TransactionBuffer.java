package net.krontixz.serveranticheat.processor;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TransactionBuffer {
    private static final Map<UUID, ConcurrentLinkedQueue<Short>> transactions = new HashMap<>();
    private static short transactionId = 0;

    public static short createTransaction(UUID uuid) {
        short id = ++transactionId;
        transactions.computeIfAbsent(uuid, k -> new ConcurrentLinkedQueue<>()).add(id);
        return id;
    }

    public static void confirmTransaction(UUID uuid, short id) {
        ConcurrentLinkedQueue<Short> queue = transactions.get(uuid);
        if (queue != null) {
            queue.removeIf(t -> t == id);
        }
    }

    public static boolean isPending(UUID uuid) {
        ConcurrentLinkedQueue<Short> queue = transactions.get(uuid);
        return queue != null && !queue.isEmpty();
    }
}
