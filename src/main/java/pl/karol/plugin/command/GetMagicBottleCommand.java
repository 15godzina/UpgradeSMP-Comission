package pl.karol.plugin.command;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import pl.karol.plugin.util.TextUtil;
import xyz.xenondevs.invui.item.builder.ItemBuilder;

public class GetMagicBottleCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        Player player = (Player) commandSender;

        if (!player.hasPermission("bagnoltd.bottle")) {
            player.sendMessage("Nie masz permisji :CCCCCC");
            return false;
        }

        ItemStack bottleOfExp = new ItemBuilder(Material.EXPERIENCE_BOTTLE).
                setDisplayName(TextUtil.translate("&bMagiczna butelka"))
                .get();
        player.getInventory().addItem(bottleOfExp);
        return false;
    }
}
