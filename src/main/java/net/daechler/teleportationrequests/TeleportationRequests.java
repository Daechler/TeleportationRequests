package net.daechler.teleportationrequests;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;

public class TeleportationRequests extends JavaPlugin {

    private static TeleportationRequests instance;
    private TeleportationManager teleportationManager;

    // Get the plugin instance
    public static TeleportationRequests getInstance() {
        return instance;
    }

    // Get the teleportation manager instance
    public TeleportationManager getTeleportationManager() {
        return teleportationManager;
    }

    @Override
    public void onEnable() {
        instance = this;
        teleportationManager = new TeleportationManager();

        // Register commands
        this.getCommand("tpa").setExecutor(new TpaCommand());
        this.getCommand("tpahere").setExecutor(new TpaHereCommand());
        this.getCommand("tpaccept").setExecutor(new TpAcceptCommand());
        this.getCommand("tpatoggle").setExecutor(new TpaToggleCommand());
        this.getCommand("tpdeny").setExecutor(new TpDenyCommand());
        this.getCommand("tpcancel").setExecutor(new TpCancelCommand());

        // Send a green message indicating that the plugin is enabled
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + getName() + " has been enabled!");
    }

    @Override
    public void onDisable() {
        // Send a red message indicating that the plugin is disabled
        getServer().getConsoleSender().sendMessage(ChatColor.RED + getName() + " has been disabled!");
    }
}
