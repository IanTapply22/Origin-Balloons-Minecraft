package me.iantapply.originballoons.balloonTypes;

import org.bukkit.inventory.ItemStack;

public interface BalloonType {
    /**
     * Number of nodes/models within the balloon
     * @return The amount of nodes
     */
    int nodeCount();
    /**
     * Distance between nodes in blocks
     * @return The amount of blocks between nodes
     */
    double distanceBetweenNodes();
    /**
     * Amount of blocks to offset the head node.
     * This changes the length of the node/segment and can not be the same or greater than the distance between nodes.
     * @return The offset in blocks
     */
    double headNodeOffset();
    /**
     * Amount of blocks to offset the body node.
     * This changes the length of the node/segment and can not be the same or greater than the distance between nodes.
     * @return The offset in blocks
     */
    double bodyNodeOffset();
    /**
     * Amount of blocks to offset the tail node.
     * This changes the length of the node/segment and can not be the same or greater than the distance between nodes.
     * @return The offset in blocks
     */
    double tailNodeOffset();
    /**
     * The maximum angle/degrees of freedom a node joint has.
     * @return The maximum angle/degrees of freedom a node joint has.
     */
    double maxNodeJointAngle();
    /**
     * Interpolation between nodes on the Y axis.
     * MUST BE A NUMBER BETWEEN 0 AND 1
     * @return The interpolation between nodes on the Y axis
     */
    double yAxisInterpolation();
    /**
     * The interpolation for the turning spline.
     * @return The interpolation for the turning spline.
     */
    double turningSplineInterpolation();

    // Item that's on the head of the head, body, and tail node armor stands.
    // These items should have the CustomModelData tag for custom models.
    // Most colourable balloons will use dyed leather horse armor to change the colour.
    ItemStack headNodeItem();
    ItemStack bodyNodeItem();
    ItemStack tailNodeItem();
}
