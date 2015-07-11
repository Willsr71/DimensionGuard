package net.willsr71.mystcraftutils.commands;

import net.willsr71.mystcraftutils.DimensionData;
import net.willsr71.mystcraftutils.MystcraftUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandClaim {
    private MystcraftUtils plugin;

    public CommandClaim(MystcraftUtils plugin){
        this.plugin = plugin;
    }

    public void run(CommandSender cs, String[] args){
        if(plugin.commandUtils.isConsoleSender(cs)) return;

        Player player = (Player) cs;
        String dimension = player.getWorld().getName();
        if(plugin.commandUtils.isBlacklistedDimension(cs, dimension)) return;
        if(plugin.commandUtils.doesDimensionExist(cs, dimension, "claim.messages.alreadyClaimed")) return;

        List<String> owners = new ArrayList<>();
        List<String> members = new ArrayList<>();
        owners.add(player.getName());

        MystcraftUtils.instance.dimensions.put(dimension, new DimensionData(dimension, owners, members));
        MystcraftUtils.instance.save();

        plugin.commandDispatcher.sendFromConfig("claim.commands", player.getName(), dimension);

        cs.sendMessage(plugin.chatUtils.replaceDim(plugin.chatUtils.getString("claim.messages.success"), dimension));
    }
}
