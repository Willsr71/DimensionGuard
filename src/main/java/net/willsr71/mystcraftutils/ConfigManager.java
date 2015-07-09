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
        getFile();
    }

    public Configuration getConfig(){
        return YamlConfiguration.loadConfiguration(configFile);
    }

    private void getFile(){
        configFile = new File(dataFolder, configName);
        if(!configFile.exists()){
            try {
                Files.copy(plugin.getResource(configName), configFile.toPath());
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
