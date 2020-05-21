package leyendux.github.io.forgechecker.util;

import leyendux.github.io.forgechecker.Main;
import leyendux.github.io.forgechecker.manager.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MethodUtils {

    //Easy method to broadcast all members that has the permission that is set in StringUtils class
    public static void broadcastStaff(String message) {
        for(Player player : Bukkit.getServer().getOnlinePlayers()) {
            //Check if the player has the permission then send a message
            if(player.hasPermission(StringUtils.PERMISSION.getName())) {
                player.sendMessage(message);
            }
        }
    }

    public static PlayerManager getPlayerManager() { return PlayerManager.getPlayerManager(); }

    public static short secondsToTicks(byte seconds) {
        return (short) (seconds * 20 / 1);
    }

    public static String formatModList(Set<String> modList) {
        String mods = "§8» §e" + modList.size() + " mods found:\n" +
                "§8» §e" + modList.toString()
                .replace("[", "§8[§e")
                .replace("]", "§8]")
                .replace(",", "§8,§e");
        return mods;
    }

    public static String formatForgeUsers(Set<String> forgeUsers) {
        String mods = "§8» §e" + forgeUsers.size() + " players found:\n" +
                "§8» §e" + forgeUsers.toString()
                .replace("[", "§8[§e")
                .replace("]", "§8]")
                .replace(",", "§8,§e");
        return mods;
    }

    //Read bytes that are sent from player's FML|HS channel
    public static Map getModData(byte[] data) {
        Map<String, String> mods = new HashMap();
        boolean store = false;
        String tempName = null;
        //Loop from all bytes and check if it's already readed or not
        for (int i = 2; i < data.length; store = !store) {
            int end = i + data[i] + 1;
            //Get the complete array of bytes then parse it into a String
            byte[] range = Arrays.copyOfRange(data, i + 1, end);
            String string = new String(range);
            //Save it into a HashMap depending if it's already readed or not to continue the loop
            if (store) {
                mods.put(tempName, string);
            } else {
                tempName = string;
            }
            i = end;
        }
        return mods;
    }

    //Easy methods to register channels from Main class
    public static void registerChannel(String channelName, PluginMessageListener pluginMessageListener) {
        Bukkit.getMessenger().registerIncomingPluginChannel(Main.getInstance(), channelName, pluginMessageListener);
    }
    public static void registerChannel(String channelName) {
        Bukkit.getMessenger().registerOutgoingPluginChannel(Main.getInstance(), channelName);
    }
}
