package net.daechler.teleportationrequests;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TeleportationManager {

    private final Map<UUID, TeleportationRequest> teleportationRequests = new HashMap<>();
    private final Map<UUID, Boolean> teleportationToggles = new HashMap<>();

    public TeleportationRequest getRequest(Player player) {
        return teleportationRequests.get(player.getUniqueId());
    }

    public void addRequest(Player sender, Player target, boolean toSender) {
        teleportationRequests.put(target.getUniqueId(), new TeleportationRequest(sender, target, toSender));
    }

    public void removeRequest(Player player) {
        teleportationRequests.remove(player.getUniqueId());
    }

    public boolean isTeleportationToggled(Player player) {
        return teleportationToggles.getOrDefault(player.getUniqueId(), false);
    }

    public void toggleTeleportation(Player player) {
        teleportationToggles.put(player.getUniqueId(), !isTeleportationToggled(player));
    }
}
