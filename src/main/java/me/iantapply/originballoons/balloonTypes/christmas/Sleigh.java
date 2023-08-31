package me.iantapply.originballoons.balloonTypes.christmas;

import me.iantapply.originballoons.balloonTypes.BalloonModel;
import me.iantapply.originballoons.balloonTypes.BalloonType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Sleigh implements BalloonType {
    @Override
    public int nodeCount() {
        return 2;
    }

    @Override
    public double distanceBetweenNodes() {
        return 2.0;
    }

    @Override
    public ItemStack headNodeItem() {
        return BalloonModel.createBlankModel(Material.LEATHER_HORSE_ARMOR, 10279);
    }

    @Override
    public ItemStack bodyNodeItem() {
        return new ItemStack(Material.STICK);
    }

    @Override
    public ItemStack tailNodeItem() {
        return BalloonModel.createBlankModel(Material.LEATHER_HORSE_ARMOR, 10278);
    }
}
