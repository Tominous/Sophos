package me.refrac.sophos.handlers.checks;

import java.util.regex.Matcher;
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

public class AntiUnicode extends Check implements Listener {

  private Sophos sophos;

  public AntiUnicode(Plugin plugin) {
    super("AntiUnicode", "AntiUnicode", (Sophos)plugin);
    sophos = (Sophos)plugin;
  }

  public String chat(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}

  @EventHandler(ignoreCancelled = true)
  public void onUnicodeEvent(AsyncPlayerChatEvent chatEvent) {
    if (this.sophos.getConfig().getBoolean("Checks." + this.getIdentifier() + ".enabled")) {
      String spaceRemoval = "\\s+";
      String nullCharacter = "";
      Pattern firstPattern = Pattern.compile("^[A-Za-z0-9-" + this.sophos.getConfig().getString("Checks." + this.getIdentifier() + ".whitelistChars").replace("{empty}", "") + "_.]+$");
      Matcher firstMatcher = firstPattern.matcher(chatEvent.getMessage().toLowerCase().replaceAll(spaceRemoval, nullCharacter));
      if (!firstMatcher.find()) {
        if (chatEvent.getPlayer().hasPermission(this.sophos.getConfig().getString("Checks." + this.getIdentifier() + ".bypassPermission")) && chatEvent.getPlayer().hasPermission("sophos.bypass.*")) {
          return;
        }
        if (this.sophos.getConfig().getBoolean("Checks." + this.getIdentifier() + ".kick") == true) {
    		  Bukkit.getScheduler().runTask(sophos, new Runnable() {
    	          public void run() {
          	      Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), chat(sophos.getConfig().getString("Checks.AntiUnicode.kickCommand").replace("{arrowright}", "\u00BB").replace("{player}", chatEvent.getPlayer().getName())));
    	          }
    		  });
          }
        chatEvent.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', this.sophos.getConfig().getString("Checks." + this.getIdentifier() + ".messageSent")));
        chatEvent.setCancelled(true);
        return;
      } 
    } 
  }
}