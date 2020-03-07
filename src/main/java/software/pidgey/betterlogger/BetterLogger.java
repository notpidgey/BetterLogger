package software.pidgey.betterlogger;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import software.pidgey.betterlogger.SQLData.BlockInteraction;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.*;

public final class BetterLogger extends JavaPlugin {

    public static File file;
    public static PrintStream fileStream;
    public static Connection conn;
    public static ConnectionSource connSource;

    public static Dao<BlockInteraction, String> blockDao;

    @Override
    public void onEnable() {
        getLogger().info("BetterLogger is now running.");
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("BetterLogger").getDataFolder().getAbsolutePath(), "BetterLogger.db");

        if(!file.exists()){
            file.getParentFile().mkdirs();
            System.out.println("Database does not exist, creating database.");
            sqlCreate();
            sqlOpen();
        }
        else{
            System.out.println("Database already exists, proceeding.");
            sqlOpen();
        }

        getServer().getPluginManager().registerEvents(new Events(),this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void sqlOpen(){
        String url = "jdbc:sqlite:" + file.getAbsolutePath();
        try {
            connSource = new JdbcConnectionSource(url);
            blockDao = DaoManager.createDao(connSource, BlockInteraction.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            TableUtils.createTable(connSource, BlockInteraction.class);
            connSource.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sqlCreate(){
        String url = "jdbc:sqlite:" + file.getAbsolutePath();
        try {
            conn = DriverManager.getConnection(url);
            if(conn != null){
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("Database created.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong when creating a database");
        }
    }
}
