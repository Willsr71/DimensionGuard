package net.willsr71.mystcraftutils.commands;

import net.willsr71.mystcraftutils.MystcraftUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandAddOwner {
    private MystcraftUtils plugin;

    public CommandAddOwner(MystcraftUtils plugin){
        this.plugin = plugin;
    }

    public void run(CommandSender cs, String[] args){
        if(plugin.commandUtils.isConsoleSender(cs)) return;
        if(args.length != 1){
            cs.sendMessage("/myst addowner <player>");
            return;
        }

        Player csPlayer = (Player) cs;
        String dimension = csPlayer.getWorld().getName();
        if(plugin.commandUtils.isBlacklistedDimension(cs, dimension)) return;
        if(!plugin.commandUtils.doesDimensionExist(cs, dimension)) return;
        if(!plugin.commandUtils.hasOwnerPermission(cs, dimension, cs.getName())) return;

        Player player = Bukkit.getPlayer(args[0]);
        if(!plugin.commandUtils.isPlayer(cs, args[0])) return;
        if(plugin.commandUtils.isOwner(cs, dimension, args[0])){
            plugin.commandUtils.sendMessage(cs.getName(), "addOwner.messages.alreadyOwner", args[0], dimension);
            return;
        }
        if(plugin.commandUtils.isMember(cs, dimension, args[0])){
            plugin.commandUtils.sendMessage(cs.getName(), "addOwner.messages.alreadyMember", args[0], dimension);
            plugin.dimensions.get(dimension).removeMember(player.getName());
        }

        cs.sendMessage(plugin.chatUtils.replacePlayer(plugin.chatUtils.replaceDim(plugin.chatUtils.getString("addOwner.messages.toSender"), dimension), player.getName()));
        player.sendMessage(plugin.chatUtils.replacePlayer(plugin.chatUtils.replaceDim(plugin.chatUtils.getString("addOwner.messages.toReceiver"), dimension), player.getName()));

        plugin.dimensions.get(dimension).addOwner(player.getName());
        plugin.save();
    }
}
