package net.profinoob.gamblingPlugin.games.implementations;

import net.kyori.adventure.text.Component;
import net.profinoob.gamblingPlugin.GamblingPlugin;
import net.profinoob.gamblingPlugin.games.Game;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Coinflip extends Game {

    public Coinflip() {
        super("coinflip", 1, "/gamble coinflip <bet>");
    }

    @Override
    public void play(Player player, int bet) {

        // Check if bet is valid
        if (!(bet >= getMinBet() && bet <= getMaxBet())) {
            player.sendMessage(ChatColor.RED + "Bet must be between " + getMinBet() + " and " + getMaxBet() + "!");
            return;
        }

        if (!(hasEnoughMoney(player, bet))) {
            player.sendMessage(ChatColor.RED + "You don't have enough money to bet that amount!");
            return;
        }

        // Game logic
        boolean win = Math.random() < 0.5;
        player.sendMessage(ChatColor.BLUE + "You flipped a coin and it landed on " + (win ? "heads" : "tails") + "!");
        GamblingPlugin.getPlugin().getMoneyManager().removeMoney(player, bet);

        if (win) {
            GamblingPlugin.getPlugin().getMoneyManager().addMoney(player, (int) Math.round(bet * 1.5));
            Component message = Component.text(ChatColor.GREEN + "You won " + Math.round(bet * 1.5) + " coins!");
            player.sendMessage(message);
        } else {
            Component message = Component.text(ChatColor.RED + "You lost " + bet + " coins!");
            player.sendMessage(message);
        }

    }

}
