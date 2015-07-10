package net.willsr71.mystcraftutils;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.nio.file.Files;

public class ConfigManager {
    private MystcraftUtils plugin;

    private String configName;
    private File file;
    private FileConfiguration fileConfig;

    public ConfigManager(MystcraftUtils plugin, String configName){
        this.plugin = plugin;
        this.configName = configName;
        File dataFolder = plugin.getDataFolder();
        if(!dataFolder.exists()) dataFolder.mkdirs();

        file = new File(dataFolder, configName);
        if(!file.exists()) createConfig();

        reloadConfig();
    }

    public Configuration getConfig(){
        return fileConfig;
    }

    public void reloadConfig(){
        fileConfig = YamlConfiguration.loadConfiguration(file);
    }

    public void saveConfig(){
        try {
            fileConfig.save(file);
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
        file.delete();
    }
}
