package net.willsr71.mystcraftutils;

import net.willsr71.mystcraftutils.commands.CommandBase;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class MystcraftUtils extends JavaPlugin {
    public static MystcraftUtils instance;

    public MiscUtils miscUtils;
    public CommandBase commandBase;
    public ConfigManager configManager;
    public ConfigManager dimensionConfigManager;
    public Configuration config;
    public Configuration dimensionConfig;
    public WorldManager worldManager;
    public PlayerManager playerManager;

    public static String version = "1.0";
    public HashMap<String, DimensionData> dimensions = new HashMap<>();

    public void onEnable(){
        instance = this;
        configManager = new ConfigManager(this, "configuration.yml");
        dimensionConfigManager = new ConfigManager(this, "dimensions.yml");
        commandBase = new CommandBase(this, "myst");
        miscUtils = new MiscUtils(this);
        playerManager = new PlayerManager(this);
        worldManager = new WorldManager(this);
        reload();

        this.getCommand("myst").setExecutor(commandBase);

        Bukkit.getPluginManager().registerEvents(new EventListener(this), this);

        try {
            BukkitMetrics metrics = new BukkitMetrics(this);
            metrics.start();
        } catch (IOException e) {
            getLogger().warning("Failed to load BukkitMetrics");
            e.printStackTrace();
        }
    }

    public void onDisable(){
        save();

        getLogger().info("Disabled MystcraftUtils v" + version);
    }

    public void save(){
        Set<String> dims = dimensions.keySet();
        getLogger().info("Saving " + dims.size() + " dimensions...");
        for (String dim : dimensionConfig.getKeys(false)){
            dimensionConfig.set(dim, null);
        }

        for (String dim : dims) {
            DimensionData dimData = dimensions.get(dim);

            dimensionConfig.set(dim, null);
            dimensionConfig.set(dim + ".owners", dimData.getOwners());
            dimensionConfig.set(dim + ".members", dimData.getMembers());
        }
        dimensionConfigManager.saveConfig();
        getLogger().info("Done");
    }

    public void reload(){
        configManager.reloadConfig();
        dimensionConfigManager.reloadConfig();
        config = configManager.getConfig();
        dimensionConfig = dimensionConfigManager.getConfig();
        replaceConfig(0);

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

    private void replaceConfig(int r){
        String configVersion = config.getString("dontTouch.version.seriouslyThisWillEraseYourConfig");

        // Try reloading the config up to 5 times, giving a chance to whatever is holding open the files to let go. If this fails then skip replacing them.
        if(r < 5 && (configVersion == null || !configVersion.equals(version))){
            configManager.replaceConfig();
            config = configManager.getConfig();
            replaceConfig(r + 1);
        }else if(r >= 5){
            getLogger().severe("Error loading MystcraftUtils configs.");
        }
    }
}
