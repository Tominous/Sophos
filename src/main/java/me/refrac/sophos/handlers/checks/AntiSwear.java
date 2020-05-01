package me.refrac.sophos.handlers.checks;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.refrac.sophos.Core;
import me.refrac.sophos.handlers.Check;

public class AntiSwear extends Check
  implements Listener
{
  private final Core plugin;
  
  public AntiSwear(Core plugin) {
  	super("AntiSwear", "AntiSwear", plugin);
      this.plugin = plugin;
  }

  public String chat(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
  
  @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
  public void onSwearEvent(AsyncPlayerChatEvent chatEvent) {
    String strongPattern = "[^a-z ]";
    String spaceRemoval = "\\s+";
    String nullCharacter = "";
    String chatEventMessage = chatEvent.getMessage().toLowerCase().replaceAll(strongPattern, nullCharacter).replaceAll(spaceRemoval, nullCharacter);
    List blockedWordsList = this.plugin.getConfig().getStringList("Checks." + this.getIdentifier() + ".blockedWords");
    List whitelistedWordsList = this.plugin.getConfig().getStringList("Checks." + this.getIdentifier() + ".whitelistedWords");
    String messageSent = chatEventMessage;
    if (this.plugin.getConfig().getBoolean("Checks." + this.getIdentifier() + ".enabled")) {
      if (chatEvent.getPlayer().hasPermission(this.plugin.getConfig().getString("Checks." + this.getIdentifier() + ".bypassPermission")) && chatEvent.getPlayer().hasPermission("sophos.bypass.*")) {
        return;
      }
      if (plugin.getConfig().getBoolean("Checks." + this.getIdentifier() + ".caseSensitive")) {
      for (Object allowedWords : whitelistedWordsList) {
          if (!messageSent.equalsIgnoreCase((String)allowedWords))
            continue;  return;
      } 
      for (Object blockedWords : blockedWordsList) {
        if (!messageSent.equalsIgnoreCase((String)blockedWords))
          continue;  chatEvent.setCancelled(true);
          if (this.plugin.getConfig().getBoolean("Alerts." + this.getIdentifier() + ".enabled") == true) {
              if (chatEvent.getPlayer().hasPermission("sophos.alerts")) {
          	    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("Alerts." + this.getIdentifier() + ".messageSent").replace("{player}", chatEvent.getPlayer().getName()).replace("{arrowright}", "\u00BB")));
              }
          }
          if (this.plugin.getConfig().getBoolean("Checks." + this.getIdentifier() + ".kick") == true) {
      		  Bukkit.getScheduler().runTask(plugin, new Runnable() {
      	          public void run() {
            	      Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), chat(Core.plugin.getConfig().getString("Checks.AntiSwear.kickCommand").replace("{arrowright}", "\u00BB").replace("{player}", chatEvent.getPlayer().getName())));
      	          }
      		  });
            }
        chatEvent.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("Checks." + this.getIdentifier() + ".messageSent")));
      }
    }
      else {
          for (Object allowedWords : whitelistedWordsList) {
              if (!messageSent.contentEquals((CharSequence)allowedWords))
                continue;  return;
          } 
          for (Object blockedWords : blockedWordsList) {
            if (!messageSent.contentEquals((CharSequence)blockedWords))
              continue;  chatEvent.setCancelled(true);
              if (this.plugin.getConfig().getBoolean("Alerts." + this.getIdentifier() + ".enabled") == true) {
                  if (chatEvent.getPlayer().hasPermission("sophos.alerts")) {
              	    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("Alerts." + this.getIdentifier() + ".messageSent").replace("{player}", chatEvent.getPlayer().getName()).replace("{arrowright}", "\u00BB")));
                  }
              }
              if (this.plugin.getConfig().getBoolean("Checks." + this.getIdentifier() + ".kick") == true) {
          		  Bukkit.getScheduler().runTask(plugin, new Runnable() {
          	          public void run() {
                	      Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), chat(Core.plugin.getConfig().getString("Checks.AntiSwear.kickCommand").replace("{arrowright}", "\u00BB").replace("{player}", chatEvent.getPlayer().getName())));
          	          }
          		  });
                }
            chatEvent.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("Checks." + this.getIdentifier() + ".messageSent")));
          }
        }
  }
  }
}