package net.daechler.teleportationrequests;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpaHereCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage(ChatColor.RED + "Usage: /tpahere <player>");
            return true;
        }

        Player player = (Player) sender;
        Player target = Bukkit.getPlayer(args[0]);

        if (target == null || !target.isOnline()) {
            player.sendMessage(ChatColor.RED + "That player is not online!");
            return true;
        }

        TeleportationManager teleportationManager = TeleportationRequests.getInstance().getTeleportationManager();

        if (teleportationManager.isTeleportationToggled(target)) {
            player.sendMessage(ChatColor.RED + "That player is not accepting teleport requests!");
            return true;
        }

        teleportationManager.addRequest(player, target, true);

        player.sendMessage(ChatColor.GREEN + "Teleport request sent to " + target.getName() + ".");
        target.sendMessage(ChatColor.GREEN + player.getName() + " wants you to teleport to them. Type /tpaccept to accept, or /tpdeny to deny.");

        return true;
    }
}
