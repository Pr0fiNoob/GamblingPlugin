package net.profinoob.gamblingPlugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class GamblingPlugin extends JavaPlugin {

    private static GamblingPlugin plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static GamblingPlugin getPlugin() {
        return plugin;
    }
}
