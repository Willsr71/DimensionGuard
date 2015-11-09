package net.willsr71.dimensionguard.commands;

import net.willsr71.dimensionguard.DimensionGuard;
import org.bukkit.command.CommandSender;

public class CommandReload {
    private DimensionGuard plugin;

    public CommandReload(DimensionGuard plugin) {
        this.plugin = plugin;
    }

    public void run(CommandSender cs, String[] args) {
        DimensionGuard.instance.reload();
        cs.sendMessage(plugin.miscUtils.parse("&7Configuration has been reloaded"));
    }
}
