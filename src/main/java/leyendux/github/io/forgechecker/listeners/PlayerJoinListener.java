package leyendux.github.io.forgechecker.listeners;

import leyendux.github.io.forgechecker.events.ForgeUserJoinEvent;
import leyendux.github.io.forgechecker.events.ServerPlayerJoinEvent;
import leyendux.github.io.forgechecker.player.ServerPlayer;
import leyendux.github.io.forgechecker.util.MethodUtils;
import leyendux.github.io.forgechecker.util.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        //Check if player exists in the memory, if not add it to the memory
        if(!MethodUtils.getPlayerManager().availableServerPlayer(player.getUniqueId())) {
            MethodUtils.getPlayerManager().createServerPlayer(player.getUniqueId());
        }
        //Call custom event
        ServerPlayer serverPlayer = MethodUtils.getPlayerManager().getServerPlayer(player.getUniqueId());
        Bukkit.getPluginManager().callEvent(new ServerPlayerJoinEvent(serverPlayer));
    }

    @EventHandler
    public void onServerPlayerJoinEvent(ServerPlayerJoinEvent event) {
        ServerPlayer serverPlayer = event.getServerPlayer();
        //Set default values then send the handshake to Spigot server
        serverPlayer.setForgeUser(false);
        serverPlayer.setModList(null);
        serverPlayer.sendFMLHandshake();
    }

    @EventHandler
    public void onForgeUserJoinEvent(ForgeUserJoinEvent event) {
        ServerPlayer serverPlayer = event.getServerPlayer();
        MethodUtils.broadcastStaff(StringUtils.PREFIX.getName() + "§7is using §cForge Client §8(§eFML|HS§8)");
        serverPlayer.setForgeUser(true);
        serverPlayer.setModList(event.getModList());
    }
}
