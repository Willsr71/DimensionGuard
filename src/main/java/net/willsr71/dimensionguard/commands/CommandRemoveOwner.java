package net.willsr71.dimensionguard.commands;

import net.willsr71.dimensionguard.DimensionGuard;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandRemoveOwner {
    private DimensionGuard plugin;

    public CommandRemoveOwner(DimensionGuard plugin) {
        this.plugin = plugin;
    }

    public void run(CommandSender cs, String[] args) {
        if (plugin.miscUtils.isConsoleSender(cs)) return;
        if (args.length != 1) {
            cs.sendMessage("/dg removeowner <player>");
            return;
        }

        Player csPlayer = (Player) cs;
        String dimension = csPlayer.getWorld().getName();
        if (plugin.miscUtils.isDimensionClaimBlacklisted(cs, dimension)) return;
        if (!plugin.miscUtils.doesDimensionExist(cs, dimension)) return;
        if (!plugin.miscUtils.hasOwnerPermission(cs, dimension, cs.getName())) return;
        if (!plugin.miscUtils.isOwner(dimension, args[0])) {
            cs.sendMessage(plugin.miscUtils.getString("removeOwner.messages.notFound").replace("%dimension%", dimension).replace("%player%", args[0]));
            return;
        }
        if (cs.getName().equals(args[0])) {
            cs.sendMessage(plugin.miscUtils.getString("errSelf"));
            return;
        }

        plugin.dimensions.get(dimension).removeOwner(args[0]);
        plugin.save();

        plugin.miscUtils.sendCommandsFromConfig("removeOwner.commands", args[0], dimension);

        cs.sendMessage(plugin.miscUtils.getString("removeOwner.messages.success").replace("%dimension%", dimension).replace("%player%", args[0]));
    }
}
