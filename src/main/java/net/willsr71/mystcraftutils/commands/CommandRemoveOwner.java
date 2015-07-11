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
        if(!(cs instanceof Player)){
            cs.sendMessage(plugin.chatUtils.getString("noConsoleMessage"));
            return;
        }
        if(args.length != 1){
            cs.sendMessage("/myst removeowner <player>");
            return;
        }

        Player csPlayer = (Player) cs;
        String dimension = csPlayer.getWorld().getName();
        if(plugin.config.getStringList("blacklistedDimensions").contains(dimension)){
            csPlayer.sendMessage(plugin.chatUtils.getString("blacklistMessage"));
            return;
        }
        if(!plugin.dimensions.containsKey(dimension)){
            csPlayer.sendMessage(plugin.chatUtils.replaceDim(plugin.chatUtils.getString("notRegisteredMessage"), dimension));
            return;
        }
        if(!plugin.dimensions.get(dimension).isOwner(csPlayer.getName())){
            csPlayer.sendMessage(plugin.chatUtils.replacePlayer(plugin.chatUtils.getString("noDimPermission"), dimension));
            return;
        }

        if(plugin.dimensions.get(dimension).isOwner(args[0])){
            csPlayer.sendMessage(plugin.chatUtils.replacePlayer(plugin.chatUtils.getString("removeOwner.messages.notFound"), args[0]));
            return;
        }

        plugin.dimensions.get(dimension).removeOwner(args[0]);
    }
}
