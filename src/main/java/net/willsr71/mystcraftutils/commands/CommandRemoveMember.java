package net.willsr71.mystcraftutils.commands;

import net.willsr71.mystcraftutils.MystcraftUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandRemoveMember {
    private MystcraftUtils plugin;

    public CommandRemoveMember(MystcraftUtils plugin){
        this.plugin = plugin;
    }

    public void run(CommandSender cs, String[] args){
        if(plugin.commandUtils.isConsoleSender(cs)) return;
        if(args.length != 1){
            cs.sendMessage("/myst removemember <player>");
            return;
        }

        Player csPlayer = (Player) cs;
        String dimension = csPlayer.getWorld().getName();
        if(plugin.commandUtils.isBlacklistedDimension(cs, dimension)) return;
        if(!plugin.commandUtils.doesDimensionExist(cs, dimension)) return;
        if(!plugin.commandUtils.hasOwnerPermission(cs, dimension, cs.getName())) return;
        if(!plugin.commandUtils.isMember(dimension, args[0])){
            plugin.commandUtils.sendMessage(cs.getName(), "removeMember.messages.notFound", args[0], dimension);
            return;
        }

        plugin.dimensions.get(dimension).removeMember(args[0]);
        plugin.save();

        plugin.commandDispatcher.sendFromConfig("removeMember.commands", args[0], dimension);

        cs.sendMessage(plugin.chatUtils.replacePlayer(plugin.chatUtils.replaceDim(plugin.chatUtils.getString("removeMember.messages.success"), dimension), args[0]));
    }
}
