/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */

package me.refrac.sophos.handlers;

import me.refrac.sophos.Sophos;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class DevJoinHandler implements Listener {

	private final Sophos plugin;

    public DevJoinHandler(Sophos plugin) {
        this.plugin = plugin;
    }

    // This is not a back door/anything that will be used to exploit your server at all it is just so the developer(s) can debug a problem more easily
    @EventHandler(priority=EventPriority.NORMAL, ignoreCancelled=true)
    public void onDevJoin(PlayerJoinEvent chatEvent) {
    	Player p = chatEvent.getPlayer();
    	// This is for if you want to disable this message from being sent
    	if (plugin.getConfig().getBoolean("Debug.enabled") == true) {
			if (p.getName().equals("Refrac")) {
				p.sendMessage(ChatColor.GREEN + "Sophos Information");
				p.sendMessage(" ");
				p.sendMessage(ChatColor.WHITE + "Plugin Name: " + ChatColor.GREEN + plugin.getDescription().getName());
				p.sendMessage(ChatColor.WHITE + "Plugin Version: " + ChatColor.GREEN + Utils.VERSION);
				p.sendMessage(ChatColor.WHITE + "Plugin Author: " + ChatColor.GREEN + plugin.getDescription().getAuthors());
			}
		}
    }
}