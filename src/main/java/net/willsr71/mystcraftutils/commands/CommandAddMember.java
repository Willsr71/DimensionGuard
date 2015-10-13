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
        if(plugin.commandUtils.isDimensionClaimBlacklisted(cs, dimension)) return;
        if(!plugin.commandUtils.doesDimensionExist(cs, dimension)) return;
        if(!plugin.commandUtils.hasOwnerPermission(cs, dimension, cs.getName())) return;

        Player player = Bukkit.getPlayer(args[0]);
        if(!plugin.commandUtils.isPlayer(cs, args[0])) return;
        if(plugin.commandUtils.isOwner(dimension, args[0])){
            cs.sendMessage(plugin.chatUtils.getString("addMember.messages.alreadyOwner").replace("%dimension%", dimension).replace("%player%", args[0]));
            return;
        }
        if(plugin.commandUtils.isMember(dimension, args[0])){
            cs.sendMessage(plugin.chatUtils.getString("addMember.messages.alreadyMember").replace("%dimension%", dimension).replace("%player%", args[0]));
        }

        cs.sendMessage(plugin.chatUtils.getString("addMember.messages.toSender").replace("%dimension%", dimension).replace("%player%", player.getName()));
        player.sendMessage(plugin.chatUtils.getString("addMember.messages.toReveiver").replace("%dimension%", dimension).replace("%player%", player.getName()));

        plugin.dimensions.get(dimension).addMember(player.getName());
        plugin.save();

        plugin.commandDispatcher.sendFromConfig("addMember.commands", player.getName(), dimension);
    }
}
