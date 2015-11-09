package net.willsr71.dimensionguard.commands;

import net.willsr71.dimensionguard.DimensionGuard;
import org.bukkit.command.CommandSender;

public class CommandHelp {
    private DimensionGuard plugin;

    public CommandHelp(DimensionGuard plugin) {
        this.plugin = plugin;
    }

    public void run(CommandSender cs) {
        cs.sendMessage(plugin.miscUtils.parse("&a ______ Help ______ "));
        cs.sendMessage(plugin.miscUtils.parse("&a/dg claim &9- Claim the dimension you are in", false));
        cs.sendMessage(plugin.miscUtils.parse("&a/dg delete &9- Delete the dimension you are in", false));
        cs.sendMessage(plugin.miscUtils.parse("&a/dg info &9- Get info about the dimension you are in", false));
        cs.sendMessage(plugin.miscUtils.parse("&a/dg kick <player> &9- Kick a player from a dimension", false));
        cs.sendMessage(plugin.miscUtils.parse("&a/dg list &9- List all the dimensions registered", false));
        cs.sendMessage(plugin.miscUtils.parse("&a/dg listown &9- List the dimensions that you are part of", false));
        cs.sendMessage(plugin.miscUtils.parse("&a/dg spawn &9- Teleport to world spawn", false));
        cs.sendMessage(plugin.miscUtils.parse("&a/dg tpx <dimension> &9- Teleport to spawn of world specified", false));
        cs.sendMessage(plugin.miscUtils.parse("&a/dg addowner <player> &9- Add an owner to the dimension you are in", false));
        cs.sendMessage(plugin.miscUtils.parse("&a/dg addmember <player> &9- Add a member to the dimension you are in", false));
        cs.sendMessage(plugin.miscUtils.parse("&a/dg removeowner <player> &9- Remove an owner from the dimension you are in", false));
        cs.sendMessage(plugin.miscUtils.parse("&a/dg removemember <player> &9- Remove a member from the dimension you are in", false));
        cs.sendMessage(plugin.miscUtils.parse("&a/dg reload &9- Reload the plugin configs (including dimension data)", false));
    }
}
