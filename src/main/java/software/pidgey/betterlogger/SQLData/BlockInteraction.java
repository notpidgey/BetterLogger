package software.pidgey.betterlogger.SQLData;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.types.DateTimeType;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "BlockInteraction")
public class BlockInteraction {

    @DatabaseField(columnName = "Time")
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

    public BlockInteraction(Date time, String player, int x, int y, int z, String interactionType, String block){
        this.time = time;
        this.player = player;
        this.x = x;
        this.y = y;
        this.z = z;
        this.interactionType = interactionType;
        this.block = block;
    }

}
