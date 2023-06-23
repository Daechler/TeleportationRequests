package net.daechler.teleportationrequests;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TpAcceptCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }

        Player player = (Player) sender;
        TeleportationManager teleportationManager = TeleportationRequests.getInstance().getTeleportationManager();
        TeleportationRequest request = teleportationManager.getRequest(player);

        if (request == null) {
            player.sendMessage(ChatColor.RED + "You have no teleport requests!");
            return true;
        }

        Player teleportingPlayer = request.isToSender() ? request.getTarget() : request.getSender();
        Player otherPlayer = request.isToSender() ? request.getSender() : request.getTarget();
        Location originalLocation = teleportingPlayer.getLocation();

        new BukkitRunnable() {
            int countdown = 10;

            @Override
            public void run() {
                Location currentLocation = teleportingPlayer.getLocation();
                if (originalLocation.getX() != currentLocation.getX() || originalLocation.getY() != currentLocation.getY() || originalLocation.getZ() != currentLocation.getZ()) {
                    teleportingPlayer.sendMessage(ChatColor.RED + "Teleportation cancelled because you moved.");
                    otherPlayer.sendMessage(ChatColor.RED + "Teleportation cancelled because " + teleportingPlayer.getName() + " moved.");
                    cancel();
                } else if (countdown > 0) {
                    teleportingPlayer.sendTitle(ChatColor.GREEN + "Teleportation in " + countdown + "...", "", 20, 40, 20);
                    countdown--;
                } else {
                    if (request.isToSender()) {
                        request.getTarget().teleport(request.getSender());
                    } else {
                        request.getSender().teleport(request.getTarget());
                    }

                    player.sendMessage(ChatColor.GREEN + "Teleport request accepted.");
                    request.getSender().sendMessage(ChatColor.GREEN + player.getName() + " has accepted your teleport request.");
                    teleportationManager.removeRequest(player);
                    cancel();
                }
            }
        }.runTaskTimer(TeleportationRequests.getInstance(), 0, 20); // 20 ticks = 1 second

        return true;
    }
}
