package net.willsr71.mystcraftutils.commands;

import net.willsr71.mystcraftutils.MystcraftUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTPX {
    private MystcraftUtils plugin;

    public CommandTPX(MystcraftUtils plugin){
        this.plugin = plugin;
    }

    public void run(CommandSender cs, String[] args){
        if(!(cs instanceof Player)){
            cs.sendMessage(plugin.chatUtils.getString("noConsoleMessage"));
            return;
        }
        if(args.length != 1){
            cs.sendMessage("/tpx <dimension>");
            return;
        }
        Player player = (Player) cs;

        plugin.playerManager.sendToWorldSpawn(player, args[0]);
    }
}
