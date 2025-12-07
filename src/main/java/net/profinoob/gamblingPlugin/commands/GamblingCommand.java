package net.profinoob.gamblingPlugin.commands;

import net.profinoob.gamblingPlugin.GamblingPlugin;
import net.profinoob.gamblingPlugin.games.Game;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GamblingCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        // Check if Command is run by a player which has permission

        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can execute this command!");
            return true;
        }

        if (!(player.hasPermission("gambling.play") || player.hasPermission("gambling.play.coinflip"))) {
            player.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
            return true;
        }

        if (args.length == 0) {
            player.sendMessage(ChatColor.BLUE + "GUI is coming soon!");
            return true;
        }

        Game game;
        switch (args[0].toLowerCase()) {
            case "games":
                player.sendMessage(ChatColor.BLUE + "Available games: ");
                player.sendMessage(ChatColor.BLUE + String.valueOf(GamblingPlugin.getPlugin().getGameManager().getAll().keySet()));
                return true;

                // Add more games here
            case "coinflip":
                game = GamblingPlugin.getPlugin().getGameManager().get("coinflip");
                break;

            default:
                player.sendMessage(ChatColor.RED + "Invalid game!");
                return true;
        }

        // If config failed, game might be null
        if (game == null) {
            player.sendMessage(ChatColor.RED + "This game is currently disabled due to an error.");
            return true;
        }

        // Check if there are enough args for game
        if (!(args.length == 1 + game.getArgumentCount())) {
            player.sendMessage(ChatColor.RED + "Invalid arguments! Usage: " + game.getUsage());
            return true;
        }

        try {
            game.play(player, Integer.parseInt(args[1]));
        } catch (NumberFormatException e) {
            player.sendMessage(ChatColor.RED + "Invalid bet! Please enter a valid number.");
        }

        return true;
    }
}
