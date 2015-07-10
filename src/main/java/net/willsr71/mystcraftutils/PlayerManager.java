package net.willsr71.mystcraftutils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class PlayerManager {

    public static void sendToSpawn(Player player){
        if(MystcraftUtils.instance.config.getStringList("blacklistedDimensions").contains(player.getWorld().getName())){
            player.sendMessage(ChatUtils.getString("blacklistMessage"));
            return;
        }
        String olddimension = player.getWorld().getName();
        World spawnWorld = Bukkit.getWorld(MystcraftUtils.instance.config.getString("spawnWorld"));
        Location spawn = spawnWorld.getSpawnLocation();
        tpPos(player, spawn);

        player.sendMessage(ChatUtils.replaceDim(ChatUtils.getString("spawnMessage"), olddimension));

        if(player.hasPermission("mystcraftutils.lightning")) lightningEffect(spawnWorld, spawnWorld.getSpawnLocation());
    }

    public static void tpPos(Player player, Location loc){
        loc.setX(loc.getX() + 0.5D);
        loc.setZ(loc.getZ() + 0.5D);
        player.teleport(loc);
    }

    public static void lightningEffect(World world, Location loc){
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
