package net.willsr71.mystcraftutils;

import net.willsr71.mystcraftutils.commands.CommandBase;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class MystcraftUtils extends JavaPlugin {
    public static MystcraftUtils instance;

    public boolean debug;
    public HashMap<String, DimensionData> dimensions = new HashMap<>();

    public void onEnable(){
        instance = this;

        getConfig().getDefaults();
        debug = getConfig().getBoolean("debug");

        this.getCommand("myst").setExecutor(new CommandBase("myst"));

        Bukkit.getPluginManager().registerEvents(new EventListener(this), this);
    }
}
