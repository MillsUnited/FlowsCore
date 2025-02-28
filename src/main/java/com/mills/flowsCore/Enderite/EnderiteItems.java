package com.mills.flowsCore.Enderite;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class EnderiteItems implements Listener {
    private final JavaPlugin plugin;
    private final NamespacedKey durabilityKey;

    public EnderiteItems(JavaPlugin plugin) {
        this.plugin = plugin;
        this.durabilityKey = new NamespacedKey(plugin, "custom_durability");
    }


    public ItemStack enderiteBlock() {

        ItemStack enderiteBlock = new ItemStack(Material.CRYING_OBSIDIAN);
        ItemMeta blockMeta = enderiteBlock.getItemMeta();
        blockMeta.setDisplayName(ChatColor.YELLOW + "Enderite Ore");
        blockMeta.setCustomModelData(1);
        enderiteBlock.setItemMeta(blockMeta);
        return enderiteBlock;
    }

    public ItemStack enderiteAnvil() {

        ItemStack enderiteAnvil = new ItemStack(Material.ANVIL);
        ItemMeta anvilMeta = enderiteAnvil.getItemMeta();
        anvilMeta.setDisplayName(ChatColor.YELLOW + "Enderite Forger");
        anvilMeta.setCustomModelData(1);
        enderiteAnvil.setItemMeta(anvilMeta);
        return enderiteAnvil;
    }

    public ItemStack enderiteShard() {

        ItemStack enderiteShard = new ItemStack(Material.AMETHYST_SHARD);
        ItemMeta shardMeta = enderiteShard.getItemMeta();
        shardMeta.setDisplayName(ChatColor.YELLOW + "Enderite Shard");
        NamespacedKey model = new NamespacedKey("minecraft", "enderite_shard");
        shardMeta.setItemModel(model);
        enderiteShard.setItemMeta(shardMeta);
        return enderiteShard;
    }

    public ItemStack enderiteIngot() {

        ItemStack enderiteIngot = new ItemStack(Material.NETHERITE_INGOT);
        ItemMeta ingotMeta =  enderiteIngot.getItemMeta();
        ingotMeta.setDisplayName(ChatColor.YELLOW + "Enderite Ingot");
        NamespacedKey model = new NamespacedKey("minecraft", "enderite_ingot");
        ingotMeta.setItemModel(model);
        enderiteIngot.setItemMeta(ingotMeta);
        return enderiteIngot;
    }

    public ItemStack enderiteHelmet() {
        AttributeModifier kbres = new AttributeModifier(
                UUID.randomUUID(), "generic.knockback_resistance", 2, AttributeModifier.Operation.ADD_NUMBER);
        AttributeModifier armor = new AttributeModifier(
                UUID.randomUUID(), "generic.armor", 4, AttributeModifier.Operation.ADD_NUMBER);
        AttributeModifier armortough = new AttributeModifier(
                UUID.randomUUID(), "generic.armor_toughness", 4, AttributeModifier.Operation.ADD_NUMBER);
        ItemStack enderiteHelmet = new ItemStack(Material.NETHERITE_HELMET);
        ItemMeta helmetMeta = enderiteHelmet.getItemMeta();
        helmetMeta.setDisplayName(ChatColor.YELLOW + "Enderite Helmet");
        NamespacedKey model = new NamespacedKey("minecraft", "enderite_helmet");
        helmetMeta.setItemModel(model);
        helmetMeta.addAttributeModifier(Attribute.ARMOR, kbres);
        helmetMeta.addAttributeModifier(Attribute.ARMOR, armor);
        helmetMeta.addAttributeModifier(Attribute.ARMOR, armortough);
        PersistentDataContainer data = helmetMeta.getPersistentDataContainer();
        data.set(durabilityKey, PersistentDataType.INTEGER, 907);
        enderiteHelmet.setItemMeta(helmetMeta);
        return enderiteHelmet;
    }

    public ItemStack enderiteChestplate() {
        AttributeModifier kbres = new AttributeModifier(
                UUID.randomUUID(), "generic.knockback_resistance", 2, AttributeModifier.Operation.ADD_NUMBER);
        AttributeModifier armor = new AttributeModifier(
                UUID.randomUUID(), "generic.armor", 9, AttributeModifier.Operation.ADD_NUMBER);
        AttributeModifier armortough = new AttributeModifier(
                UUID.randomUUID(), "generic.armor_toughness", 4, AttributeModifier.Operation.ADD_NUMBER);
        ItemStack enderiteChestplate = new ItemStack(Material.NETHERITE_CHESTPLATE);
        ItemMeta chestplateMeta = enderiteChestplate.getItemMeta();
        chestplateMeta.setDisplayName(ChatColor.YELLOW + "Enderite Chestplate");
        NamespacedKey model = new NamespacedKey("minecraft", "enderite_chestplate");
        chestplateMeta.setItemModel(model);
        chestplateMeta.addAttributeModifier(Attribute.ARMOR, kbres);
        chestplateMeta.addAttributeModifier(Attribute.ARMOR, armor);
        chestplateMeta.addAttributeModifier(Attribute.ARMOR, armortough);
        PersistentDataContainer data = chestplateMeta.getPersistentDataContainer();
        data.set(durabilityKey, PersistentDataType.INTEGER, 1092);
        enderiteChestplate.setItemMeta(chestplateMeta);
        return enderiteChestplate;
    }

    public ItemStack enderiteLeggings() {
        AttributeModifier kbres = new AttributeModifier(
                UUID.randomUUID(), "generic.knockback_resistance", 2, AttributeModifier.Operation.ADD_NUMBER);
        AttributeModifier armor = new AttributeModifier(
                UUID.randomUUID(), "generic.armor", 7, AttributeModifier.Operation.ADD_NUMBER);
        AttributeModifier armortough = new AttributeModifier(
                UUID.randomUUID(), "generic.armor_toughness", 4, AttributeModifier.Operation.ADD_NUMBER);
        ItemStack enderiteLeggings = new ItemStack(Material.NETHERITE_LEGGINGS);
        ItemMeta leggingsMeta = enderiteLeggings.getItemMeta();
        leggingsMeta.setDisplayName(ChatColor.YELLOW + "Enderite Leggings");
        NamespacedKey model = new NamespacedKey("minecraft", "enderite_leggings");
        leggingsMeta.setItemModel(model);
        leggingsMeta.addAttributeModifier(Attribute.ARMOR, kbres);
        leggingsMeta.addAttributeModifier(Attribute.ARMOR, armor);
        leggingsMeta.addAttributeModifier(Attribute.ARMOR, armortough);
        PersistentDataContainer data = leggingsMeta.getPersistentDataContainer();
        data.set(durabilityKey, PersistentDataType.INTEGER, 1055);
        enderiteLeggings.setItemMeta(leggingsMeta);
        return enderiteLeggings;
    }

    public ItemStack enderiteBoots() {
        AttributeModifier kbres = new AttributeModifier(
                UUID.randomUUID(), "generic.knockback_resistance", 2, AttributeModifier.Operation.ADD_NUMBER);
        AttributeModifier armor = new AttributeModifier(
                UUID.randomUUID(), "generic.armor", 4, AttributeModifier.Operation.ADD_NUMBER);
        AttributeModifier armortough = new AttributeModifier(
                UUID.randomUUID(), "generic.armor_toughness", 4, AttributeModifier.Operation.ADD_NUMBER);
        ItemStack enderiteBoots = new ItemStack(Material.NETHERITE_BOOTS);
        ItemMeta bootsMeta = enderiteBoots.getItemMeta();
        bootsMeta.setDisplayName(ChatColor.YELLOW + "Enderite Boots");
        NamespacedKey model = new NamespacedKey("minecraft", "enderite_boots");
        bootsMeta.setItemModel(model);
        bootsMeta.addAttributeModifier(Attribute.ARMOR, kbres);
        bootsMeta.addAttributeModifier(Attribute.ARMOR, armor);
        bootsMeta.addAttributeModifier(Attribute.ARMOR, armortough);
        PersistentDataContainer data = bootsMeta.getPersistentDataContainer();
        data.set(durabilityKey, PersistentDataType.INTEGER, 981);
        enderiteBoots.setItemMeta(bootsMeta);
        return enderiteBoots;
    }

    public ItemStack enderiteSword() {
        AttributeModifier attack = new AttributeModifier(
                UUID.randomUUID(), "generic.attack", 9, AttributeModifier.Operation.ADD_NUMBER);
        ItemStack enderiteSword = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta swordMeta = enderiteSword.getItemMeta();
        swordMeta.setDisplayName(ChatColor.YELLOW + "Enderite Sword");
        NamespacedKey model = new NamespacedKey("minecraft", "enderite_sword");
        swordMeta.setItemModel(model);
        swordMeta.addAttributeModifier(Attribute.ARMOR, attack);
        PersistentDataContainer data = swordMeta.getPersistentDataContainer();
        data.set(durabilityKey, PersistentDataType.INTEGER, 2531);
        enderiteSword.setItemMeta(swordMeta);
        enderiteSword.setItemMeta(swordMeta);
        return enderiteSword;
    }

    public ItemStack enderitePickaxe() {
        ItemStack enderitePickaxe = new ItemStack(Material.NETHERITE_PICKAXE);
        ItemMeta pickaxeMeta = enderitePickaxe.getItemMeta();
        pickaxeMeta.setDisplayName(ChatColor.YELLOW + "Enderite Pickaxe");
        NamespacedKey model = new NamespacedKey("minecraft", "enderite_pickaxe");
        pickaxeMeta.setItemModel(model);
        PersistentDataContainer data = pickaxeMeta.getPersistentDataContainer();
        data.set(durabilityKey, PersistentDataType.INTEGER, 2531);
        enderitePickaxe.setItemMeta(pickaxeMeta);
        enderitePickaxe.setItemMeta(pickaxeMeta);
        return enderitePickaxe;
    }

    public ItemStack enderiteAxe() {
        ItemStack enderiteAxe = new ItemStack(Material.NETHERITE_AXE);
        ItemMeta axeMeta = enderiteAxe.getItemMeta();
        axeMeta.setDisplayName(ChatColor.YELLOW + "Enderite Axe");
        NamespacedKey model = new NamespacedKey("minecraft", "enderite_axe");
        axeMeta.setItemModel(model);
        PersistentDataContainer data = axeMeta.getPersistentDataContainer();
        data.set(durabilityKey, PersistentDataType.INTEGER, 2531);
        enderiteAxe.setItemMeta(axeMeta);
        enderiteAxe.setItemMeta(axeMeta);
        return enderiteAxe;
    }

    public ItemStack enderiteShovel() {
        ItemStack enderiteShovel = new ItemStack(Material.NETHERITE_SHOVEL);
        ItemMeta shovelMeta = enderiteShovel.getItemMeta();
        shovelMeta.setDisplayName(ChatColor.YELLOW + "Enderite Shovel");
        NamespacedKey model = new NamespacedKey("minecraft", "enderite_shovel");
        shovelMeta.setItemModel(model);
        PersistentDataContainer data = shovelMeta.getPersistentDataContainer();
        data.set(durabilityKey, PersistentDataType.INTEGER, 2531);
        enderiteShovel.setItemMeta(shovelMeta);
        enderiteShovel.setItemMeta(shovelMeta);
        return enderiteShovel;
    }

    public ItemStack enderiteHoe() {
        ItemStack enderiteHoe = new ItemStack(Material.NETHERITE_HOE);
        ItemMeta hoeMeta = enderiteHoe.getItemMeta();
        hoeMeta.setDisplayName(ChatColor.YELLOW + "Enderite Hoe");
        NamespacedKey model = new NamespacedKey("minecraft", "enderite_hoe");
        hoeMeta.setItemModel(model);
        PersistentDataContainer data = hoeMeta.getPersistentDataContainer();
        data.set(durabilityKey, PersistentDataType.INTEGER, 2531);
        enderiteHoe.setItemMeta(hoeMeta);
        enderiteHoe.setItemMeta(hoeMeta);
        return enderiteHoe;
    }

    @EventHandler
    public void onItemDamage(PlayerItemDamageEvent e) {
        ItemStack item = e.getItem();
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            PersistentDataContainer data = meta.getPersistentDataContainer();
            if (data.has(durabilityKey, PersistentDataType.INTEGER)) {
                int maxDurability = data.get(durabilityKey, PersistentDataType.INTEGER);
                int currentDamage = ((org.bukkit.inventory.meta.Damageable) meta).getDamage();

                if (currentDamage >= maxDurability) {
                    e.getPlayer().getInventory().remove(item);
                } else {
                    ((org.bukkit.inventory.meta.Damageable) meta).setDamage(currentDamage + e.getDamage());
                    item.setItemMeta(meta);
                }
                e.setCancelled(true);
            }
        }
    }
}
