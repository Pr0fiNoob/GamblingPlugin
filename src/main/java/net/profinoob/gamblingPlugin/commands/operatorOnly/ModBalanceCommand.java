package net.profinoob.gamblingPlugin.commands.operatorOnly;

import net.profinoob.gamblingPlugin.GamblingPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ModBalanceCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        // Check if Command is run by Console
        if (!(sender instanceof ConsoleCommandSender)) {
            // Check if Command is run by a player which has permission
            if (!(sender.isOp() || sender.hasPermission("gambling.mod.balance"))) {
                sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
                return true;
            }
        }

        // Check if Arguments are correct
        if (args.length != 3) {
            sender.sendMessage(ChatColor.RED + "Invalid arguments! Usage: /modbalance <player> <add/remove/set> <amount>");
            return true;
        }

        // Get target player
        Player target = sender.getServer().getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage(ChatColor.RED + "Player " + args[0] + " not found!");
            return true;
        }

        // Get amount
        int amount;
        try {
            amount = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            sender.sendMessage(ChatColor.RED + "Invalid amount! Please enter a valid Integer.");
            return true;
        }

        // Perform action based on argument
        switch (args[1].toLowerCase()) {
            case "add":
                GamblingPlugin.getPlugin().getMoneyManager().addMoney(target, amount);
                sender.sendMessage("Added " + amount + " coins to " + target.getName() + "' balance");
                GamblingPlugin.getPlugin().getComponentLogger().info("{} added {} coins to {}'s balance", sender.getName(), amount, target.getName());
                break;

            case "remove":
                GamblingPlugin.getPlugin().getMoneyManager().removeMoney(target, amount);
                sender.sendMessage("Removed " + amount + " coins from " + target.getName() + "' balance");
                GamblingPlugin.getPlugin().getComponentLogger().info("{} removed {} coins from {}'s balance", sender.getName(), amount, target.getName());
                break;

            case "set":
                GamblingPlugin.getPlugin().getMoneyManager().setBalance(target, amount);
                sender.sendMessage("Set " + target.getName() + "'s balance to " + amount + " coins");
                GamblingPlugin.getPlugin().getComponentLogger().info("{} set {}'s balance to {} coins", sender.getName(), target.getName(), amount);
                break;

            default:
                sender.sendMessage(ChatColor.RED + "Invalid argument! Usage: /modbalance <player> <add/remove/set> <amount>");
                break;
        }

        return true;
    }
}
