package software.pidgey.betterlogger.BlockData;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.types.DateTimeType;
import com.j256.ormlite.table.DatabaseTable;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static software.pidgey.betterlogger.BetterLogger.blockDao;

@DatabaseTable(tableName = "Block Interaction")
public class BlockInteractionData {

    @DatabaseField(columnName = "Time", dataType = DataType.DATE_STRING, format = "E, MM.dd.yyyy hh:mm:ss a")
    public Date time;

    @DatabaseField(columnName = "UID")
    public String uid;

    @DatabaseField(columnName = "Username")
    public String username;

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

    public BlockInteractionData(){

    }

    public void setLocation(Location location){
        x = location.getBlockX();
        y = location.getBlockY();
        z = location.getBlockZ();
    }

    public void setUid(Player player){
        uid = player.getUniqueId().toString();
        username = player.getName();
    }
}
