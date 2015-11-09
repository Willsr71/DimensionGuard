package net.willsr71.dimensionguard.commands;

import net.willsr71.dimensionguard.DimensionGuard;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandKick {
    private DimensionGuard plugin;

    public CommandKick(DimensionGuard plugin) {
        this.plugin = plugin;
    }

    public void run(CommandSender cs, String[] args) {
        if (plugin.miscUtils.isConsoleSender(cs)) return;
        if (args.length < 1) {
            cs.sendMessage(plugin.miscUtils.parse("/myst kick <player>"));
            return;
        }

        Player csPlayer = (Player) cs;
        if (!(plugin.miscUtils.hasOwnerPermission(cs, csPlayer.getWorld().getName(), cs.getName()) || plugin.miscUtils.hasPermission(cs, "dimensionguard.kick")))
            return;

        Player player = Bukkit.getPlayer(args[0]);
        String dimension = player.getWorld().getName();

        plugin.playerManager.sendPlayerToWorldSpawn(player, "0");

        plugin.miscUtils.sendCommandsFromConfig("kick.commands", player.getName(), dimension);

        cs.sendMessage(plugin.miscUtils.getString("kick.messages.toSender").replace("%dimension%", dimension).replace("%player%", player.getName()));
        player.sendMessage(plugin.miscUtils.getString("kick.messages.toReceiver").replace("%dimension%", dimension));
    }
}
