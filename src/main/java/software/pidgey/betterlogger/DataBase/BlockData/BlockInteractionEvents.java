package software.pidgey.betterlogger.DataBase.BlockData;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.sql.SQLException;

import static software.pidgey.betterlogger.BetterLogger.blockDao;
import static software.pidgey.betterlogger.Utilities.getDate;

public class BlockInteractionEvents implements Listener {

    @EventHandler
    private void onBreakBlock(BlockBreakEvent breakEvent) {
        BlockInteractionData blockBreak = new BlockInteractionData();

        blockBreak.time = getDate();
        blockBreak.interactionType = "BREAK";
        blockBreak.block = breakEvent.getBlock().getType().toString();

        blockBreak.setLocation(breakEvent.getBlock().getLocation());
        blockBreak.setUid(breakEvent.getPlayer());

        try {
            blockDao.create(blockBreak);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    private void onPlace(BlockPlaceEvent placeEvent) {
        BlockInteractionData blockPlace = new BlockInteractionData();

        blockPlace.time = getDate();
        blockPlace.interactionType = "PLACE";
        blockPlace.block = placeEvent.getBlock().getType().toString();

        blockPlace.setLocation(placeEvent.getBlock().getLocation());
        blockPlace.setUid(placeEvent.getPlayer());

        try {
            blockDao.create(blockPlace);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
