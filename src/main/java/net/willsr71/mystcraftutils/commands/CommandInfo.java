package net.willsr71.mystcraftutils.commands;

import net.willsr71.mystcraftutils.DimensionData;
import net.willsr71.mystcraftutils.MystcraftUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CommandInfo {
    private MystcraftUtils plugin;

    public CommandInfo(MystcraftUtils plugin){
        this.plugin = plugin;
    }

    public void run(CommandSender cs, String[] args){
        if(plugin.commandUtils.isConsoleSender(cs)) return;

        Player player = (Player) cs;
        String dimension = player.getWorld().getName();
        if(!plugin.commandUtils.doesDimensionExist(cs, dimension, "notRegisteredMessage")) return;

        HashMap<String, DimensionData> dimensions = (HashMap) plugin.dimensions.clone();

        DimensionData dimData = dimensions.get(dimension);
        String owners = "&7Owners:";
        String members = "&7Members:";
        for (String owner : dimData.getOwners()) {
            owners = (owners + " &6" + owner + "&7,").trim();
        }
        for (String member : dimData.getMembers()) {
            members = (members + " &6" + member + "&7,").trim();
        }
        if(owners.equals("&7Owners:")) owners = owners + " &6None";
        else owners = owners.substring(0, owners.length() - 3);
        if(members.equals("&7Members:")) members = members + " &6None";
        else members = members.substring(0, members.length() - 3);

        cs.sendMessage(plugin.chatUtils.parse("&7Dimension info for dimension &6" + dimData.getName() + "&7:"));
        cs.sendMessage(plugin.chatUtils.parse(owners));
        cs.sendMessage(plugin.chatUtils.parse(members));
    }

}
