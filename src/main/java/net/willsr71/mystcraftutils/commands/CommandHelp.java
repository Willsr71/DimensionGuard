package net.willsr71.mystcraftutils.commands;

import net.willsr71.mystcraftutils.ChatUtils;
import org.bukkit.command.CommandSender;

public class CommandHelp {

    public static void run(CommandSender cs){
        cs.sendMessage(ChatUtils.parse("&aMystcraftUtils Help"));
        cs.sendMessage(ChatUtils.parse("&a/myst claim"));
        cs.sendMessage(ChatUtils.parse("&a/myst delete"));
        cs.sendMessage(ChatUtils.parse("&a/myst dimlist"));
        cs.sendMessage(ChatUtils.parse("&a/myst help"));
        cs.sendMessage(ChatUtils.parse("&a/myst id"));
        cs.sendMessage(ChatUtils.parse("&a/myst kick <player>"));
        cs.sendMessage(ChatUtils.parse("&a/myst reload"));
        cs.sendMessage(ChatUtils.parse("&a/myst spawn"));
    }
}
