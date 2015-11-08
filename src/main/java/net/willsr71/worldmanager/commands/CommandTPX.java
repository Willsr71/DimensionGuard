package net.willsr71.worldmanager.commands;

import net.willsr71.worldmanager.WorldManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTPX {
    private WorldManager plugin;

    public CommandTPX(WorldManager plugin) {
        this.plugin = plugin;
    }

    public void run(CommandSender cs, String[] args) {
        if (plugin.miscUtils.isConsoleSender(cs)) return;
        if (!plugin.miscUtils.hasPermission(cs, "worldmanager.tpx")) return;
        if (args.length != 1) {
            cs.sendMessage("/tpx <dimension>");
            return;
        }
        Player player = (Player) cs;

        plugin.playerManager.sendPlayerToWorldSpawn(player, args[0]);
    }
}
