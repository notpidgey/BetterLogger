package software.pidgey.betterlogger;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import software.pidgey.betterlogger.ChestData.ChestMovementData;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static software.pidgey.betterlogger.BetterLogger.chestDao;

public class Events implements Listener {

    @EventHandler
    private void onEnterBed(PlayerBedEnterEvent bedEnterEvent) {

    }

    @EventHandler
    private void onLeaveBed(PlayerBedLeaveEvent bedLeaveEvent) {

    }

    @EventHandler
    private void onDropItem(PlayerDropItemEvent dropItemEvent) {

    }

    @EventHandler
    private void onWorldEvent(PlayerLevelChangeEvent levelChangeEvent) {

    }

    @EventHandler
    private void onKill(PlayerPortalEvent portalEvent) {

    }

    @EventHandler
    private void onKill(PlayerJoinEvent joinEvent) {

    }

    @EventHandler
    private void onKill(PlayerQuitEvent quitEvent) {

    }

    @EventHandler
    private void onKill(PlayerGameModeChangeEvent gameModeChangeEvent) {

    }

    @EventHandler
    private void onKill(PlayerItemConsumeEvent itemConsumeEvent) {

    }

    @EventHandler
    private void onKill(PlayerDeathEvent deathEvent) {

    }

    @EventHandler
    private void onKill(PlayerRespawnEvent respawnEvent) {

    }

    @EventHandler
    private void onPickup(InventoryPickupItemEvent pickupItemEvent) {

    }

}
