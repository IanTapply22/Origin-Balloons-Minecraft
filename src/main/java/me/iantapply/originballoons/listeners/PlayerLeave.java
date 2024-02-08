package me.iantapply.originballoons.listeners;

import me.iantapply.originballoons.OriginBalloons;
import me.iantapply.originballoons.builders.Balloon;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeave implements Listener {

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Balloon playerBalloon = OriginBalloons.getPlayerBalloon(event.getPlayer().getUniqueId());
        if (playerBalloon != null) {
            playerBalloon.destroy();
        }
    }
}
