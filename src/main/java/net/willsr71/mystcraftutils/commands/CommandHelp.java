package net.willsr71.mystcraftutils.commands;

import net.willsr71.mystcraftutils.MystcraftUtils;
import org.bukkit.command.CommandSender;

public class CommandHelp {

    public static void run(CommandSender cs){
        cs.sendMessage("===================");
        cs.sendMessage("MystcraftUtils Help");
        cs.sendMessage("===================");
        cs.sendMessage("&2/myst id");
    }
}
