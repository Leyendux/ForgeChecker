package leyendux.github.io.forgechecker.manager;

import leyendux.github.io.forgechecker.player.ServerPlayer;

import java.util.HashMap;
import java.util.UUID;

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
}
