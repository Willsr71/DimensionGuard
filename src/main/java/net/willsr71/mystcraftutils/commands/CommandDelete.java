package net.willsr71.mystcraftutils.commands;

import net.willsr71.mystcraftutils.MystcraftUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandDelete {
    private MystcraftUtils plugin;

    public CommandDelete(MystcraftUtils plugin){
        this.plugin = plugin;
    }

    public void run(CommandSender cs, String[] args){
        if(plugin.commandUtils.isConsoleSender(cs)) return;

        Player player = (Player) cs;
        String dimension = player.getWorld().getName();
        if(plugin.commandUtils.isBlacklistedDimension(cs, dimension)) return;

        if(!plugin.commandUtils.doesDimensionExist(cs, dimension, "notRegisteredMessage")) return;

        MystcraftUtils.instance.dimensions.remove(dimension);
        MystcraftUtils.instance.save();

        plugin.commandDispatcher.sendFromConfig("delete.commands", player.getName(), dimension);

        player.sendMessage(plugin.chatUtils.replaceDim(plugin.chatUtils.getString("delete.messages.success"), dimension));
    }
}
