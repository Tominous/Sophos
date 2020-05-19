/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */

package me.refrac.sophos.cmds;

import java.util.ArrayList;

import me.refrac.sophos.Sophos;
import me.refrac.sophos.handlers.Placeholders;
import me.refrac.sophos.handlers.Utils;
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

public class CMDSCToggle implements CommandExecutor, Listener {
	
	private Sophos plugin;
	  
	public CMDSCToggle(Sophos plugin) {
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
			Player player = (Player)sender;
			if (args.length == 0) {
			if (!player.hasPermission("sophos.staff")) {
				player.sendMessage(chat(plugin.getConfig().getString("Messages.no-permission")));
				return false;
			}
			if (this.toggle.contains(player)) {
				this.toggle.remove(player);
				player.sendMessage(chat( Sophos.plugin.getConfig().getString("Messages.toggleOff")));
				return false;
			}
			this.toggle.add(player);
			player.sendMessage(chat( Sophos.plugin.getConfig().getString("Messages.toggleOn")));
			return true;
	    }
		return false;
	}

	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onChat(AsyncPlayerChatEvent chatEvent) {
		if (this.plugin.getConfig().getBoolean("Messages.enabled") == true) {
			Player player = chatEvent.getPlayer();

			String finalString = plugin.getConfig().getString("Messages.format");

			finalString = Utils.setupPlaceholderAPI(player, finalString);
			finalString = Utils.colorFormat(player, finalString);
			finalString = Placeholders.setPlaceholders(player, finalString);
			finalString = Utils.chat(finalString);
			finalString = finalString.replace("{message}", chatEvent.getMessage());
			finalString = finalString.replaceAll("%", "%%");
			finalString = Utils.replaceAllVariables(player, finalString);

			if (this.toggle.contains(player)) {

				chatEvent.setCancelled(true);

				for (Player staff : Bukkit.getServer().getOnlinePlayers()) {
					if (staff.hasPermission("sophos.staff")) {
						staff.sendMessage(finalString);
					}
				}
			}
		}
	}
}