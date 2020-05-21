package leyendux.github.io.forgechecker;

import leyendux.github.io.forgechecker.commands.ForgeCheckCMD;
import leyendux.github.io.forgechecker.commands.ForgeUsersCMD;
import leyendux.github.io.forgechecker.listeners.PlayerJoinListener;
import leyendux.github.io.forgechecker.listeners.ServerMessageListener;
import leyendux.github.io.forgechecker.util.MethodUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private static Main instance;

    @Override
    public void onEnable() {
        super.onEnable();
        instance = this;
        registerListeners();
        registerMessageChannels();
        registerCommands();
    }

    @Override
    public void onDisable() {
        super.onDisable();
        instance = null;
    }

    public static Main getInstance() {return instance;}

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
    }

    private void registerMessageChannels() {
        MethodUtils.registerChannel("FML|HS", new ServerMessageListener());
        MethodUtils.registerChannel("FML|HS");
    }

    private void registerCommands() {
        getCommand("forgecheck").setExecutor(new ForgeCheckCMD());
        getCommand("forgeusers").setExecutor(new ForgeUsersCMD());
    }
}
