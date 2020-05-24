package leyendux.github.io.forgechecker.manager;

import leyendux.github.io.forgechecker.player.ServerPlayer;
import leyendux.github.io.forgechecker.util.MethodUtils;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.*;

public class PlayerManager {

    private static PlayerManager playerManager;

    public static PlayerManager getPlayerManager() {
        if(playerManager == null) {
            playerManager = new PlayerManager();
        }
        return playerManager;
    }

    private HashMap<UUID, ServerPlayer> serverPlayers = new HashMap<UUID, ServerPlayer>();

    public HashMap<UUID, ServerPlayer> getServerPlayers() { return serverPlayers; }

    public ServerPlayer getServerPlayer(UUID uuid) { return getServerPlayers().get(uuid); }

    public void createServerPlayer(UUID uuid) { serverPlayers.put(uuid, new ServerPlayer(uuid)); }

    public boolean availableServerPlayer(UUID uuid) { return serverPlayers.containsKey(uuid); }

    public LinkedList<TextComponent> getForgeUsers() {
        LinkedList<TextComponent> forgeUsers = new LinkedList<TextComponent>();
        for(ServerPlayer serverPlayer : serverPlayers.values()) {
            if(serverPlayer.isForgeUser() && serverPlayer.getOfflinePlayer().isOnline()) {
                TextComponent player = MethodUtils.createClickableChat("§e" + serverPlayer.getName(), "/forgecheck " + serverPlayer.getName(), "§aClick to check " + serverPlayer.getName() + "'s mods", "RUN_COMMAND");
                forgeUsers.add(player);
            }
        }
        return forgeUsers;
    }

    public Set<String> getForgeUsersRaw() {
        Set<String> forgeUsers = new HashSet<String>();
        for(ServerPlayer serverPlayer : serverPlayers.values()) {
            if(serverPlayer.isForgeUser() && serverPlayer.getOfflinePlayer().isOnline()) {
                forgeUsers.add(serverPlayer.getName());
            }
        }
        return forgeUsers;
    }
}
