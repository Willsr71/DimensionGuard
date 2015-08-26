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
        String modifiedWorldName;
        if(isValidDimension(worldName)) modifiedWorldName = worldName;
        else if(isValidDimension("DIM" + worldName)) modifiedWorldName = "DIM" + worldName;
        else if(isValidDimension("DIM_MYST" + worldName)) modifiedWorldName = "DIM_MYST" + worldName;
        else{
            player.sendMessage(plugin.chatUtils.replaceDim(plugin.chatUtils.getString("invalidWorld"), worldName));
            return;
        }

        player.sendMessage(plugin.chatUtils.replaceDim(plugin.chatUtils.getString("teleportSuccess"), modifiedWorldName));

        World world = Bukkit.getWorld(modifiedWorldName);
        Location spawn = world.getSpawnLocation();
        tpPos(player, spawn);

        if(player.hasPermission("mystcraftutils.lightning")) lightningEffect(world, world.getSpawnLocation());
    }

    public void sendToSpawn(Player player, boolean forced){
        if(!forced && plugin.config.getStringList("blacklistedDimensions").contains(player.getWorld().getName())){
            player.sendMessage(plugin.chatUtils.getString("blacklistMessage"));
            return;
        }
        sendToWorldSpawn(player, Bukkit.getWorlds().get(0).getName());
    }

    public void sendToSpawn(Player player){
        sendToSpawn(player, false);
    }

    public void sendAllToSpawn(String dimension){
        World world = Bukkit.getWorld(dimension);
        List<Player> players = world.getPlayers();
        for(Player player : players){
            sendToSpawn(player);
        }
    }

    public void autoKick(Player player){
        String world = player.getWorld().getName();
        String name = player.getName();
        if(!(plugin.commandUtils.hasOwnerPermission(player, world, name) || plugin.commandUtils.hasMemberPermission(player, world, name))){
            sendToSpawn(player);
        }
    }

    public void tpPos(Player player, Location loc){
        loc.setX(loc.getX() + 0.5D);
        loc.setZ(loc.getZ() + 0.5D);
        player.teleport(loc);
    }

    public boolean isValidDimension(String worldName){
        return Bukkit.getWorld(worldName) != null;
    }

    public void lightningEffect(World world, Location loc){
        for(int x = 0; x < 50; x++){
            world.strikeLightningEffect(loc);
        }
    }
}
