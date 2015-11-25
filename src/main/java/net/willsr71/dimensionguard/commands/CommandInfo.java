package net.willsr71.dimensionguard.commands;

import net.willsr71.dimensionguard.DimensionData;
import net.willsr71.dimensionguard.DimensionGuard;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CommandInfo {
    private DimensionGuard plugin;

    public CommandInfo(DimensionGuard plugin) {
        this.plugin = plugin;
    }

    public void run(CommandSender cs, String[] args) {
        if (plugin.miscUtils.isConsoleSender(cs)) return;

        Player player = (Player) cs;
        String dimension = player.getWorld().getName();
        if (plugin.miscUtils.isDimensionClaimBlacklisted(cs, dimension)) return;
        if (!plugin.miscUtils.doesDimensionExist(cs, dimension)) return;

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
        if (owners.equals("&7Owners:")) owners = owners + " &6None";
        else owners = owners.substring(0, owners.length() - 3);
        if (members.equals("&7Members:")) members = members + " &6None";
        else members = members.substring(0, members.length() - 3);

        cs.sendMessage(plugin.miscUtils.parse("&3Dimension info for dimension &f" + dimData.getName() + "&3:"));
        cs.sendMessage(plugin.miscUtils.parse(owners));
        cs.sendMessage(plugin.miscUtils.parse(members));
    }

}
