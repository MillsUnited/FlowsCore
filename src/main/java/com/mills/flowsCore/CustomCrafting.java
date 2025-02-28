package com.mills.flowsCore;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomCrafting {

    private Main main;

    public CustomCrafting(Main main) {
        this.main = main;
        netheriteCraft();
    }

    public void netheriteCraft() {

        ItemStack scrapitem = new ItemStack(Material.GOLD_NUGGET);
        ItemMeta scrapmeta = scrapitem.getItemMeta();
        NamespacedKey model = new NamespacedKey("minecraft", "scrap");
        if (scrapmeta != null) {
            scrapmeta.setDisplayName(ChatColor.WHITE + "Condensed Netherite Scrap");
            scrapmeta.setItemModel(model);
        }
        scrapitem.setItemMeta(scrapmeta);

        ShapelessRecipe scraprecipe = new ShapelessRecipe(new NamespacedKey("flowscore", "condensed_netherite_scrap"), scrapitem);
        scraprecipe.addIngredient(Material.NETHERITE_SCRAP);
        scraprecipe.addIngredient(Material.NETHERITE_SCRAP);
        scraprecipe.addIngredient(Material.NETHERITE_SCRAP);
        scraprecipe.addIngredient(Material.NETHERITE_SCRAP);
        scraprecipe.addIngredient(Material.GOLD_INGOT);
        scraprecipe.addIngredient(Material.GOLD_INGOT);
        scraprecipe.addIngredient(Material.GOLD_INGOT);
        scraprecipe.addIngredient(Material.GOLD_INGOT);

        Bukkit.getServer().addRecipe(scraprecipe);

        ItemStack ingotitem = new ItemStack(Material.NETHERITE_INGOT);
        ShapelessRecipe ingotrecipe = new ShapelessRecipe(new NamespacedKey("flowscore", "netherite_ingot_custom"), ingotitem);
        ingotrecipe.addIngredient(new RecipeChoice.ExactChoice(scrapitem));
        ingotrecipe.addIngredient(new RecipeChoice.ExactChoice(scrapitem));
        ingotrecipe.addIngredient(new RecipeChoice.ExactChoice(scrapitem));
        ingotrecipe.addIngredient(new RecipeChoice.ExactChoice(scrapitem));
        ingotrecipe.addIngredient(Material.GOLD_BLOCK);
        ingotrecipe.addIngredient(Material.GOLD_BLOCK);
        ingotrecipe.addIngredient(Material.GOLD_BLOCK);
        ingotrecipe.addIngredient(Material.GOLD_BLOCK);

        Bukkit.getServer().addRecipe(ingotrecipe);

        NamespacedKey netheriteIngotKey = new NamespacedKey("minecraft", "netherite_ingot");
        Bukkit.getServer().removeRecipe(netheriteIngotKey);
    }
}