package me.iantapply.originballoons.balloonTypes;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class BalloonModel {

    /**
     * Generates a coloured model with the specified colour
     * @param item The item type you want to generate (usually leather horse armor, because it's dyable)
     * @param colour The colour to dye the leather horse armor
     * @param customModelData The custom model data value
     * @return The generated item created with the corresponding metadata
     */
    public static ItemStack createColouredModel(Material item, Color colour, int customModelData) {
        ItemStack generatedItem = new ItemStack(item);
        LeatherArmorMeta generatedItemMeta = (LeatherArmorMeta) generatedItem.getItemMeta();
        generatedItemMeta.setColor(colour);
        generatedItemMeta.setCustomModelData(customModelData);
        generatedItem.setItemMeta(generatedItemMeta);
        return generatedItem;
    }

    /**
     * Generates a coloured model with the specified color derived from the given RGB values
     * @param item The item type you want to generate (usually leather horse armor, because it's dyable)
     * @param colourRed The red colour value in the RGB
     * @param colourGreen The green colour value in the RGB
     * @param colourBlue The blue colour value in the RGB
     * @param customModelData The custom model data value
     * @return The generated item created with the corresponding metadata
     */
    public static ItemStack createColouredModel(Material item, int colourRed, int colourGreen, int colourBlue, int customModelData) {
        ItemStack generatedItem = new ItemStack(item);
        LeatherArmorMeta generatedItemMeta = (LeatherArmorMeta) generatedItem.getItemMeta();
        generatedItemMeta.setColor(Color.fromRGB(colourRed, colourGreen, colourBlue));
        generatedItemMeta.setCustomModelData(customModelData);
        generatedItem.setItemMeta(generatedItemMeta);
        return generatedItem;
    }

    /**
     * Creates a model from any item without a specified colour
     * @param item The item type you want to generate
     * @param customModelData The custom mode data value
     * @return The generated item created with the corresponding metadata
     */
    public static ItemStack createBlankModel(Material item, int customModelData) {
        ItemStack generatedItem = new ItemStack(item);
        ItemMeta generatedItemMeta = generatedItem.getItemMeta();
        generatedItemMeta.setCustomModelData(customModelData);
        generatedItem.setItemMeta(generatedItemMeta);
        return generatedItem;
    }
}
