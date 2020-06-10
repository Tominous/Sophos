package me.refrac.sophos.gui;

import java.util.ArrayList;
import java.util.Arrays;

import me.refrac.sophos.Sophos;
import me.refrac.sophos.handlers.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import me.refrac.sophos.handlers.Check;

public class SophosGUI implements Listener {

  public static Inventory SophosMain = Bukkit.createInventory(null, 36, ChatColor.RED + "Sophos");
  private static Inventory SophosChecks = Bukkit.createInventory(null, 27, ChatColor.GOLD + "Checks");
  private static ItemStack back = createItem(Material.ARROW, 1, "&6Back");

  private static Sophos plugin;
  
  public static String chat(String s) { return ChatColor.translateAlternateColorCodes('&', s); }
  
  public SophosGUI(Sophos plugin) {
    SophosGUI.plugin = plugin;
    ItemStack checks = createItem(Material.COMPASS, 1, "&cChecks");
    ItemStack info = createItem(Material.BOOK, 1, "&aInfo");
    ItemStack reload = createItem(Material.LAVA_BUCKET, 1, "&cReload");
    ItemStack close = createItem(Material.ARROW, 1, "&cClose");
    ItemStack support = createItem(Material.BOOK, 1, "&aSupport");
    ItemMeta checksm = checks.getItemMeta();
    ItemMeta infom = info.getItemMeta();
    ItemMeta reloadm = reload.getItemMeta();
    ItemMeta supportm = support.getItemMeta();
    checksm.setLore(checksLore());
    infom.setLore(infoLore());
    reloadm.setLore(reloadLore());
    supportm.setLore(supportLore());
    checks.setItemMeta(checksm);
    info.setItemMeta(infom);
    reload.setItemMeta(reloadm);
    support.setItemMeta(supportm);
    SophosMain.setItem(10, checks);
    SophosMain.setItem(13, info);
    SophosMain.setItem(16, reload);
    SophosMain.setItem(31, close);
    SophosMain.setItem(35, support);
    for (int i = 0; i < 36; i++) {
        if (SophosMain.getItem(i) == null) {
        	SophosMain.setItem(i, Glass());
        }
    }
    return;
  }

