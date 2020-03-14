package software.pidgey.betterlogger;

import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;
import software.pidgey.betterlogger.BlockData.BlockInteractionData;
import software.pidgey.betterlogger.ChestData.ChestMovementData;

import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

import static software.pidgey.betterlogger.BetterLogger.connSource;
import static software.pidgey.betterlogger.BetterLogger.databaseFile;
import static software.pidgey.betterlogger.BetterLogger.chestDao;
import static software.pidgey.betterlogger.BetterLogger.blockDao;
import static software.pidgey.betterlogger.BetterLogger.conn;

public class SQLUtilities {

    public static void sqlOpen(){
        String url = "jdbc:sqlite:" + databaseFile.getAbsolutePath();
        try {
            connSource = new JdbcConnectionSource(url);
            blockDao = DaoManager.createDao(connSource, BlockInteractionData.class);
            chestDao = DaoManager.createDao(connSource, ChestMovementData.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            TableUtils.createTable(connSource, BlockInteractionData.class);
            TableUtils.createTable(connSource, ChestMovementData.class);
            connSource.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sqlCreate(){
        String url = "jdbc:sqlite:" + databaseFile.getAbsolutePath();
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
