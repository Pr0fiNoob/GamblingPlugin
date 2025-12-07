package net.profinoob.gamblingPlugin;

import net.profinoob.gamblingPlugin.commands.BalanceCommand;
import net.profinoob.gamblingPlugin.commands.ExchangeCommand;
import net.profinoob.gamblingPlugin.commands.GamblingCommand;
import net.profinoob.gamblingPlugin.commands.operatorOnly.ModBalanceCommand;
import net.profinoob.gamblingPlugin.games.GameManager;
import net.profinoob.gamblingPlugin.games.implementations.Coinflip;
import net.profinoob.gamblingPlugin.utils.MoneyManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public final class GamblingPlugin extends JavaPlugin {

    private static GamblingPlugin plugin;
    private MoneyManager moneyManager;
    private GameManager gameManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        // Gambling YEEEEEEE

        plugin = this;

        // Save default config if it doesn't exist
        saveDefaultConfig();
        // Copy any new defaults into existing configs (e.g. for newly added games)
        if (mergeMissingDefaults(getConfig())) {
            saveConfig();
        }

        // Initialize Manager classes
        moneyManager = new MoneyManager();
        gameManager = new GameManager();

        // Register commands
        getCommand("exchange").setExecutor(new ExchangeCommand());
        getCommand("balance").setExecutor(new BalanceCommand());
        getCommand("gamble").setExecutor(new GamblingCommand());
        getCommand("modbalance").setExecutor(new ModBalanceCommand());

        // Register TabCompleters
        // Yeah, I'm lazy so for now there will be no tab completion (unless someone finds this repo and adds it before me... pretty please?)

        // Register EventListeners



        // Register Games

        // Coinflip
        try {
            gameManager.register(new Coinflip());
        } catch (Exception e) {
            getLogger().severe("Failed to register Coinflip game!");
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        // Save balances to money.yml
        if (moneyManager != null) {
            moneyManager.saveData();
        }
    }

    public static GamblingPlugin getPlugin() {
        return plugin;
    }

    public MoneyManager getMoneyManager() {
        return moneyManager;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    private boolean mergeMissingDefaults(FileConfiguration config) {
        InputStream resourceStream = getResource("config.yml");
        if (resourceStream == null) {
            return false;
        }

        boolean updated = false;
        try (InputStreamReader reader = new InputStreamReader(resourceStream, StandardCharsets.UTF_8)) {
            YamlConfiguration defaults = YamlConfiguration.loadConfiguration(reader);
            for (String key : defaults.getKeys(true)) {
                if (!config.isSet(key)) {
                    config.set(key, defaults.get(key));
                    updated = true;
                }
            }
        } catch (Exception e) {
            getLogger().warning("Unable to merge default config values: " + e.getMessage());
        }

        return updated;
    }
}
