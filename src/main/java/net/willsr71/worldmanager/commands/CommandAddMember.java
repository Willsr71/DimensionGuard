package net.willsr71.worldmanager.commands;

import net.willsr71.worldmanager.WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandAddMember {
    private WorldManager plugin;

    public CommandAddMember(WorldManager plugin) {
        this.plugin = plugin;
    }

    public void run(CommandSender cs, String[] args) {
        if (plugin.miscUtils.isConsoleSender(cs)) return;
        if (args.length != 1) {
            cs.sendMessage("/myst addmember <player>");
            return;
        }

        Player csPlayer = (Player) cs;
        String dimension = csPlayer.getWorld().getName();
        if (plugin.miscUtils.isDimensionClaimBlacklisted(cs, dimension)) return;
        if (!plugin.miscUtils.doesDimensionExist(cs, dimension)) return;
        if (!plugin.miscUtils.hasOwnerPermission(cs, dimension, cs.getName())) return;

        Player player = Bukkit.getPlayer(args[0]);
        if (!plugin.miscUtils.isPlayer(cs, args[0])) return;
        if (plugin.miscUtils.isOwner(dimension, args[0])) {
            cs.sendMessage(plugin.miscUtils.getString("addMember.messages.alreadyOwner").replace("%dimension%", dimension).replace("%player%", args[0]));
            return;
        }
        if (plugin.miscUtils.isMember(dimension, args[0])) {
            cs.sendMessage(plugin.miscUtils.getString("addMember.messages.alreadyMember").replace("%dimension%", dimension).replace("%player%", args[0]));
        }

        cs.sendMessage(plugin.miscUtils.getString("addMember.messages.toSender").replace("%dimension%", dimension).replace("%player%", player.getName()));
        player.sendMessage(plugin.miscUtils.getString("addMember.messages.toReveiver").replace("%dimension%", dimension).replace("%player%", player.getName()));

        plugin.dimensions.get(dimension).addMember(player.getName());
        plugin.save();

        plugin.miscUtils.sendCommandsFromConfig("addMember.commands", player.getName(), dimension);
    }
}
