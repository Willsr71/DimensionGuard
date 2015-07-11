package net.willsr71.mystcraftutils;

import org.bukkit.Bukkit;
import org.bukkit.World;

import java.io.File;

public class WorldManager {
    private MystcraftUtils plugin;

    public WorldManager(MystcraftUtils plugin){
        this.plugin = plugin;
    }

    public void deleteWorld(String dimension){
        World world = Bukkit.getWorld(dimension);
        File worldFolder = world.getWorldFolder();
        Bukkit.unloadWorld(dimension, true);
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
}
