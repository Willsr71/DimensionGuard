package net.willsr71.mystcraftutils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class PlayerManager {
    private MystcraftUtils plugin;

    public PlayerManager(MystcraftUtils plugin){
        this.plugin = plugin;
    }

    public void sendToSpawn(Player player){
        if(plugin.config.getStringList("blacklistedDimensions").contains(player.getWorld().getName())){
            player.sendMessage(plugin.chatUtils.getString("blacklistMessage"));
            return;
        }
        String olddimension = player.getWorld().getName();
        World spawnWorld = Bukkit.getWorld(plugin.config.getString("spawnWorld"));
        if(spawnWorld == null){
            plugin.getLogger().warning("Invalid spawn world: " + plugin.config.getString("spawnWorld"));
            player.sendMessage(plugin.chatUtils.replaceDim(plugin.chatUtils.getString("invalidSpawnWorld"), plugin.config.getString("spawnWorld")));
            return;
        }
        Location spawn = spawnWorld.getSpawnLocation();
        tpPos(player, spawn);

        player.sendMessage(plugin.chatUtils.replaceDim(plugin.chatUtils.getString("spawnMessage"), olddimension));

        if(player.hasPermission("mystcraftutils.lightning")) lightningEffect(spawnWorld, spawnWorld.getSpawnLocation());
    }

    public void tpPos(Player player, Location loc){
        loc.setX(loc.getX() + 0.5D);
        loc.setZ(loc.getZ() + 0.5D);
        player.teleport(loc);
    }

    public void lightningEffect(World world, Location loc){
        Location toStrike = loc.clone();
        int s = 5;
        for(int x = -s; x < s; x++){
            toStrike.setX(loc.getX() + x);
            for(int z = -s; z < s; z++){
                toStrike.setZ(loc.getZ() + z);
                world.strikeLightningEffect(toStrike);
            }
        }
    }
}
