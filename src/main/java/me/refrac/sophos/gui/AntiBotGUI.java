package me.refrac.sophos.gui;

import me.refrac.sophos.Sophos;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class AntiBotGUI implements Listener {

    private static Sophos plugin;

    private static String chat(String s) { return ChatColor.translateAlternateColorCodes('&', s); }

    public static Inventory AntiBotMain = Bukkit.createInventory(null, 45, ChatColor.RED + "AntiBot");

    private static ArrayList<Player> passedAntiBot;

    public static ArrayList<Player> getPassedAntiBot() {
        return new ArrayList<>(passedAntiBot);
    }

    public AntiBotGUI(Sophos plugin) {
        AntiBotGUI.plugin = plugin;
        this.passedAntiBot = new ArrayList<>();
        AntiBotMain.setItem(22, Glass1());
        for (int i = 0; i < 45; i++) {
            if (AntiBotMain.getItem(i) == null) {
                AntiBotMain.setItem(i, Glass());
            }
        }
        return;
    }

    // Failed Glass
    private ItemStack Glass() {
        ItemStack stone = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)14);
        ItemMeta stonem = stone.getItemMeta();
        stonem.setDisplayName(chat("&cFail"));
        stone.setItemMeta(stonem);
        return stone;
    }

    // Passed Glass
    private ItemStack Glass1() {
        ItemStack stone = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)5);
        ItemMeta stonem = stone.getItemMeta();
        stonem.setDisplayName(chat(plugin.getConfig().getString("Checks.AntiJoinSpam.GUI_passed_name")));
        stone.setItemMeta(stonem);
        return stone;
    }

    public static void openAntiBotMain(Player player) { player.openInventory(AntiBotMain); }

    private static ItemStack createItem(Material material, int amount, String name, String... lore) {
        ItemStack thing = new ItemStack(material, amount);
        ItemMeta thingm = thing.getItemMeta();
        thingm.setDisplayName(chat(name));
        thingm.setLore(Arrays.asList(lore));
        thing.setItemMeta(thingm);
        return thing;
    }

    @EventHandler(ignoreCancelled = true)
    public void onInventoryClick(final InventoryClickEvent e) {
        if (e.getView().getTitle().contains("AntiBot")) {
            Player player = (Player)e.getWhoClicked();
            e.setCancelled(true);
            e.setResult(Event.Result.DENY);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (!e.getCurrentItem().hasItemMeta()) {
                return;
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(chat(plugin.getConfig().getString("Checks.AntiJoinSpam.GUI_passed_name")))) {
                player.sendMessage(chat(plugin.getConfig().getString("Checks.AntiJoinSpam.passed_message").replace("{arrowright}", "\u00BB")));
                this.passedAntiBot.add(player);
                player.getOpenInventory().close();
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals("Fail")) {
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), chat(plugin.getConfig().getString("Checks.AntiJoinSpam.failed_message").replace("{player}", player.getName()).replace("{arrowright}", "\u00BB")));
            }
        }
    }
}