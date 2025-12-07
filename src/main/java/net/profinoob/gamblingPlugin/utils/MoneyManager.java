package net.profinoob.gamblingPlugin.utils;

import net.profinoob.gamblingPlugin.GamblingPlugin;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

public class MoneyManager {

    private final GamblingPlugin plugin;
    private final File file;
    private final YamlConfiguration config;
    private final Map<UUID, Integer> balances;

    public MoneyManager() {
        this.plugin = GamblingPlugin.getPlugin();
        this.file = new File(plugin.getDataFolder(), "money.yml");
        this.balances = new java.util.concurrent.ConcurrentHashMap<>();

        // Create file if it doesn't exist
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.config = YamlConfiguration.loadConfiguration(file);
        loadData();
    }

    public int getBalance(Player player) {
        return balances.getOrDefault(player.getUniqueId(), 0);
    }

    public void addMoney(Player player, int amount) {
        balances.merge(player.getUniqueId(), amount, Integer::sum);
    }

    public void removeMoney(Player player, int amount) {
        balances.merge(player.getUniqueId(), -amount, Integer::sum);
    }

    public void setBalance(Player player, int amount) {
        balances.put(player.getUniqueId(), amount);
    }

    // Load from money.yml to HashMap
    private void loadData() {
        for (String key : config.getKeys(false)) {
            UUID uuid = UUID.fromString(key);
            int balance = config.getInt(key);
            balances.put(uuid, balance);
        }
    }

    // Save HashMap to money.yml
    public void saveData() {
        for (Map.Entry<UUID, Integer> entry : balances.entrySet()) {
            config.set(entry.getKey().toString(), entry.getValue());
        }
        try {
            config.save(file);
            GamblingPlugin.getPlugin().getComponentLogger().info("Saved balances to money.yml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}