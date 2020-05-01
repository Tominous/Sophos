/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */

package me.refrac.sophos.handlers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.clip.placeholderapi.PlaceholderAPI;
import me.refrac.sophos.Core;

public class ChatHandler implements Listener {

	private final Core plugin;

    public ChatHandler(Core plugin) {
        this.plugin = plugin;
    }
    
    public String chat(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
    
    @EventHandler(priority=EventPriority.LOWEST, ignoreCancelled=true)
    public void onChat(AsyncPlayerChatEvent chatEvent) {
    	if (plugin.getConfig().getBoolean("Chat.enabled") == true) {
        Player p = chatEvent.getPlayer();
		
        boolean isUsingPlaceholder = false;
	    if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
	      isUsingPlaceholder = true;
	    }
	    
	    String chatFormat = chat(isUsingPlaceholder ? PlaceholderAPI.setPlaceholders(p, this.plugin.getConfig().getString("Chat.format").replace("{player}", p.getName()).replace("{displayname}", p.getDisplayName()).replace("{message}", chatEvent.getMessage()).replace("{arrowright}", "\u00BB")) : this.plugin.getConfig().getString("Chat.format").replace("{player}", p.getName()).replace("{displayname}", p.getDisplayName()).replace("{message}", chatEvent.getMessage()).replace("{arrowright}", "\u00BB"));
	    
	    chatEvent.setFormat(String.format(chatFormat));
        }
    }
}