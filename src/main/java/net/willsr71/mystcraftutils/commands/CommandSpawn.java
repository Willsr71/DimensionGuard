package net.willsr71.mystcraftutils.commands;

import net.willsr71.mystcraftutils.MystcraftUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSpawn {
    private MystcraftUtils plugin;

    public CommandSpawn(MystcraftUtils plugin){
        this.plugin = plugin;
    }

    public void run(CommandSender cs, String[] args){
        if(cs instanceof Player && args.length == 0){
            plugin.playerManager.sendToSpawn((Player) cs);
        }else if(args.length == 1 && cs.hasPermission("mysycraftutils.tp.others")){
            plugin.playerManager.sendToSpawn(Bukkit.getPlayer(args[0]));
        }
    }
}
