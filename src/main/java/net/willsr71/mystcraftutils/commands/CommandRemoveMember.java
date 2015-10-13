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
        if(plugin.miscUtils.isConsoleSender(cs)) return;
        if(args.length != 1){
            cs.sendMessage("/myst removemember <player>");
            return;
        }

        Player csPlayer = (Player) cs;
        String dimension = csPlayer.getWorld().getName();
        if(plugin.miscUtils.isDimensionClaimBlacklisted(cs, dimension)) return;
        if(!plugin.miscUtils.doesDimensionExist(cs, dimension)) return;
        if(!plugin.miscUtils.hasOwnerPermission(cs, dimension, cs.getName())) return;
        if(!plugin.miscUtils.isMember(dimension, args[0])){
            cs.sendMessage(plugin.miscUtils.getString("removeMember.messages.notFound").replace("%dimension%", dimension).replace("%player%", args[0]));
            return;
        }

        plugin.dimensions.get(dimension).removeMember(args[0]);
        plugin.save();

        plugin.commandDispatcher.sendFromConfig("removeMember.commands", args[0], dimension);

        cs.sendMessage(plugin.miscUtils.getString("removeMember.messages.success").replace("%dimension%", dimension).replace("%player%", args[0]));
    }
}
