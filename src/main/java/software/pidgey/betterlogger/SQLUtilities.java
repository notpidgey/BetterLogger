package software.pidgey.betterlogger;

import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import software.pidgey.betterlogger.BlockData.BlockInteractionData;
import software.pidgey.betterlogger.ChestData.ChestMovementData;

import java.io.IOException;
import java.sql.*;

import static software.pidgey.betterlogger.BetterLogger.chestDao;
import static software.pidgey.betterlogger.BetterLogger.blockDao;

public class SQLUtilities {

    public static String connectionURL = "jdbc:postgresql://localhost:5432/BetterLogger";
    public static Connection conn;
    public static ConnectionSource connSource;

    public static void sqlOpen(){
        try {
            blockDao = DaoManager.createDao(connSource, BlockInteractionData.class);
            chestDao = DaoManager.createDao(connSource, ChestMovementData.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void sqlCreate(){
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "admin", "admin");
            Statement statement = conn.createStatement();
            statement.executeUpdate("CREATE DATABASE \"BetterLogger\"");

            connSource = new JdbcConnectionSource(connectionURL, "admin", "admin");
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

        sqlOpen();
    }
}
