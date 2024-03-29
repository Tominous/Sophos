package me.refrac.sophos.handlers.checks;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.refrac.sophos.Sophos;
import me.refrac.sophos.handlers.Check;

public class AntiSpam extends Check implements Listener {

  private Sophos sophos;
  private final HashMap<Player, Integer> chatCooldown;
  private final HashMap<Player, Integer> chatCooldown1;
  private final HashMap<Player, BukkitRunnable> cooldownTask;
  private final HashMap<Player, BukkitRunnable> cooldownTask1;
  
  public AntiSpam(Plugin plugin) {
	super("ChatCooldown", "AntiSpam", (Sophos)plugin);
    sophos = (Sophos)plugin;
    this.chatCooldown = new HashMap<Player, Integer>();
    this.chatCooldown1 = new HashMap<Player, Integer>();
    this.cooldownTask = new HashMap<Player, BukkitRunnable>();
    this.cooldownTask1 = new HashMap<Player, BukkitRunnable>();
  }
  
  public String chat(String s) {
	return ChatColor.translateAlternateColorCodes('&', s);
  }

  @EventHandler(ignoreCancelled = true)
  public void onSpamEvent(AsyncPlayerChatEvent chatEvent) {
    if (this.sophos.getConfig().getBoolean("Checks." + this.getIdentifier() + ".enabled")) {
      if (chatEvent.getPlayer().hasPermission(this.sophos.getConfig().getString("Checks." + this.getIdentifier() + ".bypassPermission")) && chatEvent.getPlayer().hasPermission("sophos.bypass.*")) {
        return;
      }
      final Player eventUser = chatEvent.getPlayer();
      if (!chatEvent.getPlayer().hasPermission("sophos.chat.donor")) {
        if (chatEvent.getPlayer().hasPermission(this.sophos.getConfig().getString("Checks." + this.getIdentifier() + ".bypassPermission")) && chatEvent.getPlayer().hasPermission("sophos.bypass.*")) {
          return;
        }
      if (this.chatCooldown.containsKey(eventUser)) {
        chatEvent.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', this.sophos.getConfig().getString("Checks." + this.getIdentifier() + ".cooldownMessage").replace("{time}", String.valueOf(this.chatCooldown.get(eventUser)))));
        chatEvent.setCancelled(true);
        return;
      }
      } else if (chatEvent.getPlayer().hasPermission("sophos.chat.donor")) {
        if (chatEvent.getPlayer().hasPermission(this.sophos.getConfig().getString("Checks." + this.getIdentifier() + ".bypassPermission")) && chatEvent.getPlayer().hasPermission("sophos.bypass.*")) {
          return;
        }
      if (this.chatCooldown1.containsKey(eventUser)) {
          chatEvent.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', this.sophos.getConfig().getString("Checks." + this.getIdentifier() + ".donorCooldownMessage").replace("{time}", String.valueOf(this.chatCooldown1.get(eventUser)))));
          chatEvent.setCancelled(true);
          return;
      }
        if (chatEvent.getPlayer().hasPermission(this.sophos.getConfig().getString("Checks." + this.getIdentifier() + ".bypassPermission")) && chatEvent.getPlayer().hasPermission("sophos.bypass.*")) {
          return;
        }
      this.chatCooldown1.put(eventUser, Integer.valueOf(this.sophos.getConfig().getInt("Checks." + this.getIdentifier() + ".donorDuration")));
      this.cooldownTask1.put(eventUser, new BukkitRunnable() {
            public void run() {
              AntiSpam.this.chatCooldown1.put(eventUser, Integer.valueOf(((Integer)AntiSpam.this.chatCooldown1.get(eventUser)).intValue() - 1));
              if (((Integer)AntiSpam.this.chatCooldown1.get(eventUser)).intValue() == 0) {
                AntiSpam.this.chatCooldown1.remove(eventUser);
                AntiSpam.this.cooldownTask1.remove(eventUser);
                cancel();
              } 
            }
          });
      ((BukkitRunnable)this.cooldownTask1.get(eventUser)).runTaskTimer(this.sophos, 20L, 20L);
      } else if (!chatEvent.getPlayer().hasPermission("sophos.chat.donor")) {
        if (chatEvent.getPlayer().hasPermission(this.sophos.getConfig().getString("Checks." + this.getIdentifier() + ".bypassPermission")) && chatEvent.getPlayer().hasPermission("sophos.bypass.*")) {
          return;
        }
      this.chatCooldown.put(eventUser, Integer.valueOf(this.sophos.getConfig().getInt("Checks." + this.getIdentifier() + ".cooldownDuration")));
      this.cooldownTask.put(eventUser, new BukkitRunnable()
          {
            public void run() {
              AntiSpam.this.chatCooldown.put(eventUser, Integer.valueOf(((Integer)AntiSpam.this.chatCooldown.get(eventUser)).intValue() - 1));
              if (((Integer)AntiSpam.this.chatCooldown.get(eventUser)).intValue() == 0) {
                AntiSpam.this.chatCooldown.remove(eventUser);
                AntiSpam.this.cooldownTask.remove(eventUser);
                cancel();
              } 
            }
          });
      ((BukkitRunnable)this.cooldownTask.get(eventUser)).runTaskTimer(this.sophos, 20L, 20L);
    }
    }
  }
}