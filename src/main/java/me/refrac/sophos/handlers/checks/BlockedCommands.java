package me.refrac.sophos.handlers.checks;

import java.util.List;

import me.refrac.sophos.Sophos;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import me.refrac.sophos.handlers.Check;
import org.bukkit.plugin.Plugin;

public class BlockedCommands extends Check implements Listener {

  private Sophos sophos;

  public BlockedCommands(Plugin plugin) {
    super("CommandBlocker", "BlockedCommands", (Sophos)plugin);
    sophos = (Sophos)plugin;
  }


  @EventHandler(ignoreCancelled = true)
  public void onBlockedCommandEvent(PlayerCommandPreprocessEvent commandEvent) {
    if (this.sophos.getConfig().getBoolean("Checks." + this.getIdentifier() + ".enabled")) {
      if (commandEvent.getPlayer().hasPermission(this.sophos.getConfig().getString("Checks." + this.getIdentifier() + ".bypassPermission")) || commandEvent.getPlayer().hasPermission("sophos.bypass.*")) {
        return;
      }
      List<String> disableCommands = this.sophos.getConfig().getStringList("Checks." + this.getIdentifier() + ".disabledCommands");
      for (Object message : disableCommands) {
        if (!commandEvent.getMessage().equalsIgnoreCase("/" + message))
          continue; 
        if (this.sophos.getConfig().getBoolean("Checks." + this.getIdentifier() + ".kick") == true) {
        	commandEvent.getPlayer().kickPlayer(this.sophos.getConfig().getString("Checks." + this.getIdentifier() + ".kickMessage"));
        }
        commandEvent.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', this.sophos.getConfig().getString("Checks." + this.getIdentifier() + ".disabledCommand")));
        commandEvent.setCancelled(true);
      } 
    } 
  }
}