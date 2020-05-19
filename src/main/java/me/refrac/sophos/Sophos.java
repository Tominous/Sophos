/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */

package me.refrac.sophos;

import java.util.ArrayList;
import java.util.List;

import me.refrac.sophos.handlers.*;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import me.refrac.sophos.cmds.*;
import me.refrac.sophos.gui.*;
import me.refrac.sophos.handlers.checks.*;

public class Sophos extends JavaPlugin implements Listener {
	
  public static Sophos plugin;

  public List<Check> Checks;
    
  public Sophos() {
    super();
    this.Checks = new ArrayList<>();
  }

  private void addChecks() {
    this.Checks.add(new AntiAdvertisement(this));
    this.Checks.add(new AntiCapslock(this));
    this.Checks.add(new AntiCommandSpam(this));
    this.Checks.add(new AntiJoinSpam(this));
    this.Checks.add(new AntiSpam(this));
    this.Checks.add(new AntiSwear(this));
    this.Checks.add(new AntiUnicode(this));
    this.Checks.add(new AutoCorrect(this));
    this.Checks.add(new BlockedCommands(this));
    this.Checks.add(new AutoDot(this));
  }
  
  @Override
  public void onEnable() {
    // Plugin startup logic
    long startTiming = System.currentTimeMillis();
    Logger.INFO.out("Enabling Sophos...");

    new SophosAPI(this);

    Logger.NONE.out("");
    Logger.NONE.out(ChatColor.LIGHT_PURPLE + "   ____    ___    ____    _   _    ___     ____  ");
    Logger.NONE.out(ChatColor.LIGHT_PURPLE + "  / ___|  / _    |  _    | | | |  / _     / ___| ");
    Logger.NONE.out(ChatColor.LIGHT_PURPLE + "    ___  | | | | | |_) | | |_| | | | | |    ___  ");
    Logger.NONE.out(ChatColor.LIGHT_PURPLE + "  ___) | | |_| | |  __/  |  _  | | |_| |  ___) | " + ChatColor.YELLOW + "By " + Utils.DEVELOPER_NAME);
    Logger.NONE.out(ChatColor.LIGHT_PURPLE + " |____/    ___/  |_|     |_| |_|   ___/  |____/  " + ChatColor.YELLOW + "v" + Utils.VERSION);
    Logger.NONE.out("");

    Logger.INFO.out("Loading config files...");
    getConfig().options().copyDefaults(true);
    saveDefaultConfig();
    Logger.SUCCESS.out("Config files successfully loaded.");

    Logger.INFO.out("Loading handlers...");
    this.registerListener(this);
    this.getHandlers();
    this.addChecks();
    Logger.SUCCESS.out("Handlers successfully loaded.");

    Logger.INFO.out("Loading commands...");
    getCommand("sophos").setExecutor(new CMDSophos(this));
    if (this.getConfig().getBoolean("Messages.enabled") == true) {
        getCommand("staffchat").setExecutor(new CMDStaffChat(this));
        getCommand("sctoggle").setExecutor(new CMDSCToggle(this));
    }
    if (this.getConfig().getBoolean("MuteChat.enabled") == true) {
        getCommand("mutechat").setExecutor(new CMDMutechat(this));
    }
    if (this.getConfig().getBoolean("ClearChat.enabled") == true) {
        getCommand("clearchat").setExecutor(new CMDClearChat(this));
    }
    Logger.SUCCESS.out("Commands successfully loaded.");

    Logger.SUCCESS.out("Sophos successfully enabled. (" + (System.currentTimeMillis() - startTiming) + "ms)");
    Logger.INFO.out("Report any issues or errors directly to the developers @ " + Utils.SUPPORT_URL);
  }
 
  @Override
  public void onDisable() {
    Logger.SUCCESS.out("Sophos successfully disabled.");
    Logger.INFO.out("Report any issues or errors directly to the developers @ " + Utils.SUPPORT_URL);
  }
  
  public ArrayList<Check> getChecks() {
	return new ArrayList<>(this.Checks);
  }

  public void registerListener(Listener listener) {
    this.getServer().getPluginManager().registerEvents(listener, this);
  }

  private void getHandlers() {
	getServer().getPluginManager().registerEvents(new CMDSCToggle(this), this);
	getServer().getPluginManager().registerEvents(new CMDMutechat(this), this);
	getServer().getPluginManager().registerEvents(new ChatHandler(this), this);
	getServer().getPluginManager().registerEvents(new JoinQuitHandler(this), this);
	getServer().getPluginManager().registerEvents(new DevJoinHandler(this), this);
	getServer().getPluginManager().registerEvents(new AntiSwear(this), this);
    if (this.getConfig().getBoolean("Checks.AntiSwear.enabled") == true) {
	    Logger.SUCCESS.out("AntiSwear successfully loaded.");
	}
    getServer().getPluginManager().registerEvents(new AntiAdvertisement(this), this);
    if (this.getConfig().getBoolean("Checks.AntiAdvertisement.enabled") == true) {
        Logger.SUCCESS.out("AntiAdvertisement successfully loaded.");
    }
    getServer().getPluginManager().registerEvents(new AntiCapslock(this), this);
    if (this.getConfig().getBoolean("Checks.AntiCapslock.enabled") == true) {
        Logger.SUCCESS.out("AntiCapslock successfully loaded.");
    }
    getServer().getPluginManager().registerEvents(new AntiCommandSpam(this), this);
    if (this.getConfig().getBoolean("Checks.CommandCooldown.enabled") == true) {
        Logger.SUCCESS.out("AntiCommandSpam successfully loaded.");
    }
    getServer().getPluginManager().registerEvents(new AntiJoinSpam(this), this);
    if (this.getConfig().getBoolean("Checks.AntiJoinSpam.enabled") == true) {
        Logger.SUCCESS.out("AntiJoinSpam successfully loaded.");
    }
    getServer().getPluginManager().registerEvents(new AntiSpam(this), this);
    if (this.getConfig().getBoolean("Checks.ChatCooldown.enabled") == true) {
      Logger.SUCCESS.out("AntiSpam successfully loaded.");
    }
    getServer().getPluginManager().registerEvents(new AntiUnicode(this), this);
    if (this.getConfig().getBoolean("Checks.AntiUnicode.enabled") == true) {
        Logger.SUCCESS.out("AntiUnicode successfully loaded.");
    }
    getServer().getPluginManager().registerEvents(new AutoCorrect(this), this);
    if (this.getConfig().getBoolean("Checks.AutoCorrect.enabled") == true) {
        Logger.SUCCESS.out("AutoCorrect successfully loaded.");
    }
    getServer().getPluginManager().registerEvents(new AutoDot(this), this);
    if (this.getConfig().getBoolean("Checks.AutoDot.enabled") == true) {
      Logger.SUCCESS.out("AutoDot successfully loaded.");
    }
    getServer().getPluginManager().registerEvents(new BlockedCommands(this), this);
    if (this.getConfig().getBoolean("Checks.CommandBlocker.enabled") == true) {
      Logger.SUCCESS.out("CommandBlocker successfully loaded.");
    }
    if (this.getConfig().getBoolean("GUI.enabled") == true) {
    	getServer().getPluginManager().registerEvents(new GUI(this), this);
    }
    getServer().getPluginManager().registerEvents(new AntiBotGUI(this), this);
  }
}