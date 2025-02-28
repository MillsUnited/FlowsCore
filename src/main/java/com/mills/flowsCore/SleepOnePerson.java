package com.mills.flowsCore;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class SleepOnePerson implements Listener {

    private Main main;

    public SleepOnePerson(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerSleep(PlayerBedEnterEvent e) {
        Player player = e.getPlayer();
        World world = player.getWorld();

        boolean isNight = world.getTime() >= 12542 && world.getTime() <= 23458;
        boolean isThundering = world.isThundering();

        if ((isNight || isThundering) && e.getBedEnterResult() == PlayerBedEnterEvent.BedEnterResult.OK) {

            world.setTime(0);
            world.setStorm(false);
            world.setThundering(false);
            world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);

            Bukkit.broadcastMessage(ChatColor.YELLOW + player.getName() + " has slept. Good morning!");
        }

    }


}
