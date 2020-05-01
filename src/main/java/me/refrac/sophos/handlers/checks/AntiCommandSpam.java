package me.refrac.sophos.handlers.checks;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.scheduler.BukkitRunnable;

import me.refrac.sophos.Core;
import me.refrac.sophos.handlers.Check;

public class AntiCommandSpam extends Check
  implements Listener
{
  private final Core plugin;
  private final HashMap<Player, Integer> commandCooldown;
  private final HashMap<Player, BukkitRunnable> cooldownTask;
  
  public AntiCommandSpam(Core plugin) {
	super("CommandCooldown", "AntiCommandSpam", plugin);
    this.plugin = plugin;
    this.commandCooldown = new HashMap<Player, Integer>();
    this.cooldownTask = new HashMap<Player, BukkitRunnable>();
  }
  
  public String chat(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
  
  @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
  public void onSpamEvent(PlayerCommandPreprocessEvent commandEvent) {
    if (this.plugin.getConfig().getBoolean("Checks." + this.getIdentifier() + ".enabled")) {
      if (commandEvent.getPlayer().hasPermission(this.plugin.getConfig().getString("Checks." + this.getIdentifier() + ".bypassPermission")) && commandEvent.getPlayer().hasPermission("sophos.bypass.*")) {
        return;
      }
      final Player eventUser = commandEvent.getPlayer();
      if (this.commandCooldown.containsKey(eventUser)) {
        commandEvent.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("Checks." + this.getIdentifier() + ".cooldownMessage").replace("{time}", String.valueOf(this.commandCooldown.get(eventUser)))));
        commandEvent.setCancelled(true);
        return;
      } 
      this.commandCooldown.put(eventUser, Integer.valueOf(this.plugin.getConfig().getInt("Checks." + this.getIdentifier() + ".cooldownDuration")));
      this.cooldownTask.put(eventUser, new BukkitRunnable()
          {
            public void run() {
              AntiCommandSpam.this.commandCooldown.put(eventUser, Integer.valueOf(((Integer)AntiCommandSpam.this.commandCooldown.get(eventUser)).intValue() - 1));
              if (((Integer)AntiCommandSpam.this.commandCooldown.get(eventUser)).intValue() == 0) {
                AntiCommandSpam.this.commandCooldown.remove(eventUser);
                AntiCommandSpam.this.cooldownTask.remove(eventUser);
                cancel();
              } 
            }
          });
      ((BukkitRunnable)this.cooldownTask.get(eventUser)).runTaskTimer(this.plugin, 20L, 20L);
    } 
  }
}