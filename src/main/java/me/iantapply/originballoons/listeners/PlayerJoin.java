package me.iantapply.originballoons.listeners;

import me.iantapply.originballoons.balloonTypes.christmas.Train;
import me.iantapply.originballoons.balloonTypes.lunar.LunarDragon;
import me.iantapply.originballoons.builders.Balloon;
import me.iantapply.originballoons.builders.BalloonBuilder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Balloon trainBalloon = new BalloonBuilder(new Train(), event.getPlayer()).build();
        trainBalloon.initialize();
        trainBalloon.run();
//        Balloon lunarDragonBalloon = new BalloonBuilder(new LunarDragon(), event.getPlayer()).build();
//        lunarDragonBalloon.initialize();
//        lunarDragonBalloon.run();
    }
}
