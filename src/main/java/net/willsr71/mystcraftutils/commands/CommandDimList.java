package net.willsr71.mystcraftutils.commands;

import net.willsr71.mystcraftutils.ChatUtils;
import net.willsr71.mystcraftutils.DimensionData;
import net.willsr71.mystcraftutils.MystcraftUtils;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Set;

public class CommandDimList {

    public static void run(CommandSender cs, String[] args){
        HashMap<String, DimensionData> dimensions = (HashMap) MystcraftUtils.instance.dimensions.clone();
        if(dimensions.size()==0){
            cs.sendMessage(ChatUtils.parse("&7No dimensions registered"));
            return;
        }
        Set<String> dimList = dimensions.keySet();
        for (String dim : dimList) {
            DimensionData dimData = dimensions.get(dim);

            String owners = "&7Owners:";
            if (dimData.getOwners().size() !=0 ) {
                for (String owner : dimData.getOwners()) {
                    owners = (owners + " &6" + owner + "&7,").trim();
                }
            }else{
                owners = owners + " &6None";
            }


            String members = "&7Members:";
            if (dimData.getOwners().size() !=0 ) {
                for (String member : dimData.getMembers()) {
                    members = (members + " &6" + member + "&7,").trim();
                }
            }else{
                members = members + " &6None";
            }

            cs.sendMessage(ChatUtils.parse("&7Dimension name: &6" + dimData.name));
            cs.sendMessage(ChatUtils.parse(owners));
            cs.sendMessage(ChatUtils.parse(members));
        }
    }
}
