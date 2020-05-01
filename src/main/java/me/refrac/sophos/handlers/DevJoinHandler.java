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
import org.bukkit.event.player.PlayerJoinEvent;

import me.refrac.sophos.Core;

public class DevJoinHandler implements Listener {

	private final Core plugin;

    public DevJoinHandler(Core plugin) {
        this.plugin = plugin;
    }
    
    public String chat(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
    
    // This is not a back door/anything that will be used to exploit your server at all it is just so the developer(s) can debug a problem more easily
    @EventHandler(priority=EventPriority.LOWEST, ignoreCancelled=true)
    public void onDevJoin(PlayerJoinEvent chatEvent) {
    	Player p = chatEvent.getPlayer();
    	if (p.getName().equalsIgnoreCase("Refrac")) {
        	p.sendMessage(ChatColor.GREEN + "Sophos Information");
        	p.sendMessage(chat("&r"));
    		p.sendMessage(ChatColor.WHITE + "Plugin Name: " + ChatColor.GREEN + plugin.getDescription().getName().toString());
    		p.sendMessage(ChatColor.WHITE + "Plugin Version: " + ChatColor.GREEN + plugin.getDescription().getVersion().toString());
    		p.sendMessage(ChatColor.WHITE + "Plugin Author: " + ChatColor.GREEN + plugin.getDescription().getAuthors().toString());
    		p.sendMessage(ChatColor.WHITE + "Server Version/Software: " + ChatColor.GREEN + Bukkit.getVersion().toString());
        }
    }
}