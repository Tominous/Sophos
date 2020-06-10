package me.refrac.sophos.handlers.checks;

import me.refrac.sophos.Sophos;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.refrac.sophos.handlers.Check;
import org.bukkit.plugin.Plugin;

public class AntiCapslock extends Check implements Listener {

  private Sophos sophos;

  public AntiCapslock(Plugin plugin) {
  	super("AntiCapslock", "AntiCapslock", (Sophos)plugin);
    sophos = (Sophos)plugin;
  }
  
  public String chat(String s) {
	return ChatColor.translateAlternateColorCodes('&', s);
  }

  @EventHandler(ignoreCancelled = true)
  public void onCapslockEvent(AsyncPlayerChatEvent chatEvent) {
    if (this.sophos.getConfig().getBoolean("Checks." + this.getIdentifier() + ".enabled")) {
      if (chatEvent.getPlayer().hasPermission(this.sophos.getConfig().getString("Checks." + this.getIdentifier() + ".bypassPermission")) || chatEvent.getPlayer().hasPermission("sophos.bypass.*")) {
        return;
      }
      int upperChar = 0;
      int lowerChar = 0;
      if (chatEvent.getMessage().toLowerCase().length() >= this.sophos.getConfig().getInt("Checks." + this.getIdentifier() + ".minimumLength")) {
        int number = 0;
        while (number < chatEvent.getMessage().length()) {
          if (Character.isLetter(chatEvent.getMessage().charAt(number))) {
            if (Character.isUpperCase(chatEvent.getMessage().charAt(number))) {
              upperChar++;
            } else {
              lowerChar++;
            } 
          }
          number++;
        } 
        if (upperChar + lowerChar != 0 && 1.0D * upperChar / (upperChar + lowerChar) * 100.0D >= this.sophos.getConfig().getInt("Checks." + this.getIdentifier() + ".percentRequired")) {
          chatEvent.setCancelled(true);
          if (this.sophos.getConfig().getBoolean("Checks." + this.getIdentifier() + ".kick") == true) {
            Bukkit.getScheduler().runTask(sophos, new Runnable() {
              public void run() {
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), chat(sophos.getConfig().getString("Checks.AntiCapslock.kickCommand").replace("{arrowright}", "\u00BB").replace("{player}", chatEvent.getPlayer().getName())));
              }
            });
          }
          }
          chatEvent.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', this.sophos.getConfig().getString("Checks." + this.getIdentifier() + ".messageSent")));
        } 
      } 
    } 
  }