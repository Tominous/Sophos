/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */

package me.refrac.sophos.handlers;

import me.refrac.sophos.Sophos;
import me.refrac.sophos.gui.AntiBotGUI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitHandler implements Listener {

	private final Sophos plugin;

    public JoinQuitHandler(Sophos plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority=EventPriority.NORMAL, ignoreCancelled=true)
    public void onJoin(PlayerJoinEvent chatEvent) {
    	if (plugin.getConfig().getBoolean("Join-Quit.enabled") == true) {
			Player player = chatEvent.getPlayer();

			chatEvent.setJoinMessage(null);

			if (!player.hasPlayedBefore()) {
				Bukkit.broadcastMessage(Utils.colorFormat(player, this.plugin.getConfig().getString("Join-Quit.firstjoin_message").replace("{player}", player.getName()).replace("{displayname}", player.getDisplayName())));
			} else {
				if (player.hasPermission("sophos.silent.join")) {
					player.sendMessage(Utils.colorFormat(player, this.plugin.getConfig().getString("Join-Quit.silent_join_message").replace("{player}", player.getName()).replace("{displayname}", player.getDisplayName())));
					return;
				} else {
					Bukkit.broadcastMessage(Utils.colorFormat(player, this.plugin.getConfig().getString("Join-Quit.join_message").replace("{player}", player.getName()).replace("{displayname}", player.getDisplayName())));
			}
			}
    }
    }
    
    @EventHandler(priority=EventPriority.NORMAL, ignoreCancelled=true)
    public void onQuit(PlayerQuitEvent chatEvent) {
    	if (plugin.getConfig().getBoolean("Join-Quit.enabled") == true) {
			Player player = chatEvent.getPlayer();

			chatEvent.setQuitMessage(null);

			if (player.hasPermission("sophos.silent.quit")) {
				player.sendMessage(Utils.colorFormat(player, this.plugin.getConfig().getString("Join-Quit.silent_quit_message").replace("{player}", player.getName()).replace("{displayname}", player.getDisplayName())));
				return;
			} else {
				Bukkit.broadcastMessage(Utils.colorFormat(player, this.plugin.getConfig().getString("Join-Quit.quit_message").replace("{player}", player.getName()).replace("{displayname}", player.getDisplayName())));
		    }
        }
    }
}