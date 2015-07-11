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
        if(!plugin.commandUtils.doesDimensionExist(cs, dimension, "notRegisteredMessage")) return;
        if(!plugin.commandUtils.isOwner(cs, dimension, cs.getName(), "noDimPermission")) return;
        if(!plugin.commandUtils.isMember(cs, dimension, args[0], "removeMember.messages.notFound")) return;

        plugin.dimensions.get(dimension).removeMember(args[0]);
    }
}
