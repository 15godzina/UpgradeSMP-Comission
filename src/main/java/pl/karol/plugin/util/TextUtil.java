package pl.karol.plugin.util;

import org.bukkit.ChatColor;

public class TextUtil {
    public static String translate(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
