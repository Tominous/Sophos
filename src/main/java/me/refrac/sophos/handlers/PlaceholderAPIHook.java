package me.refrac.sophos.handlers;

import java.util.List;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

public class PlaceholderAPIHook {

    public static String setPlaceholders(Player player, String message) { return message = PlaceholderAPI.setPlaceholders(player, message); }

    public static List<String> setPlaceholders(Player player, List<String> message) { return message = PlaceholderAPI.setPlaceholders(player, message); }
}