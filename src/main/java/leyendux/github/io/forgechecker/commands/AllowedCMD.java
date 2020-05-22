package leyendux.github.io.forgechecker.commands;

import leyendux.github.io.forgechecker.Main;
import leyendux.github.io.forgechecker.util.MethodUtils;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AllowedCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] arguments) {
        if(arguments.length < 1) {
            return false;
        }
        String modName = arguments[0];
        if(MethodUtils.blockedModList.contains(modName)) {
            commandSender.sendMessage("§c" + modName + " is already added as disallowed mod");
            return true;
        }
        Player player = (Player) commandSender;
        MethodUtils.blockedModList.add(modName);
        TextComponent message = MethodUtils.createClickableChat("§aAdded " + modName + " as disallowed mod §8(§cUndo§8)", "/removeallowed " + modName, "§aClick to undo the action!","RUN_COMMAND");
        player.spigot().sendMessage(message);
        Main.getMySQL().updateData();
        return true;
    }
}
