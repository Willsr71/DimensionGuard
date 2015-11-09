package net.willsr71.dimensionguard.commands;

import net.willsr71.dimensionguard.DimensionGuard;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandDelete {
    private DimensionGuard plugin;

    public CommandDelete(DimensionGuard plugin) {
        this.plugin = plugin;
    }

    public void run(CommandSender cs, String[] args) {
        if (plugin.miscUtils.isConsoleSender(cs)) return;

        Player player = (Player) cs;
        String dimension = player.getWorld().getName();
        if (plugin.miscUtils.isDimensionClaimBlacklisted(cs, dimension)) return;
        if (!plugin.miscUtils.doesDimensionExist(cs, dimension)) return;
        if (!plugin.miscUtils.hasOwnerPermission(cs, dimension, cs.getName())) return;

        boolean success = true;
        String uid = "";
        if (plugin.config.getInt("delete.uidchars") != 0)
            uid = player.getWorld().getUID().toString().substring(0, plugin.config.getInt("delete.uidchars"));

        if (!args[0].equals("confirm")) success = false;
        else if (!args[1].equals(dimension)) success = false;
        else if (!args[2].equals(uid)) success = false;
        if (!success) {
            cs.sendMessage(plugin.miscUtils.getString("delete.messages.confirm").replace("%uid%", uid));
            return;
        }

        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage(plugin.miscUtils.getString("delete.messages.success").replace("%dimension%", dimension));
        }

        plugin.miscUtils.sendCommandsFromConfig("delete.commands", player.getName(), dimension);
        plugin.playerManager.sendAllToSpawn(dimension);
        plugin.dimensions.remove(dimension);
        plugin.fileManager.deleteWorld(dimension);
        plugin.save();
    }
}
