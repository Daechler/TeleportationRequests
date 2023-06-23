package net.daechler.teleportationrequests;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpCancelCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage(ChatColor.RED + "Usage: /tpcancel <player>");
            return true;
        }

        Player player = (Player) sender;
        Player target = Bukkit.getPlayer(args[0]);

        if (target == null || !target.isOnline()) {
            player.sendMessage(ChatColor.RED + "That player is not online!");
            return true;
        }

        TeleportationManager teleportationManager = TeleportationRequests.getInstance().getTeleportationManager();
        TeleportationRequest request = teleportationManager.getRequest(target);

        if (request == null || !request.getSender().equals(player)) {
            player.sendMessage(ChatColor.RED + "You have no outgoing teleport requests to that player!");
            return true;
        }

        player.sendMessage(ChatColor.GREEN + "Teleport request to " + target.getName() + " has been cancelled.");
        target.sendMessage(ChatColor.RED + player.getName() + " has cancelled the teleport request.");
        teleportationManager.removeRequest(target);

        return true;
    }
}
