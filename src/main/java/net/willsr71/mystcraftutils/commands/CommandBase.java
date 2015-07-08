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
        String argsc = "";
        for(String arg : args){
            argsc = (argsc + " " + arg).trim();
        }
        cs.sendMessage(cs.getName() + " issued command " + command + " with arguments \"" + argsc + "\"");

        return false;
    }
}
