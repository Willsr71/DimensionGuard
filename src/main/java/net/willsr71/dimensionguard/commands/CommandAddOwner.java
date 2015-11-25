package net.willsr71.dimensionguard.commands;

import net.willsr71.dimensionguard.DimensionGuard;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandAddOwner {
    private DimensionGuard plugin;

    public CommandAddOwner(DimensionGuard plugin) {
        this.plugin = plugin;
    }

    public void run(CommandSender cs, String[] args) {
        if (plugin.miscUtils.isConsoleSender(cs)) return;
        if (args.length < 1) {
            cs.sendMessage("/dg addowner <player>");
            return;
        }

        Player csPlayer = (Player) cs;
        String dimension = csPlayer.getWorld().getName();
        if (plugin.miscUtils.isDimensionClaimBlacklisted(cs, dimension)) return;
        if (!plugin.miscUtils.doesDimensionExist(cs, dimension)) return;
        if (!plugin.miscUtils.hasOwnerPermission(cs, dimension, cs.getName())) return;

        Player player = Bukkit.getPlayer(args[0]);
        if (!plugin.miscUtils.isPlayer(cs, args[0])) return;
        if (plugin.miscUtils.isAnyOwner(player)) {
            cs.sendMessage(plugin.miscUtils.getString("addOwner.messages.alreadyOwnerOtherDim").replace("%player%", args[0]).replace("%dimension%", dimension));
            return;
        }
        if (plugin.miscUtils.isOwner(dimension, args[0])) {
            cs.sendMessage(plugin.miscUtils.getString("addOwner.messages.alreadyOwner").replace("%player%", args[0]).replace("%dimension%", dimension));
            return;
        }
        if (plugin.miscUtils.isMember(dimension, args[0])) {
            cs.sendMessage(plugin.miscUtils.getString("addOwner.messages.alreadyMember").replace("%player%", args[0]).replace("%dimension%", dimension));
            plugin.dimensions.get(dimension).removeMember(player.getName());
        }

        boolean success = true;
        String uid = player.getWorld().getUID().toString().substring(0, plugin.config.getInt("addOwner.uidchars"));
        if (!(args.length == 4)) success = false;
        else if (!args[1].equals("confirm")) success = false;
        else if (!args[2].equals(dimension)) success = false;
        else if (!args[3].equals(uid)) success = false;
        if (!success) {
            cs.sendMessage(plugin.miscUtils.getString("addOwner.messages.confirm").replace("%uid%", uid));
            return;
        }

        cs.sendMessage(plugin.miscUtils.getString("addOwner.messages.toSender").replace("%dimension%", dimension).replace("%player%", player.getName()));
        player.sendMessage(plugin.miscUtils.getString("addOwner.messages.toReceiver").replace("%dimension%", dimension).replace("%player%", player.getName()));

        plugin.dimensions.get(dimension).addOwner(player.getName());
        plugin.save();

        plugin.miscUtils.sendCommandsFromConfig("addOwner.commands", player.getName(), dimension);
    }
}
