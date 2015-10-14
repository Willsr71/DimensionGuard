package net.willsr71.mystcraftutils.commands;

import net.willsr71.mystcraftutils.MystcraftUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSpawn {
    private MystcraftUtils plugin;

    public CommandSpawn(MystcraftUtils plugin){
        this.plugin = plugin;
    }

    public void run(CommandSender cs, String[] args){
        if(plugin.miscUtils.isConsoleSender(cs)) return;
        Player player = (Player) cs;

        plugin.playerManager.sendPlayerToWorldSpawn(player, "0");
    }
}
