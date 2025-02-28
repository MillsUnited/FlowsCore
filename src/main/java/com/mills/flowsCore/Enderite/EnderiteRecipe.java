package com.mills.flowsCore.Enderite;

import com.mills.flowsCore.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.*;

public class EnderiteRecipe {

    private Main main;
    private EnderiteItems enderiteItems;

    public EnderiteRecipe(Main main, EnderiteItems enderiteItems) {
        this.main = main;
        this.enderiteItems = enderiteItems;
        enderiteSmelting();
        enderiteIngotCraft();
        enderiteForger();
    }

    public void enderiteSmelting() {
        ItemStack input = enderiteItems.enderiteBlock();
        ItemStack output = enderiteItems.enderiteShard();

        FurnaceRecipe enderiteSmelt = new FurnaceRecipe(
                new NamespacedKey(main, "enderite_smelting"),
                output,
                input.getType(),
                5.0f,
                200);

        Bukkit.addRecipe(enderiteSmelt);
    }

    public void enderiteIngotCraft() {
        ItemStack result = enderiteItems.enderiteIngot();
        NamespacedKey key = new NamespacedKey(main, "enderite_ingot");
        ShapedRecipe recipe = new ShapedRecipe(key, result);
        recipe.shape(
                "EEE",
                "EEE",
                "EEE"
        );
        recipe.setIngredient('E', new RecipeChoice.ExactChoice(enderiteItems.enderiteShard()));
        Bukkit.addRecipe(recipe);
    }

    public void enderiteForger() {
        ItemStack result = enderiteItems.enderiteAnvil();
        NamespacedKey key = new NamespacedKey(main, "enderite_forger");
        ShapedRecipe recipe = new ShapedRecipe(key, result);
        recipe.shape(
                "AEA",
                "EDE",
                "AEA"
        );
        recipe.setIngredient('E', Material.POPPED_CHORUS_FRUIT);
        recipe.setIngredient('A', Material.ENDER_EYE);
        recipe.setIngredient('D', Material.ANVIL);
        Bukkit.addRecipe(recipe);
    }

}
