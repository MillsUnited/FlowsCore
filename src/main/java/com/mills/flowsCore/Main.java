package com.mills.flowsCore;

import com.mills.flowsCore.Enderite.*;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private CustomCrafting customCrafting;
    private WorldBorder worldBorder;
    private HomesManager homesManager;
    private EnderiteItems enderiteItems;
    private EnderiteRecipe enderiteRecipe;

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        this.homesManager = new HomesManager(getDataFolder());
        this.enderiteItems = new EnderiteItems(this);

        Bukkit.getPluginManager().registerEvents(new ServerEvents(this), this);
        Bukkit.getPluginManager().registerEvents(new SleepOnePerson(this), this);
        Bukkit.getPluginManager().registerEvents(new EnderiteItems(this), this);
        Bukkit.getPluginManager().registerEvents(new EnderiteBreak(enderiteItems), this);
        Bukkit.getPluginManager().registerEvents(new EnderiteAnvil(enderiteItems), this);
        Bukkit.getPluginManager().registerEvents(new EnderiteAnvilListener(enderiteItems), this);
        getCommand("endunlock").setExecutor(new EndunlockCommand(this));
        getCommand("endlock").setExecutor(new EndlockCommand(this));
        getCommand("sethome").setExecutor(new SetHomeCommand(this));
        getCommand("deletehome").setExecutor(new DeleteHomeCommand(this));
        getCommand("home").setExecutor(new HomeCommand(this));
        getCommand("homes").setExecutor(new HomesCommand(this));
        getCommand("spawn").setExecutor(new SpawnCommand());

        worldBorder = new WorldBorder();
        worldBorder.setWorldBorder();

        customCrafting = new CustomCrafting(this);
        enderiteRecipe = new EnderiteRecipe(this, enderiteItems);

        EnderiteBlockManager blockManager = new EnderiteBlockManager(this, enderiteItems);
        Bukkit.getPluginManager().registerEvents(blockManager, this);

        World endWorld = Bukkit.getWorld("world_the_end");
        if (endWorld != null) {
            endWorld.getPopulators().add(blockManager);
        }
    }

    public HomesManager getHomesManager() {
        return homesManager;
    }

    public EnderiteItems getEnderiteItems() {
        return enderiteItems;
    }
}