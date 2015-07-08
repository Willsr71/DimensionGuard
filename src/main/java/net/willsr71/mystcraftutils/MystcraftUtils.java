package net.willsr71.mystcraftutils;

import net.willsr71.mystcraftutils.commands.CommandBase;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class MystcraftUtils extends JavaPlugin implements Listener {
    public static MystcraftUtils instance;

    public void onEnable(){
        instance = this;

        this.getCommand("myst").setExecutor(new CommandBase("myst"));
    }
}
