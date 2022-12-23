package net.helydev.com;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import net.helydev.com.commands.EnderChestCommand;
import net.helydev.com.data.FillData;
import net.helydev.com.data.UserData;
import net.helydev.com.listeners.EnderChestListener;
import net.helydev.com.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Joako (21/12/2022 | 23:14)
 **/

@Getter
public class main extends JavaPlugin {

    public static main INSTANCE;
    private List<FillData> dataList;
    private Map<UUID, UserData> storage;

    private final Gson GSON = new GsonBuilder().setPrettyPrinting().serializeNulls()
            .enableComplexMapKeySerialization().excludeFieldsWithModifiers(Modifier.TRANSIENT, Modifier.STATIC)
            .create();

    @Override
    public void onEnable() {
        INSTANCE = this;
        this.saveDefaultConfig();
        this.dataList = new ArrayList<>();
        this.storage = new ConcurrentHashMap<>();
        this.loadFill();
        this.loadUserData();
        this.getCommand("enderchest").setExecutor(new EnderChestCommand());
        Bukkit.getPluginManager().registerEvents(new EnderChestListener(), this);
    }

    @Override
    public void onDisable() {
        try {
            this.saveUserData();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        INSTANCE = null;
    }

    public void loadFill() {
        ConfigurationSection section = this.getConfig().getConfigurationSection("enderchest");

        for (String key : section.getKeys(false)) {
            String permission = section.getString(key + ".permission");
            int slotStart = section.getInt(key + ".slot.start") - 1;
            int slotAmount = section.getInt(key + ".slot.amount") + slotStart;
            int glassData = section.getInt(key + ".fill.data");
            String glassDisplayname = section.getString(key + ".fill.displayname");
            List<String> glassLore = section.getStringList(key + ".fill.lore");

            ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) glassData);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(CC.translate(glassDisplayname));
            meta.setLore(CC.translate(glassLore));
            item.setItemMeta(meta);

            this.getDataList().add(new FillData(permission, slotStart, slotAmount, item));
        }
    }

    public void loadUserData() {
        File dir = new File(main.INSTANCE.getDataFolder(), "data");
        if (!dir.exists()) return;

        for (File file : dir.listFiles()) {
            try (FileReader reader = new FileReader(file)) {
                UserData userData = this.getGSON().fromJson(reader, UserData.class);
                this.getStorage().put(userData.getUuid(), userData);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void saveUserData() throws IOException {
        File folder = new File(main.INSTANCE.getDataFolder(), "data");
        if (!folder.exists()) {
            folder.mkdirs();
        }
        for (UserData userData : this.getStorage().values()) {
            File file = new File(folder, userData.getUuid() + ".json");

            if (!file.exists()) {
                file.createNewFile();
            }
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(this.getGSON().toJson(userData, UserData.class));
            }
        }
    }
}
