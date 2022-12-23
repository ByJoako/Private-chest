package net.helydev.com.commands;

import net.helydev.com.data.FillData;
import net.helydev.com.data.UserData;
import net.helydev.com.main;
import net.helydev.com.utils.CC;
import net.helydev.com.utils.InventoryUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

/**
 * Created by Joako (21/12/2022 | 23:22)
 **/

public class EnderChestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(CC.translate("&cUse command in-game!"));
            return false;
        }

        Player player = (Player) sender;
        Inventory inv = Bukkit.createInventory(null, 9 * 6, CC.translate("&7EnderChest"));
        for (FillData data : main.INSTANCE.getDataList()) {
            if (!player.hasPermission(data.getPermission())) {
                for (int j = data.getSlotStart(); j < data.getSlotAmount(); j++) {
                    inv.setItem(j, data.getItem());
                }
            }
        }
        UserData userData = main.INSTANCE.getStorage().get(player.getUniqueId());
        if (userData != null) {
            for (Map.Entry<Integer, String> entry: userData.getItems().entrySet()) {
                ItemStack itemStack = InventoryUtil.itemStackArrayFromBase64(entry.getValue());
                inv.setItem(entry.getKey(), itemStack);
            }
        }

        player.openInventory(inv);
        return false;
    }
}
