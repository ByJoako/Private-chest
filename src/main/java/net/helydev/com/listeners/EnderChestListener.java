package net.helydev.com.listeners;

import net.helydev.com.data.UserData;
import net.helydev.com.main;
import net.helydev.com.utils.CC;
import net.helydev.com.utils.InventoryUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Joako (22/12/2022 | 00:20)
 **/

public class EnderChestListener implements Listener {

    @EventHandler
    public void onInteractInventory(InventoryClickEvent event) {
        Inventory inv = event.getInventory();
        if (!inv.getTitle().equals(CC.translate("&7EnderChest"))) return;

        if (!(event.getWhoClicked() instanceof Player)) return;

        ItemStack item = event.getCurrentItem();
        if (item == null) return;
        if (item.getType().equals(Material.STAINED_GLASS_PANE) && item.hasItemMeta()
                && item.getItemMeta().hasDisplayName() && item.getItemMeta().hasLore()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Inventory inv = event.getInventory();
        if (!inv.getTitle().equals(CC.translate("&7EnderChest"))) return;

        UUID uuid = event.getPlayer().getUniqueId();
        UserData userData = main.INSTANCE.getStorage().get(uuid);
        if (userData == null) {
            Map<Integer, String> items = new HashMap<>();
            userData = main.INSTANCE.getStorage().put(uuid, new UserData(uuid, items));
        } else {
            userData.getItems().clear();
        }
        for (int slot = 0; slot < inv.getSize(); slot++) {
            ItemStack item = inv.getItem(slot);
            if (item != null && !item.getType().equals(Material.STAINED_GLASS_PANE) && !item.hasItemMeta() && !item.getItemMeta().hasDisplayName() && !item.getItemMeta().hasLore()) {
                String itemStack = InventoryUtil.itemStackArrayToBase64(item);
                userData.getItems().put(slot, itemStack);
            }
        }
    }
}
