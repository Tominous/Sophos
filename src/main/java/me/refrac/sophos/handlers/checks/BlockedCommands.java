package me.refrac.sophos.handlers.checks;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import me.refrac.sophos.Core;
import me.refrac.sophos.handlers.Check;

public class BlockedCommands extends Check
  implements Listener
{
  private final Core plugin;
  
  public BlockedCommands(Core plugin) {
	  	super("CommandBlocker", "BlockedCommands", plugin);
	      this.plugin = plugin;
}

  
  @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
  public void onBlockedCommandEvent(PlayerCommandPreprocessEvent commandEvent) {
    if (this.plugin.getConfig().getBoolean("Checks." + this.getIdentifier() + ".enabled")) {
      if (commandEvent.getPlayer().hasPermission(this.plugin.getConfig().getString("Checks." + this.getIdentifier() + ".bypassPermission")) && commandEvent.getPlayer().hasPermission("sophos.bypass.*")) {
        return;
      }
      List<String> disableCommands = this.plugin.getConfig().getStringList("Checks." + this.getIdentifier() + ".disabledCommands");
      for (Object message : disableCommands) {
        if (!commandEvent.getMessage().equalsIgnoreCase("/" + message))
          continue; 
        if (this.plugin.getConfig().getBoolean("Checks." + this.getIdentifier() + ".kick") == true) {
        	commandEvent.getPlayer().kickPlayer(this.plugin.getConfig().getString("Checks." + this.getIdentifier() + ".kickMessage"));
        }
        commandEvent.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("Checks." + this.getIdentifier() + ".disabledCommand")));
        commandEvent.setCancelled(true);
      } 
    } 
  }
}