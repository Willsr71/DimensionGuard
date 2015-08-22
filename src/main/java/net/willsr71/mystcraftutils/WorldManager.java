package net.willsr71.mystcraftutils;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;

import java.io.File;
import java.io.IOException;

public class WorldManager {
    private MystcraftUtils plugin;

    public WorldManager(MystcraftUtils plugin){
        this.plugin = plugin;
    }

    public void deleteWorld(String dimension){
        World world = Bukkit.getWorld(dimension);
        File worldFolder = world.getWorldFolder();
        boolean success = Bukkit.unloadWorld(dimension, true);
        if(!copyFolder(worldFolder, dimension)){
            plugin.getLogger().warning("Error copying dimension folder " + worldFolder.getAbsolutePath());
            return;
        }
        deleteFolder(worldFolder);
    }

    private boolean deleteFolder(File path) {
        if(path.exists()) {
            File files[] = path.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteFolder(file);
                } else {
                    file.delete();
                }
            }
        }
        return(path.delete());
    }

    private boolean copyFolder(File path, String name){
        File backupFolder = new File("worldBackups/" + name);
        backupFolder.mkdirs();
        try{
            FileUtils.copyDirectory(path, backupFolder);
            return true;
        }catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }
}
