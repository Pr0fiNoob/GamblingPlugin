package net.profinoob.gamblingPlugin.commands;

import net.profinoob.gamblingPlugin.GamblingPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BalanceCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        // Check if Command is run by a player which has permission
        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatColor.RED + "Only players can execute this command!");
            return true;
        }

        if (!(player.hasPermission("gambling.balance"))) {
            player.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
            return true;
        }


        switch (args.length) {

            case 0:
                player.sendMessage(ChatColor.BLUE + "Your balance is: " + GamblingPlugin.getPlugin().getMoneyManager().getBalance(player) + " coins");
                break;

            case 1:
                Player target = GamblingPlugin.getPlugin().getServer().getPlayer(args[0]);
                if (target == null) {
                    player.sendMessage(ChatColor.BLUE + "Player " + args[0] + " not found!");
                    break;
                }

                player.sendMessage(ChatColor.BLUE + target.getName() + "'s balance is: " + GamblingPlugin.getPlugin().getMoneyManager().getBalance(target) + " coins");
                break;

            default:
            player.sendMessage(ChatColor.BLUE + "Usage: /balance <player (optional)>");
            break;
        }

        return true;
    }
}
