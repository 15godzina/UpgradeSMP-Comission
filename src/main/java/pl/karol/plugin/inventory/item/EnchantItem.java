package pl.karol.plugin.inventory.item;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import pl.karol.plugin.util.TextUtil;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.AbstractItem;

import java.util.Map;
import java.util.concurrent.RecursiveTask;

public class EnchantItem extends AbstractItem {
    public Map<Enchantment, Integer> enchantments = Map.of(
            Enchantment.DAMAGE_ALL, 8,
            Enchantment.PROTECTION_ENVIRONMENTAL, 8,
            Enchantment.LOOT_BONUS_MOBS, 6,
            Enchantment.DIG_SPEED, 8,
            Enchantment.LOOT_BONUS_BLOCKS, 10,
            Enchantment.FIRE_ASPECT, 8,
            Enchantment.DURABILITY, 10
    );

    private final ItemStack bottleOfExp = new ItemBuilder(Material.EXPERIENCE_BOTTLE).
            setDisplayName(TextUtil.translate("&bMagiczna butelka"))
            .get();
    @Override
    public ItemProvider getItemProvider() {
        return new ItemBuilder(Material.ENCHANTING_TABLE)
                .setDisplayName(TextUtil.translate("&cUlepsz swoj item!"));
    }

    @Override
    public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent inventoryClickEvent) {
        ItemStack ih = inventoryClickEvent.getCursor();
        if (ih.getType() == Material.AIR) {
            player.sendMessage(TextUtil.translate("&cMasz pusto w łapce"));
            return;
        }
        ItemStack itemStack = ih.clone();
        if (!isUpgradeableItem(itemStack)) {

            player.sendMessage(TextUtil.translate("&cNie moge ulepszyc twojego przedmiotu"));
            return;
        }


        for (Enchantment enchantment : itemStack.getEnchantments().keySet()) {
            int currentLevel = itemStack.getEnchantmentLevel(enchantment);// Pobierz docelowy poziom ulepszenia (domyślnie 0)

            if (currentLevel < enchantments.get(enchantment)) { // Sprawdź, czy docelowy poziom ulepszenia jest większy niż aktualny
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.addEnchant(enchantment, currentLevel + 1, true);
                itemStack.setItemMeta(itemMeta);

                player.sendMessage(TextUtil.translate("&aPomyślnie ulepszono Twój przedmiot!"));
            }
        }

        player.setItemOnCursor(itemStack);
        player.getInventory().removeItem(bottleOfExp.asOne());


    }

    private boolean isUpgradeableItem(ItemStack item) {
        for (Enchantment enchantment : this.enchantments.keySet()) {
            if (item.getEnchantments().containsKey(enchantment)) {
                int maxLevel = ((Integer)this.enchantments.get(enchantment)).intValue();
                int currentLevel = item.getEnchantmentLevel(enchantment);
                if (currentLevel < maxLevel)
                    return true;
            }
        }
        return false;
    }
}
