package com.mills.flowsCore.Enderite;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Map;

public class EnderiteAnvilListener implements Listener {

    private EnderiteItems enderiteItems;

    public EnderiteAnvilListener(EnderiteItems enderiteItems) {
        this.enderiteItems = enderiteItems;
    }

    @EventHandler
    public void onAnvilClick(InventoryClickEvent e) {
        if (!(e.getInventory() instanceof AnvilInventory)) return;
        if (!e.getView().getTitle().equals("Enderite Forger")) return;

        AnvilInventory anvil = (AnvilInventory) e.getInventory();
        ItemStack firstSlot = anvil.getItem(0);
        ItemStack secondSlot = anvil.getItem(1);

        if (firstSlot == null || secondSlot == null) return;
        if (!isNetheriteItem(firstSlot) || !isEnderiteIngot(secondSlot)) return;

        ItemStack enderiteItem = getEnderiteItem(firstSlot);
        copyItemAttributes(firstSlot, enderiteItem);
        anvil.setItem(2, enderiteItem);
    }

    private boolean isNetheriteItem(ItemStack item) {
        Material type = item.getType();
       return switch (type) {
           case NETHERITE_SWORD, NETHERITE_PICKAXE, NETHERITE_AXE, NETHERITE_SHOVEL,
                NETHERITE_HELMET, NETHERITE_CHESTPLATE, NETHERITE_LEGGINGS, NETHERITE_BOOTS -> true;
           default -> false;
       };
    }

    private boolean isEnderiteIngot(ItemStack item) {
        if (item == null) return false;
        return item.isSimilar(enderiteItems.enderiteIngot());
    }

    private ItemStack getEnderiteItem(ItemStack netheriteItem) {
        Material type = netheriteItem.getType();

        return switch (type) {
            case NETHERITE_SWORD -> enderiteItems.enderiteSword().clone();
            case NETHERITE_PICKAXE -> enderiteItems.enderitePickaxe().clone();
            case NETHERITE_AXE -> enderiteItems.enderiteAxe().clone();
            case NETHERITE_HOE -> enderiteItems.enderiteHoe().clone();
            case NETHERITE_SHOVEL -> enderiteItems.enderiteShovel().clone();
            case NETHERITE_HELMET -> enderiteItems.enderiteHelmet().clone();
            case NETHERITE_CHESTPLATE -> enderiteItems.enderiteChestplate().clone();
            case NETHERITE_LEGGINGS -> enderiteItems.enderiteLeggings().clone();
            case NETHERITE_BOOTS -> enderiteItems.enderiteBoots().clone();
            default -> netheriteItem;
        };
    }

    private void copyItemAttributes(ItemStack source, ItemStack target) {
        if (source == null || target == null) return;

        ItemMeta sourceMeta = source.getItemMeta();
        ItemMeta targetMeta = target.getItemMeta();

        if (sourceMeta != null && targetMeta != null) {
            if (sourceMeta.hasDisplayName()) {
                targetMeta.setDisplayName(sourceMeta.getDisplayName());
            }

            if (sourceMeta.hasLore()) {
                targetMeta.setLore(sourceMeta.getLore());
            }

            for (Map.Entry<Enchantment, Integer> entry : source.getEnchantments().entrySet()) {
                targetMeta.addEnchant(entry.getKey(), entry.getValue(), true);
            }

            PersistentDataContainer sourcePDC = sourceMeta.getPersistentDataContainer();
            PersistentDataContainer targetPDC = targetMeta.getPersistentDataContainer();
            for (NamespacedKey key : sourcePDC.getKeys()) {
                targetPDC.set(key, PersistentDataType.STRING, sourcePDC.get(key, PersistentDataType.STRING));
            }

            target.setItemMeta(targetMeta);
        }

    }

}
