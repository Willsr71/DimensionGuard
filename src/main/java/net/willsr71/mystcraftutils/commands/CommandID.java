package net.willsr71.mystcraftutils.commands;

import net.willsr71.mystcraftutils.MystcraftUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandID {
    private MystcraftUtils plugin;

    public CommandID(MystcraftUtils plugin){
        this.plugin = plugin;
    }

    public void run(CommandSender cs, String[] args){
        if(!(cs instanceof Player)){
            cs.sendMessage(plugin.chatUtils.getString("noConsoleMessage"));
            return;
        }
        Player player = (Player) cs;
        String dimension = player.getWorld().getName();
        cs.sendMessage(plugin.chatUtils.replaceDim(plugin.chatUtils.getString("idMessage"), dimension));
    }
}