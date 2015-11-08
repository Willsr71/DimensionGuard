package net.willsr71.worldmanager.commands;

import net.willsr71.worldmanager.WorldManager;
import org.bukkit.command.CommandSender;

public class CommandReload {
    private WorldManager plugin;

    public CommandReload(WorldManager plugin) {
        this.plugin = plugin;
    }

    public void run(CommandSender cs, String[] args) {
        WorldManager.instance.reload();
        cs.sendMessage(plugin.miscUtils.parse("&7Configuration has been reloaded"));
    }
}
