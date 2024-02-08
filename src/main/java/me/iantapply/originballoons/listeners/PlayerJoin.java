package me.iantapply.originballoons.listeners;

import me.iantapply.originballoons.OriginBalloons;
import me.iantapply.originballoons.balloonTypes.BalloonType;
import me.iantapply.originballoons.builders.Balloon;
import me.iantapply.originballoons.builders.BalloonBuilder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (OriginBalloons.getPlayerBalloon(event.getPlayer().getUniqueId()) != null) {
            BalloonType balloonType = OriginBalloons.getPlayerBalloon(event.getPlayer().getUniqueId()).getBalloonType();

            OriginBalloons.removePlayerBalloon(event.getPlayer().getUniqueId());

            BalloonBuilder builder = new BalloonBuilder(balloonType, event.getPlayer());
            Balloon playerBalloon = builder.build();

            OriginBalloons.setPlayerBalloon(event.getPlayer().getUniqueId(), playerBalloon);
            playerBalloon.initialize();
            playerBalloon.run();
        }
    }
}
