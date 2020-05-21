package leyendux.github.io.forgechecker.listeners;

import leyendux.github.io.forgechecker.events.ForgeUserJoinEvent;
import leyendux.github.io.forgechecker.player.ServerPlayer;
import leyendux.github.io.forgechecker.util.MethodUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class ServerMessageListener implements PluginMessageListener {

    @Override
    public void onPluginMessageReceived(String s, Player player, byte[] bytes) {
        if(bytes[0] == 2) {
            ServerPlayer serverPlayer = MethodUtils.getPlayerManager().getServerPlayer(player.getUniqueId());
            Bukkit.getPluginManager().callEvent(new ForgeUserJoinEvent(serverPlayer, bytes));
        }
    }
}
