package me.refrac.sophos.handlers.checks;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.refrac.sophos.Sophos;
import me.refrac.sophos.handlers.Check;

public class AntiCommandSpam extends Check implements Listener {

  private Sophos sophos;
  private final HashMap<Player, Integer> commandCooldown;
  private final HashMap<Player, BukkitRunnable> cooldownTask;
  
  public AntiCommandSpam(Plugin plugin) {
	super("CommandCooldown", "AntiCommandSpam", (Sophos)plugin);
    sophos = (Sophos)plugin;
    this.commandCooldown = new HashMap<>();
    this.cooldownTask = new HashMap<>();
  }
  
  public String chat(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}

  @EventHandler(ignoreCancelled = true)
  public void onSpamEvent(PlayerCommandPreprocessEvent commandEvent) {
    if (this.sophos.getConfig().getBoolean("Checks." + this.getIdentifier() + ".enabled")) {
      if (commandEvent.getPlayer().hasPermission(this.sophos.getConfig().getString("Checks." + this.getIdentifier() + ".bypassPermission")) || commandEvent.getPlayer().hasPermission("sophos.bypass.*")) {
        return;
      }
      final Player eventUser = commandEvent.getPlayer();
      if (this.commandCooldown.containsKey(eventUser)) {
        commandEvent.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', this.sophos.getConfig().getString("Checks." + this.getIdentifier() + ".cooldownMessage").replace("{time}", String.valueOf(this.commandCooldown.get(eventUser)))));
        commandEvent.setCancelled(true);
        return;
      } 
      this.commandCooldown.put(eventUser, Integer.valueOf(this.sophos.getConfig().getInt("Checks." + this.getIdentifier() + ".cooldownDuration")));
      this.cooldownTask.put(eventUser, new BukkitRunnable()
          {
            public void run() {
              AntiCommandSpam.this.commandCooldown.put(eventUser, Integer.valueOf((AntiCommandSpam.this.commandCooldown.get(eventUser)).intValue() - 1));
              if ((AntiCommandSpam.this.commandCooldown.get(eventUser)).intValue() == 0) {
                AntiCommandSpam.this.commandCooldown.remove(eventUser);
                AntiCommandSpam.this.cooldownTask.remove(eventUser);
                cancel();
              } 
            }
          });
      (this.cooldownTask.get(eventUser)).runTaskTimer(this.sophos, 20L, 20L);
    } 
  }
}