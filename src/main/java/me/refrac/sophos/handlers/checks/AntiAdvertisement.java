package me.refrac.sophos.handlers.checks;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.refrac.sophos.Sophos;
import me.refrac.sophos.SophosAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.refrac.sophos.handlers.Check;
import org.bukkit.plugin.Plugin;

public class AntiAdvertisement extends Check implements Listener {
	
    private Sophos sophos;

    public AntiAdvertisement(Plugin plugin) {
    	super("AntiAdvertisement", "AntiAdvertisement", (Sophos)plugin);
        sophos = (Sophos)plugin;
    }
    
    public String chat(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}

    @SuppressWarnings("unused")
    @EventHandler(ignoreCancelled = true)
    public void onAdvertisementEvent(AsyncPlayerChatEvent chatEvent) {
        if (this.sophos.getConfig().getBoolean("Checks." + this.getIdentifier() + ".enabled")) {
            String chatEventMessage;
            if (chatEvent.getPlayer().hasPermission(this.sophos.getConfig().getString("Checks." + this.getIdentifier() + ".bypassPermission")) || chatEvent.getPlayer().hasPermission("sophos.bypass.*")) {
                return;
            }
            String spaceRemoval = "\\s+";
            String nullCharacter = "";
            String strongPattern = "[^a-z0-9 ]";
            String messageSent = chatEventMessage = chatEvent.getMessage().toLowerCase().replaceAll("[^a-z0-9 ]", "").replaceAll(spaceRemoval, "");
            Pattern firstPattern = Pattern.compile("(?i)(((([a-zA-Z0-9-]+\\.)+(gs|ts|adv|no|uk|us|de|eu|com|net|noip|to|gs|me|info|biz|tv|au|tk|dev|la|edu|gov|ly|club))+(\\:[0-9]{2,5})?))");
            Pattern secondPattern = Pattern.compile("(?i)(((([0-9]{1,3}\\.){3}[0-9]{1,3})(\\:[0-9]{2,5})?))");
            Pattern thirdPattern = Pattern.compile("([a-zA-Z0-9\\-]+\\.)+[a-zA-Z]{2,}(:[0-9]{1,5})?");
            Matcher firstMatcher = firstPattern.matcher(chatEvent.getMessage().toLowerCase().replaceAll(spaceRemoval, ""));
            Matcher secondMatcher = secondPattern.matcher(chatEvent.getMessage().toLowerCase().replaceAll(spaceRemoval, ""));
            Matcher thirdMatcher = thirdPattern.matcher(chatEvent.getMessage().toLowerCase().replaceAll(spaceRemoval, ""));
            List whitelistSites = this.sophos.getConfig().getStringList("Checks." + this.getIdentifier() + ".whitelistSites");
            for (Object allowed : whitelistSites) {
                if (!chatEvent.getMessage().contains((CharSequence)allowed)) continue;
                return;
            }
            if (firstMatcher.find()) {
                chatEvent.setCancelled(true);
                if (this.sophos.getMessages().getBoolean("Alerts." + this.getIdentifier() + ".enabled") == true) {
                    if (chatEvent.getPlayer().hasPermission("sophos.alerts")) {
                	    Bukkit.broadcastMessage(chat(this.sophos.getMessages().getString("Alerts." + this.getIdentifier() + ".messageSent").replace("{player}", chatEvent.getPlayer().getName()).replace("{arrowright}", "\u00BB")));
                    }
                }
                if (this.sophos.getConfig().getBoolean("Checks." + this.getIdentifier() + ".kick") == true) {
          		  Bukkit.getScheduler().runTask(sophos, new Runnable() {
          	          public void run() {
                	      Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), chat(sophos.getConfig().getString("Checks.AntiAdvertisement.kickCommand").replace("{arrowright}", "\u00BB").replace("{player}", chatEvent.getPlayer().getName())));
          	          }
          		  });
                }
                chatEvent.getPlayer().sendMessage(chat((String)this.sophos.getConfig().getString("Checks." + this.getIdentifier() + ".messageSent")));
                return;
            }
            if (secondMatcher.find()) {
                chatEvent.setCancelled(true);
                if (this.sophos.getMessages().getBoolean("Alerts." + this.getIdentifier() + ".enabled") == true) {
                    if (chatEvent.getPlayer().hasPermission("sophos.alerts")) {
                    	Bukkit.broadcastMessage(chat(this.sophos.getMessages().getString("Alerts." + this.getIdentifier() + ".messageSent").replace("{player}", chatEvent.getPlayer().getName()).replace("{arrowright}", "\u00BB")));
                    }
                }
                if (this.sophos.getConfig().getBoolean("Checks." + this.getIdentifier() + ".kick") == true) {
          		  Bukkit.getScheduler().runTask(sophos, new Runnable() {
          	          public void run() {
                	      Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), chat(sophos.getConfig().getString("Checks.AntiAdvertisement.kickCommand").replace("{arrowright}", "\u00BB").replace("{player}", chatEvent.getPlayer().getName())));
          	          }
          		  });
                }
                chatEvent.getPlayer().sendMessage(chat((String)this.sophos.getConfig().getString("Checks." + this.getIdentifier() + ".messageSent").replace("{player}", chatEvent.getPlayer().getName())));
                return;
            }
            if (thirdMatcher.find()) {
                chatEvent.setCancelled(true);
                if (this.sophos.getMessages().getBoolean("Alerts." + this.getIdentifier() + ".enabled") == true) {
                    if (chatEvent.getPlayer().hasPermission("sophos.alerts")) {
                    	Bukkit.broadcastMessage(chat(this.sophos.getMessages().getString("Alerts." + this.getIdentifier() + ".messageSent").replace("{player}", chatEvent.getPlayer().getName()).replace("{arrowright}", "\u00BB")));
                    }
                }
                if (this.sophos.getConfig().getBoolean("Checks." + this.getIdentifier() + ".kick") == true) {
          		  Bukkit.getScheduler().runTask(sophos, new Runnable() {
          	          public void run() {
                	      Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), chat(sophos.getConfig().getString("Checks.AntiAdvertisement.kickCommand").replace("{arrowright}", "\u00BB").replace("{player}", chatEvent.getPlayer().getName())));
          	          }
          		  });
                }
                chatEvent.getPlayer().sendMessage(chat((String)this.sophos.getConfig().getString("Checks." + this.getIdentifier() + ".messageSent")));
                return;
            }
            List blockedWordsList = this.sophos.getConfig().getStringList("Checks." + this.getIdentifier() + ".blockedExtensions");
            for (Object blockedWords : blockedWordsList) {
                if (!messageSent.contains((CharSequence)blockedWords)) continue;
                chatEvent.setCancelled(true);
                chatEvent.getPlayer().sendMessage(chat((String)this.sophos.getConfig().getString("Checks." + this.getIdentifier() + ".messageSent")));
                break;
            }
        }
    }
}