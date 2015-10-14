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
        if(plugin.miscUtils.isConsoleSender(cs)) return;
        if(!plugin.miscUtils.hasPermission(cs, "mystcraftutils.tpx")) return;
        if(args.length != 1){
            cs.sendMessage("/tpx <dimension>");
            return;
        }
        Player player = (Player) cs;

        plugin.playerManager.sendPlayerToWorldSpawn(player, args[0]);
    }
}
