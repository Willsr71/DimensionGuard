package net.willsr71.mystcraftutils;

import org.bukkit.ChatColor;

public class ChatUtils {
    private MystcraftUtils plugin;

    public ChatUtils(MystcraftUtils plugin){
        this.plugin = plugin;
    }

    public String parse(String string) {
        //if(!string.contains("&a[&cMystcraftUtils&a]&r")) string = "&a[&cMystcraftUtils&a]&r " + string;
        string = "&a[&cMystcraft&dUtils&a]&r " + string;
        string = ChatColor.translateAlternateColorCodes('&', string);
        return string;
    }

    public String replacePlayer(String message, String player){
        return message.replace("%player%", player);
    }

    public String replaceDim(String message, String dimension){
        return message.replace("%dimension%", dimension);
    }

    public String getString(String string) {
        //return parse(MystcraftUtils.config.getString(string));
        String out = plugin.config.getString(string);
        if(out == null) out = "&7Error fetching \"&6" + string + "&7\" from config.";
        out = parse(out);
        plugin.getLogger().info("Response: " + out);
        return out;
    }
}
