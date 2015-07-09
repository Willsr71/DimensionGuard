package net.willsr71.mystcraftutils;

import net.willsr71.mystcraftutils.commands.CommandBase;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class MystcraftUtils extends JavaPlugin {
    public static MystcraftUtils instance;
    public CommandBase commandBase;
    public ConfigManager configManager;
    public ConfigManager dimensionConfigManager;
    public Configuration config;
    public Configuration dimensionConfig;

    public HashMap<String, DimensionData> dimensions = new HashMap<>();

    public void onEnable(){
        instance = this;
        configManager = new ConfigManager(this, "config.yml");
        dimensionConfigManager = new ConfigManager(this, "dimensions.yml");
        commandBase = new CommandBase(this, "myst");
        reload();

        this.getCommand("myst").setExecutor(commandBase);

        Bukkit.getPluginManager().registerEvents(new EventListener(this), this);
    }

    public void reload(){
        config = configManager.getConfig();
        dimensionConfig = dimensionConfigManager.getConfig();
        String version = config.getString("dontTouch.version.seriouslyThisWillEraseYourConfig");
        if(version == null || !version.equals("1.0")){
            configManager.replaceConfig();
            config = configManager.getConfig();
        }
    }
}
