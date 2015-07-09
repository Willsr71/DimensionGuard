package net.willsr71.mystcraftutils.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class CommandHelp {

    public static void run(CommandSender cs){
        cs.sendMessage(ChatColor.GREEN + "===================");
        cs.sendMessage(ChatColor.GREEN + "MystcraftUtils Help");
        cs.sendMessage(ChatColor.GREEN + "===================");
        cs.sendMessage(ChatColor.GREEN + "/myst id");
    }
}
