package net.willsr71.dimensionguard.commands;

import net.willsr71.dimensionguard.DimensionGuard;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandForceDelete {
    private DimensionGuard plugin;

    public CommandForceDelete(DimensionGuard plugin) {
        this.plugin = plugin;
    }

    public void run(CommandSender cs, String[] args) {
        if (!plugin.miscUtils.isConsoleSender(cs)) return;

        if (args.length != 1) {
            cs.sendMessage("/dg forcedelete <player>");
            return;
        }

        Player player = Bukkit.getPlayer(args[0]);

        if (player == null) {
            cs.sendMessage("Player is not online");
            return;
        }

        String dimension = player.getWorld().getName();
        if (plugin.miscUtils.isDimensionClaimBlacklisted(cs, dimension)) return;
        if (!plugin.miscUtils.doesDimensionExist(cs, dimension)) return;
        if (!plugin.miscUtils.hasOwnerPermission(cs, dimension, cs.getName())) return;

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
