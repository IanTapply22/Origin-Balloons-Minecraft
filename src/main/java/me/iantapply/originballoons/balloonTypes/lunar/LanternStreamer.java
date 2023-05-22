package me.iantapply.originballoons.balloonTypes.lunar;

import me.iantapply.originballoons.balloonTypes.BalloonType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class LanternStreamer implements BalloonType {
    @Override
    public int nodeCount() {
        return 10;
    } // TODO

    @Override
    public double distanceBetweenNodes() {
        return 2.0;
    } // TODO

    @Override
    public double headNodeOffset() {
        return 0.0;
    }

    @Override
    public double bodyNodeOffset() {
        return 0.0;
    }

    @Override
    public double tailNodeOffset() {
        return 0.0;
    }

    @Override
    public double maxNodeJointAngle() {
        return 35.0;
    }

    @Override
    public double yAxisInterpolation() {
        return 0.35;
    }

    @Override
    public double turningSplineInterpolation() {
        return 0.50;
    }

    @Override
    public ItemStack headNodeItem() {
        return new ItemStack(Material.STICK);
    }

    @Override
    public ItemStack bodyNodeItem() {
        return new ItemStack(Material.STICK);
    }

    @Override
    public ItemStack tailNodeItem() {
        return new ItemStack(Material.STICK);
    }
}
