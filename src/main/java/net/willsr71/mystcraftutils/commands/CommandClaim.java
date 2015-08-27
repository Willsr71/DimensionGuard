package net.willsr71.mystcraftutils.commands;

import net.willsr71.mystcraftutils.DimensionData;
import net.willsr71.mystcraftutils.MystcraftUtils;
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
        claim(player);
    }

    public void claim(Player player){
        String dimension = player.getWorld().getName();
        if(plugin.commandUtils.isDimensionClaimBlacklisted(player, dimension)) return;
        if(plugin.commandUtils.isDimensionClaimed(player, dimension)) return;
        if(plugin.commandUtils.isAnyOwner(player)){
            plugin.chatUtils.sendMessage(player.getName(), "dimensionMaxExceeded", player.getName(), dimension);
            return;
        }

        List<String> owners = new ArrayList<>();
        List<String> members = new ArrayList<>();
        owners.add(player.getName());

        plugin.dimensions.put(dimension, new DimensionData(dimension, owners, members));
        plugin.save();

        plugin.commandDispatcher.sendFromConfig("claim.commands", player.getName(), dimension);

        player.sendMessage(plugin.chatUtils.replaceDim(plugin.chatUtils.getString("claim.messages.success"), dimension));
    }
}
