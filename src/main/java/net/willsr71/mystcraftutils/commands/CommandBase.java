package net.willsr71.mystcraftutils.commands;

import net.willsr71.mystcraftutils.MystcraftUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandBase extends Command implements CommandExecutor {
    private MystcraftUtils plugin;

    public CommandBase(MystcraftUtils plugin, String name) {
        super(name);
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String command, String[] args) {
        return run(cs, command, args);
    }

    @Override
    public boolean execute(CommandSender cs, String command, String[] args) {
        //return run(cs, command, args);
        return false;
    }

    public boolean run(CommandSender cs, String command, String[] args){
        if(args.length < 1){
            CommandHelp.run(cs);
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

        if(subcommand.equals("claim")) CommandClaim.run(cs, argsToSend);
        else if(subcommand.equals("delete")) CommandDelete.run(cs, argsToSend);
        else if(subcommand.equals("dimlist")) CommandDimList.run(cs, argsToSend);
        else if(subcommand.equals("id")) CommandID.run(cs, argsToSend);
        else if(subcommand.equals("kick")) CommandKick.run(cs, argsToSend);
        else if(subcommand.equals("reload")) CommandReload.run(cs, argsToSend);
        else if(subcommand.equals("spawn")) CommandSpawn.run(cs, argsToSend);
        else CommandHelp.run(cs);
        return true;
    }
}
