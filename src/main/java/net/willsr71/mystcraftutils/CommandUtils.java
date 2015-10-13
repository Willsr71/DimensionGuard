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
        cs.sendMessage(plugin.chatUtils.getString("playerNotFound").replace("%player%", name));
        return false;
    }

    public boolean hasPermission(CommandSender cs, String permission){
        if(cs.hasPermission(permission)){
            return true;
        }
        cs.sendMessage(plugin.chatUtils.getString("noPermission"));
        return false;
    }

    public boolean isDimensionClaimBlacklisted(CommandSender cs, String dimension){
        if(Bukkit.getWorlds().get(0).getName().equals(dimension) || plugin.config.getStringList("antiClaimDimensions").contains(dimension)){
            cs.sendMessage(plugin.chatUtils.getString("blacklistMessage"));
            return true;
        }
        return false;
    }

    public boolean isDimensionTeleportBlacklisted(CommandSender cs, String dimension){
        if(Bukkit.getWorlds().get(0).getName().equals(dimension) || plugin.config.getStringList("antiTeleportDimensions").contains(dimension)){
            cs.sendMessage(plugin.chatUtils.getString("blacklistMessage"));
            return true;
        }
        return false;
    }

    public boolean doesDimensionExist(CommandSender cs, String dimension){
        if(plugin.dimensions.containsKey(dimension)){
            return true;
        }
        cs.sendMessage(plugin.chatUtils.getString("notRegisteredMessage").replace("%dimension%", dimension));
        return false;
    }

    public boolean isDimensionClaimed(CommandSender cs, String dimension){
        if(plugin.dimensions.containsKey(dimension)){
            String owner = plugin.dimensions.get(dimension).getOwners().get(0);
            cs.sendMessage(plugin.chatUtils.getString("claim.messages.alreadyClaimed").replace("%dimension%", dimension).replace("%player%", owner));
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

    public boolean isOwner(String dimension, String player) {
        return plugin.dimensions.get(dimension) != null && plugin.dimensions.get(dimension).isOwner(player);
    }

    public boolean hasOwnerPermission(CommandSender cs, String dimension, String player){
        if(hasPermission(cs, "mystcraftutils.overrideowner")) return true;
        boolean owner = isOwner(dimension, player);
        if(!owner) cs.sendMessage(plugin.chatUtils.getString("noDimPermission").replace("%player%", player).replace("%dimension%", dimension));
        return owner;
    }

    public boolean isMember(String dimension, String player) {
        return plugin.dimensions.get(dimension) != null && plugin.dimensions.get(dimension).isMember(player);
    }

    public boolean hasMemberPermission(CommandSender cs, String dimension, String player){
        if(hasPermission(cs, "mystcraftutils.overridemember")) return true;
        boolean member = isMember(dimension, player);
        if(!member) cs.sendMessage(plugin.chatUtils.getString("noDimPermission").replace("%player%", player).replace("%dimension%", dimension));
        return member;
    }
}
