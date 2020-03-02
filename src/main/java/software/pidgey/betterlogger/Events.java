package software.pidgey.betterlogger;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.player.*;
import software.pidgey.betterlogger.SQLData.BlockInteraction;

import java.sql.SQLException;
import java.util.Date;

import static software.pidgey.betterlogger.BetterLogger.blockDao;

public class Events implements Listener {
    @EventHandler
    public void onBreakBlock(BlockBreakEvent breakEvent){
        Date date = new Date();

        BlockInteraction blockBreak = new BlockInteraction();
        blockBreak.time = date;
        blockBreak.player = breakEvent.getPlayer().getName();
        blockBreak.x = breakEvent.getBlock().getLocation().getBlockX();
        blockBreak.y = breakEvent.getBlock().getLocation().getBlockY();
        blockBreak.z = breakEvent.getBlock().getLocation().getBlockZ();
        blockBreak.interactionType = "Break";
        blockBreak.block = breakEvent.getBlock().getType().toString();

        try {
            blockDao.create(blockBreak);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent placeEvent)
    {
        String content = String.format("%-15s %-15s %-7s\n",
                placeEvent.getPlayer().getName(),
                "X=" + placeEvent.getBlock().getLocation().getBlockX() + ", Y=" +
                        placeEvent.getBlock().getLocation().getBlockY() + ", Z=" +
                        placeEvent.getBlock().getLocation().getBlockZ(),
                "Placed: " + placeEvent.getBlock().getType().toString());
    }

    @EventHandler
    public void onEnterBed(PlayerBedEnterEvent bedEnterEvent){
        String content = String.format("%-15s %-12s %-7s\n",
                bedEnterEvent.getPlayer().getName(),
                "X=" + bedEnterEvent.getBed().getLocation().getBlockX() + ", Y=" +
                        bedEnterEvent.getBed().getLocation().getBlockY() + ", Z=" +
                        bedEnterEvent.getBed().getLocation().getBlockZ(),
                "Entered bed.");
    }

    @EventHandler
    public void onLeaveBed(PlayerBedLeaveEvent bedLeaveEvent){

    }

    @EventHandler
    public void onDropItem(PlayerDropItemEvent dropItemEvent){

    }

    @EventHandler
    public void onWorldEvent(PlayerLevelChangeEvent levelChangeEvent){

    }

    @EventHandler
    public void onKill(PlayerPortalEvent portalEvent){

    }

    @EventHandler
    public void onKill(PlayerJoinEvent joinEvent){

    }

    @EventHandler
    public void onKill(PlayerQuitEvent quitEvent){

    }

    @EventHandler
    public void onKill(PlayerGameModeChangeEvent gameModeChangeEvent){

    }

    @EventHandler
    public void onKill(PlayerItemConsumeEvent itemConsumeEvent){

    }

    @EventHandler
    public void onKill(PlayerDeathEvent deathEvent){

    }

    @EventHandler
    public void onKill(PlayerRespawnEvent respawnEvent){

    }

    @EventHandler
    public void onPickup(InventoryPickupItemEvent pickupItemEvent) {

    }
}
