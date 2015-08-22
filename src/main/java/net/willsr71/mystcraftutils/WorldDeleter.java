package net.willsr71.mystcraftutils;

import org.bukkit.scheduler.BukkitRunnable;

public class WorldDeleter extends BukkitRunnable{
    private final MystcraftUtils plugin;

    public WorldDeleter(MystcraftUtils plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        // What you want to schedule goes here
        plugin.getServer().broadcastMessage("Welcome to Bukkit! Remember to read the documentation!");
    }
}
