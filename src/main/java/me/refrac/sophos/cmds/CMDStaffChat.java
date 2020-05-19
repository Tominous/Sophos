/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */

package me.refrac.sophos.cmds;

import me.refrac.sophos.handlers.Placeholders;
import me.refrac.sophos.handlers.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.base.Joiner;

import me.clip.placeholderapi.PlaceholderAPI;
import me.refrac.sophos.Sophos;

public class CMDStaffChat implements CommandExecutor {
	
	private Sophos plugin;
	
	public CMDStaffChat(Sophos plugin) {
	    this.plugin = plugin;
	}
	
	public String chat(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
		if (!(sender instanceof Player)) return false;
	    Player player = (Player)sender;
	    if (!player.hasPermission("sophos.staffchat.use") && !player.hasPermission("sophos.staff")) {
			player.sendMessage(chat(plugin.getConfig().getString("Messages.no-permission")));
	    	return false;
	    }
	    if (args.length > 0) {
	    String finalString, str = Joiner.on(" ").join(args);
		finalString = plugin.getConfig().getString("Messages.format");

		finalString = Utils.setupPlaceholderAPI(player, finalString);
		finalString = Utils.colorFormat(player, finalString);
		finalString = Placeholders.setPlaceholders(player, finalString);
		finalString = Utils.chat(finalString);
		finalString = finalString.replace("{message}", str);
		finalString = finalString.replaceAll("%", "%%");
		finalString = Utils.replaceAllVariables(player, finalString);

	    for (Player staff : Bukkit.getServer().getOnlinePlayers()) {
            if (staff.hasPermission("sophos.staffchat.use") && staff.hasPermission("sophos.staff")) {
              staff.sendMessage(finalString);
            }
          }
	    } else {
			player.sendMessage(chat("&cUsage: &7/staffchat <message>"));
		}
	    
	    return true;
	}
}