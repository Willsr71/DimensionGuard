package net.willsr71.mystcraftutils.commands;

import net.willsr71.mystcraftutils.MystcraftUtils;
import org.bukkit.command.CommandSender;

public class CommandHelp {
    private MystcraftUtils plugin;

    public CommandHelp(MystcraftUtils plugin){
        this.plugin = plugin;
    }

    public void run(CommandSender cs){
        cs.sendMessage(plugin.chatUtils.parse("&a ______ Help ______ "));
        cs.sendMessage(plugin.chatUtils.parse("&a/myst claim &9- Claim the dimension you are in", false));
        cs.sendMessage(plugin.chatUtils.parse("&a/myst delete &9- Delete the dimension you are in", false));
        cs.sendMessage(plugin.chatUtils.parse("&a/myst info &9- Get info about the dimension you are in", false));
        cs.sendMessage(plugin.chatUtils.parse("&a/myst kick <player> &9- Kick a player from a dimension", false));
        cs.sendMessage(plugin.chatUtils.parse("&a/myst list &9- List all the dimensions registered", false));
        cs.sendMessage(plugin.chatUtils.parse("&a/myst listown &9- List the dimensions that you are part of", false));
        cs.sendMessage(plugin.chatUtils.parse("&a/myst spawn &9- Teleport to world spawn", false));
        cs.sendMessage(plugin.chatUtils.parse("&a/myst addowner &9- Add an owner to the dimension you are in", false));
        cs.sendMessage(plugin.chatUtils.parse("&a/myst addmember &9- Add a member to the dimension you are in", false));
        cs.sendMessage(plugin.chatUtils.parse("&a/myst removeowner &9- Remove an owner from the dimension you are in", false));
        cs.sendMessage(plugin.chatUtils.parse("&a/myst removemember &9- Remove a member from the dimension you are in", false));
        cs.sendMessage(plugin.chatUtils.parse("&a/myst reload &9- Reload the plugin configs (including dimension data)", false));
    }
}
