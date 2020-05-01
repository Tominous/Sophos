/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */

package me.refrac.sophos.cmds;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.clip.placeholderapi.PlaceholderAPI;
import me.refrac.sophos.Core;

public class CMDSCToggle implements CommandExecutor, Listener {
	
	private Core plugin;
	  
	public CMDSCToggle(Core plugin) {
	    this.plugin = plugin;
	    this.toggle = new ArrayList<>();
	}
	
	public String chat(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	
	private ArrayList<Player> toggle;
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
		if (!(sender instanceof Player)) return false;
	    Player p = (Player)sender;
	    if (args.length == 0) {
	    if (!p.hasPermission("sophos.staffchat.use") && !p.hasPermission("sophos.staff")) {
	    	p.sendMessage(chat(plugin.getConfig().getString("Messages.no-permission")));
	    	return false;
	    } 
	    if (this.toggle.contains(p)) {
	    	this.toggle.remove(p);
	    	p.sendMessage(chat(Core.plugin.getConfig().getString("Messages.toggleOff")));
	    	return false;
	    } else {
	    	this.toggle.add(p);
	    	p.sendMessage(chat(Core.plugin.getConfig().getString("Messages.toggleOn")));
	    	return true;
	    }    
	    }
		return false;
	}

	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
	public void onChat(AsyncPlayerChatEvent chatEvent) {
		Player p = chatEvent.getPlayer ();

		boolean isUsingPlaceholder = false;
		if (Bukkit.getPluginManager ().isPluginEnabled ( "PlaceholderAPI" )) {
			isUsingPlaceholder = true;
		}

		String format = chat ( isUsingPlaceholder ? PlaceholderAPI.setPlaceholders ( p , Core.plugin.getConfig ().getString ( "Messages.format" ).replace ( "{player}" , p.getName () ).replace ( "{displayname}" , p.getDisplayName () ).replace ( "{message}" , chatEvent.getMessage () ).replace ( "{arrowright}" , "\u00BB" ) ) : Core.plugin.getConfig ().getString ( "Messages.format" ).replace ( "{player}" , p.getName () ).replace ( "{message}" , chatEvent.getMessage () ).replace ( "{arrowright}" , "\u00BB" ) );

		if (this.toggle.contains ( p )) {

			chatEvent.setCancelled ( true );

			for (Player staff : Bukkit.getServer ().getOnlinePlayers ()) {
				if (staff.hasPermission ( "sophos.staffchat.use" ) && staff.hasPermission ( "sophos.staff" )) {
					staff.sendMessage ( format );
				}
			}
		}
	}
}