package com.mills.flowsCore;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.text.Normalizer;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class ServerEvents implements Listener {

    private Main main;

    public ServerEvents(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onEyeInteract(PlayerInteractEvent e) {

        boolean endUnlocked = main.getConfig().getBoolean("end-unlocked", false);
        Player player = e.getPlayer();

        if (!endUnlocked) {
            if (player.getInventory().getItemInMainHand().getType() == Material.ENDER_EYE) {
                e.setCancelled(true);
                player.sendMessage(ChatColor.RED + "The end is not unlocked yet!");
            }
        }
    }

    @EventHandler
    public void onBlockedCommand(PlayerCommandPreprocessEvent e) {
        List<String> blockedCommand = List.of(
                "/pl",
                "/plugins",
                "/version",
                "/?",
                "/help",
                "/bukkit:help",
                "/bukkit:plugins",
                "/me",
                "/minecraft:me");

        String command = e.getMessage().toLowerCase().split(" ")[0];
        if (blockedCommand.contains(command)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onTabComplete(PlayerCommandSendEvent e) {
        List<String> blockedCommand = List.of(
                "/pl",
                "/plugins",
                "/version",
                "/?",
                "/help",
                "/bukkit:help",
                "/bukkit:plugins",
                "/me",
                "/minecraft:me");

        e.getCommands().removeIf(cmd -> blockedCommand.contains(cmd.toLowerCase()));
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        List<String> flagged = List.of("nigger", "nigga", "faggot", "fag", "negro", "niger");

        String playerMessage = e.getMessage().toLowerCase().replaceAll("\\s+", "");

        Map<String, String> symbolSubstitutions = Map.of(
                "!", "i",
                "@", "a",
                "$", "s",
                "1", "i",
                "3", "e",
                "0", "o",
                "7", "t",
                "9", "g"
        );

        for (Map.Entry<String, String> entry : symbolSubstitutions.entrySet()) {
            playerMessage = playerMessage.replace(entry.getKey(), entry.getValue());
        }

        playerMessage = Normalizer.normalize(playerMessage, Normalizer.Form.NFD);
        playerMessage = playerMessage.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        playerMessage = playerMessage.replaceAll("[^a-z]", "");

        for (String word : flagged) {
            StringBuilder regex = new StringBuilder();
            regex.append(word.charAt(0)); // First character
            for (int i = 1; i < word.length(); i++) {
                regex.append("[a-z]?").append(word.charAt(i)); // Allow one extra character
            }

            if (Pattern.compile(regex.toString()).matcher(playerMessage).find()) {
                Player player = e.getPlayer();
                double damage = 1.0;
                e.setCancelled(true);
                player.setHealth(Math.max(0, player.getHealth() - damage));
                player.sendMessage(ChatColor.RED + "Why are you saying no no words in my Minecraft server!");
                return;
            }
        }
    }
}
