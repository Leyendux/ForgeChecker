package leyendux.github.io.forgechecker.events;

import leyendux.github.io.forgechecker.player.ServerPlayer;
import leyendux.github.io.forgechecker.util.MethodUtils;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ForgeUserJoinEvent extends Event {
    private ServerPlayer serverPlayer;
    private byte[] data;
    private static final HandlerList HANDLERS_LIST = new HandlerList();

    public ForgeUserJoinEvent(ServerPlayer serverPlayer, byte[] data) {
        this.serverPlayer = serverPlayer;
        this.data = data;
    }

    public ServerPlayer getServerPlayer() { return serverPlayer; }

    //Return a set of values readed by getModData method from MethodUtils class
    public Set<String> getModList() { return Collections.unmodifiableSet(MethodUtils.getModData(data).keySet()); }

    @Override
    public HandlerList getHandlers() { return HANDLERS_LIST; }

    public static HandlerList getHandlerList() { return HANDLERS_LIST; }
}
