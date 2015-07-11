package net.willsr71.mystcraftutils;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandUtils {
    private MystcraftUtils plugin;

    public CommandUtils(MystcraftUtils plugin){
        this.plugin = plugin;
    }

    public void sendMessage(String target, String configEntry, String player, String dimension){
        String message = plugin.chatUtils.getString(configEntry);
        message = plugin.chatUtils.replacePlayer(message, player);
        message = plugin.chatUtils.replaceDim(message, dimension);
        Bukkit.getPlayer(target).sendMessage(message);
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

    public boolean doesDimensionExist(CommandSender cs, String dimension){
        if(plugin.dimensions.containsKey(dimension)){
            return true;
        }
        cs.sendMessage(plugin.chatUtils.replaceDim(plugin.chatUtils.getString("notRegisteredMessage"), dimension));
        return false;
    }

    public boolean isDimensionClaimed(CommandSender cs, String dimension){
        if(plugin.dimensions.containsKey(dimension)){
            String owner = plugin.dimensions.get(dimension).getOwners().get(0);
            cs.sendMessage(plugin.chatUtils.replacePlayer(plugin.chatUtils.replaceDim(plugin.chatUtils.getString("claim.messages.alreadyClaimed"), dimension), owner));
            return true;
        }
        return false;
    }

    public boolean isAnyOwner(Player player){
        for(String dim : plugin.dimensions.keySet()){
            if(plugin.dimensions.get(dim).isOwner(player.getName())) return true;
        }
        return false;
    }

    public boolean isOwner(String dimension, String player){
        if(plugin.dimensions.get(dimension) == null) return false;
        if(plugin.dimensions.get(dimension).isOwner(player)){
            return true;
        }
        return false;
    }

    public boolean hasOwnerPermission(CommandSender cs, String dimension, String player){
        if(hasPermission(cs, "mystcraftutils.overrideowner")) return true;
        boolean owner = isOwner(dimension, player);
        if(!owner) sendMessage(cs.getName(), "noDimPermission", player, dimension);
        return owner;
    }

    public boolean isMember(String dimension, String player){
        if(plugin.dimensions.get(dimension) == null) return false;
        if(plugin.dimensions.get(dimension).isMember(player)){
            return true;
        }
        return false;
    }

    public boolean hasMemberPermission(CommandSender cs, String dimension, String player){
        if(hasPermission(cs, "mystcraftutils.overridemember")) return true;
        boolean member = isMember(dimension, player);
        if(!member) sendMessage(cs.getName(), "noDimPermission", player, dimension);
        return member;
    }
}
