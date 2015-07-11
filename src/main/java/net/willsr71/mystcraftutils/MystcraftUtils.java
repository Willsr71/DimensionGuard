package net.willsr71.mystcraftutils;

import net.willsr71.mystcraftutils.commands.CommandBase;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class MystcraftUtils extends JavaPlugin {
    public static MystcraftUtils instance;
    public CommandBase commandBase;
    public ConfigManager configManager;
    public ConfigManager dimensionConfigManager;
    public Configuration config;
    public Configuration dimensionConfig;
    public ChatUtils chatUtils;
    public PlayerManager playerManager;
    public CommandDispatcher commandDispatcher;

    public static String version = "1.0";
    public HashMap<String, DimensionData> dimensions = new HashMap<>();

    public void onEnable(){
        instance = this;
        configManager = new ConfigManager(this, "config.yml");
        dimensionConfigManager = new ConfigManager(this, "dimensions.yml");
        commandBase = new CommandBase(this, "myst");
        chatUtils = new ChatUtils(this);
        playerManager = new PlayerManager(this);
        commandDispatcher = new CommandDispatcher(this);
        reload();

        this.getCommand("myst").setExecutor(commandBase);

        Bukkit.getPluginManager().registerEvents(new EventListener(this), this);
    }

    public void onDisable(){
        // Just in case it didn't save for some reason
        save();

        getLogger().info("Disabled MystcraftUtils v" + version);
    }

    public void save(){
        Set<String> dims = dimensions.keySet();
        for (String dim : dims) {
            DimensionData dimData = dimensions.get(dim);

            dimensionConfig.set(dim, null);
            dimensionConfig.set(dim + ".owners", dimData.getOwners());
            dimensionConfig.set(dim + ".members", dimData.getMembers());
        }
        dimensionConfigManager.saveConfig();
    }

    public void reload(){
        configManager.reloadConfig();
        dimensionConfigManager.reloadConfig();
        config = configManager.getConfig();
        dimensionConfig = dimensionConfigManager.getConfig();
        String configVersion = config.getString("dontTouch.version.seriouslyThisWillEraseYourConfig");
        boolean manualOverwrite = config.getBoolean("dontTouch.manualConfigReset");
        if(configVersion == null || !configVersion.equals(version) || manualOverwrite){
            configManager.replaceConfig();
            config = configManager.getConfig();
        }

        Set<String> dims = dimensionConfig.getKeys(false);
        getLogger().info("Loading " + dims.size() + " dimensions...");
        for(String dim : dims){
            List<String> owners = dimensionConfig.getStringList(dim + ".owners");
            List<String> members = dimensionConfig.getStringList(dim + ".members");
            DimensionData dimData = new DimensionData(dim, owners, members);
            dimensions.put(dim, dimData);
        }
        getLogger().info("Done");
    }
}
