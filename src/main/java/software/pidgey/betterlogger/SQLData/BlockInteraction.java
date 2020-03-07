package software.pidgey.betterlogger.SQLData;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.types.DateTimeType;
import com.j256.ormlite.table.DatabaseTable;
import org.bukkit.Location;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static software.pidgey.betterlogger.BetterLogger.blockDao;

@DatabaseTable(tableName = "BlockInteraction")
public class BlockInteraction {

    @DatabaseField(columnName = "Time", dataType = DataType.DATE_STRING, format = "E, MM.dd.yyyy hh:mm:ss a")
    public Date time;

    @DatabaseField(columnName = "Player")
    public String player;

    @DatabaseField(columnName = "X")
    public int x;

    @DatabaseField(columnName = "Y")
    public int y;

    @DatabaseField(columnName = "Z")
    public int z;

    @DatabaseField(columnName = "Interaction Type")
    public String interactionType;

    @DatabaseField(columnName = "Block")
    public String block;

    public BlockInteraction(){

    }

    public void setLocation(Location location){
        x = location.getBlockX();
        y = location.getBlockY();
        z = location.getBlockZ();
    }
}
