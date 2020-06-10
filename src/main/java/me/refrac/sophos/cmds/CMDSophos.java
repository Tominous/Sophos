/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */

package me.refrac.sophos.cmds;

import me.refrac.sophos.Sophos;
import me.refrac.sophos.gui.SophosGUI;
import me.refrac.sophos.handlers.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.plugin.Plugin;

public class CMDSophos implements CommandExecutor {

    private Sophos sophos;

    public CMDSophos(Plugin plugin) {
        sophos = (Sophos)plugin;
    }

    @lombok.SneakyThrows
    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        Player player = (Player) sender;
        if (args.length == 0) {
            sender.sendMessage(Utils.color(Utils.PREFIX + "&eCurrently running &cSophos v" + Utils.VERSION + " &eby &c" + Utils.DEVELOPER_NAME + " &euse &c/sophos help &eto view the help page."));
            return true;
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("help")) {
                if (!sender.hasPermission("sophos.staff")) {
                    sender.sendMessage(Utils.color(sophos.getMessages().getString("Messages.Chat.no-permission")));
                    return false;
                }
                this.sendHelpMessage((Player)sender);
                return true;
            } else if (args[0].equalsIgnoreCase("reload")) {
                if (!sender.hasPermission("sophos.admin")) {
                    sender.sendMessage(Utils.color(sophos.getMessages().getString("Messages.Chat.no-permission")));
                    return false;
                }
                sophos.reloadConfig();
                sophos.reloadMessages();
                sender.sendMessage(Utils.color("&7Config files successfully reloaded."));
                return true;
            } else if (args[0].equalsIgnoreCase("about")) {
                if (!sender.hasPermission("reefcore.admin")) {
                    sender.sendMessage(Utils.color(sophos.getMessages().getString("Messages.Chat.no-permission").replace("&", "§")));
                    return false;
                }
                sender.sendMessage(Utils.color("&7&l&m-------------------------------------------"));
                sender.sendMessage(" ");
                sender.sendMessage(ChatColor.YELLOW + "Created by: " + ChatColor.RED + Utils.DEVELOPER_NAME);
                sender.sendMessage(ChatColor.YELLOW + "Version: " + ChatColor.RED + Utils.VERSION);
                sender.sendMessage(ChatColor.YELLOW + "Support: " + ChatColor.RED + Utils.SUPPORT_URL);
                sender.sendMessage(" ");
                sender.sendMessage(Utils.color("&7&l&m-------------------------------------------"));
                return true;
            } else if (args[0].equalsIgnoreCase("gui")) {
                if (!player.hasPermission("sophos.admin")) {
                    player.sendMessage(Utils.color(sophos.getMessages().getString("Messages.Chat.no-permission")));
                    return false;
                }
                if (!(sender instanceof Player)) return false;
                if (sophos.getConfig().getBoolean("Sophos_GUI.enabled") == true) {
                    SophosGUI.opensophosMain(player);
                }
            }
            return false;
        }
        return false;
    }

    private void sendHelpMessage(Player player) {
        // Yes i know it's not pretty but it works
        TextComponent mainComponent = new TextComponent("\u00A7c\u00A7l/sophos \u00A77- \u00A7f\u00A7lShows the current version.");
        mainComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click me!").create()));
        mainComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/sophos"));
        TextComponent mainComponent1 = new TextComponent ( "\u00A7c\u00A7l/sophos help \u00A77- \u00A7f\u00A7lView this help page.");
        mainComponent1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click me!").create()));
        mainComponent1.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/sophos help"));
        TextComponent mainComponent2 = new TextComponent("\u00A7c\u00A7l/sophos reload \u00A77- \u00A7f\u00A7lReloads the config file.");
        mainComponent2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click me!").create()));
        mainComponent2.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/sophos reload"));
        TextComponent mainComponent3 = new TextComponent ( "\u00A7c\u00A7l/sophos about \u00A77- \u00A7f\u00A7lView the about page.");
        mainComponent3.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click me!").create()));
        mainComponent3.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/sophos about"));
        TextComponent mainComponent4 = new TextComponent ( "\u00A7c\u00A7l/sophos gui \u00A77- \u00A7f\u00A7lOpens the GUI.");
        mainComponent4.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click me!").create()));
        mainComponent4.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/sophos gui"));
        TextComponent mainComponent5 = new TextComponent("\u00A7c\u00A7l/chat \u00A77- \u00A7f\u00A7lView the Chat manager help page.");
        mainComponent5.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click me!").create()));
        mainComponent5.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/chat"));
        TextComponent mainComponent6 = new TextComponent("\u00A7c\u00A7l/sc <message> \u00A77- \u00A7f\u00A7lStaffChat command.");
        mainComponent6.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click me!").create()));
        mainComponent6.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/sc"));
        TextComponent mainComponent7 = new TextComponent("\u00A7c\u00A7l/sctoggle \u00A77- \u00A7f\u00A7lStaffChat toggle command.");
        mainComponent7.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click me!").create()));
        mainComponent7.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/sctoggle"));

        player.sendMessage(Utils.color("&7&m----------------------------------------------------"));
        player.sendMessage("");
        player.sendMessage(Utils.color("&c&lSophos &cv" + Utils.VERSION));
        player.sendMessage(Utils.color("&eby &c" + Utils.DEVELOPER_NAME));
        player.sendMessage("");
        player.sendMessage(Utils.color("&e&lStaff help menu"));
        player.sendMessage("");
        player.spigot().sendMessage(mainComponent6);
        player.spigot().sendMessage(mainComponent7);
        player.spigot().sendMessage(mainComponent5);
        player.sendMessage("");
        player.sendMessage(Utils.color("&e&lAdmin help menu"));
        player.spigot().sendMessage(mainComponent);
        player.spigot().sendMessage(mainComponent1);
        player.spigot().sendMessage(mainComponent2);
        player.spigot().sendMessage(mainComponent3);
        player.spigot().sendMessage(mainComponent4);
        player.sendMessage("");
        player.sendMessage(Utils.color("&7&m----------------------------------------------------"));
    }
}