package me.refrac.sophos.handlers.checks;

import java.util.HashMap;
import java.util.Map;

import me.refrac.sophos.Sophos;
import me.refrac.sophos.gui.AntiBotGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

import me.refrac.sophos.handlers.Check;
import org.bukkit.plugin.Plugin;

public class AntiJoinSpam extends Check implements Listener {

  private Sophos sophos;
  private static Map<Player, Location> storedData;

  public AntiJoinSpam(Plugin plugin) {
	super("AntiJoinSpam", "AntiJoinSpam", (Sophos)plugin);
    sophos = (Sophos)plugin;
    this.storedData = new HashMap<>();
  }
  
  @EventHandler(ignoreCancelled = true)
  public void onJoinEvent(PlayerJoinEvent joinEvent) {
    if (this.sophos.getConfig().getBoolean("Checks." + this.getIdentifier() + ".enabled") || joinEvent.getPlayer().hasPermission("sophos.bypass.*")) {
      if (joinEvent.getPlayer().hasPermission(this.sophos.getConfig().getString("Checks." + this.getIdentifier() + ".bypassPermission")) || joinEvent.getPlayer().hasPermission("sophos.bypass.*")) {
        return;
      }
      Player eventUser = joinEvent.getPlayer();
      Location userLocation = eventUser.getLocation();
      if (this.storedData.containsKey(eventUser)) {
        return;
      }
      this.storedData.put(eventUser, userLocation);
      if (this.sophos.getConfig().getBoolean("Checks." + this.getIdentifier() + ".GUI") == true) {
        Bukkit.getScheduler().runTask(sophos, new Runnable() {
          public void run() {
          AntiBotGUI.openAntiBotMain(eventUser);
          }
        });
      }
    }
  }
  
  @EventHandler(ignoreCancelled = true)
  public void onQuitEvent(PlayerQuitEvent quitEvent) {
    if (this.sophos.getConfig().getBoolean("Checks." + this.getIdentifier() + ".enabled") || quitEvent.getPlayer().hasPermission("sophos.bypass.*")) {
      if (quitEvent.getPlayer().hasPermission("Checks." + this.getIdentifier() + ".bypass") || quitEvent.getPlayer().hasPermission("sophos.bypass.*")) {
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

  @EventHandler(ignoreCancelled = true)
  public void onMovementEvent(PlayerMoveEvent movementEvent) {
    if (this.sophos.getConfig().getBoolean("Checks." + this.getIdentifier() + ".enabled")) {
      if (movementEvent.getPlayer().hasPermission(this.sophos.getConfig().getString("Checks." + this.getIdentifier() + ".bypassPermission")) || movementEvent.getPlayer().hasPermission("sophos.bypass.*")) {
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
  @EventHandler(ignoreCancelled = true)
  public void onMovementEvent2(PlayerMoveEvent movementEvent) {
      if (this.sophos.getConfig().getBoolean("Checks." + this.getIdentifier() + ".enabled")) {
        if (this.sophos.getConfig().getBoolean("Checks." + this.getIdentifier() + ".GUI") == true) {
          if (movementEvent.getPlayer().hasPermission("Checks." + this.getIdentifier() + ".bypass") || movementEvent.getPlayer().hasPermission("sophos.bypass.*")) {
              return;
          }
          Player eventUser = movementEvent.getPlayer();
          if (this.storedData.containsKey(eventUser) && !AntiBotGUI.getPassedAntiBot().contains(eventUser)) {
              eventUser.teleport(movementEvent.getFrom());
            Bukkit.getScheduler().runTask(sophos, new Runnable() {
              public void run() {
                AntiBotGUI.openAntiBotMain(eventUser);
              }
            });
          }
      }
    }
  }

  @EventHandler(ignoreCancelled = true)
  public void onChatEvent(AsyncPlayerChatEvent chatEvent) {
    if (this.sophos.getConfig().getBoolean("Checks." + this.getIdentifier() + ".enabled")) {
      if (chatEvent.getPlayer().hasPermission(this.sophos.getConfig().getString("Checks." + this.getIdentifier() + ".bypassPermission")) || chatEvent.getPlayer().hasPermission("sophos.bypass.*")) {
        return;
      }
      Player eventUser = chatEvent.getPlayer();
      if (this.storedData.containsKey(eventUser)) {
        chatEvent.setCancelled(true);
        chatEvent.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', this.sophos.getConfig().getString("Checks." + this.getIdentifier() + ".messageSent")));
      } else {
        return;
      } 
    } 
  }

  @EventHandler(ignoreCancelled = true)
  public void onCommandEvent(PlayerCommandPreprocessEvent commandEvent) {
    if (this.sophos.getConfig().getBoolean("Checks." + this.getIdentifier() + ".enabled")) {
      if (commandEvent.getPlayer().hasPermission("Checks." + this.getIdentifier() + ".bypass") || commandEvent.getPlayer().hasPermission("sophos.bypass.*")) {
        return;
      }
      Player eventUser = commandEvent.getPlayer();
      if (this.storedData.containsKey(eventUser)) {
        commandEvent.setCancelled(true);
        commandEvent.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', this.sophos.getConfig().getString("Checks." + this.getIdentifier() + ".messageSent")));
      } else {
        return;
      }
    }
  }
}