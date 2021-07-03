package net.helydev.com.listener;

import net.helydev.com.EnderChest;
import net.helydev.com.utils.CC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.HashMap;
import java.util.Map;

public class SaveInventory implements Listener {

    public static Map<Player, String>EditChest=new HashMap<>();

    @EventHandler
    public void onCloseInv(InventoryCloseEvent event){
        if(event.getView().getTitle().contains(CC.translate(EnderChest.getInstance().getConfig().getString("Enderchest.6.displayname")))){
            EnderChest.menu.put(event.getPlayer().getName(), event.getInventory().getContents());
            return;
        }
        if(event.getView().getTitle().contains(CC.translate(EnderChest.getInstance().getConfig().getString("Enderchest.5.displayname")))){
            EnderChest.menu.put(event.getPlayer().getName(), event.getInventory().getContents());
            return;
        }
        if(event.getView().getTitle().contains(CC.translate(EnderChest.getInstance().getConfig().getString("Enderchest.4.displayname")))){
            EnderChest.menu.put(event.getPlayer().getName(), event.getInventory().getContents());
            return;
        }
        if(event.getView().getTitle().contains(CC.translate(EnderChest.getInstance().getConfig().getString("Enderchest.3.displayname")))){
            EnderChest.menu.put(event.getPlayer().getName(), event.getInventory().getContents());
            return;
        }
        if(event.getView().getTitle().contains(CC.translate(EnderChest.getInstance().getConfig().getString("Enderchest.2.displayname")))){
            EnderChest.menu.put(event.getPlayer().getName(), event.getInventory().getContents());
            return;
        }
        if(event.getView().getTitle().contains(CC.translate(EnderChest.getInstance().getConfig().getString("Enderchest.1.displayname")))){
            EnderChest.menu.put(event.getPlayer().getName(), event.getInventory().getContents());
            return;
        }
        if(event.getView().getTitle().contains(CC.translate("&ePrivate enderchest &7"))){
            Player player= (Player) event.getPlayer();
            EnderChest.menu.put(EditChest.get(player), event.getInventory().getContents());
            EditChest.remove(player);
        }
    }
}
