package me.refrac.sophos.handlers.checks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.refrac.sophos.Core;
import me.refrac.sophos.handlers.Check;


public class AntiCapslock extends Check
  implements Listener
{
  private final Core plugin;
  
  public AntiCapslock(Core plugin) {
  	super("AntiCapslock", "AntiCapslock", plugin);
      this.plugin = plugin;
  }
  
  public String chat(String s) {
	return ChatColor.translateAlternateColorCodes('&', s);
  }

  @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
  public void onCapslockEvent(AsyncPlayerChatEvent chatEvent) {
    if (this.plugin.getConfig().getBoolean("Checks." + this.getIdentifier() + ".enabled")) {
      if (chatEvent.getPlayer().hasPermission(this.plugin.getConfig().getString("Checks." + this.getIdentifier() + ".bypassPermission")) && chatEvent.getPlayer().hasPermission("sophos.bypass.*")) {
        return;
      }
      int upperChar = 0;
      int lowerChar = 0;
      if (chatEvent.getMessage().toLowerCase().length() >= this.plugin.getConfig().getInt("Checks." + this.getIdentifier() + ".minimumLength")) {
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
        if (upperChar + lowerChar != 0 && 1.0D * upperChar / (upperChar + lowerChar) * 100.0D >= this.plugin.getConfig().getInt("Checks." + this.getIdentifier() + ".percentRequired")) {
          chatEvent.setCancelled(true);
          if (this.plugin.getConfig().getBoolean("Checks." + this.getIdentifier() + ".kick") == true) {
          	Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), chat(this.plugin.getConfig().getString("Checks." + this.getIdentifier() + ".kickCommand").replace("{arrowright}", "\u00BB").replace("{player}", chatEvent.getPlayer().getName())));
          }
          chatEvent.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("Checks." + this.getIdentifier() + ".messageSent")));
        } 
      } 
    } 
  }
}