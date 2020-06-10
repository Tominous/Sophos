package me.refrac.sophos.handlers.checks;

import java.util.List;
import java.util.regex.Pattern;

import me.refrac.sophos.Sophos;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.refrac.sophos.handlers.Check;
import org.bukkit.plugin.Plugin;

public class AntiSwear extends Check implements Listener {

  private Sophos sophos;

  public AntiSwear(Plugin plugin) {
  	super("AntiSwear", "AntiSwear", (Sophos)plugin);
    sophos = (Sophos)plugin;
  }

  public String chat(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}

  @EventHandler(ignoreCancelled = true)
  public void onSwearEvent(AsyncPlayerChatEvent chatEvent) {
    if (this.sophos.getConfig().getBoolean("Checks." + this.getIdentifier() + ".enabled")) {
        String strongPattern = "[^a-z ]";
        String spaceRemoval = "\\s+";
        String nullCharacter = "";
        String chatEventMessage = chatEvent.getMessage().toLowerCase().replaceAll(strongPattern , nullCharacter).replaceAll(spaceRemoval, nullCharacter);
        List<String> blockedWordsList = this.sophos.getConfig().getStringList("Checks." + this.getIdentifier() + ".blockedWords");
        List<String> whitelistedWordsList = this.sophos.getConfig().getStringList("Checks." + this.getIdentifier() + ".whitelistedWords");
        String messageSent = chatEventMessage;
        if (chatEvent.getPlayer().hasPermission(this.sophos.getConfig().getString("Checks." + this.getIdentifier() + ".bypassPermission")) && chatEvent.getPlayer().hasPermission("sophos.bypass.*")) {
          return;
        }
        for (Object allowedWords : whitelistedWordsList) {
            if (!messageSent.equalsIgnoreCase((String)allowedWords))
              continue;  return;
        }
        for (Object blockedWords : blockedWordsList) {
          if (!messageSent.contains((String)blockedWords))
            continue;  chatEvent.setCancelled(true);
            if (this.sophos.getMessages().getBoolean("Alerts." + this.getIdentifier() + ".enabled") == true) {
                if (chatEvent.getPlayer().hasPermission("sophos.alerts")) {
                  Bukkit.broadcastMessage(chat(this.sophos.getMessages().getString("Alerts." + this.getIdentifier() + ".messageSent").replace("{player}", chatEvent.getPlayer().getName()).replace("{arrowright}", "\u00BB")));
                }
            }
            if (this.sophos.getConfig().getBoolean("Checks." + this.getIdentifier() + ".kick") == true) {
                Bukkit.getScheduler().runTask(sophos, new Runnable() {
                    public void run() {
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), chat(sophos.getConfig().getString("Checks.AntiSwear.kickCommand").replace("{arrowright}", "\u00BB").replace("{player}", chatEvent.getPlayer().getName())));
                    }
                });
              }
          chatEvent.getPlayer().sendMessage(chat(this.sophos.getConfig().getString("Checks." + this.getIdentifier() + ".messageSent")));
      }
    }
  }
}