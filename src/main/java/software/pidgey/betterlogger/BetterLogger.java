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
import software.pidgey.betterlogger.Commands.CommandManager;
import software.pidgey.betterlogger.SQLUtilities;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.*;

import static software.pidgey.betterlogger.SQLUtilities.*;

public final class BetterLogger extends JavaPlugin {

    public static Dao<BlockInteractionData, String> blockDao;
    public static Dao<ChestMovementData, String> chestDao;

    public static File configurationFile;

    @Override
    public void onEnable() {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        getLogger().info("BetterLogger is now running.");
        configurationFile = new File(Bukkit.getServer().getPluginManager().getPlugin("BetterLogger").getDataFolder().getAbsolutePath(),
                "Plugin.yml");

        try{
            conn = DriverManager.getConnection(connectionURL, "admin", "admin");
            connSource = new JdbcConnectionSource(connectionURL);
            sqlOpen();
        }catch (Exception ex){
            sqlCreate();
        }

        registerEvents(new Listener[] {new BlockInteractionEvents(), new ChestMovementEvents(this)});
        registerCommands(new String[] {"queryRaw", ""});
    }

    private void registerEvents(Listener[] listeners) {
        for (Listener listener : listeners) {
            getServer().getPluginManager().registerEvents(listener, this);
        }
    }

    private void registerCommands(String[] commands){
        for(String command : commands){
            getCommand(command).setExecutor(new CommandManager());
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void setupConfig(){

    }
}
