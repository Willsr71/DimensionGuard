package net.willsr71.mystcraftutils.commands;

import net.willsr71.mystcraftutils.MystcraftUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSpawn {

    public static void run(CommandSender cs, String[] args){
        if(cs instanceof Player && args.length == 0){
            sendToSpawn((Player) cs);
        }else if(args.length == 1 && cs.hasPermission("mysycraftutils.tp.others")){
            sendToSpawn(Bukkit.getPlayer(args[0]));
        }
    }

    public static void sendToSpawn(Player player){
        if(MystcraftUtils.instance.getConfig().getStringList("blacklistedDimensions").contains(player.getWorld().getName())) return;
        World spawnWorld = Bukkit.getWorld(MystcraftUtils.instance.getConfig().getString("spawnWorld"));
        Location spawn = spawnWorld.getSpawnLocation();
        spawn.setX(spawn.getX() + 0.5D);
        spawn.setZ(spawn.getZ() + 0.5D);
        player.teleport(spawn);

        if(!player.hasPermission("mystcraftutils.lightning")) return;
        Location lightning = spawnWorld.getSpawnLocation();
        for(int x = -10; x < 10; x++){
            lightning.setX(spawn.getX() + x);
            for(int z = -10; z < 10; z++){
                lightning.setZ(spawn.getZ() + z);
                spawnWorld.strikeLightningEffect(lightning);
            }
        }
    }
}
