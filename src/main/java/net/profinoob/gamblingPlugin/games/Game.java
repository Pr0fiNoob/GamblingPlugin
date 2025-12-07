package net.profinoob.gamblingPlugin.games;

import net.profinoob.gamblingPlugin.GamblingPlugin;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public abstract class Game {

    // Variables
    private String name;
    private String displayName;
    private int minBet;
    private int maxBet;
    private int argumentCount;
    private String usage;


    // Constructors
    public Game(String name, int argumentCount, String usage) {
        this.name = name;
        this.argumentCount = argumentCount; // Number of required arguments
        this.usage = usage;
        ConfigurationSection config = GamblingPlugin.getPlugin().getConfig().getConfigurationSection("games." + name);

        // Check if Config Exists
        if (config == null || config.getKeys(false).isEmpty()) {
            GamblingPlugin.getPlugin().getComponentLogger().error("No correct config found for game: {}! It will not work!", name);
            throw new IllegalArgumentException("No correct config found for game: " + name + "!");
        }

        this.displayName = config.getString("display-name");
        this.minBet = config.getInt("min-bet");
        this.maxBet = config.getInt("max-bet");

        // Check if min-bet and max-bet are valid
        if (minBet < 1 || maxBet < minBet) {
            GamblingPlugin.getPlugin().getComponentLogger().error("Invalid min-bet or max-bet for game: {}! It will not work!", name);
            throw new IllegalArgumentException("Invalid min-bet or max-bet for game: " + name + "!");
        }
    }

    // Methods
    public abstract void play(Player player, int bet);

    protected boolean hasEnoughMoney(Player player, int bet) {
        return bet <= GamblingPlugin.getPlugin().getMoneyManager().getBalance(player);
    }


    // Getters
    public String getName() {
        return name;
    }

    public int getMinBet() {
        return minBet;
    }

    public int getMaxBet() {
        return maxBet;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getArgumentCount() {
        return argumentCount;
    }

    public String getUsage() {
        return usage;
    }
}
