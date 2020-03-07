package software.pidgey.betterlogger;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.*;
import software.pidgey.betterlogger.SQLData.BlockInteraction;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static software.pidgey.betterlogger.BetterLogger.blockDao;

public class Events implements Listener {

    private Date getDate(){
        Date date = new Date();
        return date;
    }

    @EventHandler
    private void onBreakBlock(BlockBreakEvent breakEvent){
        BlockInteraction blockBreak = new BlockInteraction();

        blockBreak.time = getDate();
        blockBreak.player = breakEvent.getPlayer().getName();
        blockBreak.interactionType = "Break";
        blockBreak.block = breakEvent.getBlock().getType().toString();

        blockBreak.setLocation(breakEvent.getBlock().getLocation());

        try {
            blockDao.create(blockBreak);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    private void onPlace(BlockPlaceEvent placeEvent) {
        BlockInteraction blockPlace = new BlockInteraction();

        blockPlace.time = getDate();
        blockPlace.player = placeEvent.getPlayer().getName();
        blockPlace.interactionType = "Break";
        blockPlace.block = placeEvent.getBlock().getType().toString();

        blockPlace.setLocation(placeEvent.getBlock().getLocation());

        try {
            blockDao.create(blockPlace);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    private void onChestMove(InventoryMoveItemEvent moveEvent){
        if(moveEvent.getSource().getType() == InventoryType.CHEST){
            System.out.println("INVENTORY TYPE CHEST");
        }
    }

    @EventHandler
    private void onEnterBed(PlayerBedEnterEvent bedEnterEvent){

    }

    @EventHandler
    private void onLeaveBed(PlayerBedLeaveEvent bedLeaveEvent){

    }

    @EventHandler
    private void onDropItem(PlayerDropItemEvent dropItemEvent){

    }

    @EventHandler
    private void onWorldEvent(PlayerLevelChangeEvent levelChangeEvent){

    }

    @EventHandler
    private void onKill(PlayerPortalEvent portalEvent){

    }

    @EventHandler
    private void onKill(PlayerJoinEvent joinEvent){

    }

    @EventHandler
    private void onKill(PlayerQuitEvent quitEvent){

    }

    @EventHandler
    private void onKill(PlayerGameModeChangeEvent gameModeChangeEvent){

    }

    @EventHandler
    private void onKill(PlayerItemConsumeEvent itemConsumeEvent){

    }

    @EventHandler
    private void onKill(PlayerDeathEvent deathEvent){

    }

    @EventHandler
    private void onKill(PlayerRespawnEvent respawnEvent){

    }

    @EventHandler
    private void onPickup(InventoryPickupItemEvent pickupItemEvent) {

    }
}
