package net.willsr71.dimensionguard.commands;

import net.willsr71.dimensionguard.DimensionGuard;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSpawn {
    private DimensionGuard plugin;

    public CommandSpawn(DimensionGuard plugin) {
        this.plugin = plugin;
    }

    public void run(CommandSender cs, String[] args) {
        if (plugin.miscUtils.isConsoleSender(cs)) return;
        Player player = (Player) cs;

        plugin.playerManager.sendPlayerToWorldSpawn(player, "0");
    }
}
