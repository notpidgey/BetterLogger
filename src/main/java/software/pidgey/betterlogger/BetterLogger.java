package software.pidgey.betterlogger;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import software.pidgey.betterlogger.DataBase.BlockData.BlockInteractionData;
import software.pidgey.betterlogger.DataBase.BlockData.BlockInteractionEvents;
import software.pidgey.betterlogger.DataBase.ChestData.ChestMovementData;
import software.pidgey.betterlogger.DataBase.ChestData.ChestMovementEvents;
import software.pidgey.betterlogger.Commands.CommandManager;

import java.io.File;
import java.sql.*;

import static software.pidgey.betterlogger.DataBase.SQLUtilities.*;

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
            conn = DriverManager.getConnection(connectionURL);
            connSource = new JdbcConnectionSource(connectionURL);
            sqlOpen();
        }catch (Exception ex){
            sqlCreate();
        }

        registerEvents(new Listener[] {new BlockInteractionEvents(), new ChestMovementEvents(this)});
        getCommand("BetterLogger").setExecutor(new CommandManager());
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
