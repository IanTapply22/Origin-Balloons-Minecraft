package me.iantapply.originballoons;

import lombok.Getter;
import lombok.Setter;
import me.iantapply.originballoons.balloonTypes.BalloonType;
import me.iantapply.originballoons.balloonTypes.christmas.Sleigh;
import me.iantapply.originballoons.balloonTypes.christmas.Train;
import me.iantapply.originballoons.balloonTypes.lunar.LunarDragon;
import me.iantapply.originballoons.balloonTypes.summer.Carp;
import me.iantapply.originballoons.builders.Balloon;
import me.iantapply.originballoons.commands.manager.CommandManager;
import me.iantapply.originballoons.listeners.PlayerJoin;
import me.iantapply.originballoons.listeners.PlayerLeave;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class OriginBalloons extends JavaPlugin {

    // Get the plugin instance
    @Getter @Setter
    private static OriginBalloons instance;
    private static final Map<UUID, Balloon> playerBalloons = new HashMap<>();
    public static final Map<String, BalloonType> balloonTypeMap = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("OriginBalloons has been enabled!");
        setInstance(this);

        new CommandManager(getInstance());

        // Register balloon types
        registerBalloons();

        Bukkit.getPluginManager().registerEvents(new PlayerLeave(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("OriginBalloons has been disabled!");
        // Destroy balloons for all players
        for (Balloon balloon : playerBalloons.values()) {
            balloon.destroy();
        }
        playerBalloons.clear();
    }

    public void registerBalloons() {
        // Register balloon types here by name
        balloonTypeMap.put("train", new Train());
        balloonTypeMap.put("lunardragon", new LunarDragon());
        balloonTypeMap.put("carp", new Carp());
        balloonTypeMap.put("sleigh", new Sleigh());
    }

    // Add a method to set a player's balloon
    public static void setPlayerBalloon(UUID playerId, Balloon balloon) {
        playerBalloons.put(playerId, balloon);
    }

    // Add a method to get a player's balloon
    public static Balloon getPlayerBalloon(UUID playerId) {
        return playerBalloons.get(playerId);
    }

    // Add a method to remove a player's balloon
    public static void removePlayerBalloon(UUID playerId) {
        Balloon balloon = playerBalloons.remove(playerId);
        if (balloon != null) {
            balloon.destroy();
        }
    }
}
