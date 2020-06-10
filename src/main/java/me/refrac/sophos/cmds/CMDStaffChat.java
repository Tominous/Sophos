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

import me.refrac.sophos.Sophos;
import org.bukkit.plugin.Plugin;

public class CMDStaffChat implements CommandExecutor {
	
	private Sophos sophos;

	public CMDStaffChat(Plugin plugin) {
		sophos = (Sophos)plugin;
	}
	
	public String chat(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
		if (!(sender instanceof Player)) return false;
	    Player player = (Player)sender;
	    if (!player.hasPermission("sophos.staff")) {
			player.sendMessage(chat(sophos.getMessages().getString("Messages.Chat.no-permission")));
	    	return false;
	    }
	    if (args.length < 1) {
			player.sendMessage(chat("&cUsage: &7/staffchat <message>"));
		}
	    if (args.length > 0) {
	    String finalString, str = Joiner.on(" ").join(args);
		finalString = sophos.getMessages().getString("Messages.Chat.staffchat_format");

		finalString = Utils.setupPlaceholderAPI(player, finalString);
		finalString = Utils.colorFormat(player, finalString);
		finalString = Placeholders.setPlaceholders(player, finalString);
		finalString = Utils.color(finalString);
		finalString = finalString.replace("{message}", str);
		finalString = finalString.replaceAll("%", "%%");
		finalString = Utils.replaceAllVariables(player, finalString);

	    for (Player staff : Bukkit.getServer().getOnlinePlayers()) {
            if (staff.hasPermission("sophos.staff")) {
              staff.sendMessage(finalString);
            }
          }
	    }
	    return true;
	}
}