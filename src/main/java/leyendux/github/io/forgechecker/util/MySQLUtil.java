package leyendux.github.io.forgechecker.util;

import leyendux.github.io.forgechecker.Main;
import org.bukkit.Bukkit;

import java.sql.*;

public class MySQLUtil {

    private Connection connection;
    private String host, database, username, password;
    private int port;

    public void setup(String host, String database, String username, String password, int port) {
        this.host = host;
        this.database = database;
        this.username = username;
        this.password =  password;
        this.port = port;

        try {
            synchronized (this) {
                if(getConnection() != null && !getConnection().isClosed()) {
                    return;
                }

                Class.forName("com.mysql.jdbc.Driver");
                setConnection(DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, this.username, this.password));

                Main.getInstance().getLogger().info("MySQL loaded successfully");
                createTable();
                loadData();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void disable() {
        try {
            if(getConnection() != null && !getConnection().isClosed()) {
                getConnection().close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private void createTable() {
        try {
            Main.getInstance().getLogger().info("Creating table...");
            PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS modList (ServerName varchar(255), BlockedMods varchar(255))");
            statement.executeUpdate();
            Main.getInstance().getLogger().info("Table created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean loadData() {
        try {
            Main.getInstance().getLogger().info("Loading data...");
            PreparedStatement statement = connection.prepareStatement("SELECT * from modList WHERE ServerName=?");
            statement.setString(1, Bukkit.getServerName());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                String[] blockedMods = resultSet.getString("BlockedMods").replace("[", "").replace("]", "").replace(" ", "").split(",");
                for(String blockedMod : blockedMods) {
                    MethodUtils.blockedModList.add(blockedMod);
                }
                Main.getInstance().getLogger().info("Data loaded successfully");
                return true;
            }
            createData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void createData() {
        try {
            Main.getInstance().getLogger().info("Creating data...");
            PreparedStatement statement = connection.prepareStatement("INSERT INTO modList (ServerName, BlockedMods) VALUES (?, ?)");
            statement.setString(1, Bukkit.getServerName());
            statement.setString(2, MethodUtils.blockedModList.toString());
            statement.executeUpdate();
            Main.getInstance().getLogger().info("Data created successfully");
            loadData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateData() {
        try {
            Main.getInstance().getLogger().info("Updating data...");
            PreparedStatement statement = connection.prepareStatement("UPDATE modList SET BlockedMods = ? WHERE ServerName = ?");
            statement.setString(1, MethodUtils.blockedModList.toString());
            statement.setString(2, Bukkit.getServerName());
            statement.executeUpdate();
            Main.getInstance().getLogger().info("Successfully updated.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
