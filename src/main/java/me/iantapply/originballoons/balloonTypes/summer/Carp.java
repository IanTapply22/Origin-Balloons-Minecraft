package me.iantapply.originballoons.balloonTypes.summer;

import me.iantapply.originballoons.balloonTypes.BalloonModel;
import me.iantapply.originballoons.balloonTypes.BalloonType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Carp implements BalloonType {
    @Override
    public int nodeCount() {
        return 4;
    }

    @Override
    public double distanceBetweenNodes() {
        return 1.5;
    }

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
    public ItemStack headNodeItem() {
        return BalloonModel.createBlankModel(Material.LEATHER_HORSE_ARMOR, 10214);
    }

    @Override
    public ItemStack bodyNodeItem() {
        return BalloonModel.createBlankModel(Material.LEATHER_HORSE_ARMOR, 10215);
    }

    @Override
    public ItemStack tailNodeItem() {
        return BalloonModel.createBlankModel(Material.LEATHER_HORSE_ARMOR, 10216);
    }
}
