package net.willsr71.mystcraftutils;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitScheduler;

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
        Bukkit.unloadWorld(dimension, true);

        scheduleTask(worldFolder, dimension);
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

    private boolean copyFolder(File path, String dimension){
        String backupLocation = plugin.config.getString("backupLocation");
        backupLocation = backupLocation.replace("%server%", Bukkit.getServer().getName());
        File backupFolder = new File(backupLocation + dimension);
        backupFolder.mkdirs();
        try{
            FileUtils.copyDirectory(path, backupFolder);
            return true;
        }catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }

    private void scheduleTask(final File worldFolder, final String dimension) {
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                if(!copyFolder(worldFolder, dimension)){
                    plugin.getLogger().warning("Error copying dimension folder " + worldFolder.getAbsolutePath());
                    return;
                }
                deleteFolder(worldFolder);
                plugin.getLogger().info("Dimension " + dimension + " has been deleted.");
            }
        }, 20L);
    }
}
