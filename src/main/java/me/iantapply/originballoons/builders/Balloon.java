package me.iantapply.originballoons.builders;

import me.iantapply.originballoons.OriginBalloons;
import me.iantapply.originballoons.balloonTypes.BalloonType;
import me.iantapply.originballoons.nodes.Node;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Balloon {

    private final BalloonType balloonType;
    private final Player balloonOwner;
    private Node tentacle;

    public BalloonType getBalloonType() {
        return balloonType;
    }

    public Player getBalloonOwner() {
        return balloonOwner;
    }

    /**
     * Initializes the balloons functionality
     */
    public void initialize() {
        Location balloonOwnerLocation = getBalloonOwner().getLocation();

        // Things that only need to be set up once and not looped over
        Node current = new Node((float) balloonOwnerLocation.getX(), (float) balloonOwnerLocation.getY() + 2, (float) balloonOwnerLocation.getZ(), (float) ((float) balloonType.distanceBetweenNodes() + balloonType.tailNodeOffset()), 0, getBalloonType(), balloonOwner, balloonType.maxNodeJointAngle(), balloonType.yAxisInterpolation(), balloonType.turningSplineInterpolation());

        // Create a new node for each node in the balloon type
        for (int i = 1; i < balloonType.nodeCount(); i++) {
            Node next;
            if (i == balloonType.nodeCount() - 1) {
                next = new Node(current, (float) ((float) balloonType.distanceBetweenNodes() + balloonType.headNodeOffset()), i, getBalloonType(), balloonOwner, balloonType.maxNodeJointAngle(), balloonType.yAxisInterpolation(), balloonType.turningSplineInterpolation());
            } else {
                next = new Node(current, (float) ((float) balloonType.distanceBetweenNodes() + balloonType.bodyNodeOffset()), i, getBalloonType(), balloonOwner, balloonType.maxNodeJointAngle(), balloonType.yAxisInterpolation(), balloonType.turningSplineInterpolation());
            }
            current.child = next;
            current = next;
        }
        tentacle = current;
    }

    /**
     * Runs the balloons' functionality that needs to loop infinitely
     */
    public void run() {

        long timeInTicks = 1;
        new BukkitRunnable() {

            @Override
            public void run() {
                Location balloonOwnerLocation = balloonOwner.getLocation();

                // Constantly teleport the
                tentacle.follow((float) balloonOwnerLocation.getX(), (float) balloonOwnerLocation.getY() + 2, (float) balloonOwnerLocation.getZ());
                tentacle.show();

                // Make the other segments follow
                Node next = tentacle.parent;
                while (next != null) {
                    next.follow();
                    next.show();

                    next = next.parent;
                }
            }
        }.runTaskTimer(OriginBalloons.getInstance(), timeInTicks, timeInTicks);
    }

    Balloon(BalloonBuilder builder) {
        this.balloonType = builder.balloonType;
        this.balloonOwner = builder.balloonOwner;
    }
}
