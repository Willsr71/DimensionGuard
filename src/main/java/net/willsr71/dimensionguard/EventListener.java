package net.willsr71.dimensionguard;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.*;

public class EventListener implements Listener {
    DimensionGuard plugin;

    public EventListener(DimensionGuard plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (plugin.config.getBoolean("autoSpawnLogout")) plugin.playerManager.autoSendPlayerToSpawn(event.getPlayer());
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerKick(PlayerKickEvent event) {
        if (plugin.config.getBoolean("autoSpawnLogout")) plugin.playerManager.autoSendPlayerToSpawn(event.getPlayer());
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (plugin.config.getBoolean("autoSpawnLogin")) plugin.playerManager.autoSendPlayerToSpawn(event.getPlayer());
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerChangedWorld(PlayerChangedWorldEvent event) {
        plugin.commandBase.commandClaim.claim(event.getPlayer());
        plugin.playerManager.autoKick(event.getPlayer());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (!event.getClickedBlock().getType().name().equals("MYSTCRAFT_BLOCKBOOKBINDER")) return;
        if (!plugin.miscUtils.isAnyOwner(event.getPlayer())) return;
        event.setCancelled(true);
        event.getPlayer().sendMessage("alreadyOwnDimension");
    }
}
