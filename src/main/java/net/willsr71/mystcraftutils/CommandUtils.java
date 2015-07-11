package net.willsr71.mystcraftutils;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandUtils {
    private MystcraftUtils plugin;

    public CommandUtils(MystcraftUtils plugin){
        this.plugin = plugin;
    }

    public boolean isConsoleSender(CommandSender cs){
        if(!(cs instanceof Player)) {
            cs.sendMessage(plugin.chatUtils.getString("noConsoleMessage"));
            return true;
        }
        return false;
    }

    public boolean isPlayer(CommandSender cs, String name){
        Player player = Bukkit.getPlayer(name);
        if(player != null){
            return true;
        }
        cs.sendMessage(plugin.chatUtils.replacePlayer(plugin.chatUtils.getString("playerNotFound"), name));
        return false;
    }

    public boolean hasPermission(CommandSender cs, String permission){
        if(cs.hasPermission(permission)){
            return true;
        }
        cs.sendMessage(plugin.chatUtils.getString("noPermission"));
        return false;
    }

    public boolean isBlacklistedDimension(CommandSender cs, String dimension){
        if(plugin.config.getStringList("blacklistedDimensions").contains(dimension)) {
            cs.sendMessage(plugin.chatUtils.getString("blacklistMessage"));
            return true;
        }
        return false;
    }

    public boolean doesDimensionExist(CommandSender cs, String dimension, String message){
        if(plugin.dimensions.containsKey(dimension)){
            return true;
        }
        cs.sendMessage(plugin.chatUtils.replacePlayer(plugin.chatUtils.replaceDim(plugin.chatUtils.getString(message), dimension), cs.getName()));
        return false;
    }

    public boolean isOwner(CommandSender cs, String dimension, String player, String message){
        if(plugin.dimensions.get(dimension).isOwner(player)){
            return true;
        }
        cs.sendMessage(plugin.chatUtils.replacePlayer(plugin.chatUtils.getString(message), dimension));
        return false;
    }

    public boolean isMember(CommandSender cs, String dimension, String player, String message){
        if(plugin.dimensions.get(dimension).isMember(player)){
            return true;
        }
        cs.sendMessage(plugin.chatUtils.replacePlayer(plugin.chatUtils.getString(message), dimension));
        return false;
    }
}
