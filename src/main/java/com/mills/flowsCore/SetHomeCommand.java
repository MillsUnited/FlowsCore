package com.mills.flowsCore;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class SetHomeCommand implements CommandExecutor {

    private Main main;
    private HomesManager homesManager;

    public SetHomeCommand(Main main) {
        this.main = main;
        this.homesManager = main.getHomesManager();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            UUID uuid = player.getUniqueId();

            int getAmount = homesManager.getHomeamount(uuid);
            if (getAmount >= 3) {
                player.sendMessage(ChatColor.RED + "You have reached the max amount of homes (3)!");
                return true;
            }

            if (args.length == 0) {
                player.sendMessage(ChatColor.RED + "Invalid name, provide a name!");
                return true;
            }

            String home = args[0];
            int limit = 10;
            if (home.length() >= limit) {
                player.sendMessage(ChatColor.RED + "character limit reached, make your home >= 10 character limit!");
                return true;
            }
            double x = player.getLocation().getX();
            double y = player.getLocation().getY();
            double z = player.getLocation().getZ();
            World world = player.getWorld();

            homesManager.saveHome(uuid, world.getName(), home, x, y, z);
            player.sendMessage(ChatColor.RED + "set home " + home + "!");

        }
        return false;
    }
}
