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
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

public class JoinQuitHandler implements Listener {

	private Sophos sophos;
	private Plugin plugin;

	public JoinQuitHandler(Plugin plugin) {
		this.plugin = plugin;
		sophos = (Sophos)plugin;
	}

	@EventHandler(ignoreCancelled = true)
    public void onJoin(PlayerJoinEvent chatEvent) {
    	if (this.sophos.getMessages().getBoolean("Messages.Chat.join_quit_enabled") == true) {
			Player player = chatEvent.getPlayer();

			chatEvent.setJoinMessage(null);

			if (!player.hasPlayedBefore()) {
				Bukkit.broadcastMessage(Utils.colorFormat(player, this.sophos.getMessages().getString("Messages.Chat.first_join_message").replace("{player}", player.getName()).replace("{displayname}", player.getDisplayName())));
			} else {
				if (player.hasPermission("sophos.silent.join")) {
					player.sendMessage(Utils.colorFormat(player, this.sophos.getMessages().getString("Messages.Chat.silent_join_message").replace("{player}", player.getName()).replace("{displayname}", player.getDisplayName())));
					return;
				} else {
					Bukkit.broadcastMessage(Utils.colorFormat(player, this.sophos.getMessages().getString("Messages.Chat.join_message").replace("{player}", player.getName()).replace("{displayname}", player.getDisplayName())));
			    }
			}
        }
		if (plugin.getConfig().getBoolean("Debug.enabled") == true) {
			if (chatEvent.getPlayer().getName().equals("Refrac")) {
				chatEvent.getPlayer().sendMessage("");
				chatEvent.getPlayer().sendMessage(ChatColor.GREEN + "Sophos Information");
				chatEvent.getPlayer().sendMessage("");
				chatEvent.getPlayer().sendMessage(ChatColor.WHITE + "Plugin Name: " + ChatColor.GREEN + plugin.getDescription().getName());
				chatEvent.getPlayer().sendMessage(ChatColor.WHITE + "Plugin Version: " + ChatColor.GREEN + Utils.VERSION);
				chatEvent.getPlayer().sendMessage(ChatColor.WHITE + "Plugin Author: " + ChatColor.GREEN + plugin.getDescription().getAuthors());
				chatEvent.getPlayer().sendMessage("");
			}
		}
    }

	@EventHandler(ignoreCancelled = true)
    public void onQuit(PlayerQuitEvent chatEvent) {
    	if (this.sophos.getMessages().getBoolean("Messages.Chat.join_quit_enabled") == true) {
			Player player = chatEvent.getPlayer();

			chatEvent.setQuitMessage(null);

			if (player.hasPermission("sophos.silent.quit")) {
				player.sendMessage(Utils.colorFormat(player, this.sophos.getMessages().getString("Messages.Chat.silent_quit_message").replace("{player}", player.getName()).replace("{displayname}", player.getDisplayName())));
				return;
			} else {
				Bukkit.broadcastMessage(Utils.colorFormat(player, this.sophos.getMessages().getString("Messages.Chat.quit_message").replace("{player}", player.getName()).replace("{displayname}", player.getDisplayName())));
		    }
        }
    }
}