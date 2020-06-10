/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */

package me.refrac.sophos;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.refrac.sophos.handlers.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import me.refrac.sophos.cmds.*;
import me.refrac.sophos.gui.*;
import me.refrac.sophos.handlers.checks.*;

public class Sophos extends JavaPlugin implements Listener {

  private final List<Check> Checks;
  private File mfile;
  private FileConfiguration messages;
  private File cfile;
  private FileConfiguration config;
    
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
    this.Checks.add(new AutoDot(this));
    this.Checks.add(new BlockedCommands(this));
  }
  
  @Override
  public void onEnable() {
    // Plugin startup logic
    long startTiming = System.currentTimeMillis();
    Logger.INFO.out("Enabling Sophos");

    new SophosAPI(this);

    Logger.NONE.out("");
    Logger.NONE.out(ChatColor.LIGHT_PURPLE + "   ____    ___    ____    _   _    ___     ____  ");
    Logger.NONE.out(ChatColor.LIGHT_PURPLE + "  / ___|  / _    |  _    | | | |  / _     / ___| ");
    Logger.NONE.out(ChatColor.LIGHT_PURPLE + "    ___  | | | | | |_) | | |_| | | | | |    ___  ");
    Logger.NONE.out(ChatColor.LIGHT_PURPLE + "  ___) | | |_| | |  __/  |  _  | | |_| |  ___) | " + ChatColor.YELLOW + "By " + Utils.DEVELOPER_NAME);
    Logger.NONE.out(ChatColor.LIGHT_PURPLE + " |____/    ___/  |_|     |_| |_|   ___/  |____/  " + ChatColor.YELLOW + "v" + Utils.VERSION);
    Logger.NONE.out("");

    Logger.INFO.out("Loading config files");
    createConfig();
    createMessages();
    Logger.SUCCESS.out("Successfully loaded the config files");

    Logger.INFO.out("Loading handlers");
    this.registerListener(this);
    this.getHandlers();
    this.addChecks();
    Logger.SUCCESS.out("Successfully loaded the handlers");

    Logger.INFO.out("Loading commands");
    getCommand("sophos").setExecutor(new CMDSophos(this));
    if (this.getMessages().getBoolean("Messages.Chat.staffchat_enabled") == true) {
        getCommand("staffchat").setExecutor(new CMDStaffChat(this));
        getCommand("sctoggle").setExecutor(new CMDSCToggle(this));
    }
    if (this.getMessages().getBoolean("Messages.Chat.chat_enabled") == true) {
        getCommand("chat").setExecutor(new CMDChat(this));
    }
    Logger.SUCCESS.out("Successfully loaded the commands");

    Logger.SUCCESS.out("Sophos successfully enabled. (" + (System.currentTimeMillis() - startTiming) + "ms)");
    Logger.INFO.out("Report any issues or errors directly to the developers @ " + Utils.SUPPORT_URL);
  }
 
  @Override
  public void onDisable() {
    Logger.INFO.out("Shutting down Sophos");
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
    if (this.getMessages().getBoolean("Messages.Chat.chat_enabled") == true) {
        getServer().getPluginManager().registerEvents(new CMDChat(this), this);
    }
	getServer().getPluginManager().registerEvents(new ChatHandler(this), this);
	getServer().getPluginManager().registerEvents(new JoinQuitHandler(this), this);
	getServer().getPluginManager().registerEvents(new AntiSwear(this), this);
    if (this.getConfig().getBoolean("Checks.AntiSwear.enabled") == true) {
	    Logger.SUCCESS.out("AntiSwear successfully loaded");
	}
    getServer().getPluginManager().registerEvents(new AntiAdvertisement(this), this);
    if (this.getConfig().getBoolean("Checks.AntiAdvertisement.enabled") == true) {
        Logger.SUCCESS.out("AntiAdvertisement successfully loaded");
    }
    getServer().getPluginManager().registerEvents(new AntiCapslock(this), this);
    if (this.getConfig().getBoolean("Checks.AntiCapslock.enabled") == true) {
        Logger.SUCCESS.out("AntiCapslock successfully loaded");
    }
    getServer().getPluginManager().registerEvents(new AntiCommandSpam(this), this);
    if (this.getConfig().getBoolean("Checks.CommandCooldown.enabled") == true) {
        Logger.SUCCESS.out("AntiCommandSpam successfully loaded");
    }
    getServer().getPluginManager().registerEvents(new AntiJoinSpam(this), this);
    if (this.getConfig().getBoolean("Checks.AntiJoinSpam.enabled") == true) {
        Logger.SUCCESS.out("AntiJoinSpam successfully loaded");
    }
    getServer().getPluginManager().registerEvents(new AntiSpam(this), this);
    if (this.getConfig().getBoolean("Checks.ChatCooldown.enabled") == true) {
      Logger.SUCCESS.out("AntiSpam successfully loaded");
    }
    if (this.getConfig().getBoolean("Checks.AntiTabComplete.enabled") == true) {
      Logger.SUCCESS.out("AntiTabComplete successfully loaded");
    }
    getServer().getPluginManager().registerEvents(new AntiUnicode(this), this);
    if (this.getConfig().getBoolean("Checks.AntiUnicode.enabled") == true) {
        Logger.SUCCESS.out("AntiUnicode successfully loaded");
    }
    getServer().getPluginManager().registerEvents(new AutoCorrect(this), this);
    if (this.getConfig().getBoolean("Checks.AutoCorrect.enabled") == true) {
        Logger.SUCCESS.out("AutoCorrect successfully loaded");
    }
    getServer().getPluginManager().registerEvents(new AutoDot(this), this);
    if (this.getConfig().getBoolean("Checks.AutoDot.enabled") == true) {
      Logger.SUCCESS.out("AutoDot successfully loaded");
    }
    getServer().getPluginManager().registerEvents(new BlockedCommands(this), this);
    if (this.getConfig().getBoolean("Checks.CommandBlocker.enabled") == true) {
      Logger.SUCCESS.out("CommandBlocker successfully loaded");
    }
    if (this.getConfig().getBoolean("Sophos_GUI.enabled") == true) {
    	getServer().getPluginManager().registerEvents(new SophosGUI(this), this);
    }
    getServer().getPluginManager().registerEvents(new AntiBotGUI(this), this);
  }

  public FileConfiguration getMessages() {
    return this.messages;
  }

  // Create Messages File
  private void createMessages() {
    mfile = new File(getDataFolder(), "messages.yml");
    if (!mfile.exists()) {
      mfile.getParentFile().mkdirs();
      saveResource("messages.yml", false);
    }

    messages= new YamlConfiguration ();
    try {
      messages.load(mfile);
    } catch (IOException | InvalidConfigurationException e) {
      e.printStackTrace();
    }
  }

  // Reload Messages File
  public void reloadMessages() {
    try {
      messages = YamlConfiguration.loadConfiguration(mfile);
    } catch(Exception ex) {
      Logger.ERROR.out(ChatColor.DARK_RED + "Failed to reload messages file! Report this to the developer @ " + Utils.SUPPORT_URL);
    }
  }

  public FileConfiguration getConfig() {
    return this.config;
  }

  // Create Config File
  private void createConfig() {
    cfile = new File(getDataFolder(), "config.yml");
    if (!cfile.exists()) {
      cfile.getParentFile().mkdirs();
      saveResource("config.yml", false);
    }

    config= new YamlConfiguration ();
    try {
      config.load(cfile);
    } catch (IOException | InvalidConfigurationException e) {
      e.printStackTrace();
    }
  }

  // Reload Config File
  public void reloadConfig() {
    try {
      config = YamlConfiguration.loadConfiguration(cfile);
    } catch(Exception ex) {
      Logger.ERROR.out(ChatColor.DARK_RED + "Failed to reload config file! Report this to the developer @ " + Utils.SUPPORT_URL);
    }
  }
}