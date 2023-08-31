package me.iantapply.originballoons.balloonTypes;

import org.bukkit.inventory.ItemStack;

public interface BalloonType {

    /**
     * The name of the balloon that will be shown in the GUI
     * @return The name of the balloon
     */
    default String balloonName(){return "Example Balloon";}

    /**
     * The description of the balloon that will be shown in the GUI
     * @return The description of the balloon
     */
    default String balloonDescription(){return "Example Description";}

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
    default double headNodeOffset(){return 0.0;}

    /**
     * Amount of blocks to offset the body node.
     * This changes the length of the node/segment and can not be the same or greater than the distance between nodes.
     * @return The offset in blocks
     */
    default double bodyNodeOffset(){return 0.0;}

    /**
     * Amount of blocks to offset the tail node.
     * This changes the length of the node/segment and can not be the same or greater than the distance between nodes.
     * @return The offset in blocks
     */
    default double tailNodeOffset(){return 0.0;}

    /**
     * The maximum angle/degrees of freedom a node joint has.
     * @return The maximum angle/degrees of freedom a node joint has.
     */
    default double maxNodeJointAngle(){return 35.0;}

    /**
     * Interpolation between nodes on the Y axis.
     * MUST BE A NUMBER BETWEEN 0 AND 1
     * @return The interpolation between nodes on the Y axis
     */
    default double yAxisInterpolation(){return 0.35;}

    /**
     * The interpolation for the turning spline.
     * @return The interpolation for the turning spline.
     */
    default double turningSplineInterpolation(){return 0.50;}

    // Item that's on the head of the head, body, and tail node armor stands.
    // These items should have the CustomModelData tag for custom models.
    // Most colourable balloons will use dyed leather horse armor to change the colour.
    ItemStack headNodeItem();
    ItemStack bodyNodeItem();
    ItemStack tailNodeItem();
}
