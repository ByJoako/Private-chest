package net.helydev.com;

import net.helydev.com.command.EnderchestCommand;
import net.helydev.com.listener.SaveInventory;
import net.helydev.com.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnderChest extends JavaPlugin {

    public static EnderChest instance;
    public static Map<String, ItemStack[]>menu= new HashMap<>();

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(CC.translate("&4Plugin create by Hely Development"));
        instance=this;
        this.saveDefaultConfig();
        this.getServer().getPluginManager().registerEvents(new SaveInventory(), this);
        this.getCommand("enderchest").setExecutor(new EnderchestCommand());
        if(this.getConfig().contains("data")){
            Bukkit.getConsoleSender().sendMessage(CC.translate("&6loading cache file..."));
            this.RestoreMenu();
            this.getConfig().set("data", null);
            this.saveConfig();
            Bukkit.getConsoleSender().sendMessage(CC.translate("&aFiles uploaded successfully."));
        }
    }

    @Override
    public void onDisable() {
        if(!menu.isEmpty()){
            this.SaveMenu();
        }
        instance=null;
    }

    public void SaveMenu(){
        for(Map.Entry<String, ItemStack[]> entry: menu.entrySet()){
            this.getConfig().set("data."+entry.getKey(), entry.getValue());
        }
        this.saveConfig();
    }

    public void RestoreMenu(){
        this.getConfig().getConfigurationSection("data").getKeys(false).forEach(key ->{
            @SuppressWarnings("unchecked")
            ItemStack[] content = ((List<ItemStack>)this.getConfig().get("data."+key)).toArray(new ItemStack[0]);
            menu.put(key, content);
        });
    }

    public static EnderChest getInstance() {
        return instance;
    }
}
