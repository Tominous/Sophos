/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */

package me.refrac.sophos.cmds;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.clip.placeholderapi.PlaceholderAPI;
import me.refrac.sophos.Core;
import net.md_5.bungee.api.ChatColor;

public class CMDClearChat implements CommandExecutor {
	
	  private Core plugin;
	  
	  public CMDClearChat(Core plugin) {
	     this.plugin = plugin;
	  }
	  	  
	  public String chat(String s) {
		 return ChatColor.translateAlternateColorCodes('&', s);
	  }
	  
	  @Override
	  public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
		 boolean isUsingPlaceholder = false;
		 if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
			 isUsingPlaceholder = true;
		 }
	     if (!sender.hasPermission("sophos.staff") && !sender.hasPermission("sophos.admin")) {
		     sender.sendMessage(chat(plugin.getConfig().getString("Messages.no-permission")));
		     return false;
		 }
	     Bukkit.broadcastMessage(StringUtils.repeat(" \n", this.plugin.getConfig().getInt("ClearChat.lines")));
		 if (this.plugin.getConfig().getBoolean("ClearChat.broadcast") == true) {
		     Bukkit.broadcastMessage(chat(isUsingPlaceholder ? PlaceholderAPI.setPlaceholders((OfflinePlayer) sender, this.plugin.getConfig().getString("ClearChat.broadcastClearedSent").replace("{player}", sender.getName())) : this.plugin.getConfig().getString("ClearChat.broadcastClearedSent").replace("{player}", sender.getName())));
		 } else {
		     sender.sendMessage(chat(isUsingPlaceholder ? PlaceholderAPI.setPlaceholders((OfflinePlayer) sender, this.plugin.getConfig().getString("ClearChat.chatClearedSent").replace("{player}", sender.getName())) : this.plugin.getConfig().getString("ClearChat.chatClearedSent").replace("{player}", sender.getName())));
		 }
	     return true;
		 }
}