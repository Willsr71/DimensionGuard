package net.willsr71.mystcraftutils;

import org.bukkit.ChatColor;

public class ChatUtils {
    public static final char COLOR_CHAR = '\u00A7';
    public static final String ALL_CODES = "0123456789AaBbCcDdEeFfKkLlMmNnOoRr";

    public static String parse(String string) {
        string = translateAlternateColorCodes('&', string);
        return string;
    }

    public static String replacePlayer(String message, String player){
        return message.replace("%player%", player);
    }

    public static String replaceDim(String message, String dimension){
        return message.replace("%dimension%", dimension);
    }

    public static String getString(String string){
        return parse(MystcraftUtils.instance.config.getString(string));
    }

    public static String translateAlternateColorCodes(char altColorChar, String textToTranslate) {
        if(textToTranslate == null || textToTranslate.length() == 0) return textToTranslate;
        char[] b = new char[textToTranslate.length()];
        for(int x = 0; x < textToTranslate.length(); x++){
            b[x] = textToTranslate.charAt(x);
        }
        //char[] b = textToTranslate.toCharArray();
        for (int i = 0; i < b.length - 1; i++) {
            if (b[i] == altColorChar && ALL_CODES.indexOf(b[i+1]) > -1) {
                b[i] = COLOR_CHAR;
                b[i+1] = Character.toLowerCase(b[i+1]);
            }
        }
        return new String(b);
    }
}
