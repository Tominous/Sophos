package me.refrac.sophos.handlers.checks;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.refrac.sophos.Core;
import me.refrac.sophos.handlers.Check;

public class DotEvent extends Check
  implements Listener
{
  private final Core plugin;
  
  public DotEvent(Core plugin) {
	  super("AutoDot", "DotEvent", plugin);
	  this.plugin = plugin;
  }

  @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
  public void onDotEvent(AsyncPlayerChatEvent chatEvent) {
    if (this.plugin.getConfig().getBoolean("Checks." + this.getIdentifier() + ".enabled")) {
      if (chatEvent.getPlayer().hasPermission(this.plugin.getConfig().getString("Checks." + this.getIdentifier() + ".bypassPermission")) && chatEvent.getPlayer().hasPermission("sophos.bypass.*")) {
        return;
      }
      String chatMessage = chatEvent.getMessage();
      String pattern = "(?i)[a-z]";
      String lastChar = chatMessage.substring(chatMessage.length() - 1);
      if (lastChar.matches(pattern)) {
        chatMessage = String.valueOf(chatMessage) + ".";
        chatEvent.setMessage(chatMessage);
      } 
    } 
  }
}