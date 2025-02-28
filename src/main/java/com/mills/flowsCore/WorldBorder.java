package com.mills.flowsCore;

import org.bukkit.Bukkit;
import org.bukkit.World;

public class WorldBorder {

    public void setWorldBorder() {

        World overworld = Bukkit.getWorld("world");
        if (overworld != null) {
            org.bukkit.WorldBorder border = overworld.getWorldBorder();
            border.setCenter(0, 0);
            border.setSize(10000);
            border.setDamageAmount(8.0);

        }

        World nether = Bukkit.getWorld("world_nether");
        if (nether != null) {
            org.bukkit.WorldBorder border = nether.getWorldBorder();
            border.setCenter(0, 0);
            border.setSize(1250);
            border.setDamageAmount(8.0);

        }
    }
}
