package net.willsr71.mystcraftutils.commands;

import net.willsr71.mystcraftutils.DimensionData;
import net.willsr71.mystcraftutils.MystcraftUtils;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Set;

public class CommandListOwn {
    private MystcraftUtils plugin;

    public CommandListOwn(MystcraftUtils plugin){
        this.plugin = plugin;
    }

    public void run(CommandSender cs, String[] args){
        HashMap<String, DimensionData> dimensions = (HashMap) plugin.dimensions.clone();

        String owners = "&7Owner:";
        String members = "&7Member:";
        for (String dim : dimensions.keySet()) {
            DimensionData dimData = dimensions.get(dim);
            if(dimData.isOwner(cs.getName())) owners = (owners + " &6" + dimData.getName() + "&7,").trim();
            if(dimData.isMember(cs.getName())) members = (members + " &6" + dimData.getName() + "&7,").trim();
        }
        if(owners.equals("&7Owner:")) owners = owners + " &6None";
        else owners = owners.substring(0, owners.length()-3);
        if(members.equals("&7Member:")) members = members + " &6None";
        else members = members.substring(0, members.length()-3);

        cs.sendMessage(plugin.chatUtils.parse("&7Dimensions that you are part of:"));
        cs.sendMessage(plugin.chatUtils.parse(owners));
        cs.sendMessage(plugin.chatUtils.parse(members));
    }
}
