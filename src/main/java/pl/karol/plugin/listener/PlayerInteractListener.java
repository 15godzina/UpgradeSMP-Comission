package pl.karol.plugin.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import pl.karol.plugin.PaperPlugin;
import pl.karol.plugin.inventory.BottleInventory;
import pl.karol.plugin.util.TextUtil;
import xyz.xenondevs.invui.item.builder.ItemBuilder;

public class PlayerInteractListener implements Listener {
    private final PaperPlugin paperPlugin;
    private BottleInventory bottleInventory;
    private final ItemStack EXP_BOTTLE = new ItemBuilder(Material.EXPERIENCE_BOTTLE)
            .setDisplayName(TextUtil.translate("&bMagiczna butelka"))
            .get();

    public PlayerInteractListener(PaperPlugin paperPlugin) {
        this.paperPlugin = paperPlugin;

        this.bottleInventory = new BottleInventory(paperPlugin);
    }

    @EventHandler
    public void handle(PlayerInteractEvent event) {

        final Player player = event.getPlayer();

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            final ItemStack itemStack = event.getItem();
            if (itemStack == null) return;

            if (!EXP_BOTTLE.isSimilar(itemStack)) return;

            event.setCancelled(true);

            bottleInventory.open(player);
        }

    }
}
