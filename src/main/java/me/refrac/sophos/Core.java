/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */

package me.refrac.sophos;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import me.refrac.sophos.cmds.CMDClearChat;
import me.refrac.sophos.cmds.CMDMutechat;
import me.refrac.sophos.cmds.CMDSCToggle;
import me.refrac.sophos.cmds.CMDSophos;
import me.refrac.sophos.cmds.CMDStaffChat;
import me.refrac.sophos.gui.GUI;
import me.refrac.sophos.handlers.ChatHandler;
import me.refrac.sophos.handlers.Check;
import me.refrac.sophos.handlers.DevJoinHandler;
import me.refrac.sophos.handlers.JoinQuitHandler;
import me.refrac.sophos.handlers.Logger;
import me.refrac.sophos.handlers.UpdateChecker;
import me.refrac.sophos.handlers.checks.AntiAdvertisement;
import me.refrac.sophos.handlers.checks.AntiCapslock;
import me.refrac.sophos.handlers.checks.AntiCommandSpam;
import me.refrac.sophos.handlers.checks.AntiJoinSpam;
import me.refrac.sophos.handlers.checks.AntiSpam;
import me.refrac.sophos.handlers.checks.AntiSwear;
import me.refrac.sophos.handlers.checks.AntiUnicode;
import me.refrac.sophos.handlers.checks.AutoCorrect;
import me.refrac.sophos.handlers.checks.BlockedCommands;
import me.refrac.sophos.handlers.checks.DotEvent;

public class Core extends JavaPlugin
implements Listener {
	
  public static Core plugin;
  
  public String PLUGIN_URL = "https://www.spigotmc.org/resources/55372";
  
  public List<Check> Checks;
    
  public Core() {
    super();
    this.Checks = new ArrayList<>();
  }
  
  @Override
  public void onEnable() {
    plugin = this;
    
    this.registerListener(this);
    
    getHandlers();
    
    getCommand("sophos").setExecutor(new CMDSophos(this));
    getCommand("staffchat").setExecutor(new CMDStaffChat(this));
    getCommand("sctoggle").setExecutor(new CMDSCToggle(this));
    if (this.plugin.getConfig().getBoolean("MuteChat.enabled") == true) {
    	getCommand("mutechat").setExecutor(new CMDMutechat(this));
    }
    if (this.plugin.getConfig().getBoolean("ClearChat.enabled") == true) {
    	getCommand("clearchat").setExecutor(new CMDClearChat(this));
    }
    
    getConfig().options().copyDefaults(true);
    saveDefaultConfig();
    // This update checker is provided by BGHDDevelopment.
    new UpdateChecker(this, 55372).getLatestVersion(version -> {
        if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
            Logger.log(Logger.LogLevel.SUCCESS, "Sophos is up to date!");
        } else {
            Logger.log(Logger.LogLevel.OUTLINE, "-------------------------------------------");
            Logger.log(Logger.LogLevel.WARNING, "Sophos is outdated!");
            Logger.log(Logger.LogLevel.WARNING, "Newest version: " + version);
            Logger.log(Logger.LogLevel.WARNING, "Your version: " + plugin.getDescription().getVersion());
            Logger.log(Logger.LogLevel.WARNING, "Please Update Here: " + PLUGIN_URL);
            Logger.log(Logger.LogLevel.OUTLINE, "-------------------------------------------");
        }
    });
  }
 
  @Override
  public void onDisable() { 
	plugin = null;
  }
  
  public ArrayList<Check> getChecks() {
	return new ArrayList<>(this.Checks);
  }
  
  public void registerListener(Listener listener) {
    this.getServer().getPluginManager().registerEvents(listener, this);
  }
  
  public void getHandlers() {
	this.Checks.add(new AntiAdvertisement(this));
	this.Checks.add(new AntiCapslock(this));
	this.Checks.add(new AntiCommandSpam(this));
	this.Checks.add(new AntiJoinSpam(this));
	this.Checks.add(new AntiSpam(this));
	this.Checks.add(new AntiSwear(this));
	this.Checks.add(new AntiUnicode(this));
	this.Checks.add(new AutoCorrect(this));
	this.Checks.add(new BlockedCommands(this));
	this.Checks.add(new DotEvent(this));
	
	if (this.plugin.getConfig().getBoolean("Messages.enabled") == true) {
		getServer().getPluginManager().registerEvents(new CMDSCToggle(this), this);
	}
	if (this.plugin.getConfig().getBoolean("MuteChat.enabled") == true) {
		getServer().getPluginManager().registerEvents(new CMDMutechat(this), this);
	}
	if (this.plugin.getConfig().getBoolean("Chat.enabled") == true) {
		getServer().getPluginManager().registerEvents(new ChatHandler(this), this);
	}
	if (this.plugin.getConfig().getBoolean("Join-Quit.enabled") == true) {
		getServer().getPluginManager().registerEvents(new JoinQuitHandler(this), this);
	}
	getServer().getPluginManager().registerEvents(new DevJoinHandler(this), this);
	getServer().getPluginManager().registerEvents(new AntiSwear(this), this);
    getServer().getPluginManager().registerEvents(new AntiAdvertisement(this), this);
    getServer().getPluginManager().registerEvents(new AntiCapslock(this), this);
    getServer().getPluginManager().registerEvents(new AntiSpam(this), this);
    getServer().getPluginManager().registerEvents(new AntiCommandSpam(this), this);
    getServer().getPluginManager().registerEvents(new BlockedCommands(this), this);
    getServer().getPluginManager().registerEvents(new AntiJoinSpam(this), this);
    getServer().getPluginManager().registerEvents(new AntiUnicode(this), this);
    getServer().getPluginManager().registerEvents(new DotEvent(this), this);
    getServer().getPluginManager().registerEvents(new AutoCorrect(this), this);
    if (this.plugin.getConfig().getBoolean("GUI.enabled") == true) {
    	getServer().getPluginManager().registerEvents(new GUI(this), this);
    }
  }
}