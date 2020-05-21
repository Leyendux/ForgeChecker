package leyendux.github.io.forgechecker.events;

import leyendux.github.io.forgechecker.player.ServerPlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ServerPlayerJoinEvent extends Event {
    private ServerPlayer serverPlayer;
    private static final HandlerList HANDLERS_LIST = new HandlerList();

    public ServerPlayerJoinEvent(ServerPlayer serverPlayer) {
        this.serverPlayer = serverPlayer;
    }

    public ServerPlayer getServerPlayer() { return serverPlayer; }

    @Override
    public HandlerList getHandlers() { return HANDLERS_LIST; }

    public static HandlerList getHandlerList() { return HANDLERS_LIST; }
}
