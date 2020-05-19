/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */

package me.refrac.sophos.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.refrac.sophos.Sophos;
import net.md_5.bungee.api.ChatColor;

public class CMDMutechat implements CommandExecutor, Listener {
	
	  private Sophos plugin;
	  
	  public CMDMutechat(Sophos plugin) {
	     this.plugin = plugin;
	  }
	  	  
	  public String chat(String s) {
		 return ChatColor.translateAlternateColorCodes('&', s);
	  }

	  private static boolean isMuted;
	  
	  @Override
	  public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
	     if (!sender.hasPermission("sophos.staff")) {
		     sender.sendMessage(chat(plugin.getConfig().getString("Messages.no-permission")));
		     return false;
		 }
	     isMuted = !isMuted;
		 if (this.plugin.getConfig().getBoolean("MuteChat.broadcast") == true) {
		     Bukkit.broadcastMessage(chat(isMuted ? this.plugin.getConfig().getString("MuteChat.broadcastMutedSent").replace("{player}", sender.getName()) : this.plugin.getConfig().getString("MuteChat.broadcastUnMutedSent").replace("{player}", sender.getName())));
		 } else {
		     sender.sendMessage(chat(isMuted ? this.plugin.getConfig().getString("MuteChat.chatMutedSent").replace("{player}", sender.getName()) : this.plugin.getConfig().getString("MuteChat.chatUnMutedSent").replace("{player}", sender.getName())));
		 }
	     return true;
	  }

	  @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	  public void onChatEvent(AsyncPlayerChatEvent chatEvent) {
	  	 if (this.plugin.getConfig().getBoolean("MuteChat.enabled") == true) {
	  	 	if (chatEvent.getPlayer().hasPermission(this.plugin.getConfig().getString("MuteChat.bypassPermission")) && chatEvent.getPlayer().hasPermission("sophos.bypass.*")) {
	  	 		return;
	  	 	}
	  	 	if (!isMuted) {
	  	 		return;
	  	 	}
	  	 	chatEvent.setCancelled(true);
	  	 	chatEvent.getPlayer().sendMessage(chat(this.plugin.getConfig().getString("MuteChat.messageSent")));
	  	 }
	  }
}