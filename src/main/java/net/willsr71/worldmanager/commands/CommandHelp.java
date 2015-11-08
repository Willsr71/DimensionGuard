package net.willsr71.worldmanager.commands;

import net.willsr71.worldmanager.WorldManager;
import org.bukkit.command.CommandSender;

public class CommandHelp {
    private WorldManager plugin;

    public CommandHelp(WorldManager plugin) {
        this.plugin = plugin;
    }

    public void run(CommandSender cs) {
        cs.sendMessage(plugin.miscUtils.parse("&a ______ Help ______ "));
        cs.sendMessage(plugin.miscUtils.parse("&a/myst claim &9- Claim the dimension you are in", false));
        cs.sendMessage(plugin.miscUtils.parse("&a/myst delete &9- Delete the dimension you are in", false));
        cs.sendMessage(plugin.miscUtils.parse("&a/myst info &9- Get info about the dimension you are in", false));
        cs.sendMessage(plugin.miscUtils.parse("&a/myst kick <player> &9- Kick a player from a dimension", false));
        cs.sendMessage(plugin.miscUtils.parse("&a/myst list &9- List all the dimensions registered", false));
        cs.sendMessage(plugin.miscUtils.parse("&a/myst listown &9- List the dimensions that you are part of", false));
        cs.sendMessage(plugin.miscUtils.parse("&a/myst spawn &9- Teleport to world spawn", false));
        cs.sendMessage(plugin.miscUtils.parse("&a/myst tpx <dimension> &9- Teleport to spawn of world specified", false));
        cs.sendMessage(plugin.miscUtils.parse("&a/myst addowner <player> &9- Add an owner to the dimension you are in", false));
        cs.sendMessage(plugin.miscUtils.parse("&a/myst addmember <player> &9- Add a member to the dimension you are in", false));
        cs.sendMessage(plugin.miscUtils.parse("&a/myst removeowner <player> &9- Remove an owner from the dimension you are in", false));
        cs.sendMessage(plugin.miscUtils.parse("&a/myst removemember <player> &9- Remove a member from the dimension you are in", false));
        cs.sendMessage(plugin.miscUtils.parse("&a/myst reload &9- Reload the plugin configs (including dimension data)", false));
    }
}
