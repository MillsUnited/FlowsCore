package com.mills.flowsCore;


import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private CustomCrafting customCrafting;
    private WorldBorder worldBorder;
    private HomesManager homesManager;


    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        this.homesManager = new HomesManager(getDataFolder());

        Bukkit.getPluginManager().registerEvents(new ServerEvents(this), this);
        Bukkit.getPluginManager().registerEvents(new SleepOnePerson(this), this);
        getCommand("endunlock").setExecutor(new EndunlockCommand(this));
        getCommand("endlock").setExecutor(new EndlockCommand(this));
        getCommand("sethome").setExecutor(new SetHomeCommand(this));
        getCommand("deletehome").setExecutor(new DeleteHomeCommand(this));
        getCommand("home").setExecutor(new HomeCommand(this));
        getCommand("homes").setExecutor(new HomesCommand(this));
        getCommand("spawn").setExecutor(new SpawnCommand());

        worldBorder = new WorldBorder(this);
        worldBorder.setWorldBorder();

        customCrafting = new CustomCrafting(this);
    }

    public HomesManager getHomesManager() {
        return homesManager;
    }
}