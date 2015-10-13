package net.willsr71.mystcraftutils.commands;

import net.willsr71.mystcraftutils.MystcraftUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandKick {
    private MystcraftUtils plugin;

    public CommandKick(MystcraftUtils plugin){
        this.plugin = plugin;
    }

    public void run(CommandSender cs, String[] args){
        if(plugin.commandUtils.isConsoleSender(cs)) return;
        if(args.length < 1){
            cs.sendMessage(plugin.chatUtils.parse("/myst kick <player>"));
            return;
        }

        Player csPlayer= (Player) cs;
        if(!(plugin.commandUtils.hasOwnerPermission(cs, csPlayer.getWorld().getName(), cs.getName()) || plugin.commandUtils.hasPermission(cs, "mystcraftutils.kick"))) return;

        Player player = Bukkit.getPlayer(args[0]);
        String dimension = player.getWorld().getName();

        plugin.playerManager.sendPlayerToSpawn(player);

        plugin.commandDispatcher.sendFromConfig("kick.commands", player.getName(), dimension);

        cs.sendMessage(plugin.chatUtils.getString("kick.messages.toSender").replace("%dimension%", dimension).replace("%player%", player.getName()));
        player.sendMessage(plugin.chatUtils.getString("kick.messages.toReceiver").replace("%dimension%", dimension));
    }
}
