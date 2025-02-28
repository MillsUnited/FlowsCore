package com.mills.flowsCore;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class EndlockCommand implements CommandExecutor {

    private Main main;

    public EndlockCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            UUID uuid = player.getUniqueId();
//            String flows = "de290521-fdcf-4829-b72e-9e9abe81b77b";
            String flows = "95b646ab-1d1c-4dbb-98fc-baa752362b0c";
            String flowsalt = "d6403b7e-a86b-4ec3-9e9f-8606821b9ed4";

            if (uuid.toString().equals(flows) || uuid.toString().equals(flowsalt)) {

                boolean endUnlocked = main.getConfig().getBoolean("end-unlocked", false);

                if (!endUnlocked) {
                    player.sendMessage(ChatColor.RED + "the end is already locked!");
                    return true;
                }

                main.getConfig().set("end-unlocked", false);
                main.saveConfig();
                player.sendMessage(ChatColor.RED + "locked the end");

            } else {
                player.sendMessage(ChatColor.RED + "you do not have permission to do this!");
            }
            return true;
        }
        sender.sendMessage("only flows can run this command!");

        return false;
    }
}
