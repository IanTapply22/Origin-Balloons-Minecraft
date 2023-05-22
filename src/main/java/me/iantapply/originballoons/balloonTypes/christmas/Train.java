package me.iantapply.originballoons.balloonTypes.christmas;

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
        ItemStack item = new ItemStack(Material.LEATHER_HORSE_ARMOR);
        LeatherArmorMeta itemMeta = (LeatherArmorMeta) item.getItemMeta();
        itemMeta.setColor(Color.fromRGB(222, 149, 191));
        itemMeta.setCustomModelData(10280);
        item.setItemMeta(itemMeta);
        return item;
    }

    @Override
    public ItemStack bodyNodeItem() {
        ItemStack item = new ItemStack(Material.LEATHER_HORSE_ARMOR);
        LeatherArmorMeta itemMeta = (LeatherArmorMeta) item.getItemMeta();
        itemMeta.setColor(Color.fromRGB(222, 149, 191));
        itemMeta.setCustomModelData(10281);
        item.setItemMeta(itemMeta);
        return item;
    }

    @Override
    public ItemStack tailNodeItem() {
        ItemStack item = new ItemStack(Material.LEATHER_HORSE_ARMOR);
        LeatherArmorMeta itemMeta = (LeatherArmorMeta) item.getItemMeta();
        itemMeta.setColor(Color.fromRGB(222, 149, 191));
        itemMeta.setCustomModelData(10282);
        item.setItemMeta(itemMeta);
        return item;
    }
}
