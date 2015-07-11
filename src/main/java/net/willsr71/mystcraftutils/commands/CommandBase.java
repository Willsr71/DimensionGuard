package net.willsr71.mystcraftutils.commands;

import net.willsr71.mystcraftutils.MystcraftUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandBase extends Command implements CommandExecutor {
    private MystcraftUtils plugin;
    public CommandClaim commandClaim;
    public CommandDelete commandDelete;
    public CommandHelp commandHelp;
    public CommandID commandID;
    public CommandKick commandKick;
    public CommandList commandList;
    public CommandReload commandReload;
    public CommandSpawn commandSpawn;

    public CommandBase(MystcraftUtils plugin, String name) {
        super(name);
        this.plugin = plugin;
        commandClaim = new CommandClaim(plugin);
        commandDelete = new CommandDelete(plugin);
        commandHelp = new CommandHelp(plugin);
        commandID = new CommandID(plugin);
        commandKick = new CommandKick(plugin);
        commandList = new CommandList(plugin);
        commandReload = new CommandReload(plugin);
        commandSpawn = new CommandSpawn(plugin);
    }

    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String command, String[] args) {
        return run(cs, command, args);
    }

    @Override
    public boolean execute(CommandSender cs, String command, String[] args) {
        return false;
    }

    public boolean run(CommandSender cs, String command, String[] args){
        if(args.length < 1){
            commandHelp.run(cs);
            return true;
        }
        String subcommand = args[0].toLowerCase();

        String[] argsToSend = new String[args.length-1];
        String argsToString = "";
        for(int x = 0; x < args.length; x++){
            if(x!=0) argsToSend[x-1] = args[x];
            argsToString = (argsToString + " " + args[x]).trim();
        }
        if(!command.equals("myst")) return false;

        if(subcommand.equals("claim")) commandClaim.run(cs, argsToSend);
        else if(subcommand.equals("delete")) commandDelete.run(cs, argsToSend);
        else if(subcommand.equals("id")) commandID.run(cs, argsToSend);
        else if(subcommand.equals("kick")) commandKick.run(cs, argsToSend);
        else if(subcommand.equals("list")) commandList.run(cs, argsToSend);
        else if(subcommand.equals("reload")) commandReload.run(cs, argsToSend);
        else if(subcommand.equals("spawn")) commandSpawn.run(cs, argsToSend);
        else commandHelp.run(cs);
        return true;
    }
}
