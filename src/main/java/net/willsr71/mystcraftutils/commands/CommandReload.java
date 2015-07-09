package net.willsr71.mystcraftutils.commands;

import net.willsr71.mystcraftutils.ChatUtils;
import net.willsr71.mystcraftutils.MystcraftUtils;
import org.bukkit.command.CommandSender;

public class CommandReload {

    public static void run(CommandSender cs){
        MystcraftUtils.instance.reload();
        cs.sendMessage(ChatUtils.parse(MystcraftUtils.instance.config.getString("reloadMessage")));
    }
}
