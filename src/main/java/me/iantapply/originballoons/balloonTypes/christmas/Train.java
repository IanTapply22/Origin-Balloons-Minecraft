package me.iantapply.originballoons.balloonTypes.christmas;

import me.iantapply.originballoons.balloonTypes.BalloonModel;
import me.iantapply.originballoons.balloonTypes.BalloonType;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class Train implements BalloonType {
    @Override
    public int nodeCount() {
        return 5;
    }

    @Override
    public double distanceBetweenNodes() {
        return 2.0;
    }

    @Override
    public ItemStack headNodeItem() {
        return BalloonModel.createColouredModel(Material.LEATHER_HORSE_ARMOR, 222, 149, 191, 10280);
    }

    @Override
    public ItemStack bodyNodeItem() {
        return BalloonModel.createColouredModel(Material.LEATHER_HORSE_ARMOR, 222, 149, 191, 10281);
    }

    @Override
    public ItemStack tailNodeItem() {
        return BalloonModel.createColouredModel(Material.LEATHER_HORSE_ARMOR, 222, 149, 191, 10282);
    }
}
