/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */

package me.refrac.sophos.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.refrac.sophos.Core;
import me.refrac.sophos.gui.GUI;
import me.refrac.sophos.handlers.UpdateChecker;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class CMDSophos
  implements CommandExecutor {
  
  private Core plugin;
  
  public CMDSophos(Core plugin) {
    this.plugin = plugin;
  }
  
  public String chat(String s) {
	return ChatColor.translateAlternateColorCodes('&', s);
  }
  
  @lombok.SneakyThrows
  @Override
  public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
    Player p = (Player)sender;
    if (args.length == 0) {
      if (!(sender instanceof Player)) return false;
          if (!p.hasPermission("sophos.admin")) {
        	  p.sendMessage(chat(plugin.getConfig().getString("Messages.no-permission")));
        	  return false;
          }
          if (plugin.getConfig().getBoolean("GUI.enabled") == true) {
          GUI.opensophosMain(p);
          } else {
        	  p.sendMessage("The GUI is currently disabled redirecting to the help page...");
        	  wait((long)0.5);
        	  // Yes i know it's not pretty but it works
              TextComponent mainComponent = new TextComponent("\u00A7c\u00A7l/sophos \u00A77- \u00A7f\u00A7lOpens the GUI.");
              mainComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click me!").create()));
              mainComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/sophos"));
              TextComponent mainComponent1 = new TextComponent("\u00A7c\u00A7l/sophos help \u00A77- \u00A7f\u00A7lView this help page.");
              mainComponent1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click me!").create()));
              mainComponent1.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/sophos help"));
              TextComponent mainComponent2 = new TextComponent("\u00A7c\u00A7l/sophos reload \u00A77- \u00A7f\u00A7lReloads the config file.");
              mainComponent2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click me!").create()));
              mainComponent2.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/sophos reload"));
              TextComponent mainComponent3 = new TextComponent("\u00A7c\u00A7l/sc <message> \u00A77- \u00A7f\u00A7lStaffChat command.");
              mainComponent3.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click me!").create()));
              mainComponent3.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/sc"));
              TextComponent mainComponent4 = new TextComponent("\u00A7c\u00A7l/sctoggle \u00A77- \u00A7f\u00A7lStaffChat toggle command.");
              mainComponent4.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click me!").create()));
              mainComponent4.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/sctoggle"));
              TextComponent mainComponent5 = new TextComponent("\u00A7c\u00A7l/mutechat \u00A77- \u00A7f\u00A7lMuteChat command.");
              mainComponent5.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click me!").create()));
              mainComponent5.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/mutechat"));
              TextComponent mainComponent6 = new TextComponent("\u00A7c\u00A7l/clearchat \u00A77- \u00A7f\u00A7lClearChat command.");
              mainComponent6.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click me!").create()));
              mainComponent6.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/clearchat"));
              
              p.sendMessage(chat("&7&l&m-------------------------------------------"));
              p.sendMessage("");
              p.sendMessage(chat("&c&lSophos &fv" + this.plugin.getDescription().getVersion().toString()));
              p.sendMessage(chat("&7by &fRefrac &7& &fAnnaDev"));
              p.sendMessage("");
              ((Player)sender).spigot().sendMessage(mainComponent);
              ((Player)sender).spigot().sendMessage(mainComponent1);
              ((Player)sender).spigot().sendMessage(mainComponent2);
              ((Player)sender).spigot().sendMessage(mainComponent5);
              ((Player)sender).spigot().sendMessage(mainComponent6);
              if (plugin.getConfig().getBoolean("Messages.enabled") == true) {
              p.sendMessage("");
              p.sendMessage(chat("&c&lStaffChat Commands"));
              p.sendMessage("");
              ((Player)sender).spigot().sendMessage(mainComponent3);
              ((Player)sender).spigot().sendMessage(mainComponent4);
              }
              p.sendMessage("");
              p.sendMessage(chat("&7&l&m-------------------------------------------"));
          }
          return true;
    } else if (args.length == 1) {
        if (args[0].equalsIgnoreCase ( "help" )) {
            if (!sender.hasPermission ( "sophos.admin" )) {
                sender.sendMessage(chat(plugin.getConfig().getString("Messages.no-permission")));
                return false;
            }
            // Yes i know it's not pretty but it works
            TextComponent mainComponent = new TextComponent("\u00A7c\u00A7l/sophos \u00A77- \u00A7f\u00A7lOpens the GUI.");
            mainComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click me!").create()));
            mainComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/sophos"));
            TextComponent mainComponent1 = new TextComponent("\u00A7c\u00A7l/sophos help \u00A77- \u00A7f\u00A7lView this help page.");
            mainComponent1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT , new ComponentBuilder("Click me!").create()));
            mainComponent1.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/sophos help"));
            TextComponent mainComponent2 = new TextComponent("\u00A7c\u00A7l/sophos reload \u00A77- \u00A7f\u00A7lReloads the config file.");
            mainComponent2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click me!").create()));
            mainComponent2.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/sophos reload"));
            TextComponent mainComponent3 = new TextComponent("\u00A7c\u00A7l/sc <message> \u00A77- \u00A7f\u00A7lStaffChat command.");
            mainComponent3.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click me!").create()));
            mainComponent3.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/sc"));
            TextComponent mainComponent4 = new TextComponent("\u00A7c\u00A7l/sctoggle \u00A77- \u00A7f\u00A7lStaffChat toggle command.");
            mainComponent4.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click me!").create()));
            mainComponent4.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/sctoggle"));
            TextComponent mainComponent5 = new TextComponent("\u00A7c\u00A7l/mutechat \u00A77- \u00A7f\u00A7lMuteChat command.");
            mainComponent5.setHoverEvent(new HoverEvent ( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click me!").create()));
            mainComponent5.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/mutechat"));
            TextComponent mainComponent6 = new TextComponent("\u00A7c\u00A7l/clearchat \u00A77- \u00A7f\u00A7lClearChat command.");
            mainComponent6.setHoverEvent(new HoverEvent ( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click me!").create()));
            mainComponent6.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/clearchat"));

            sender.sendMessage(chat("&7&l&m-------------------------------------------" ) );
            sender.sendMessage("");
            sender.sendMessage(chat("&c&lSophos &fv" + this.plugin.getDescription().getVersion()));
            sender.sendMessage(chat("&7by &fRefrac &7& &fAnnaDev"));
            sender.sendMessage("");
            ((Player)sender).spigot().sendMessage(mainComponent);
            ((Player)sender).spigot().sendMessage(mainComponent1);
            ((Player)sender).spigot().sendMessage(mainComponent2);
            ((Player)sender).spigot().sendMessage(mainComponent5);
            ((Player)sender).spigot().sendMessage(mainComponent6);
            if (plugin.getConfig().getBoolean("Messages.enabled") == true) {
                sender.sendMessage("");
                sender.sendMessage(chat("&c&lStaffChat Commands"));
                sender.sendMessage("");
                ((Player)sender).spigot().sendMessage(mainComponent3);
                ((Player)sender).spigot().sendMessage(mainComponent4);
            }
            sender.sendMessage("");
            sender.sendMessage(chat("&7&l&m-------------------------------------------" ));
            return true;
        }
        if (args[0].equalsIgnoreCase("reload")) {
        if (!sender.hasPermission("sophos.admin")) {
            sender.sendMessage(chat(plugin.getConfig().getString("Messages.no-permission")));
            return false;
        }
        Core.plugin.reloadConfig ();
        sender.sendMessage(chat("&7Config reloaded."));
        return true;
    }
      if (args[0].equalsIgnoreCase("update")) {
    	  if (!sender.hasPermission("sophos.update")) {
    		  sender.sendMessage(chat(plugin.getConfig().getString("Messages.no-permission")));
    		  return false;
    	  }
    	  // This update checker is provided by BGHDDevelopment.
    	  new UpdateChecker(plugin, 55372).getLatestVersion(version -> {
    	        if (plugin.getDescription().getVersion().equalsIgnoreCase(version)) {
    	            sender.sendMessage(ChatColor.GREEN + "Sophos is up to date!");
    	        } else {
    	        	sender.sendMessage(chat("&7&l&m-------------------------------------------"));
                    sender.sendMessage(ChatColor.RED + "Sophos is outdated!");
                    sender.sendMessage(ChatColor.RED + "Newest version: " + version);
                    sender.sendMessage(ChatColor.RED + "Your version: " + ChatColor.BOLD + plugin.getDescription().getVersion());
                    sender.sendMessage(ChatColor.GOLD + "Please Update Here: " + ChatColor.ITALIC + plugin.PLUGIN_URL);
                    sender.sendMessage(chat("&7&l&m-------------------------------------------"));
    	        }
    	    });
    	  return true;
      }
      }
	return false;
}
}