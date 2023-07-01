package pl.karol.plugin.drop;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import pl.karol.plugin.PaperPlugin;
import pl.karol.plugin.util.TextUtil;

public class DropCommand implements CommandExecutor {
    private DropConfig dropConfig;
    private final PaperPlugin plugin;

    public DropCommand(PaperPlugin plugin) {
        this.plugin = plugin;
        this.dropConfig = plugin.getDropConfig();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) commandSender;

        if (!player.hasPermission("bagnoltd.adrop")) {

            player.sendMessage(TextUtil.translate("&cNie posiadasz permisji do wykonania tej komendy :C"));
            return false;
        }

        if (player.getItemInHand().getType() == Material.AIR) return false;
        // /adrop <add/clear>

        switch (args[0]) {
            case "add":
                double chance = Double.parseDouble(args[1]);
                ItemStack itemStack = player.getItemInHand();

                final DropItem dropItem = new DropItem(itemStack, chance);

                dropConfig.drops.add(dropItem);
                dropConfig.save();
                dropConfig.load();
                player.sendMessage(TextUtil.translate("&aPomyslnie dodales drop!"));
                break;
            case "clear":
                dropConfig.drops.clear();
                dropConfig.save();
                dropConfig.load();
                player.sendMessage(TextUtil.translate("&cPomyslnie wyczysciles wszystkie dropy!"));
                break;
        }
        return false;
    }
}
