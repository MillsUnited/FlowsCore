package com.mills.flowsCore.Enderite;

import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class EnderiteBreak implements Listener {

    private EnderiteItems enderiteItems;

    public EnderiteBreak(EnderiteItems enderiteItems) {
        this.enderiteItems =  enderiteItems;
    }

    @EventHandler
    public void onEnderiteBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        Block block = e.getBlock();

        if (player.getGameMode() == GameMode.SURVIVAL) {
            if (enderiteItems.enderiteBlock().equals(block)) {
                player.getInventory().addItem(enderiteItems.enderiteBlock());
            }
        }
    }
}
