package me.iantapply.originballoons;

import me.iantapply.originballoons.balloonTypes.BalloonType;
import me.iantapply.originballoons.balloonTypes.christmas.Sleigh;
import me.iantapply.originballoons.balloonTypes.christmas.Train;
import me.iantapply.originballoons.balloonTypes.lunar.LunarDragon;
import me.iantapply.originballoons.balloonTypes.summer.Carp;
import me.iantapply.originballoons.builders.Balloon;
import me.iantapply.originballoons.commands.SpawnCommand;
import me.iantapply.originballoons.listeners.PlayerLeave;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class OriginBalloons extends JavaPlugin {

    // Get the plugin instance
    private static OriginBalloons instance;
    public static Balloon playerBalloon;
    public static final Map<String, BalloonType> balloonTypeMap = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("OriginBalloons has been enabled!");
        instance = this;

        // Register balloon types
        registerBalloons();

        Bukkit.getPluginManager().registerEvents(new PlayerLeave(), this);

        Objects.requireNonNull(this.getCommand("balloon")).setExecutor(new SpawnCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("OriginBalloons has been disabled!");
        playerBalloon.destroy();
        playerBalloon = null;
    }

    public void registerBalloons() {
        // Register balloon types here by name
        balloonTypeMap.put("train", new Train());
        balloonTypeMap.put("lunardragon", new LunarDragon());
        balloonTypeMap.put("carp", new Carp());
        balloonTypeMap.put("sleigh", new Sleigh());
    }

    public static OriginBalloons getInstance() {
        return instance;
    }
}
