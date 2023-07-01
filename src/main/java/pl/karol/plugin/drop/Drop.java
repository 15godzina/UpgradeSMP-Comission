package pl.karol.plugin.drop;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public final class Drop {
    private final String name;
    private final List<DropItem> items;
    private final Location dropOffLocation;

    public Drop(String name, List<DropItem> items, Location dropOffLocation) {
        this.name = name;
        this.items = items;
        this.dropOffLocation = dropOffLocation;
    }

    public String getName() {
        return name;
    }

    public List<DropItem> getItems() {
        return items;
    }

    public Location getDropOffLocation() {
        return dropOffLocation;
    }
}
