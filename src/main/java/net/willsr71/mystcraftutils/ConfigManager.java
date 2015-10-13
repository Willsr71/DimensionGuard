package net.willsr71.mystcraftutils;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ConfigManager {
    private MystcraftUtils plugin;

    private YamlConfiguration yamlConfiguration;
    private String configName;
    private File file;
    private File dataFolder;

    public ConfigManager(MystcraftUtils plugin, String configName){
        this.plugin = plugin;
        this.configName = configName;
        dataFolder = plugin.getDataFolder();
        file = new File(dataFolder, configName);

        reloadConfig();
    }

    public Configuration getConfig(){
        return yamlConfiguration;
    }

    public void reloadConfig(){
        if(!dataFolder.exists()) dataFolder.mkdirs();
        if(!file.exists()) createConfig();
        yamlConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    public void saveConfig(){
        try {
            yamlConfiguration.save(file);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void replaceConfig(){
        deleteConfig();
        createConfig();
    }

    private void createConfig(){
        try {
            Files.copy(plugin.getResource(configName), file.toPath());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void deleteConfig(){
        plugin.getLogger().info("Deleted configuration: " + file.delete());
    }
}
