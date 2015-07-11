package net.willsr71.mystcraftutils.commands;

import net.willsr71.mystcraftutils.MystcraftUtils;
import org.bukkit.command.CommandSender;

public class CommandReload {
    private MystcraftUtils plugin;

    public CommandReload(MystcraftUtils plugin){
        this.plugin = plugin;
    }

    public void run(CommandSender cs, String[] args){
        MystcraftUtils.instance.reload();
        cs.sendMessage(plugin.chatUtils.parse("&7Configuration has been reloaded"));
    }
}
