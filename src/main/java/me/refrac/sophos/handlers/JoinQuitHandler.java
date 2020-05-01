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
import org.bukkit.event.player.PlayerQuitEvent;

import me.clip.placeholderapi.PlaceholderAPI;
import me.refrac.sophos.Core;

public class JoinQuitHandler implements Listener {

	private final Core plugin;

    public JoinQuitHandler(Core plugin) {
        this.plugin = plugin;
    }
    
    public String chat(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
    
    @EventHandler(priority=EventPriority.LOWEST, ignoreCancelled=true)
    public void onJoin(PlayerJoinEvent chatEvent) {
    	if (plugin.getConfig().getBoolean("Join-Quit.enabled") == true) {
    	Player p = chatEvent.getPlayer();
    	
    	boolean isUsingPlaceholder = false;
	    if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
	      isUsingPlaceholder = true;
	    }
	    
	    if (this.plugin.getConfig().getBoolean("Join-Quit.enabled") == true) {
    	if (!p.hasPlayedBefore()) {
    		Bukkit.broadcastMessage(chat(isUsingPlaceholder ? PlaceholderAPI.setPlaceholders(p, this.plugin.getConfig().getString("Join-Quit.firstjoin_message").replace("{player}", p.getName()).replace("{displayname}", p.getDisplayName())) : this.plugin.getConfig().getString("Join-Quit.firstjoin_message").replace("{player}", p.getName()).replace("{displayname}", p.getDisplayName())));
    	} else {
    		if (p.hasPermission("sophos.silent.join")) {
    			p.sendMessage(chat(isUsingPlaceholder ? PlaceholderAPI.setPlaceholders(p, this.plugin.getConfig().getString("Join-Quit.silent_join_message").replace("{player}", p.getName()).replace("{displayname}", p.getDisplayName())) : this.plugin.getConfig().getString("Join-Quit.silent_join_message").replace("{player}", p.getName()).replace("{displayname}", p.getDisplayName())));
    			return;
            } else {
            	Bukkit.broadcastMessage(chat(isUsingPlaceholder ? PlaceholderAPI.setPlaceholders(p, this.plugin.getConfig().getString("Join-Quit.join_message").replace("{player}", p.getName()).replace("{displayname}", p.getDisplayName())) : this.plugin.getConfig().getString("Join-Quit.join_message").replace("{player}", p.getName()).replace("{displayname}", p.getDisplayName())));
    	}
    	}
	    }
    }
    }
    
    @EventHandler(priority=EventPriority.LOWEST, ignoreCancelled=true)
    public void onUpdateJoin(final PlayerJoinEvent e) {
        Player p = e.getPlayer();
        // This update checker is provided by BGHDDevelopment.
        if (Core.plugin.getConfig().getBoolean("Update.enabled") == true) {
            if (p.hasPermission("sophos.update")) {
                new UpdateChecker(plugin, 55372).getLatestVersion(version -> {
                    if (!plugin.getDescription().getVersion().equalsIgnoreCase(version)) {
                        p.sendMessage(chat("&7&l&m-------------------------------------------"));
                        p.sendMessage(ChatColor.RED + "Sophos is outdated!");
                        p.sendMessage(ChatColor.RED + "Newest version: " + version);
                        p.sendMessage(ChatColor.RED + "Your version: " + ChatColor.BOLD + plugin.getDescription().getVersion());
                        p.sendMessage(ChatColor.GOLD + "Please Update Here: " + ChatColor.ITALIC + plugin.PLUGIN_URL);
                        p.sendMessage(chat("&7&l&m-------------------------------------------"));
                    }
                });
            }
        }
    }
    
    @EventHandler(priority=EventPriority.LOWEST, ignoreCancelled=true)
    public void onQuit(PlayerQuitEvent chatEvent) {
    	if (plugin.getConfig().getBoolean("Join-Quit.enabled") == true) {
        Player p = chatEvent.getPlayer();
        
        boolean isUsingPlaceholder = false;
	    if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
	      isUsingPlaceholder = true;
	    }
	    	    
        if (this.plugin.getConfig().getBoolean("Join-Quit.enabled") == true) {
        	if (p.hasPermission("sophos.silent.quit")) {
        		p.sendMessage(chat(isUsingPlaceholder ? PlaceholderAPI.setPlaceholders(p, this.plugin.getConfig().getString("Join-Quit.silent_quit_message").replace("{player}", p.getName()).replace("{displayname}", p.getDisplayName())) : this.plugin.getConfig().getString("Join-Quit.silent_quit_message").replace("{player}", p.getName()).replace("{displayname}", p.getDisplayName())));
        		return;
        	} else {
                Bukkit.broadcastMessage(chat(isUsingPlaceholder ? PlaceholderAPI.setPlaceholders(p, this.plugin.getConfig().getString("Join-Quit.quit_message").replace("{player}", p.getName()).replace("{displayname}", p.getDisplayName())) : this.plugin.getConfig().getString("Join-Quit.quit_message").replace("{player}", p.getName()).replace("{displayname}", p.getDisplayName())));
        }
        }
        }
    }
}