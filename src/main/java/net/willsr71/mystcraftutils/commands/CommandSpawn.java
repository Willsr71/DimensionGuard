package net.willsr71.mystcraftutils.commands;

import net.willsr71.mystcraftutils.MystcraftUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandSpawn {

    public static void run(CommandSender cs, String[] args){
        if(cs instanceof Player && args.length == 0){
            sendToSpawn((Player) cs);
        }else if(args.length == 1 && cs.hasPermission("mysycraftutils.tp.others")){
            sendToSpawn(Bukkit.getPlayer(args[0]));
        }
    }

    public static void sendToSpawn(Player player){
        if(MystcraftUtils.instance.debug) player.sendMessage(String.valueOf(MystcraftUtils.instance.config.getStringList("blacklistedDimensions").contains(player.getWorld().getName())));
        if(MystcraftUtils.instance.config.getStringList("blacklistedDimensions").contains(player.getWorld().getName())) return;
        World spawnWorld = Bukkit.getWorld(MystcraftUtils.instance.config.getString("spawnWorld"));
        Location spawn = spawnWorld.getSpawnLocation();
        spawn.setX(spawn.getX() + 0.5D);
        spawn.setZ(spawn.getZ() + 0.5D);
        player.teleport(spawn);

        if(!player.hasPermission("mystcraftutils.lightning")) return;
        Location lightning = spawnWorld.getSpawnLocation();
        int s = 5;
        for(int x = -s; x < s; x++){
            lightning.setX(spawn.getX() + x);
            for(int z = -s; z < s; z++){
                lightning.setZ(spawn.getZ() + z);
                spawnWorld.strikeLightningEffect(lightning);
            }
        }
    }
}
