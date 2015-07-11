package net.willsr71.mystcraftutils.commands;

import net.willsr71.mystcraftutils.MystcraftUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandDelete {
    private MystcraftUtils plugin;

    public CommandDelete(MystcraftUtils plugin){
        this.plugin = plugin;
    }

    public void run(CommandSender cs, String[] args){
        if(plugin.commandUtils.isConsoleSender(cs)) return;

        Player player = (Player) cs;
        String dimension = player.getWorld().getName();
        if(plugin.commandUtils.isBlacklistedDimension(cs, dimension)) return;
        if(!plugin.commandUtils.doesDimensionExist(cs, dimension)) return;
        if(!plugin.commandUtils.hasOwnerPermission(cs, dimension, cs.getName())) return;

        boolean success = true;
        String uid = player.getWorld().getUID().toString().substring(0, plugin.config.getInt("delete.uidchars"));
        if(!(args.length == 3)) success = false;
        else if(!args[0].equals("confirm")) success = false;
        else if(!args[1].equals(dimension)) success = false;
        else if(!args[2].equals(uid)) success = false;
        if(!success){
            cs.sendMessage(plugin.chatUtils.getString("delete.messages.confirm").replace("%uid%", uid));
            return;
        }

        plugin.playerManager.sendAllToSpawn(dimension);
        plugin.dimensions.remove(dimension);
        plugin.save();

        Bukkit.unloadWorld(dimension, true);

        plugin.commandDispatcher.sendFromConfig("delete.commands", player.getName(), dimension);

        player.sendMessage(plugin.chatUtils.replaceDim(plugin.chatUtils.getString("delete.messages.success"), dimension));
    }
}
