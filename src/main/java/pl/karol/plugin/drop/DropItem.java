package pl.karol.plugin.drop;

import org.bukkit.inventory.ItemStack;

public class DropItem {
    private final ItemStack item;
    private final double chance;

    public DropItem(ItemStack item, double chance) {
        this.item = item;
        this.chance = chance;
    }

    public ItemStack getItem() {
        return item;
    }

    public double getChance() {
        return chance;
    }
}
