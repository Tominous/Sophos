package me.refrac.sophos.handlers.checks;

import java.util.HashMap;
import java.util.Map;

import me.refrac.sophos.Sophos;
import me.refrac.sophos.gui.AntiBotGUI;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

import me.refrac.sophos.handlers.Check;

public class AntiJoinSpam extends Check implements Listener {

  private final Sophos plugin;
  private static Map<Player, Location> storedData;

  public AntiJoinSpam(Sophos plugin) {
	super("AntiJoinSpam", "AntiJoinSpam", plugin);
    this.plugin = plugin;
    this.storedData = new HashMap<>();
  }
  
  @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
  public void onJoinEvent(PlayerJoinEvent joinEvent) {
    if (this.plugin.getConfig().getBoolean("Checks." + this.getIdentifier() + ".enabled")) {
      if (joinEvent.getPlayer().hasPermission(this.plugin.getConfig().getString("Checks." + this.getIdentifier() + ".bypassPermission"))) {
        return;
      }
      Player eventUser = joinEvent.getPlayer();
      Location userLocation = eventUser.getLocation();
      if (this.storedData.containsKey(eventUser)) {
        return;
      }
      this.storedData.put(eventUser, userLocation);
    }
  }
  
  @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
  public void onQuitEvent(PlayerQuitEvent quitEvent) {
    if (this.plugin.getConfig().getBoolean("Checks." + this.getIdentifier() + ".enabled")) {
      if (quitEvent.getPlayer().hasPermission("Checks." + this.getIdentifier() + ".bypass")) {
        return;
      }
      Player eventUser = quitEvent.getPlayer();
      if (this.storedData.containsKey(eventUser)) {
        this.storedData.remove(eventUser);
      } else {
        return;
      } 
    } 
  }
  
  @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
  public void onMovementEvent(PlayerMoveEvent movementEvent) {
    if (this.plugin.getConfig().getBoolean("Checks." + this.getIdentifier() + ".enabled")) {
      if (movementEvent.getPlayer().hasPermission("Checks." + this.getIdentifier() + ".bypass") && movementEvent.getPlayer().hasPermission("sophos.bypass.*")) {
        return;
      }
      Player eventUser = movementEvent.getPlayer();
      Location storedLocation = (Location)this.storedData.get(eventUser);
      if (this.storedData.containsKey(eventUser) && eventUser.getLocation().distance(storedLocation) >= 1.0D) {
        this.storedData.remove(eventUser);
      }
    } 
  }

  // AntiBotGUI movement event
  @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
  public void onMovementEvent2(PlayerMoveEvent movementEvent) {
      if (this.plugin.getConfig().getBoolean("Checks." + this.getIdentifier() + ".enabled")) {
        if (this.plugin.getConfig().getBoolean("Checks." + this.getIdentifier() + ".GUI") == true) {
          if (movementEvent.getPlayer().hasPermission("Checks." + this.getIdentifier() + ".bypass") && movementEvent.getPlayer().hasPermission("sophos.bypass.*")) {
              return;
          }
          Player eventUser = movementEvent.getPlayer();
          if (this.storedData.containsKey(eventUser) && !AntiBotGUI.getPassedAntiBot().contains(eventUser)) {
              eventUser.teleport(movementEvent.getFrom());
              AntiBotGUI.openAntiBotMain(eventUser);
          }
      }
    }
  }

  @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
  public void onChatEvent(AsyncPlayerChatEvent chatEvent) {
    if (this.plugin.getConfig().getBoolean("Checks." + this.getIdentifier() + ".enabled")) {
      if (chatEvent.getPlayer().hasPermission("Checks." + this.getIdentifier() + ".bypass") && chatEvent.getPlayer().hasPermission("sophos.bypass.*")) {
        return;
      }
      Player eventUser = chatEvent.getPlayer();
      if (this.storedData.containsKey(eventUser)) {
        chatEvent.setCancelled(true);
        chatEvent.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("Checks." + this.getIdentifier() + ".messageSent")));
      } else {
        return;
      } 
    } 
  }

  @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
  public void onCommandEvent(PlayerCommandPreprocessEvent commandEvent) {
    if (this.plugin.getConfig().getBoolean("Checks." + this.getIdentifier() + ".enabled")) {
      if (commandEvent.getPlayer().hasPermission("Checks." + this.getIdentifier() + ".bypass") && commandEvent.getPlayer().hasPermission("sophos.bypass.*")) {
        return;
      }
      Player eventUser = commandEvent.getPlayer();
      if (this.storedData.containsKey(eventUser)) {
        commandEvent.setCancelled(true);
        commandEvent.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("Checks." + this.getIdentifier() + ".messageSent")));
      } else {
        return;
      }
    }
  }
}