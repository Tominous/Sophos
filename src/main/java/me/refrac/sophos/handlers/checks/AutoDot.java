package me.refrac.sophos.handlers.checks;

import me.refrac.sophos.Sophos;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.refrac.sophos.handlers.Check;
import org.bukkit.plugin.Plugin;

public class AutoDot extends Check implements Listener {

  private Sophos sophos;

  public AutoDot(Plugin plugin) {
    super("AutoDot", "AutoDot", (Sophos)plugin);
    sophos = (Sophos)plugin;
  }

  @EventHandler(ignoreCancelled = true)
  public void onDotEvent(AsyncPlayerChatEvent chatEvent) {
    if (this.sophos.getConfig().getBoolean("Checks." + this.getIdentifier() + ".enabled")) {
      if (chatEvent.getPlayer().hasPermission(this.sophos.getConfig().getString("Checks." + this.getIdentifier() + ".bypassPermission")) || chatEvent.getPlayer().hasPermission("sophos.bypass.*")) {
        return;
      }
      String chatMessage = chatEvent.getMessage();
      String pattern = "(?i)[a-z]";
      String lastChar = chatMessage.substring(chatMessage.length() - 1);
      if (lastChar.matches(pattern)) {
        chatMessage = chatMessage + ".";
        chatEvent.setMessage(chatMessage);
      } 
    } 
  }
}