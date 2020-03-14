package software.pidgey.betterlogger;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import software.pidgey.betterlogger.BlockData.BlockInteractionData;
import software.pidgey.betterlogger.BlockData.BlockInteractionEvents;
import software.pidgey.betterlogger.ChestData.ChestMovementData;
import software.pidgey.betterlogger.ChestData.ChestMovementEvents;
import software.pidgey.betterlogger.SQLUtilities;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.*;

import static software.pidgey.betterlogger.SQLUtilities.sqlCreate;
import static software.pidgey.betterlogger.SQLUtilities.sqlOpen;

public final class BetterLogger extends JavaPlugin {

    public static File databaseFile;
    public static PrintStream fileStream;
    public static Connection conn;
    public static ConnectionSource connSource;

    public static Dao<BlockInteractionData, String> blockDao;
    public static Dao<ChestMovementData, String> chestDao;

    public static File configurationFile;

    @Override
    public void onEnable() {
        getLogger().info("BetterLogger is now running.");
        databaseFile = new File(Bukkit.getServer().getPluginManager().getPlugin("BetterLogger").getDataFolder().getAbsolutePath(),
                "BetterLogger.db");
        configurationFile = new File(Bukkit.getServer().getPluginManager().getPlugin("BetterLogger").getDataFolder().getAbsolutePath(),
                "Plugin.yml");

        if(!databaseFile.exists()){
            databaseFile.getParentFile().mkdirs();
            System.out.println("Database does not exist, creating database.");
            sqlCreate();
            sqlOpen();
        }
        else{
            System.out.println("Database already exists, proceeding.");
            sqlOpen();
        }

        if(!configurationFile.exists()){
            setupConfig();
        }

        registerEvents(new Listener[] {new BlockInteractionEvents(), new ChestMovementEvents(this)});
    }

    private void registerEvents(Listener[] listeners) {
        for (Listener listener : listeners) {
            getServer().getPluginManager().registerEvents(listener, this);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void setupConfig(){

    }
}
