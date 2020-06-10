/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */

package me.refrac.sophos.handlers;

import me.refrac.sophos.Sophos;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

public class ChatHandler implements Listener {

	private Sophos sophos;
	private Plugin plugin;

    public ChatHandler(Plugin plugin) {
        this.plugin = plugin;
        sophos = (Sophos)plugin;
    }

    @EventHandler(ignoreCancelled = true)
    public void onChat(AsyncPlayerChatEvent chatEvent) {
    	if (this.sophos.getMessages().getBoolean("Messages.Chat.chat_enabled") == true) {
            Player player = chatEvent.getPlayer();
            String message = chatEvent.getMessage();
            String format = sophos.getMessages().getString("Messages.Chat.chat_format");

            format = Utils.setupPlaceholderAPI(player, format);
            format = Utils.colorFormat(player, format);
            format = Placeholders.setPlaceholders(player, format);
            format = Utils.color(format);
            format = format.replace("{message}", message);
            format = format.replaceAll("%", "%%");
            format = Utils.replaceAllVariables(player, format);

            chatEvent.setFormat(format);
        }
    }
}