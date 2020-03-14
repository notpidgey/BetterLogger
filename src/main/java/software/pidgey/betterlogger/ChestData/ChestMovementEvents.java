package software.pidgey.betterlogger.ChestData;

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
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import software.pidgey.betterlogger.BetterLogger;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import static software.pidgey.betterlogger.BetterLogger.chestDao;
import static software.pidgey.betterlogger.Utilities.getDate;

public class ChestMovementEvents implements Listener {
    private final BetterLogger minecraftPlugin;

    public ChestMovementEvents(BetterLogger minecraftPlugin) {
        this.minecraftPlugin = minecraftPlugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent inventoryClickEvent) {
        if (inventoryClickEvent.getClickedInventory() != null && inventoryClickEvent.getWhoClicked() instanceof Player) {
            InventoryView inventoryView = inventoryClickEvent.getView();
            InventoryAction inventoryAction = inventoryClickEvent.getAction();

            Inventory topInventory = inventoryView.getTopInventory();
            Inventory bottomInventory = inventoryView.getBottomInventory();

            if (topInventory.getType() == InventoryType.CHEST && bottomInventory.getType() == InventoryType.PLAYER) {
                Chest chest = (Chest)topInventory.getHolder();
                Player player = (Player)inventoryClickEvent.getWhoClicked();
                ItemStack currentItem = inventoryClickEvent.getCurrentItem();
                NamespacedKey key = new NamespacedKey(minecraftPlugin, "ownerUUID");

                if (currentItem != null && chest != null) {
                    if (inventoryAction == InventoryAction.MOVE_TO_OTHER_INVENTORY) {
                        if (inventoryClickEvent.getClickedInventory().getType() != InventoryType.CHEST) {
                            return;
                        }
                    } else if (inventoryAction == InventoryAction.PLACE_ALL ||
                            inventoryAction == InventoryAction.PLACE_ONE ||
                            inventoryAction == InventoryAction.PLACE_SOME) {
                        if (inventoryClickEvent.getClickedInventory().getType() != InventoryType.PLAYER) {
                            return;
                        }

                        ItemMeta itemMeta = currentItem.getItemMeta();

                        if (itemMeta == null) {
                            return;
                        }

                        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();

                        if (persistentDataContainer.has(key, PersistentDataType.STRING)) {
                            String uuidString = persistentDataContainer.get(key, PersistentDataType.STRING);

                            persistentDataContainer.remove(key);
                            currentItem.setItemMeta(itemMeta);

                            if (uuidString.equals(player.getUniqueId().toString())) {
                                return;
                            }
                        }

                    } else if (inventoryAction == InventoryAction.PICKUP_ONE ||
                            inventoryAction == InventoryAction.PICKUP_SOME ||
                            inventoryAction == InventoryAction.PICKUP_ALL ||
                            inventoryAction == InventoryAction.PICKUP_HALF) {
                        if (inventoryClickEvent.getClickedInventory().getType() != InventoryType.CHEST) {
                            return;
                        }

                        List<MetadataValue> metadataValueList = chest.getMetadata("blockOwner");

                        if (metadataValueList.size() > 0) {
                            ItemMeta itemMeta = currentItem.getItemMeta();
                            MetadataValue metadataValue = metadataValueList.get(0);

                            if (itemMeta != null) {
                                itemMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, metadataValue.asString());
                                currentItem.setItemMeta(itemMeta);
                            }
                        }
                    } else {
                        return;
                    }

                    Location blockLocation = chest.getLocation();
                    List<MetadataValue> metadataValueList = chest.getMetadata("blockOwner");
                    OfflinePlayer offlinePlayer = null;

                    if (metadataValueList.size() > 0) {
                        MetadataValue metadataValue = metadataValueList.get(0);
                        offlinePlayer = minecraftPlugin.getServer().getOfflinePlayer(UUID.fromString(metadataValue.asString()));
                    }

                    chestMovementDatabaseUpdate(player, currentItem.getType().toString(), blockLocation, offlinePlayer);
                }
            }
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent blockPlaceEvent) {
        Block block = blockPlaceEvent.getBlock();

        if (block.getType() == Material.CHEST) {
            Player player = blockPlaceEvent.getPlayer();
            block.setMetadata("blockOwner", new FixedMetadataValue(minecraftPlugin, player.getUniqueId().toString()));
        }
    }

    public void chestMovementDatabaseUpdate(Player player, String block, Location location, OfflinePlayer offlinePlayer) {
        ChestMovementData chestMovement = new ChestMovementData();

        chestMovement.chestOwner = offlinePlayer == null ? "FAILED" : offlinePlayer.getName();
        chestMovement.chestOwnerUuid = offlinePlayer == null ? "FAILED" : offlinePlayer.getUniqueId().toString();

        chestMovement.time = getDate();
        chestMovement.uuid = player.getUniqueId().toString();
        chestMovement.player = player.getDisplayName();
        chestMovement.interactionType = "TAKE";
        chestMovement.block = block;

        chestMovement.setLocation(location);

        try {
            chestDao.create(chestMovement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
