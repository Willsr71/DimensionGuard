package net.willsr71.mystcraftutils.commands;

import net.willsr71.mystcraftutils.ChatUtils;
import net.willsr71.mystcraftutils.MystcraftUtils;
import net.willsr71.mystcraftutils.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandKick {

    public static void run(CommandSender cs, String[] args){
        if(!cs.hasPermission("mystcraftutils.kick")){
            cs.sendMessage(ChatUtils.parse(MystcraftUtils.instance.config.getString("noPermission")));
            return;
        }
        if(args.length < 1){
            cs.sendMessage(ChatUtils.parse("/myst kick <player>"));
            return;
        }

        Player player = Bukkit.getPlayer(args[0]);

        String dimension = player.getWorld().getName();
        cs.sendMessage(ChatUtils.parse(MystcraftUtils.instance.config.getString("sendKickMessage"), player, dimension));
        player.sendMessage(ChatUtils.parse(MystcraftUtils.instance.config.getString("recvKickMessage"), dimension));

        PlayerManager.sendToSpawn(player);
    }
}
