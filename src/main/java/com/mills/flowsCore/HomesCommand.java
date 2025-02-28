package com.mills.flowsCore;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

public class HomesCommand implements CommandExecutor {

    private Main main;
    private HomesManager homesManager;

    public HomesCommand(Main main) {
        this.main = main;
        this.homesManager = this.main.getHomesManager();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            UUID uuid = player.getUniqueId();

            if (args.length == 0) {

                List<String> homeNames = homesManager.getHomeNames(uuid);

                if (homeNames.isEmpty()) {
                    player.sendMessage(ChatColor.RED + "You don't have any homes set.");
                } else {
                    player.sendMessage(ChatColor.GREEN + "Your active homes:");
                    for (String home : homeNames) {
                        player.sendMessage(ChatColor.YELLOW + "- " + home);
                    }
                }
                return true;
            }
        }
        return false;
    }
}
