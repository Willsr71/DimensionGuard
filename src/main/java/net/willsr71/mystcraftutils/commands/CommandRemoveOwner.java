package net.willsr71.mystcraftutils.commands;

import net.willsr71.mystcraftutils.MystcraftUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandRemoveOwner {
    private MystcraftUtils plugin;

    public CommandRemoveOwner(MystcraftUtils plugin){
        this.plugin = plugin;
    }

    public void run(CommandSender cs, String[] args){
        if(plugin.commandUtils.isConsoleSender(cs)) return;
        if(args.length != 1){
            cs.sendMessage("/myst removeowner <player>");
            return;
        }

        Player csPlayer = (Player) cs;
        String dimension = csPlayer.getWorld().getName();
        if(plugin.commandUtils.isBlacklistedDimension(cs, dimension)) return;
        if(!plugin.commandUtils.doesDimensionExist(cs, dimension)) return;
        if(!plugin.commandUtils.hasOwnerPermission(cs, dimension, cs.getName())) return;
        if(!plugin.commandUtils.isOwner(cs, dimension, args[0])){
            plugin.commandUtils.sendMessage(cs.getName(), "removeOwner.messages.notFound", args[0], dimension);
            return;
        }
        if(cs.getName().equals(args[0])){
            cs.sendMessage(plugin.chatUtils.getString("errSelf"));
            return;
        }

        plugin.dimensions.get(dimension).removeOwner(args[0]);
        plugin.save();

        cs.sendMessage(plugin.chatUtils.replacePlayer(plugin.chatUtils.replaceDim(plugin.chatUtils.getString("removeOwner.messages.success"), dimension), args[0]));
    }
}
