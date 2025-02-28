package com.mills.flowsCore.Enderite;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.TileState;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnderiteBlockManager extends BlockPopulator implements Listener {
    private final EnderiteItems enderiteItems;
    private final List<Location> placedOres = new ArrayList<>();
    private final Plugin plugin;

    public EnderiteBlockManager(Plugin plugin, EnderiteItems enderiteItems) {
        this.plugin = plugin;
        this.enderiteItems = enderiteItems;
    }

    @Override
    public void populate(World world, Random random, Chunk chunk) {
        if (!world.getEnvironment().equals(World.Environment.THE_END)) return;

        for (int i = 0; i < 5; i++) {
            int x = random.nextInt(16);
            int z = random.nextInt(16);
            int worldX = chunk.getX() * 16 + x;
            int worldZ = chunk.getZ() * 16 + z;
            int y = world.getHighestBlockYAt(worldX, worldZ) - 1;

            Block block = world.getBlockAt(worldX, y, worldZ);

            if (block.getType() == Material.END_STONE &&
                    isSurroundedByEndStone(world, worldX, y, worldZ) &&
                    isFarEnough(worldX, y, worldZ)) {

                block.setType(Material.CRYING_OBSIDIAN);
                BlockState state = block.getState();
                if (state instanceof TileState tileState) {
                    PersistentDataContainer data = tileState.getPersistentDataContainer();
                    data.set(new NamespacedKey(plugin, "custom_model"), PersistentDataType.INTEGER, 1);
                    tileState.update();
                }

                placedOres.add(new Location(world, worldX, y, worldZ));

                Player debugPlayer = getFirstPlayerInEnd(world);
                if (debugPlayer != null) {
                    debugPlayer.sendMessage("§6[DEBUG] §eEnderite Block placed at §bX: " + worldX + " Y: " + y + " Z: " + worldZ);
                }
            }
        }
    }

    private Player getFirstPlayerInEnd(World world) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getWorld().equals(world)) {
                return player;
            }
        }
        return null;
    }

    private boolean isSurroundedByEndStone(World world, int x, int y, int z) {
        return isEndStone(world, x + 1, y, z) &&
                isEndStone(world, x - 1, y, z) &&
                isEndStone(world, x, y, z + 1) &&
                isEndStone(world, x, y, z - 1) &&
                isEndStone(world, x, y + 1, z) &&
                isEndStone(world, x, y - 1, z);
    }

    private boolean isEndStone(World world, int x, int y, int z) {
        return world.getBlockAt(x, y, z).getType() == Material.END_STONE;
    }

    private boolean isFarEnough(int x, int y, int z) {
        for (Location ore : placedOres) {
            if (ore.distanceSquared(new Location(ore.getWorld(), x, y, z)) < 256) {
                return false;
            }
        }
        return true;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        if (block.getType() == Material.CRYING_OBSIDIAN) {
            event.setDropItems(false);

            ItemStack drop = enderiteItems.enderiteBlock();
            block.getWorld().dropItemNaturally(block.getLocation(), drop);

            placedOres.remove(block.getLocation());
        }
    }
}