/*
 *
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 *
 */

package me.refrac.sophos.handlers;

import org.bukkit.entity.Player;

public class Placeholders {

    public static String setPlaceholders(Player player, String placeholders) {
        placeholders = Utils.colorFormat(player, placeholders.replace("{player}", player.getName()));
        placeholders = Utils.colorFormat(player, placeholders.replace("{displayname}", player.getDisplayName()));
        placeholders = Utils.chat(placeholders.replace("{arrowright}", "\u00BB"));
        return placeholders;
    }
}