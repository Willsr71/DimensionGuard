package net.willsr71.mystcraftutils.commands;

import net.willsr71.mystcraftutils.MystcraftUtils;
import org.bukkit.command.CommandSender;

public class CommandHelp {
    private MystcraftUtils plugin;

    public CommandHelp(MystcraftUtils plugin){
        this.plugin = plugin;
    }

    public void run(CommandSender cs){
        cs.sendMessage(plugin.chatUtils.parse("&aMystcraftUtils Help"));
        cs.sendMessage(plugin.chatUtils.parse("&a/myst claim"));
        cs.sendMessage(plugin.chatUtils.parse("&a/myst delete"));
        cs.sendMessage(plugin.chatUtils.parse("&a/myst help"));
        cs.sendMessage(plugin.chatUtils.parse("&a/myst id"));
        cs.sendMessage(plugin.chatUtils.parse("&a/myst kick <player>"));
        cs.sendMessage(plugin.chatUtils.parse("&a/myst list"));
        cs.sendMessage(plugin.chatUtils.parse("&a/myst reload"));
        cs.sendMessage(plugin.chatUtils.parse("&a/myst spawn"));
    }
}
