package net.willsr71.mystcraftutils;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventListener implements Listener{
    MystcraftUtils plugin;

    public EventListener(MystcraftUtils plugin){
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onQuit(PlayerQuitEvent event){
        if(plugin.config.getBoolean("autoSpawnLogout")) plugin.playerManager.sendToSpawn(event.getPlayer());
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onKick(PlayerKickEvent event){
        if(plugin.config.getBoolean("autoSpawnLogout")) plugin.playerManager.sendToSpawn(event.getPlayer());
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onJoin(PlayerJoinEvent event){
        if(plugin.config.getBoolean("autoSpawnLogin")) plugin.playerManager.sendToSpawn(event.getPlayer());
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onDimensionChange(PlayerChangedWorldEvent event){
        plugin.commandBase.commandClaim.claim(event.getPlayer());
        plugin.playerManager.autoKick(event.getPlayer());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockPlace(BlockPlaceEvent event){
        if(!event.getBlockPlaced().getType().name().equals("MYSTCRAFT_BLOCKBOOKBINDER")) return;
        if(!plugin.commandUtils.isAnyOwner(event.getPlayer())) return;
        event.setCancelled(true);
        plugin.commandUtils.sendMessage(event.getPlayer().getName(), "alreadyOwnDimension", "", "");
    }
}
