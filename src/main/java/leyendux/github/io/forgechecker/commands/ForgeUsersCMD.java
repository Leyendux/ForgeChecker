package leyendux.github.io.forgechecker.commands;

import leyendux.github.io.forgechecker.util.MethodUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ForgeUsersCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] arguments) {
        commandSender.sendMessage(MethodUtils.formatForgeUsers(MethodUtils.getPlayerManager().getForgeUsers()));
        return true;
    }
}
