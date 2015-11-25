package net.willsr71.dimensionguard.commands;

import net.willsr71.dimensionguard.DimensionData;
import net.willsr71.dimensionguard.DimensionGuard;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Set;

public class CommandList {
    private DimensionGuard plugin;

    public CommandList(DimensionGuard plugin) {
        this.plugin = plugin;
    }

    public void run(CommandSender cs, String[] args) {
        HashMap<String, DimensionData> dimensions = (HashMap) plugin.dimensions.clone();
        if (dimensions.size() == 0) {
            cs.sendMessage(plugin.miscUtils.parse("&3No dimensions registered."));
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
            if (owners.equals("&7Owners:")) owners = owners + " &6None";
            else owners = owners.substring(0, owners.length() - 3);
            if (members.equals("&7Members:")) members = members + " &6None";
            else members = members.substring(0, members.length() - 3);

            cs.sendMessage(plugin.miscUtils.parse("&3Dimension name: &f" + dimData.getName()));
            cs.sendMessage(plugin.miscUtils.parse(owners));
            cs.sendMessage(plugin.miscUtils.parse(members));
        }
    }
}
