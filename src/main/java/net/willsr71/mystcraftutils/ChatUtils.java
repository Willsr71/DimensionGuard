package net.willsr71.mystcraftutils;

import org.bukkit.ChatColor;

public class ChatUtils {
    private MystcraftUtils plugin;

    public ChatUtils(MystcraftUtils plugin){
        this.plugin = plugin;
    }

    public String parse(String string, boolean prefix) {
        if(prefix) string = "&a[&cMystcraft&9Utils&a]&r " + string;
        string = ChatColor.translateAlternateColorCodes('&', string);
        return string;
    }

    public String parse(String string){
        return parse(string, true);
    }

    public String replacePlayer(String message, String player){
        return message.replace("%player%", player);
    }

    public String replaceDim(String message, String dimension){
        return message.replace("%dimension%", dimension);
    }

    public String getString(String string) {
        String out = plugin.config.getString(string);
        if(out == null) out = "&7Error fetching \"&6" + string + "&7\" from config.";
        return parse(out);
    }
}
