package net.willsr71.mystcraftutils.commands;

import net.willsr71.mystcraftutils.MystcraftUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandID {

    public static void run(CommandSender cs, String[] args){
        Player player = MystcraftUtils.instance.getServer().getPlayer(cs.getName());
        String dimname = player.getWorld().getWorldFolder().toString();
        dimname = dimname.replace("\\", "");
        dimname = dimname.replace(".","");
        cs.sendMessage("Dimension name: " + dimname);
    }
}
