package net.helydev.com.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Joako (21/12/2022 | 23:20)
 **/

public class CC {

    public static String translate(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }


    public static List<String> translate(List<String> message) {
        return message.stream().map(CC::translate).collect(Collectors.toList());
    }

    public static void logConsole(String message) {
        Bukkit.getConsoleSender().sendMessage(CC.translate("&c[&4Log&c] " + message));
    }
}
