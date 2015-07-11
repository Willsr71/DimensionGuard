package net.willsr71.mystcraftutils.commands;

import net.willsr71.mystcraftutils.MystcraftUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandAddMember {
    private MystcraftUtils plugin;

    public CommandAddMember(MystcraftUtils plugin){
        this.plugin = plugin;
    }

    public void run(CommandSender cs, String[] args){
        if(!(cs instanceof Player)){
            cs.sendMessage(plugin.chatUtils.getString("noConsoleMessage"));
            return;
        }
        if(args.length != 1){
            cs.sendMessage("/myst addmember <player>");
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

        Player player = Bukkit.getPlayer(args[0]);
        if(player == null){
            csPlayer.sendMessage(plugin.chatUtils.replacePlayer(plugin.chatUtils.getString("playerNotFound"), args[0]));
            return;
        }
        if(plugin.dimensions.get(dimension).isOwner(player.getName())) {
            csPlayer.sendMessage(plugin.chatUtils.replacePlayer(plugin.chatUtils.getString("addMember.messages.alreadyOwner"), player.getName()));
            return;
        }
        if(plugin.dimensions.get(dimension).isMember(player.getName())) {
            csPlayer.sendMessage(plugin.chatUtils.replacePlayer(plugin.chatUtils.getString("addMember.messages.alreadyMember"), player.getName()));
            return;
        }

        plugin.dimensions.get(dimension).addMember(player.getName());
    }
}
