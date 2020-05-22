package leyendux.github.io.forgechecker.commands;

import leyendux.github.io.forgechecker.util.MethodUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ForgeUsersCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] arguments) {
        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;
            player.spigot().sendMessage(MethodUtils.formatForgeUsers(MethodUtils.getPlayerManager().getForgeUsers()));
        } else {
            commandSender.sendMessage(MethodUtils.formatForgeUsers(MethodUtils.getPlayerManager().getForgeUsersRaw()));
        }
        return true;
    }
}
