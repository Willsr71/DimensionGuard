package net.willsr71.mystcraftutils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatUtils {
    //TODO make this a better system
    public static String parse(String string){
        string = ChatColor.translateAlternateColorCodes('&', string);
        return string;
    }

    public static String parse(String string, Player player){
        string = string.replace("%player%", player.getName());
        string = parse(string);
        return string;
    }

    public static String parse(String string, CommandSender player){
        string = string.replace("%player%", player.getName());
        string = parse(string);
        return string;
    }

    public static String parse(String string, String dimension){
        string = string.replace("%dimension%", dimension);
        string = parse(string);
        return string;
    }

    public static String parse(String string, Player player, String dimension){
        string = parse(string, player);
        string = parse(string, dimension);
        string = parse(string);
        return string;
    }

    public static String parse(String string, CommandSender cs, Player player){
        string = parse(string, cs);
        string = parse(string, player);
        string = parse(string);
        return string;
    }
}
