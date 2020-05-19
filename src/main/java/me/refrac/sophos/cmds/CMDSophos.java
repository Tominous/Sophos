/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */

package me.refrac.sophos.cmds;

import me.refrac.sophos.Sophos;
import me.refrac.sophos.handlers.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.refrac.sophos.gui.GUI;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

import static me.refrac.sophos.handlers.Utils.chat;

public class CMDSophos implements CommandExecutor {

    private Sophos plugin;

    public CMDSophos(Sophos plugin) {
        this.plugin = plugin;
    }

    @lombok.SneakyThrows
    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        Player player = (Player) sender;
        if (args.length == 0) {
            if (!(sender instanceof Player)) return false;
            if (!player.hasPermission("sophos.admin")) {
                player.sendMessage(chat(plugin.getConfig().getString("Messages.no-permission")));
                return false;
            }
            if (plugin.getConfig().getBoolean("GUI.enabled") == true) {
                GUI.opensophosMain(player);
            } else {
                player.sendMessage("The GUI is currently disabled redirecting to the help page...");
                wait((long)0.5);
                // Yes i know it's not pretty but it works
                TextComponent mainComponent = new TextComponent("\u00A7c\u00A7l/sophos \u00A77- \u00A7f\u00A7lOpens the GUI.");
                mainComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click me!").create()));
                mainComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/sophos"));
                TextComponent mainComponent1 = new TextComponent ( "\u00A7c\u00A7l/sophos help \u00A77- \u00A7f\u00A7lView this help page.");
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

                player.sendMessage(chat("&7&l&m-------------------------------------------"));
                player.sendMessage("");
                player.sendMessage(chat("&c&lSophos &ev" + Utils.VERSION));
                player.sendMessage(chat("&eby &fRefrac &e& &fAnnaDev"));
                player.sendMessage("");
                player.sendMessage(chat("&e&lStaff Commands"));
                player.sendMessage("");
                player.spigot().sendMessage(mainComponent3);
                player.spigot().sendMessage(mainComponent4);
                player.spigot().sendMessage(mainComponent5);
                player.spigot().sendMessage(mainComponent6);
                player.sendMessage("");
                player.sendMessage(chat("&e&lAdmin Commands"));
                player.spigot().sendMessage(mainComponent);
                player.spigot().sendMessage(mainComponent1);
                player.spigot().sendMessage(mainComponent2);
                player.sendMessage("");
                player.sendMessage(chat("&7&l&m-------------------------------------------"));
            }
            return true;
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("help")) {
                if (!sender.hasPermission("sophos.admin")) {
                    sender.sendMessage(chat(plugin.getConfig().getString("Messages.no-permission")));
                    return false;
                }
                // Yes i know it's not pretty but it works
                TextComponent mainComponent = new TextComponent("\u00A7c\u00A7l/sophos \u00A77- \u00A7f\u00A7lOpens the GUI.");
                mainComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click me!").create()));
                mainComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/sophos"));
                TextComponent mainComponent1 = new TextComponent ( "\u00A7c\u00A7l/sophos help \u00A77- \u00A7f\u00A7lView this help page.");
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

                sender.sendMessage(chat("&7&l&m-------------------------------------------"));
                sender.sendMessage("");
                sender.sendMessage(chat("&c&lSophos &ev" + Utils.VERSION));
                sender.sendMessage(chat("&eby &fRefrac &e& &fAnnaDev"));
                sender.sendMessage("");
                sender.sendMessage(chat("&e&lStaff Commands"));
                sender.sendMessage("");
                ((Player) sender).spigot().sendMessage(mainComponent3);
                ((Player) sender).spigot().sendMessage(mainComponent4);
                ((Player) sender).spigot().sendMessage(mainComponent5);
                ((Player) sender).spigot().sendMessage(mainComponent6);
                sender.sendMessage("");
                sender.sendMessage(chat("&e&lAdmin Commands"));
                ((Player) sender).spigot().sendMessage(mainComponent);
                ((Player) sender).spigot().sendMessage(mainComponent1);
                ((Player) sender).spigot().sendMessage(mainComponent2);
                sender.sendMessage("");
                sender.sendMessage(chat("&7&l&m-------------------------------------------"));
                return true;
            } else if (args[0].equalsIgnoreCase("reload")) {
                if (!sender.hasPermission("sophos.admin")) {
                    sender.sendMessage(chat(plugin.getConfig().getString("Messages.no-permission")));
                    return false;
                }
                plugin.reloadConfig();
                sender.sendMessage(chat("&7Config reloaded."));
                return true;
            } else if (args[0].equalsIgnoreCase("about")) {
                if (!sender.hasPermission("reefcore.admin")) {
                    sender.sendMessage(chat(plugin.getConfig ().getString("Messages.no-permission").replace("&", "§")));
                    return false;
                }
                sender.sendMessage(chat("&7&l&m-------------------------------------------"));
                sender.sendMessage(" ");
                sender.sendMessage(ChatColor.YELLOW + "Created by: " + ChatColor.GREEN + Utils.DEVELOPER_NAME);
                sender.sendMessage(ChatColor.YELLOW + "Version: " + ChatColor.GREEN + Utils.VERSION);
                sender.sendMessage(ChatColor.YELLOW + "Support: " + ChatColor.GREEN + Utils.SUPPORT_URL);
                sender.sendMessage(" ");
                sender.sendMessage(chat("&7&l&m-------------------------------------------"));
                return true;
            }
            return false;
        }
        return false;
    }
}