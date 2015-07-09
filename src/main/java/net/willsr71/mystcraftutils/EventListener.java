package net.willsr71.mystcraftutils;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventListener implements Listener{
    MystcraftUtils plugin;

    public EventListener(MystcraftUtils plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        if(plugin.config.getBoolean("autoSpawnLogout")) PlayerManager.sendToSpawn(event.getPlayer());
    }

    @EventHandler
    public void onKick(PlayerKickEvent event){
        if(plugin.config.getBoolean("autoSpawnLogout")) PlayerManager.sendToSpawn(event.getPlayer());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        if(plugin.config.getBoolean("autoSpawnLogin")) PlayerManager.sendToSpawn(event.getPlayer());
    }
}
