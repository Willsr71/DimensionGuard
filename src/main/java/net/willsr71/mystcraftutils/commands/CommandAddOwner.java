package net.willsr71.mystcraftutils.commands;

import net.willsr71.mystcraftutils.MystcraftUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandAddOwner {
    private MystcraftUtils plugin;

    public CommandAddOwner(MystcraftUtils plugin){
        this.plugin = plugin;
    }

    public void run(CommandSender cs, String[] args){
        if(plugin.commandUtils.isConsoleSender(cs)) return;
        if(args.length < 1){
            cs.sendMessage("/myst addowner <player>");
            return;
        }

        Player csPlayer = (Player) cs;
        String dimension = csPlayer.getWorld().getName();
        if(plugin.commandUtils.isDimensionClaimBlacklisted(cs, dimension)) return;
        if(!plugin.commandUtils.doesDimensionExist(cs, dimension)) return;
        if(!plugin.commandUtils.hasOwnerPermission(cs, dimension, cs.getName())) return;

        Player player = Bukkit.getPlayer(args[0]);
        if(!plugin.commandUtils.isPlayer(cs, args[0])) return;
        if(plugin.commandUtils.isAnyOwner(player)){
            cs.sendMessage(plugin.chatUtils.getString("addOwner.messages.alreadyOwnerOtherDim").replace("%player%", args[0]).replace("%dimension%", dimension));
            return;
        }
        if(plugin.commandUtils.isOwner(dimension, args[0])){
            cs.sendMessage(plugin.chatUtils.getString("addOwner.messages.alreadyOwner").replace("%player%", args[0]).replace("%dimension%", dimension));
            return;
        }
        if(plugin.commandUtils.isMember(dimension, args[0])) {
            cs.sendMessage(plugin.chatUtils.getString("addOwner.messages.alreadyMember").replace("%player%", args[0]).replace("%dimension%", dimension));
            plugin.dimensions.get(dimension).removeMember(player.getName());
        }

        boolean success = true;
        String uid = player.getWorld().getUID().toString().substring(0, plugin.config.getInt("addOwner.uidchars"));
        if(!(args.length == 4)) success = false;
        else if(!args[1].equals("confirm")) success = false;
        else if(!args[2].equals(dimension)) success = false;
        else if(!args[3].equals(uid)) success = false;
        if(!success){
            cs.sendMessage(plugin.chatUtils.getString("addOwner.messages.confirm").replace("%uid%", uid));
            return;
        }

        cs.sendMessage(plugin.chatUtils.getString("addOwner.messages.toSender").replace("%dimension%", dimension).replace("%player%", player.getName()));
        player.sendMessage(plugin.chatUtils.getString("addOwner.messages.toReceiver").replace("%dimension%", dimension).replace("%player%", player.getName()));

        plugin.dimensions.get(dimension).addOwner(player.getName());
        plugin.save();

        plugin.commandDispatcher.sendFromConfig("addOwner.commands", player.getName(), dimension);
    }
}
