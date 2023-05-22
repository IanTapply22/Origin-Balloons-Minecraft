package me.iantapply.originballoons.nodes;

import lombok.Getter;
import lombok.Setter;
import me.iantapply.originballoons.OriginBalloons;
import me.iantapply.originballoons.balloonTypes.BalloonType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import java.util.Objects;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Node {

    NodeVector pointA;
    NodeVector pointB = new NodeVector();

    public Node parent = null;
    public Node child = null;

    ArmorStand balloonArmorStand;

    float index;
    float length;
    BalloonType balloonType;
    Player balloonOwner;
    double maxNodeJointAngle;
    double yAxisInterpolation;
    double turningSplineInterpolation;

    /**
     * Builder for creating lead segment.
     * @param x X axis position.
     * @param z Z axis position.
     * @param length Length of segment.
     * @param index Index number of segment.
     */
    public Node(float x, float y, float z, float length, int index, BalloonType balloonType, Player balloonOwner, double maxNodeJointAngle, double yAxisInterpolation, double turningSplineInterpolation) {
        this.length = length;
        this.index = index;
        this.balloonType = balloonType;
        this.balloonOwner = balloonOwner;
        this.pointA = new NodeVector(x, y, z);
        this.maxNodeJointAngle = maxNodeJointAngle;
        this.yAxisInterpolation = yAxisInterpolation;
        this.turningSplineInterpolation = turningSplineInterpolation;

        balloonArmorStand = Objects.requireNonNull(OriginBalloons.getInstance().getServer().getWorld("world")).spawn(new Location(OriginBalloons.getInstance().getServer().getWorld("world"), x, balloonOwner.getLocation().getY() + 1, z), ArmorStand.class); // Change Y value to the right height
        balloonArmorStand.setVisible(false);

        float dx = length * (float)cos(0);
        float dz = length * (float)sin(0);
        this.pointB.set(this.pointA.x + dx, this.pointA.y, this.pointA.z + dz);
    }

    /**
     * Builder for following segments.
     * @param parent Leading segment.
     * @param length Length of segment.
     * @param index Index number.
     */
    public Node(Node parent, float length, int index, BalloonType balloonType, Player balloonOwner, double maxNodeJointAngle, double yAxisInterpolation, double turningSplineInterpolation) {
        this.parent = parent;
        this.pointA = parent.pointB.copy();
        this.length = length;
        this.index = index;
        this.balloonType = balloonType;
        this.balloonOwner = balloonOwner;
        this.maxNodeJointAngle = maxNodeJointAngle;
        this.yAxisInterpolation = yAxisInterpolation;
        this.turningSplineInterpolation = turningSplineInterpolation;

        balloonArmorStand = Objects.requireNonNull(OriginBalloons.getInstance().getServer().getWorld("world")).spawn(new Location(OriginBalloons.getInstance().getServer().getWorld("world"), this.pointA.x, balloonOwner.getLocation().getY() + 1, this.pointA.z), ArmorStand.class); // Change Y value to the right height
        balloonArmorStand.setVisible(false);

        float dx = length * (float)cos(0);
        float dz = length * (float)sin(0);
        this.pointB.set(this.pointA.x + dx, this.pointA.z, this.pointA.z + dz);
    }

    /**
     * Follows to the child's point A location.
     */
    public void follow() {
        float targetX = this.child.pointB.x;
        float targetY = this.child.pointB.y;
        float targetZ = this.child.pointB.z;
        follow(targetX, targetY, targetZ);
    }

    /**
     * Makes point A follow to the desired location.
     * @param targetX Target X axis.
     * @param targetY Target Y axis.
     * @param targetZ Target Z axis.
     */
    public void follow(float targetX, float targetY, float targetZ) {
        NodeVector target = new NodeVector(targetX, targetY, targetZ);
        NodeVector dir = NodeVector.subtract(target, pointB);

        double targetAngle = dir.heading();
        if (this.child != null){
            double childAngle = child.heading();

            double difference = Math.abs(targetAngle - childAngle);

            if (difference > Math.PI) {
                difference = 2 * Math.PI - difference;
            }

            int sign = (targetAngle - childAngle >= 0 && targetAngle - childAngle <= Math.toRadians(180)) || (targetAngle - childAngle <= Math.toRadians(-180) && targetAngle - childAngle >= Math.toRadians(-360)) ? 1 : -1;

            double maxAngle = Math.toRadians(this.maxNodeJointAngle); // Change this based on how much freedom you want (35 is a good number)

            if (difference > maxAngle){
                targetAngle = (childAngle + (maxAngle * sign)) % (Math.PI * 2.0);
            }
        }

        pointA = target;

        // Smoothly interpolate the angle
        double currentAngle = this.heading();
        double interpolatedAngle = lerpAngle(currentAngle, targetAngle, this.turningSplineInterpolation); // Higher = snappier Lower = less snappy

        double interpolatedDx = length * cos((float) interpolatedAngle);
        double interpolatedDz = length * sin((float) interpolatedAngle);

        double interpolatedY = lerpVal(this.pointB.y, this.pointA.y, this.yAxisInterpolation); // Change this to make it smoother or to make it more sudden, this seems to be the sweet spot

        pointB.set((float) (pointA.x - interpolatedDx), (float) interpolatedY, (float) (pointA.z - interpolatedDz));
    }

    /**
     * Linear interpolation function for angles.
     * @param startAngle The starting angle in radians.
     * @param endAngle The ending angle in radians.
     * @param t The interpolation factor, ranging from 0 to 1.
     * @return The interpolated angle between startAngle and endAngle.
     */
    private double lerpAngle(double startAngle, double endAngle, double t) {
        double difference = endAngle - startAngle;
        if (difference > Math.PI) {
            difference -= 2 * Math.PI;
        } else if (difference < -Math.PI) {
            difference += 2 * Math.PI;
        }
        return startAngle + t * difference;
    }

    /**
     * Linear interpolation function for values.
     * @param startVal The starting value.
     * @param endVal The ending value.
     * @param t The interpolation factor, ranging from 0 to 1.
     * @return The interpolated value between startVal and endVal.
     */
    private double lerpVal(double startVal, double endVal, double t) {
        double difference = endVal - startVal;
        return startVal + t * difference;
    }

    public float heading(){
        return NodeVector.subtract(pointA, pointB).heading();
    }

    /**
     * Calculates the head pose of the armor stand.
     * @param pointA The first point.
     * @param pointB The second point.
     * @return The Euler angle of the armor stand.
     */
    public EulerAngle calculateHeadPose(Vector pointA, Vector pointB) {
        Location loc = new Location(Bukkit.getWorld("world"), pointA.getX(), pointA.getY(), pointA.getZ());
        loc.setDirection(pointB.subtract(pointA));
        Vector direction = loc.getDirection();

        double yaw = Math.atan2(-direction.getX(), direction.getZ()) + Math.toRadians(180.0);
        double pitch = Math.asin(direction.getY());
        double roll = 0.0;

        return new EulerAngle(pitch, yaw, roll);
    }

    /**
     * Sets the correct position and item in the armor stand.
     */
    public void show() {
        /*
         * This will set the head of the armor stand based on the balloon type
         */
        if (index == this.balloonType.nodeCount() - 1) {
            this.balloonArmorStand.setItem(EquipmentSlot.HEAD, this.balloonType.headNodeItem());
        } else if (index == 0) {
            this.balloonArmorStand.setItem(EquipmentSlot.HEAD, this.balloonType.tailNodeItem());
        } else {
            this.balloonArmorStand.setItem(EquipmentSlot.HEAD, this.balloonType.bodyNodeItem());

        }

        Vector pointAVector = new Vector(this.pointA.x, this.pointA.y, this.pointA.z);
        Vector pointBVector = new Vector(this.pointB.x, this.pointB.y, this.pointB.z);

        // Set the direction for the armor stand to face
        this.balloonArmorStand.setHeadPose(calculateHeadPose(pointAVector, pointBVector));
        // Teleport it to the correct location
        this.balloonArmorStand.teleport(new Location(Bukkit.getWorld("world"), (pointA.x + pointB.x) / 2.0, (pointA.y + pointB.y) / 2.0, (pointA.z + pointA.z) / 2.0)); // Change Y value to the right height
    }

    public void remove() {
        this.balloonArmorStand.setHealth(0.0);
    }
}
