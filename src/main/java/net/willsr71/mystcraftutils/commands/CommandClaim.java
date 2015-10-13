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
        if(plugin.miscUtils.isConsoleSender(cs)) return;
        Player player = (Player) cs;
        claim(player);
    }

    public void claim(Player player){
        String dimension = player.getWorld().getName();
        if(plugin.miscUtils.isDimensionClaimBlacklisted(player, dimension)) return;
        if(plugin.miscUtils.isDimensionClaimed(player, dimension)) return;
        if(plugin.miscUtils.isAnyOwner(player)){
            player.sendMessage(plugin.miscUtils.getString("dimensionMaxExceeded").replace("%dimension%", dimension).replace("%player%", player.getName()));
            return;
        }

        List<String> owners = new ArrayList<>();
        List<String> members = new ArrayList<>();
        owners.add(player.getName());

        plugin.dimensions.put(dimension, new DimensionData(dimension, owners, members));
        plugin.save();

        plugin.miscUtils.sendCommandsFromConfig("claim.commands", player.getName(), dimension);

        player.sendMessage(plugin.miscUtils.getString("claim.messages.success").replace("%dimension%", dimension));
    }
}
