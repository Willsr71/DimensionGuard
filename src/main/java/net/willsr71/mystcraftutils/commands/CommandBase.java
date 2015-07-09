package net.willsr71.mystcraftutils.commands;

import net.willsr71.mystcraftutils.MystcraftUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandBase extends Command implements CommandExecutor {
    private MystcraftUtils plugin;

    public CommandBase(String name) {
        super(name);
    }

    @Override
    public boolean execute(CommandSender cs, String command, String[] args) {
        return run(cs, command, args);
    }

    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String command, String[] args) {
        return run(cs, command, args);
    }

    public boolean run(CommandSender cs, String command, String[] args){
        if(args.length < 1){
            CommandHelp.run(cs);
            return true;
        }
        String subcommand = args[0];

        String[] argsToSend = new String[args.length-1];
        String argsToString = "";
        for(int x = 0; x < args.length; x++){
            if(x!=0) argsToSend[x-1] = args[x];
            argsToString = (argsToString + " " + args[x]).trim();
        }
        if(plugin.debug) cs.sendMessage(cs.getName() + " issued command " + command + " with arguments \"" + argsToString + "\"");

        if(!command.equals("myst")) return false;
        if(subcommand.equals("help")) CommandHelp.run(cs);
        if(subcommand.equals("id")) CommandID.run(cs, argsToSend);
        if(subcommand.equals("spawn")) CommandSpawn.run(cs, argsToSend);
        return true;
    }
}
