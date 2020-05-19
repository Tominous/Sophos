/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */

package me.refrac.sophos.handlers;

import me.refrac.sophos.Sophos;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.clip.placeholderapi.PlaceholderAPI;

public class ChatHandler implements Listener {

	private final Sophos plugin;

    public ChatHandler(Sophos plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority=EventPriority.NORMAL, ignoreCancelled=true)
    public void onChat(AsyncPlayerChatEvent chatEvent) {
    	if (plugin.getConfig().getBoolean("Chat.enabled") == true) {
            Player player = chatEvent.getPlayer();

            String chatFormat = plugin.getConfig().getString("Chat.format");

            chatFormat = Utils.setupPlaceholderAPI(player, chatFormat);
            chatFormat = Utils.colorFormat(player, chatFormat);
            chatFormat = Placeholders.setPlaceholders(player, chatFormat);
            chatFormat = Utils.chat(chatFormat);
            chatFormat = chatFormat.replace("{message}", chatEvent.getMessage());
            chatFormat = chatFormat.replaceAll("%", "%%");
            chatFormat = Utils.replaceAllVariables(player, chatFormat);

            chatEvent.setFormat(chatFormat);
        }
    }
}