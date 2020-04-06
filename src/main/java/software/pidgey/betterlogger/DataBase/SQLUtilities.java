package software.pidgey.betterlogger.DataBase;

import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import software.pidgey.betterlogger.DataBase.BlockData.BlockInteractionData;
import software.pidgey.betterlogger.DataBase.ChestData.ChestMovementData;

import java.io.IOException;
import java.sql.*;

import static software.pidgey.betterlogger.BetterLogger.chestDao;
import static software.pidgey.betterlogger.BetterLogger.blockDao;

public class SQLUtilities {

    public static String connectionURL = "jdbc:postgresql://localhost:5432/BetterLogger?user=admin&password=admin";
    public static Connection conn;
    public static ConnectionSource connSource;
    public static Statement statement;

    public static void sqlOpen(){
        try {
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            blockDao = DaoManager.createDao(connSource, BlockInteractionData.class);
            chestDao = DaoManager.createDao(connSource, ChestMovementData.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void sqlCreate(){
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "admin", "admin");
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.executeUpdate("CREATE DATABASE \"BetterLogger\"");

            connSource = new JdbcConnectionSource(connectionURL);
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

    public static int getRowCount(ResultSet resultSet) {
        int count = 0;

        if (resultSet == null) {
            return 0;
        }
        try {
            resultSet.last();
            count = resultSet.getRow();
            resultSet.first();
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
