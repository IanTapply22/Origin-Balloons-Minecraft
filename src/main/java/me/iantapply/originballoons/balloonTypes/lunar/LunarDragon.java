package me.iantapply.originballoons.balloonTypes.lunar;

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
    } // TODO

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
        ItemStack item = new ItemStack(Material.LEATHER_HORSE_ARMOR);
        LeatherArmorMeta itemMeta = (LeatherArmorMeta) item.getItemMeta();
        itemMeta.setColor(Color.fromRGB(255, 0, 0));
        itemMeta.setCustomModelData(10154);
        item.setItemMeta(itemMeta);
        return item;
    }

    @Override
    public ItemStack bodyNodeItem() {
        ItemStack item = new ItemStack(Material.LEATHER_HORSE_ARMOR);
        LeatherArmorMeta itemMeta = (LeatherArmorMeta) item.getItemMeta();
        itemMeta.setColor(Color.fromRGB(255, 0, 0));
        itemMeta.setCustomModelData(10155);
        item.setItemMeta(itemMeta);
        return item;
    }

    @Override
    public ItemStack tailNodeItem() {
        ItemStack item = new ItemStack(Material.LEATHER_HORSE_ARMOR);
        LeatherArmorMeta itemMeta = (LeatherArmorMeta) item.getItemMeta();
        itemMeta.setColor(Color.fromRGB(255, 0, 0));
        itemMeta.setCustomModelData(10156);
        item.setItemMeta(itemMeta);
        return item;
    }
}
