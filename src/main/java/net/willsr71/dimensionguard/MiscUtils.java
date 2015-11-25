package net.willsr71.dimensionguard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class MiscUtils {
    private DimensionGuard plugin;

    public MiscUtils(DimensionGuard plugin) {
        this.plugin = plugin;
    }

    public void sendCommandsFromConfig(String path, String player, String dimension) {
        List<String> commands = plugin.config.getStringList(path);
        for (String command : commands) {
            if (command.charAt(0) == '/') command = command.substring(1, command.length());
            command = command.replace("%player%", player);
            command = command.replace("%dimension%", dimension);

            if (plugin.config.getBoolean("debugCommands"))
                Bukkit.getPlayer(player).sendMessage("Executing \"" + command + "\"");
            CommandSender cs = Bukkit.getConsoleSender();
            if (!plugin.config.getBoolean("executeCommandsAsConsole")) cs = Bukkit.getPlayer(player);
            Bukkit.getServer().dispatchCommand(cs, command);
        }
    }

    public String getString(String string) {
        String out = plugin.config.getString(string);
        if (out == null) out = "&7Error fetching \"&6" + string + "&7\" from config.";
        return parse(out);
    }

    public String parse(String string, boolean prefix) {
        if (prefix) string = "&a[&3DimensionGuard&a]&r " + string;
        string = ChatColor.translateAlternateColorCodes('&', string);
        return string;
    }

    public String parse(String string) {
        return parse(string, true);
    }

    public boolean isConsoleSender(CommandSender cs) {
        if (!(cs instanceof Player)) {
            cs.sendMessage(getString("noConsoleMessage"));
            return true;
        }
        return false;
    }

    public boolean isPlayer(CommandSender cs, String name) {
        Player player = Bukkit.getPlayer(name);
        if (player != null) {
            return true;
        }
        cs.sendMessage(getString("playerNotFound").replace("%player%", name));
        return false;
    }

    public boolean hasPermission(CommandSender cs, String permission) {
        if (cs.hasPermission(permission)) {
            return true;
        }
        cs.sendMessage(getString("noPermission"));
        return false;
    }

    public boolean isDimensionClaimBlacklisted(CommandSender cs, String dimension) {
        if (Bukkit.getWorlds().get(0).getName().equals(dimension) || plugin.config.getStringList("antiClaimDimensions").contains(dimension)) {
            cs.sendMessage(getString("blacklistMessage"));
            return true;
        }
        return false;
    }

    public boolean isDimensionTeleportBlacklisted(CommandSender cs, String dimension) {
        if (Bukkit.getWorlds().get(0).getName().equals(dimension) || plugin.config.getStringList("antiTeleportDimensions").contains(dimension)) {
            if (cs.isOp()) {
                cs.sendMessage(getString("blacklistMessage"));
            }
            return true;
        }
        return false;
    }

    public boolean doesDimensionExist(CommandSender cs, String dimension) {
        if (plugin.dimensions.containsKey(dimension)) {
            return true;
        }
        cs.sendMessage(getString("notRegisteredMessage").replace("%dimension%", dimension));
        return false;
    }

    public boolean isDimensionClaimed(CommandSender cs, String dimension) {
        if (plugin.dimensions.containsKey(dimension)) {
            String owner = plugin.dimensions.get(dimension).getOwners().get(0);
            cs.sendMessage(getString("claim.messages.alreadyClaimed").replace("%dimension%", dimension).replace("%player%", owner));
            return true;
        }
        return false;
    }

    public boolean isAnyOwner(Player player) {
        for (String dim : plugin.dimensions.keySet()) {
            if (plugin.dimensions.get(dim).isOwner(player.getName())) return true;
        }
        return false;
    }

    public boolean isOwner(String dimension, String player) {
        return plugin.dimensions.get(dimension) != null && plugin.dimensions.get(dimension).isOwner(player);
    }

    public boolean hasOwnerPermission(CommandSender cs, String dimension, String player) {
        if (hasPermission(cs, "dimensionguard.overrideowner")) return true;
        boolean owner = isOwner(dimension, player);
        if (!owner)
            cs.sendMessage(getString("noDimPermission").replace("%player%", player).replace("%dimension%", dimension));
        return owner;
    }

    public boolean isMember(String dimension, String player) {
        return plugin.dimensions.get(dimension) != null && plugin.dimensions.get(dimension).isMember(player);
    }

    public boolean hasMemberPermission(CommandSender cs, String dimension, String player) {
        if (hasPermission(cs, "dimensionguard.overridemember")) return true;
        boolean member = isMember(dimension, player);
        if (!member)
            cs.sendMessage(getString("noDimPermission").replace("%player%", player).replace("%dimension%", dimension));
        return member;
    }
}
