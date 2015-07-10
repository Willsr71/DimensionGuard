package net.willsr71.mystcraftutils.commands;

import net.willsr71.mystcraftutils.ChatUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandID {

    public static void run(CommandSender cs, String[] args){
        if(!(cs instanceof Player)){
            cs.sendMessage(ChatUtils.getString("noConsoleMessage"));
            return;
        }
        Player player = (Player) cs;
        String dimension = player.getWorld().getName();
        cs.sendMessage(ChatUtils.replaceDim(ChatUtils.getString("idMessage"), dimension));
    }
}