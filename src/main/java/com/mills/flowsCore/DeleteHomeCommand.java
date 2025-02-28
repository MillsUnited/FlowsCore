package com.mills.flowsCore;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class DeleteHomeCommand implements CommandExecutor {

    private Main main;
    private HomesManager homesManager;

    public DeleteHomeCommand(Main main) {
        this.main = main;
        this.homesManager = this.main.getHomesManager();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            UUID uuid = player.getUniqueId();

            String home = args[0];

            if (args.length == 0) {
                player.sendMessage(ChatColor.RED + "invalid home, please provide a correct home!");
                return true;
            }

            homesManager.deleteHome(uuid, home);
            player.sendMessage(ChatColor.RED + "deleted home " + home);
        }

        return false;
    }
}
