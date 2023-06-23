package net.daechler.teleportationrequests;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpDenyCommand implements CommandExecutor {

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

        player.sendMessage(ChatColor.GREEN + "Teleport request denied.");
        request.getSender().sendMessage(ChatColor.RED + player.getName() + " has denied your teleport request.");
        teleportationManager.removeRequest(player);

        return true;
    }
}
