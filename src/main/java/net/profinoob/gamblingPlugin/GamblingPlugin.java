package net.profinoob.gamblingPlugin;

import net.profinoob.gamblingPlugin.commands.ExchangeCommand;
import net.profinoob.gamblingPlugin.utils.MoneyManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class GamblingPlugin extends JavaPlugin {

    private static GamblingPlugin plugin;
    private MoneyManager moneyManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        // Gambling YEEEEEEE

        plugin = this;

        // Save default config if it doesn't exist
        saveDefaultConfig();

        // Initialize MoneyManager
        moneyManager = new MoneyManager();

        // Register commands
        getCommand("exchange").setExecutor(new ExchangeCommand());
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


}
