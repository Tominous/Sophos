/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */

package me.refrac.sophos.cmds;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.refrac.sophos.Core;

public class CMDSophosreload
  implements CommandExecutor {
  
  private Core plugin;
  
  public CMDSophosreload(Core plugin) {
    this.plugin = plugin;
  }
  
  public String chat(String s) {
	return ChatColor.translateAlternateColorCodes('&', s);
  }
  
  @Override  
  public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
	  if (!sender.hasPermission("sophos.admin")) {
		  sender.sendMessage(chat(plugin.getConfig().getString("Messages.no-permission")));
          return false;
      }
      Core.plugin.reloadConfig();
      sender.sendMessage(chat("&7Config reloaded."));
      return true;
      }
}