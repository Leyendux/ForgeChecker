package leyendux.github.io.forgechecker.player;

import leyendux.github.io.forgechecker.Main;
import leyendux.github.io.forgechecker.util.MethodUtils;
import leyendux.github.io.forgechecker.util.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ServerPlayer {

    private UUID uuid;
    private boolean forgeUser = false;
    private Set<String> modList = null;

    public ServerPlayer(UUID uuid) { this.uuid = uuid; }

    public String getName() { return Bukkit.getOfflinePlayer(uuid).getName(); }

    public Player getPlayer() { return Bukkit.getPlayer(uuid); }

    public boolean isForgeUser() { return forgeUser; }

    public Set<String> getModList() { return modList;}

    public void setForgeUser(boolean forgeUser) { this.forgeUser = forgeUser; }

    public void setModList(Set<String> modList) { this.modList = modList; }

    //Send handshake to forge client to return correct values
    public void sendFMLHandshake() {
        new BukkitRunnable() {
            @Override
            public void run() {
                //ClientHello to Spigot Server. More info at: https://wiki.vg/Minecraft_Forge_Handshake
                getPlayer().sendPluginMessage(Main.getInstance(), StringUtils.CHANNEL_NAME.getName(), new byte[] {-2, 0});
                getPlayer().sendPluginMessage(Main.getInstance(), StringUtils.CHANNEL_NAME.getName(), new byte[] { 0, 2, 0, 0, 0, 0 });
                getPlayer().sendPluginMessage(Main.getInstance(), StringUtils.CHANNEL_NAME.getName(), new byte[] { 2, 0, 0, 0, 0 });
            }
        }.runTaskLater(Main.getInstance(), MethodUtils.secondsToTicks((byte) 1));
    }
}
