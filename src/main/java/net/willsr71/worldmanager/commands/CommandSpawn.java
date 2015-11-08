package net.willsr71.worldmanager.commands;

import net.willsr71.worldmanager.WorldManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSpawn {
    private WorldManager plugin;

    public CommandSpawn(WorldManager plugin) {
        this.plugin = plugin;
    }

    public void run(CommandSender cs, String[] args) {
        if (plugin.miscUtils.isConsoleSender(cs)) return;
        Player player = (Player) cs;

        plugin.playerManager.sendPlayerToWorldSpawn(player, "0");
    }
}
