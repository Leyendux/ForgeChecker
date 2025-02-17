package leyendux.github.io.forgechecker.util;

import leyendux.github.io.forgechecker.Main;
import leyendux.github.io.forgechecker.manager.PlayerManager;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.util.*;

public class MethodUtils {

    public static LinkedList<String> blockedModList = new LinkedList<String>();

    //Easy method to broadcast all members that has the permission that is set in StringUtils class
    public static void broadcastStaff(TextComponent message) {
        for(Player player : Bukkit.getServer().getOnlinePlayers()) {
            //Check if the player has the permission then send a message
            if(player.hasPermission(StringUtils.PERMISSION.getName())) {
                player.spigot().sendMessage(message);
            }
        }
    }

    public static PlayerManager getPlayerManager() { return PlayerManager.getPlayerManager(); }

    public static short secondsToTicks(byte seconds) {
        return (short) (seconds * 20 / 1);
    }

    public static String formatModListRaw(Set<String> modList) {
        String mods = "§8» §e" + modList.size() + " mods found:\n" +
                "§8» §e" + modList.toString()
                .replace("[", "§8[§e")
                .replace("]", "§8]")
                .replace(",", "§8,§e");
        return mods;
    }

    public static TextComponent formatModList(Set<String> modList) {
        LinkedList<TextComponent> modNames = new LinkedList<TextComponent>();
        for(String modNameRaw : modList) {
            if(blockedModList.contains(modNameRaw)) {
                TextComponent modName = createClickableChat("§c" + modNameRaw, "/removeallowed " + modNameRaw, "§8(§a✓§8)", "RUN_COMMAND");
                modNames.add(modName);
            } else {
                TextComponent modName = createClickableChat("§e" + modNameRaw, "/addallowed " + modNameRaw, "§8(§c✗§8)", "RUN_COMMAND");
                modNames.add(modName);
            }
        }
        TextComponent mods = new TextComponent("§8» §e" + modList.size() + " mods found: \n§8» §8[");
        for(int i = 0; i < modList.size(); i++) {
            mods.addExtra(modNames.get(i));
            if((i + 1) == modList.size()) {
                mods.addExtra("§8]");
            } else {
                mods.addExtra("§8, ");
            }
        }
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

    public static TextComponent formatForgeUsers(LinkedList<TextComponent> forgeUsers) {
        TextComponent mods = new TextComponent("§8» §e" + forgeUsers.size() + " players found:\n§8» §8[");
        for(int i = 0; i < forgeUsers.size(); i++) {
            mods.addExtra(forgeUsers.get(i));
            if((i + 1) == forgeUsers.size()) {
                mods.addExtra("§8]");
            } else {
                mods.addExtra("§8, ");
            }
        }
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

    public static TextComponent createClickableChat(String text, String command, String showText, String action) {
        TextComponent textComponent = new TextComponent(text);
        textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.valueOf(action), command));
        textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(showText).create()));
        return textComponent;
    }

    //Easy methods to register channels from Main class
    public static void registerChannel(String channelName, PluginMessageListener pluginMessageListener) {
        Bukkit.getMessenger().registerIncomingPluginChannel(Main.getInstance(), channelName, pluginMessageListener);
    }
    public static void registerChannel(String channelName) {
        Bukkit.getMessenger().registerOutgoingPluginChannel(Main.getInstance(), channelName);
    }
}
