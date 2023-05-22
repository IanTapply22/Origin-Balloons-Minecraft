package me.iantapply.originballoons.builders;

import me.iantapply.originballoons.balloonTypes.BalloonType;
import org.bukkit.entity.Player;

public class BalloonBuilder {

    BalloonType balloonType;
    Player balloonOwner;

    public BalloonBuilder(BalloonType balloonType, Player balloonOwner) {
        this.balloonType = balloonType;
        this.balloonOwner = balloonOwner;
    }

    public Balloon build() {
        return new Balloon(this);
    }
}
