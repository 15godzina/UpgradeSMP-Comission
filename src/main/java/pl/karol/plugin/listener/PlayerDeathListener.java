package pl.karol.plugin.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import pl.karol.plugin.util.RandomDropsUtil;
import pl.karol.plugin.util.TextUtil;
import xyz.xenondevs.invui.item.builder.ItemBuilder;

public class PlayerDeathListener implements Listener {
    private final ItemStack bottleOfExp = new ItemBuilder(Material.EXPERIENCE_BOTTLE).
            setDisplayName(TextUtil.translate("&bMagiczna butelka"))
            .get();

    @EventHandler
    public void handle(PlayerDeathEvent event) {
        Player killer = event.getEntity().getKiller();

        if (killer == null) return;

        if (!RandomDropsUtil.picked())   return;

        killer.getInventory().addItem(bottleOfExp.asOne());
    }
}
