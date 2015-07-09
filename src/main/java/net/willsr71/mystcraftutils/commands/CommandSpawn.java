package net.willsr71.mystcraftutils.commands;

import net.willsr71.mystcraftutils.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSpawn {

    public static void run(CommandSender cs, String[] args){
        if(cs instanceof Player && args.length == 0){
            PlayerManager.sendToSpawn((Player) cs);
        }else if(args.length == 1 && cs.hasPermission("mysycraftutils.tp.others")){
            PlayerManager.sendToSpawn(Bukkit.getPlayer(args[0]));
        }
    }
}
