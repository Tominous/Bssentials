package bssentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CmdInfo(onlyPlayer = true, aliases = {"echest", "ender"})
public class Enderchest extends BCommand {

    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String[] args) {
        Player p = (Player) sender;
        if (args.length == 0) {
            p.openInventory(p.getEnderChest());
            return true;
        } else if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null || !target.isOnline()) {
                p.sendMessage(ChatColor.RED + "Player not found");
                return true;
            }
            p.sendMessage("Opening " + target.getName() + "'s enderchest.");
            p.openInventory(target.getEnderChest());
            return true;
        }
        return true;
    }

}