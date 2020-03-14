package software.pidgey.betterlogger.ChestData;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.bukkit.Location;

import java.util.Date;

@DatabaseTable(tableName = "Chest Movement")
public class ChestMovementData {

    @DatabaseField(columnName = "Time", dataType = DataType.DATE_STRING, format = "E, MM.dd.yyyy hh:mm:ss a")
    public Date time;

    @DatabaseField(columnName = "UUID")
    public String uuid;

    @DatabaseField(columnName = "Username")
    public String player;

    @DatabaseField(columnName = "Chest Owner UUID")
    public String chestOwnerUuid;

    @DatabaseField(columnName = "Chest Owner")
    public String chestOwner;

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

    public ChestMovementData(){

    }

    public void setLocation(Location location){
        x = location.getBlockX();
        y = location.getBlockY();
        z = location.getBlockZ();
    }
}
