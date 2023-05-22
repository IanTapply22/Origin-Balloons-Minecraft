package me.iantapply.originballoons;

import me.iantapply.originballoons.listeners.PlayerJoin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class OriginBalloons extends JavaPlugin {

    // Get the plugin instance
    private static OriginBalloons instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("OriginBalloons has been enabled!");
        instance = this;
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("OriginBalloons has been disabled!");
    }

    public static OriginBalloons getInstance() {
        return instance;
    }
}
