package leyendux.github.io.forgechecker.commands;

import leyendux.github.io.forgechecker.player.ServerPlayer;
import leyendux.github.io.forgechecker.util.MethodUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ForgeCheckCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] arguments) {
        if(arguments.length < 1) {
            return false;
        }
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(arguments[0]);
        if((offlinePlayer == null) || (!MethodUtils.getPlayerManager().availableServerPlayer(offlinePlayer.getUniqueId()))) {
            commandSender.sendMessage(ChatColor.RED + "This player is not available!");
            return true;
        }
        ServerPlayer serverPlayer = MethodUtils.getPlayerManager().getServerPlayer(offlinePlayer.getUniqueId());
        if(!serverPlayer.isForgeUser()) {
            commandSender.sendMessage(ChatColor.RED + "This player is not using Forge!");
            return true;
        }
        commandSender.sendMessage(MethodUtils.formatModList(serverPlayer.getModList()));
        return true;
    }
}
