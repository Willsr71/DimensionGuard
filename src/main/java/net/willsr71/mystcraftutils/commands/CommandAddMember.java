package net.willsr71.mystcraftutils.commands;

import net.willsr71.mystcraftutils.MystcraftUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandAddMember {
    private MystcraftUtils plugin;

    public CommandAddMember(MystcraftUtils plugin){
        this.plugin = plugin;
    }

    public void run(CommandSender cs, String[] args){
        if(plugin.commandUtils.isConsoleSender(cs)) return;
        if(args.length != 1){
            cs.sendMessage("/myst addmember <player>");
            return;
        }

        Player csPlayer = (Player) cs;
        String dimension = csPlayer.getWorld().getName();
        if(plugin.commandUtils.isBlacklistedDimension(cs, dimension)) return;
        if(!plugin.commandUtils.doesDimensionExist(cs, dimension)) return;
        if(!plugin.commandUtils.hasOwnerPermission(cs, dimension, cs.getName())) return;

        Player player = Bukkit.getPlayer(args[0]);
        if(!plugin.commandUtils.isPlayer(cs, args[0])) return;
        if(plugin.commandUtils.isOwner(dimension, args[0])){
            plugin.chatUtils.sendMessage(cs.getName(), "addMember.messages.alreadyOwner", args[0], dimension);
            return;
        }
        if(plugin.commandUtils.isMember(dimension, args[0])){
            plugin.chatUtils.sendMessage(cs.getName(), "addMember.messages.alreadyMember", args[0], dimension);
        }

        cs.sendMessage(plugin.chatUtils.replacePlayer(plugin.chatUtils.replaceDim(plugin.chatUtils.getString("addMember.messages.toSender"), dimension), player.getName()));
        player.sendMessage(plugin.chatUtils.replacePlayer(plugin.chatUtils.replaceDim(plugin.chatUtils.getString("addMember.messages.toReceiver"), dimension), player.getName()));

        plugin.dimensions.get(dimension).addMember(player.getName());
        plugin.save();

        plugin.commandDispatcher.sendFromConfig("addMember.commands", player.getName(), dimension);
    }
}
