package net.willsr71.mystcraftutils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.List;

public class PlayerManager {
    private MystcraftUtils plugin;

    public PlayerManager(MystcraftUtils plugin){
        this.plugin = plugin;
    }

    public void sendToWorldSpawn(Player player, String worldName){
        worldName = getValidOptions(player, worldName);
        if(worldName.equals("invalid")) return;

        plugin.chatUtils.sendMessage(player.getName(), "teleportSuccess", player.getName(), worldName);
        player.sendMessage(plugin.chatUtils.replaceDim(plugin.chatUtils.getString("teleportSuccess"), worldName));

        World world = Bukkit.getWorld(worldName);
        Location spawn = world.getSpawnLocation();
        tpPos(player, spawn);

        if(player.hasPermission("mystcraftutils.lightning")) lightningEffect(world, world.getSpawnLocation());
    }

    public void sendPlayerToSpawn(Player player){
        sendToWorldSpawn(player, Bukkit.getWorlds().get(0).getName());
    }

    public void autoSendPlayerToSpawn(Player player){
        if(plugin.commandUtils.isDimensionTeleportBlacklisted(player, player.getWorld().getName())) return;
        sendPlayerToSpawn(player);
    }

    public void sendAllToSpawn(String dimension){
        World world = Bukkit.getWorld(dimension);
        List<Player> players = world.getPlayers();
        for(Player player : players){
            sendPlayerToSpawn(player);
        }
    }

    public void autoKick(Player player){
        String world = player.getWorld().getName();
        String name = player.getName();
        if(!(plugin.commandUtils.hasOwnerPermission(player, world, name) || plugin.commandUtils.hasMemberPermission(player, world, name))){
            sendPlayerToSpawn(player);
        }
    }

    public void tpPos(Player player, Location loc){
        loc.setX(loc.getX() + 0.5D);
        loc.setZ(loc.getZ() + 0.5D);
        player.teleport(loc);
    }

    public String getValidOptions(Player player, String worldName){
        if(isValidDimension(worldName)) return worldName;
        if(worldName.equals("0")) return Bukkit.getWorlds().get(0).getName();
        for(String prefix : plugin.config.getStringList("dimensionPrefixes")){
            if(isValidDimension(prefix + worldName)) return prefix + worldName;
        }
        player.sendMessage(plugin.chatUtils.replaceDim(plugin.chatUtils.getString("invalidWorld"), worldName));
        return "invalid";
    }

    public boolean isValidDimension(String worldName){
        return Bukkit.getWorld(worldName) != null;
    }

    public void lightningEffect(World world, Location loc){
        for(int x = 0; x < plugin.config.getInt("lightningCount"); x++){
            world.strikeLightningEffect(loc);
        }
    }
}
