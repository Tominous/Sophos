/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */

package me.refrac.sophos.cmds;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.base.Joiner;

import me.clip.placeholderapi.PlaceholderAPI;
import me.refrac.sophos.Core;

public class CMDStaffChat implements CommandExecutor {
	
	private Core plugin;
	
	public CMDStaffChat(Core plugin) {
	    this.plugin = plugin;
	}
	
	public String chat(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
		if (plugin.getConfig().getBoolean("Messages.enabled") == true) {
		if (!(sender instanceof Player)) return false;
		boolean isUsingPlaceholder = false;
	    if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
	      isUsingPlaceholder = true;
	    }
	    Player p = (Player)sender;
	    if (!p.hasPermission("sophos.staffchat.use") && !p.hasPermission("sophos.staff")) {
	    	p.sendMessage(chat(plugin.getConfig().getString("Messages.no-permission")));
	    	return false;
	    }
	    if (args.length >= 0) {
	    String finalString, str = Joiner.on(" ").join(args);
	    String format = chat(isUsingPlaceholder ? PlaceholderAPI.setPlaceholders(p, Core.plugin.getConfig().getString("Messages.format").replace("{player}", p.getName()).replace("{displayname}", p.getDisplayName()).replace("{message}", str).replace("{arrowright}", "\u00BB")) : Core.plugin.getConfig().getString("Messages.format").replace("{player}", p.getName()).replace("{message}", str).replace("{arrowright}", "\u00BB"));
	    
	    finalString = format;
	    
	    for (Player staff : Bukkit.getServer().getOnlinePlayers()) {
            if (staff.hasPermission("sophos.staffchat.use") && staff.hasPermission("sophos.staff")) {
              staff.sendMessage(finalString);
            }
          }
	    } else {
	    	p.sendMessage(chat("&cInvalid arguments! /staffchat <message> or /sc <message>"));
	    	return false;
	    }
	    
	    return true;
	  }
		return false;
	}
}