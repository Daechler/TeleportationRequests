package net.daechler.teleportationrequests;

import org.bukkit.entity.Player;

public class TeleportationRequest {

    private final Player sender;
    private final Player target;
    private final boolean toSender;

    public TeleportationRequest(Player sender, Player target, boolean toSender) {
        this.sender = sender;
        this.target = target;
        this.toSender = toSender;
    }

    public Player getSender() {
        return sender;
    }

    public Player getTarget() {
        return target;
    }

    public boolean isToSender() {
        return toSender;
    }
}