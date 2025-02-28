package com.mills.flowsCore.Enderite;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class EnderiteAnvil implements Listener {

    private static final NamespacedKey ENDERITE_ANVIL_KEY = new NamespacedKey("your_plugin", "enderite_anvil");

    private EnderiteItems enderiteItems;

    public EnderiteAnvil(EnderiteItems enderiteItems) {
        this.enderiteItems = enderiteItems;
    }

    @EventHandler
    public void onRightClickAnvil(PlayerInteractEvent e) {
        ItemStack item = e.getItem();
        if (item == null || !isEnderiteAnvil(item)) return;

        e.setCancelled(true);
        Player player = e.getPlayer();

        Inventory anvilGUI = Bukkit.createInventory(null, InventoryType.ANVIL, "Enderite Forger");
        player.openInventory(anvilGUI);

    }

    private boolean isEnderiteAnvil(ItemStack item) {
        if (item.getItemMeta() == null) return false;
        PersistentDataContainer data = item.getItemMeta().getPersistentDataContainer();
        return data.has(ENDERITE_ANVIL_KEY, PersistentDataType.BYTE);
    }

}
