package me.refrac.sophos.cmds;

import me.refrac.sophos.Sophos;
import me.refrac.sophos.handlers.Utils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

// Created by Refrac/Zachstyles 5/4/20
public class CMDChat implements CommandExecutor, Listener {

    private final Sophos plugin;

    public CMDChat(Sophos plugin) {
        this.plugin = plugin;
    }

    private static boolean isMuted;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        if (args.length == 0) {
            if (!sender.hasPermission("sophos.staff") || !sender.hasPermission("reefcore.admin")) {
                sender.sendMessage(Utils.color(plugin.getMessages().getString("Messages.Chat.no-permission")));
                return false;
            }
            sender.sendMessage(Utils.color("&7&m----------------------------------------------------"));
            sender.sendMessage(" ");
            sender.sendMessage(Utils.color("&e&lChat Help"));
            sender.sendMessage(" ");
            sender.sendMessage(Utils.color("&e/chat - This help page."));
            sender.sendMessage(Utils.color("&e/chat mute - Mutes the server chat."));
            sender.sendMessage(Utils.color("&e/chat clear - Clears the server chat."));
            sender.sendMessage(" ");
            sender.sendMessage(Utils.color("&7&m----------------------------------------------------"));
            return true;
        }
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("mute")) {
                if (!sender.hasPermission("reefcore.staff") || !sender.hasPermission("reefcore.admin")) {
                    sender.sendMessage(Utils.color(plugin.getMessages().getString("Messages.Chat.no-permission")));
                    return false;
                }
                isMuted = !isMuted;
                if (this.plugin.getMessages().getBoolean("Messages.Chat.mutechat_broadcast") == true) {
                    Bukkit.broadcast(Utils.color(isMuted ? this.plugin.getMessages().getString ("Messages.Chat.broadcastMutedSent").replace("{player}", sender.getName()) : this.plugin.getMessages().getString("Messages.Chat.broadcastUnMutedSent").replace("{player}", sender.getName())), "reefcore.staff");
                } else {
                    sender.sendMessage(Utils.color(isMuted ? this.plugin.getMessages().getString("Messages.Chat.chatMutedSent").replace("{player}", sender.getName()) : this.plugin.getMessages().getString("Messages.Chat.chatUnMutedSent").replace("{player}", sender.getName())));
                }
                return true;
            } else if (args[0].equalsIgnoreCase("clear")) {
                if (!sender.hasPermission("reefcore.staff") || !sender.hasPermission("reefcore.admin")) {
                    sender.sendMessage(Utils.color(plugin.getMessages().getString("Messages.Chat.no-permission")));
                    return false;
                }
                Bukkit.broadcastMessage(StringUtils.repeat(" \n", this.plugin.getMessages().getInt("Messages.Chat.lines")));
                if (this.plugin.getMessages().getBoolean("Messages.Chat.clearchat_broadcast") == true) {
                    Bukkit.broadcast(Utils.color(this.plugin.getMessages().getString("Messages.Chat.broadcastClearedSent").replace("{player}", sender.getName())), "reefcore.staff");
                } else {
                    sender.sendMessage(Utils.color(this.plugin.getMessages().getString("Messages.Chat.chatClearedSent").replace("{player}", sender.getName())));
                }
                return true;
            }
            return false;
        }
        return false;
    }

    @EventHandler(ignoreCancelled = true)
    public void onChatEvent(AsyncPlayerChatEvent chatEvent) {
        if (chatEvent.getPlayer().hasPermission(this.plugin.getMessages().getString("Messages.Chat.bypassPermission")) || chatEvent.getPlayer().hasPermission("sophos.bypass.*")) {
            return;
        }
        if (!isMuted) {
            return;
        }
        chatEvent.setCancelled(true);
        chatEvent.getPlayer().sendMessage(Utils.color(this.plugin.getMessages().getString("Messages.Chat.messageSent")));
    }
}