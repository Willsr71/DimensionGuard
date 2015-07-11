package net.willsr71.mystcraftutils.commands;

import net.willsr71.mystcraftutils.DimensionData;
import net.willsr71.mystcraftutils.MystcraftUtils;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Set;

public class CommandList {
    private MystcraftUtils plugin;

    public CommandList(MystcraftUtils plugin){
        this.plugin = plugin;
    }

    public void run(CommandSender cs, String[] args){
        HashMap<String, DimensionData> dimensions = (HashMap) plugin.dimensions.clone();
        if(dimensions.size()==0){
            cs.sendMessage(plugin.chatUtils.parse("&7No dimensions registered"));
            return;
        }
        Set<String> dimList = dimensions.keySet();
        for (String dim : dimList) {
            DimensionData dimData = dimensions.get(dim);

            String owners = "&7Owners:";
            String members = "&7Members:";
            for (String owner : dimData.getOwners()) {
                owners = (owners + " &6" + owner + "&7,").trim();
            }
            for (String member : dimData.getMembers()) {
                members = (members + " &6" + member + "&7,").trim();
            }
            if(owners.equals("&7Owners:")) owners = owners + " &6None";
            else owners = owners.substring(0,owners.length()-3);
            if(members.equals("&7Members:")) members = members + " &6None";
            else members = members.substring(0,members.length()-3);

            cs.sendMessage(plugin.chatUtils.parse("&7Dimension name: &6" + dimData.getName()));
            cs.sendMessage(plugin.chatUtils.parse(owners));
            cs.sendMessage(plugin.chatUtils.parse(members));
        }
    }
}
