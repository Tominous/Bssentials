package bssentials.listeners;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import bssentials.Bssentials;
import bssentials.api.User;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player plr = e.getPlayer();
        Bssentials bss = Bssentials.get();
        User user = User.getByName(plr.getName());

        if (!plr.hasPlayedBefore()) {
            user.getConfig().set("uuid", plr.getUniqueId().toString());
            user.save();
            if (!bss.getWarps().isSpawnSet()) {
                plr.sendMessage(ChatColor.RED + "Spawn has not been set!");
            } else {
                try {
                    plr.sendMessage(ChatColor.GREEN + "Warping to spawn");
                    bss.teleportPlayerToWarp(plr, "spawn");
                } catch (NumberFormatException | IOException ex) {
                    ex.printStackTrace();
                    plr.sendMessage(ChatColor.RED + "Unable to find spawn: " + ex.getMessage());
                }
            }
            Bukkit.broadcastMessage(ChatColor.GRAY + " Please welcome " + plr.getName() + " to the server!");
        }

        if (!user.nick.equalsIgnoreCase("_null_")) {
            plr.sendMessage(ChatColor.GRAY + "Nickname changed to: " + user.nick);
            plr.setDisplayName(user.nick);
        }
    }

}