package net.willsr71.mystcraftutils;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.List;

public class CommandDispatcher {
    private MystcraftUtils plugin;

    public CommandDispatcher(MystcraftUtils plugin){
        this.plugin = plugin;
    }

    public void sendFromConfig(String path, String player, String dimension){
        List<String> commands = plugin.config.getStringList(path);
        for(String command : commands){
            if(command.charAt(0)=='/') command = command.substring(1,command.length());
            command = command.replace("%player%", player);
            command = command.replace("%dimension%", dimension);

            if(plugin.config.getBoolean("debugCommands")) Bukkit.getPlayer(player).sendMessage("Executing \"" + command + "\"");
            CommandSender cs = Bukkit.getConsoleSender();
            if(!plugin.config.getBoolean("executeCommandsAsConsole")) cs = Bukkit.getPlayer(player);
            Bukkit.getServer().dispatchCommand(cs, command);
        }
    }
}
