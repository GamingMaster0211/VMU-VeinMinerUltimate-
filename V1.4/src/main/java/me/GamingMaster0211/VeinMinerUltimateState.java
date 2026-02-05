package me.GamingMaster0211.VeinMinerUltimate;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class VeinMinerUltimateState {

    private static final Set<UUID> enabledPlayers = new HashSet<>();

    public static boolean isEnabled(UUID uuid) {
        return enabledPlayers.contains(uuid);
    }

    public static boolean toggle(UUID uuid) {
        if (enabledPlayers.remove(uuid)) {
            return false;
        }
        enabledPlayers.add(uuid);
        return true;
    }
}

