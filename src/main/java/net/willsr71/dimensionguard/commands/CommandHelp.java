package net.willsr71.dimensionguard.commands;

import net.willsr71.dimensionguard.DimensionGuard;
import org.bukkit.command.CommandSender;

public class CommandHelp {
    private DimensionGuard plugin;

    public CommandHelp(DimensionGuard plugin) {
        this.plugin = plugin;
    }

    public void run(CommandSender cs) {
        cs.sendMessage(plugin.miscUtils.parse(" &3▬▬▬▬▬▬ &a[&3DimensionGuard Commands&a] &3▬▬▬▬▬▬ "));
        cs.sendMessage(plugin.miscUtils.parse("&f/dg claim &a- &3Claim the dimension you are in.", false));
        cs.sendMessage(plugin.miscUtils.parse("&f/dg delete &a- &3Delete the dimension you are in.", false));
        cs.sendMessage(plugin.miscUtils.parse("&f/dg info &a- &3Get info about the dimension you are in.", false));
        cs.sendMessage(plugin.miscUtils.parse("&f/dg kick <player> &a- &3Kick a player from a dimension.", false));
        cs.sendMessage(plugin.miscUtils.parse("&f/dg list &a- &3List all the dimensions registered.", false));
        cs.sendMessage(plugin.miscUtils.parse("&f/dg listown &a- &3List the dimensions that you are part of.", false));
        cs.sendMessage(plugin.miscUtils.parse("&f/dg spawn &a- &3Teleport to world spawn.", false));
        cs.sendMessage(plugin.miscUtils.parse("&f/dg tpx <dimension> &a- &3Teleport to spawn of world specified.", false));
        cs.sendMessage(plugin.miscUtils.parse("&f/dg addowner <player> &a- &3Add an owner to the dimension you are in.", false));
        cs.sendMessage(plugin.miscUtils.parse("&f/dg addmember <player> &a- &3Add a member to the dimension you are in.", false));
        cs.sendMessage(plugin.miscUtils.parse("&f/dg removeowner <player> &a- &3Remove an owner from the dimension you are in.", false));
        cs.sendMessage(plugin.miscUtils.parse("&f/dg removemember <player> &a- &3Remove a member from the dimension you are in.", false));
        cs.sendMessage(plugin.miscUtils.parse("&f/dg reload &a- &3Reload the plugin configs. (including dimension data)", false));
    }
}
