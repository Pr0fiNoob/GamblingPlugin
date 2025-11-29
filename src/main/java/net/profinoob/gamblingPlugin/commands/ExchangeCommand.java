package net.profinoob.gamblingPlugin.commands;

import net.profinoob.gamblingPlugin.GamblingPlugin;
import net.profinoob.gamblingPlugin.utils.ItemExchange;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ExchangeCommand implements CommandExecutor {

    private final List<ItemExchange> itemExchanges = new ArrayList<>();

    public ExchangeCommand() {
        loadExchanges();
    }


    private void loadExchanges() {
        // Get Exchanges
        ConfigurationSection exchangeConfig = GamblingPlugin.getPlugin().getConfig().getConfigurationSection("exchanges");

        // Confirm Exchanges Exist
        if (exchangeConfig == null || exchangeConfig.getKeys(false).isEmpty()) {
            GamblingPlugin.getPlugin().getComponentLogger().warn("No exchanges found in config.yml!");
            return;
        }

        // Check for invalid Materials
        for (String key : exchangeConfig.getKeys(false)) {
            Material mat = Material.getMaterial(key.toUpperCase());
            if (mat == null) {
                GamblingPlugin.getPlugin().getComponentLogger().warn("Skipping unknown Material: {}", key);
                continue;
            }

            // Convert key-value pairs to ItemExchange objects
            int value = exchangeConfig.getInt(key);
            itemExchanges.add(new ItemExchange(mat, value));
        }
    }

    private String getFormattedName(Material material) {
        if (material == null) return "";
        return java.util.Arrays.stream(material.name().toLowerCase().split("_"))
                .map(word -> Character.toUpperCase(word.charAt(0)) + word.substring(1))
                .collect(java.util.stream.Collectors.joining(" ")) + "(s)";
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        // Check if Command is executed properly
        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatColor.RED + "Only players can execute this command!");
            return true;
        }

        // Check if Player has permission to execute command
        if (!player.hasPermission("gambling.exchange")) {
            player.sendMessage(ChatColor.RED + "You don't have permission to use this!");
            return true;
        }

        if (args.length != 0) {
            sender.sendMessage(ChatColor.RED + "This command doesn't require arguments!");
            return true;
        }

        // Get Player's held item
        ItemStack heldItem = player.getInventory().getItemInMainHand();

        // Check if the Player wants to scam us with air (I'm allowed to make jokes, right? right?)
        if (heldItem.getType() == Material.AIR) {
            player.sendMessage(ChatColor.BLUE + "Really? You expect me to pay you for " + ChatColor.BOLD + "Nothing" + ChatColor.BLUE + "?");
            return true;
        }

        // Iterate through Exchanges and check if the Player has the right item
        for (ItemExchange exchange : itemExchanges) {
            if (exchange.getMaterial() == heldItem.getType()) {
                int amount = heldItem.getAmount();
                int payout = exchange.getValue() * amount;
                player.sendMessage(ChatColor.GREEN + "You have successfully exchanged " + ChatColor.BOLD + amount + " " + getFormattedName(heldItem.getType()) + ChatColor.GREEN + " for " + ChatColor.BOLD + payout + " coins!");

                // remove item from player's inventory and add payout to balance
                player.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
                GamblingPlugin.getPlugin().getMoneyManager().addMoney(player, payout);
                GamblingPlugin.getPlugin().getComponentLogger().info("{} exchanged {} {} for {} coins", player.getName(), amount, getFormattedName(heldItem.getType()), payout);
                return true;
            }
        }

        player.sendMessage(ChatColor.BLUE + "Hmmm... I don't know what to give you for that...");
        return true;
    }
}
