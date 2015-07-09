package net.willsr71.mystcraftutils;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.nio.file.Files;

public class ConfigManager {
    private MystcraftUtils plugin;

    private File dataFolder;
    private String configName;
    private File configFile;

    public ConfigManager(MystcraftUtils plugin, String configName){
        this.plugin = plugin;
        this.configName = configName;
        dataFolder = plugin.getDataFolder();
        if(!dataFolder.exists()) dataFolder.mkdirs();
        openConfig();
    }

    public Configuration getConfig(){
        return YamlConfiguration.loadConfiguration(configFile);
    }

    public void replaceConfig(){
        deleteConfig();
        createConfig();
        openConfig();
    }

    private void openConfig(){
        configFile = new File(dataFolder, configName);
        if(!configFile.exists()) createConfig();
    }

    private void createConfig(){
        try {
            Files.copy(plugin.getResource(configName), configFile.toPath());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void deleteConfig(){
        configFile.delete();
    }
}
