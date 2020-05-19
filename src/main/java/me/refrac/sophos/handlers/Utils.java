/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */

package me.refrac.sophos.handlers;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

// Created by Refrac/Zachstyles 5/9/20
public class Utils {

    protected static PlaceholderAPIHook placeholderApi;

    public static boolean isPlaceholderAPIEnabled() { return (placeholderApi != null); }

    public static String setupPlaceholderAPI(Player player , String message) {
        String placeholders = message;
        if (isPlaceholderAPIEnabled() && PlaceholderAPI.containsPlaceholders(placeholders)) {
            placeholders = PlaceholderAPIHook.setPlaceholders(player, placeholders);
        }
        return placeholders;
    }

    public static String replaceAllVariables(Player player, String message) {
        return colorFormat(player, message);
    }

    public static String chat(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static String colorFormat(Player player, String message) {
        if (isPlaceholderAPIEnabled()) {
            return ChatColor.translateAlternateColorCodes('&', PlaceholderAPIHook.setPlaceholders(player, message));
        }
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String DEVELOPER_NAME = "Refrac & AnnaDev";

    public static String SUPPORT_URL = "https://discord.io/RefracDev";

    public static String VERSION = "2.2.6";

}