  private ItemStack Glass() {
    ItemStack stone = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)15);
    ItemMeta stonem = stone.getItemMeta();
    stonem.setDisplayName("");
    stone.setItemMeta(stonem);
    return stone;
  }
  
  private static ArrayList<String> checksLore() {
   ArrayList<String> list = new ArrayList<>();
	list.add("");
	list.add(chat("&7You can enable / disable any checks within this gui!"));
	return list;
  }
  
  private static ArrayList<String> infoLore() {
   ArrayList<String> list = new ArrayList<>();
    list.add("");
    list.add(chat("&7You can do &f/sophos help &7to see your"));
    list.add(chat("&7options for other &fcommands&7/&ffunctions&7!"));
    list.add("");
    list.add(chat("&7Current Version: &f" + plugin.getDescription().getVersion()));
    return list;
  }
  
  private static ArrayList<String> reloadLore() {
   ArrayList<String> list = new ArrayList<>();
	list.add("");
	list.add(chat("&7Reloads the config files."));
	return list;
  }
  
  private static ArrayList<String> supportLore() {
   ArrayList<String> list = new ArrayList<>();
	list.add("");
	list.add(chat("&7If you need support on an issue/error or suggestion"));
	list.add(chat("&7feel free to join the discord."));
	list.add("");
	list.add(chat("&7Left-Click to receive a link."));
	return list;
  }

  public static void opensophosMain(Player player) { player.openInventory(SophosMain); }

  private static ItemStack createItem(Material material, int amount, String name, String... lore) {
    ItemStack thing = new ItemStack(material, amount);
    ItemMeta thingm = thing.getItemMeta();
    thingm.setDisplayName(chat(name));
    thingm.setLore(Arrays.asList(lore));
    thing.setItemMeta(thingm);
    return thing;
  }
  
  private void openChecks(Player player) {
      int slot = 0;
      for (Check check : plugin.getChecks()) {
          if (plugin.getConfig().getBoolean("Checks." + check.getIdentifier() + ".enabled")) {
              ItemStack c = createGlass(Material.STAINED_GLASS_PANE, 5, 1, ChatColor.GREEN + check.getName());
              SophosChecks.setItem(slot, c);
          } else {
              ItemStack c = createGlass(Material.STAINED_GLASS_PANE, 14, 1, ChatColor.RED + check.getName());
              SophosChecks.setItem(slot, c);
          }
          slot++;
      }
      for (int i = slot; i < 26; i++) {
          ItemStack c = createGlass(Material.STAINED_GLASS_PANE, 15, 1, ChatColor.GRAY + "N/A");
          SophosChecks.setItem(i, c);
      }
      SophosChecks.setItem(26, back);
      player.openInventory(SophosChecks);
  }
  
  private static ItemStack createGlass(Material material, int color, int amount, String name, String... lore) {
      ItemStack thing = new ItemStack(material, amount, (short) color);
      ItemMeta thingm = thing.getItemMeta();
      thingm.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
      thingm.setLore(Arrays.asList(lore));
      thing.setItemMeta(thingm);
      return thing;
  }

    @EventHandler(ignoreCancelled = true)
  public void onInventoryClick(final InventoryClickEvent e) {
	  if (e.getView().getTitle().contains("Sophos")) {
          Player player = (Player) e.getWhoClicked();
          e.setCancelled(true);
          e.setResult(Event.Result.DENY);
          if (e.getCurrentItem() == null) {
              return;
          }
          if (!e.getCurrentItem().hasItemMeta()) {
              return;
          }
          if (e.getCurrentItem().getItemMeta().getDisplayName().contains(chat("&cChecks"))) {
              openChecks(player);
          }
          if (e.getCurrentItem().getItemMeta().getDisplayName().equals(chat("&cReload"))) {
              ItemMeta meta = e.getCurrentItem().getItemMeta();
              plugin.reloadConfig();
              plugin.reloadMessages();
              meta.setDisplayName(ChatColor.GREEN + ChatColor.ITALIC.toString() + "Success!");
              e.getCurrentItem().setItemMeta(meta);
              new BukkitRunnable() {
                  public void run() {
                      ItemMeta meta = e.getCurrentItem().getItemMeta();
                      meta.setDisplayName(ChatColor.RED + "Reload");
                      e.getCurrentItem().setItemMeta(meta);
                      opensophosMain(player);
                  }
              }.runTaskLater(plugin, 40L);
          }
          if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "Close")) {
              player.getOpenInventory().close();
          }
          if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Support")) {
              player.sendMessage(chat(Utils.SUPPORT_URL));
              player.getOpenInventory().close();
          }
      } else if (e.getView().getTitle().contains("Checks")) {
          Player player = (Player) e.getWhoClicked();
          e.setCancelled(true);
          e.setResult(Event.Result.DENY);
          if (e.getCurrentItem() == null) {
              return;
          }
          if (e.getCurrentItem().hasItemMeta()) {
              String check_name = e.getCurrentItem().getItemMeta().getDisplayName();
              for (Check check : plugin.getChecks()) {
                  if (check.getName().equals(ChatColor.stripColor(check_name))) {
                      if (plugin.getConfig().getBoolean("Checks." + check.getIdentifier() + ".enabled")) {
                          plugin.getConfig().set("Checks." + check.getIdentifier() + ".enabled", false);
                          plugin.saveConfig();
                          plugin.reloadConfig();
                          check.setEnabled(false);
                          openChecks(player);
                          return;
                      }
                      plugin.getConfig().set("Checks." + check.getIdentifier() + ".enabled", true);
                      plugin.saveConfig();
                      plugin.reloadConfig();
                      check.setEnabled(true);
                      openChecks(player);
                      return;
                  }
              }
              if (ChatColor.stripColor(check_name).equals("Back")) {
                  opensophosMain(player);
              }
          }
      }
  }
}