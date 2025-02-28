package com.mills.flowsCore;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HomesManager {

    private File file;
    private FileConfiguration config;

    public HomesManager(File dataFolder) {
        file = new File(dataFolder, "homes.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public int getHomeamount(UUID uuid) {
        int homeamount = 0;
        String uuidPath = uuid.toString();

        if (config.contains(uuidPath)) {
            for (String key : config.getConfigurationSection(uuidPath).getKeys(false)) {
                homeamount++;
            }
        }
        return homeamount;
    }

    public List<String> getHomeNames(UUID uuid) {
        List<String> names = new ArrayList<>();
        String path = uuid.toString();

        if (config.contains(path)) {
            for (String name : config.getConfigurationSection(path).getKeys(false)) {
                names.add(name);
            }
        }
        return names;
    }

    public void saveHome(UUID uuid, String world, String name, Double x, Double y, Double z) {
        String path = uuid.toString() + "." + name; // This will be like 95b646ab-1d1c-4dbb-98fc-baa752362b0c.base
        config.set(path + ".World", world);  // Save the world name
        config.set(path + ".x", x);         // Save x coordinate
        config.set(path + ".y", y);         // Save y coordinate
        config.set(path + ".z", z);         // Save z coordinate
        saveConfig();
    }

    public void deleteHome(UUID uuid, String name) {
        if (config.contains(uuid.toString() + "." + name)) {
            config.set(uuid.toString() + "." + name, null);
            if (getHomeamount(uuid) == 0) {
                config.set(uuid.toString(), null);
            }
            saveConfig();
        }
    }

    public boolean doesHomeExist(UUID uuid, String name) {
        return config.contains(uuid.toString() + "." + name + ".x");
    }

    public double getHomeX(UUID uuid, String name) {
        return config.getDouble(uuid.toString() + "." + name + ".x");
    }

    public World getHomeWorld(UUID uuid, String name) {
        String worldName = config.getString(uuid.toString() + "." + name + ".World");
        if (worldName != null) {
            return Bukkit.getWorld(worldName);
        }
        return null;
    }

    public double getHomeY(UUID uuid, String name) {
        return config.getDouble(uuid.toString() + "." + name + ".y");
    }

    public double getHomeZ(UUID uuid, String name) {
        return config.getDouble(uuid.toString() + "." + name + ".z");
    }

    private void saveConfig() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
