package pl.karol.plugin.inventory.item;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import pl.karol.plugin.drop.DropConfig;
import pl.karol.plugin.drop.DropItem;
import pl.karol.plugin.util.RandomDropsUtil;
import pl.karol.plugin.util.RandomLocationUtil;
import pl.karol.plugin.util.TextUtil;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.AbstractItem;

import java.util.List;

public class DropOffItem extends AbstractItem {
    private final DropConfig dropConfig;
    private final ItemStack bottleOfExp = new ItemBuilder(Material.EXPERIENCE_BOTTLE).
            setDisplayName(TextUtil.translate("&bMagiczna butelka"))
            .get();

    public DropOffItem(DropConfig dropConfig) {
        this.dropConfig = dropConfig;
    }

    @Override
    public ItemProvider getItemProvider() {
        return new ItemBuilder(Material.CHEST)
                .setDisplayName(TextUtil.translate("&cOdpal zrzut!"))
                .addLoreLines(TextUtil.translate("&fKoszt: &c6 butelek"));
    }
    @Override
    public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent inventoryClickEvent) {

        if (!player.getInventory().containsAtLeast(bottleOfExp, 6)) {
            player.sendMessage(TextUtil.translate("&cNie masz 6 magicznych butelek"));
            return;
        }
        if (dropConfig.drops.size() < 5) {
            player.sendMessage(TextUtil.translate("&cNIesttety admin rtozjebal serwer:P"));
            return;
        }


        Location dropOffLocation = RandomLocationUtil.pickLocation(player);

        List<DropItem> pickedDrops = RandomDropsUtil.pickRandomDrops(dropConfig.drops, 6);


        Location chestLocation = dropOffLocation
                .clone()
                .getWorld()
                .getHighestBlockAt(dropOffLocation)
                .getLocation()
                .add(0.0, 1.0 , 0.0);
        chestLocation.getBlock().setType(Material.CHEST);
        Chest chest = (Chest) chestLocation.getBlock().getState();
        Inventory inventory = chest.getInventory();

        pickedDrops.forEach(dropItem -> {
            inventory.addItem(dropItem.getItem());
        });

        player.getInventory().removeItem(bottleOfExp.asQuantity(6));
        Bukkit.getOnlinePlayers().forEach(players -> players.sendMessage(TextUtil.translate("&cNa kordynatach: X:" + chestLocation.getX() + " Y:" + chestLocation.getY() + " Z:" + chestLocation.getZ() + " spadl zrzut!")));




    }
}
