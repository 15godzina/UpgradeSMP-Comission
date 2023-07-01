package pl.karol.plugin.inventory.item;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import pl.karol.plugin.util.TextUtil;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.AbstractItem;
import xyz.xenondevs.invui.item.impl.SimpleItem;

import java.util.UUID;

public class HealthItem extends AbstractItem {
    private final ItemStack bottleOfExp = new ItemBuilder(Material.EXPERIENCE_BOTTLE).
            setDisplayName(TextUtil.translate("&bMagiczna butelka"))
            .get();
    @Override
    public ItemProvider getItemProvider() {
        return new ItemBuilder(Material.HEART_OF_THE_SEA)
                .setDisplayName(TextUtil.translate("&cKup nowe serce!"));
    }

    @Override
    public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent inventoryClickEvent) {
        if (player.getMaxHealth() == 40.0) {
            inventoryClickEvent.getClickedInventory().close();
            player.sendMessage(TextUtil.translate("&cPosiadasz maksymalną liczbe serc!"));
            return;
        }
        player.getInventory().removeItem(bottleOfExp.asOne());
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).addModifier(new AttributeModifier(player.getName(), 2.0, AttributeModifier.Operation.ADD_NUMBER));
        player.sendMessage(TextUtil.translate("&cKupiles nastepne serce!"));
    }
}
