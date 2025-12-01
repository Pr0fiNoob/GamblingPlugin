package net.profinoob.gamblingPlugin.games;

import net.profinoob.gamblingPlugin.GamblingPlugin;
import org.bukkit.configuration.ConfigurationSection;

public abstract class Game {

    // Variables
    private String name;
    private String displayName;
    private ConfigurationSection config;
    private int minBet;
    private int maxBet;


    // Constructors
    public Game(String name, int minBet, int maxBet) {
        this.name = name;
        this.config = GamblingPlugin.getPlugin().getConfig().getConfigurationSection("games." + name);

        // Check if Config Exists
        if (config == null || config.getKeys(false).isEmpty()) {
            GamblingPlugin.getPlugin().getComponentLogger().error("No correct config found for game: {}! It will not work!", name);
        }

        this.displayName = config.getString("display-name");
        this.minBet = config.getInt("min-bet");
        this.maxBet = config.getInt("max-bet");

    }

    // Methods
    public abstract void play();


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
}
