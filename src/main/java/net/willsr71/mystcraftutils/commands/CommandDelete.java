package net.willsr71.mystcraftutils.commands;

import net.willsr71.mystcraftutils.ChatUtils;
import net.willsr71.mystcraftutils.MystcraftUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandDelete {

    public static void run(CommandSender cs, String[] args){
        if(!(cs instanceof Player)){
            cs.sendMessage(ChatUtils.getString("noConsoleMessage"));
            return;
        }
        Player player = (Player) cs;
        if(MystcraftUtils.instance.config.getStringList("blacklistedDimensions").contains(player.getWorld().getName())){
            player.sendMessage(ChatUtils.getString("blacklistMessage"));
            return;
        }

        String dimension = player.getWorld().getName();

        if(!MystcraftUtils.instance.dimensions.containsKey(dimension)){
            player.sendMessage(ChatUtils.replaceDim(ChatUtils.getString("dimNotRegisteredMessage"), dimension));
            return;
        }

        MystcraftUtils.instance.dimensions.remove(dimension);
        MystcraftUtils.instance.save();

        player.sendMessage(ChatUtils.replaceDim(ChatUtils.getString("deleteMessage"), dimension));
    }
}
