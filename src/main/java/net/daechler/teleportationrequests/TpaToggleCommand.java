package net.daechler.teleportationrequests;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpaToggleCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }

        Player player = (Player) sender;
        TeleportationManager teleportationManager = TeleportationRequests.getInstance().getTeleportationManager();
        teleportationManager.toggleTeleportation(player);

        if (teleportationManager.isTeleportationToggled(player)) {
            player.sendMessage(ChatColor.GREEN + "Teleportation requests enabled.");
        } else {
            player.sendMessage(ChatColor.RED + "Teleportation requests disabled.");
        }

        return true;
    }
}
