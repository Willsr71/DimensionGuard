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
        if(!plugin.commandUtils.doesDimensionExist(cs, dimension, "notRegisteredMessage")) return;
        if(!plugin.commandUtils.isOwner(cs, dimension, cs.getName(), "noDimPermissions")) return;

        Player player = Bukkit.getPlayer(args[0]);
        if(!plugin.commandUtils.isPlayer(cs, args[0])) return;
        if(plugin.commandUtils.isOwner(cs, dimension, player.getName(), "addOwner.messages.alreadyOwner")) return;

        if(plugin.commandUtils.isMember(cs, dimension, player.getName(), "addOwner.messages.alreadyMember")) plugin.dimensions.get(dimension).removeMember(player.getName());

        plugin.dimensions.get(dimension).addOwner(player.getName());
    }
}
