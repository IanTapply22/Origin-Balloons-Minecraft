package me.iantapply.originballoons.balloonTypes.lunar;

import me.iantapply.originballoons.balloonTypes.BalloonModel;
import me.iantapply.originballoons.balloonTypes.BalloonType;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class LunarDragon implements BalloonType {
    @Override
    public int nodeCount() {
        return 5;
    }

    @Override
    public double distanceBetweenNodes() {
        return 1.7;
    }

    @Override
    public double headNodeOffset() {
        return 0.35;
    }

    @Override
    public double bodyNodeOffset() {
        return -0.3;
    }

    @Override
    public double tailNodeOffset() {
        return -0.35;
    }

    @Override
    public ItemStack headNodeItem() {
        return BalloonModel.createColouredModel(Material.LEATHER_HORSE_ARMOR, Color.RED, 10154);
    }

    @Override
    public ItemStack bodyNodeItem() {
        return BalloonModel.createColouredModel(Material.LEATHER_HORSE_ARMOR, Color.RED, 10155);
    }

    @Override
    public ItemStack tailNodeItem() {
        return BalloonModel.createColouredModel(Material.LEATHER_HORSE_ARMOR, Color.RED, 10156);
    }
}
