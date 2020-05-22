package leyendux.github.io.forgechecker;

import leyendux.github.io.forgechecker.commands.AllowedCMD;
import leyendux.github.io.forgechecker.commands.DisallowedCMD;
import leyendux.github.io.forgechecker.commands.ForgeCheckCMD;
import leyendux.github.io.forgechecker.commands.ForgeUsersCMD;
import leyendux.github.io.forgechecker.listeners.PlayerJoinListener;
import leyendux.github.io.forgechecker.listeners.ServerMessageListener;
import leyendux.github.io.forgechecker.util.MethodUtils;
import leyendux.github.io.forgechecker.util.MySQLUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public class Main extends JavaPlugin {
    private static Main instance;
    public static MySQLUtil mySQL;

    @Override
    public void onEnable() {
        super.onEnable();
        instance = this;
        mySQL = new MySQLUtil();
        registerListeners();
        registerMessageChannels();
        registerCommands();
        mySQL.setup("209.222.98.118", "s18075_blockedmods", "u18075_tCwJXFDAuO", "t^dq59I8JbxWgIoMgaSu.gLw", 3306);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        mySQL.disable();
        instance = null;
    }

    public static Main getInstance() {return instance;}

    public static MySQLUtil getMySQL() {return mySQL;}

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
        getCommand("addallowed").setExecutor(new AllowedCMD());
        getCommand("removeallowed").setExecutor(new DisallowedCMD());
    }
}
