package com.mills.flowsCore;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            double randomX = -27 + (Math.random() * (-17 - (-27)));
            double randomZ = -520 + (Math.random() * (-510 - (-520)));

            World world = Bukkit.getWorld("world");
            if (isBlockAboveAir(player) && isBlockAboveAir2(player)) {
                Location spawn = new Location(world, randomX, 64, randomZ);
                player.teleport(spawn);
                player.sendMessage("teleported to spawn!");
            }
        }
        return false;
    }

    public boolean isBlockAboveAir(Player player) {
        Block blockAbove = player.getLocation().add(0, 1, 0).getBlock();
        return blockAbove.getType() == Material.AIR;
    }

    public boolean isBlockAboveAir2(Player player) {
        Block blockAbove = player.getLocation().add(0, 2, 0).getBlock();
        return blockAbove.getType() == Material.AIR;
    }
}
