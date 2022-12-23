package net.helydev.com.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by Joako (22/12/2022 | 19:45)
 **/

public class InventoryUtil {

    public static String itemStackArrayToBase64(ItemStack item) {
        if(item == null) return "";

        try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream)) {
            dataOutput.writeObject(item);
            return Base64Coder.encodeLines(outputStream.toByteArray());
        } catch (IOException e) {
            throw new IllegalStateException("Unable to save items.", e);
        }
    }

    public static ItemStack itemStackArrayFromBase64(String data) {
        if(data == null || data.isEmpty()) return new ItemStack(Material.AIR);


        try(ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream)) {
            return (ItemStack) dataInput.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new IllegalStateException("Unable to load items.", e);
        }
    }
}
