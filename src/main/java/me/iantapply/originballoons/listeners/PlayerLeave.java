package me.iantapply.originballoons.listeners;

import me.iantapply.originballoons.OriginBalloons;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeave implements Listener {

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        if (OriginBalloons.playerBalloon != null) {
            OriginBalloons.playerBalloon.destroy();
            OriginBalloons.playerBalloon = null;
        }

    }
}
