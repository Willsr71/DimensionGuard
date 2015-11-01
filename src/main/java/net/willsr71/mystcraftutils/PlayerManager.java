package net.willsr71.mystcraftutils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.List;

public class PlayerManager {
    private MystcraftUtils plugin;

    public PlayerManager(MystcraftUtils plugin) {
        this.plugin = plugin;
    }

    public void sendPlayerToWorldSpawn(Player player, String worldName) {
        worldName = getValidOptions(worldName);
        if (worldName.equals("invalid")) {
            player.sendMessage(plugin.miscUtils.getString("invalidWorld").replace("%dimension%", worldName));
            return;
        }

        Location loc = Bukkit.getWorld(worldName).getSpawnLocation();
        loc.setX(loc.getX() + 0.5D);
        loc.setZ(loc.getZ() + 0.5D);
        player.teleport(loc);
    }

    public void autoSendPlayerToSpawn(Player player) {
        if (plugin.miscUtils.isDimensionTeleportBlacklisted(player, player.getWorld().getName())) return;
        sendPlayerToWorldSpawn(player, "0");
    }

    public void sendAllToSpawn(String dimension) {
        World world = Bukkit.getWorld(dimension);
        List<Player> players = world.getPlayers();
        for (Player player : players) {
            sendPlayerToWorldSpawn(player, "0");
        }
    }

    public void autoKick(Player player) {
        String world = player.getWorld().getName();
        String name = player.getName();
        if (plugin.miscUtils.isDimensionTeleportBlacklisted(player, world)) return;
        if (plugin.miscUtils.hasOwnerPermission(player, world, name)) return;
        if (plugin.miscUtils.hasMemberPermission(player, world, name)) return;
        sendPlayerToWorldSpawn(player, "0");
    }

    public String getValidOptions(String worldName) {
        if (Bukkit.getWorld(worldName) != null) return worldName;
        if (worldName.equals("0")) return Bukkit.getWorlds().get(0).getName();
        for (String prefix : plugin.config.getStringList("dimensionPrefixes")) {
            if (Bukkit.getWorld(prefix + worldName) != null) return prefix + worldName;
        }
        return "invalid";
    }
}
