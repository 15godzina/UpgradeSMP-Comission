package pl.karol.plugin.inventory;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import pl.karol.plugin.PaperPlugin;
import pl.karol.plugin.inventory.item.*;
import pl.karol.plugin.util.TextUtil;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.SimpleItem;
import xyz.xenondevs.invui.window.Window;

public class BottleInventory {
    private final PaperPlugin plugin;
    private final Gui gui;

    public BottleInventory(PaperPlugin plugin) {
        this.plugin = plugin;

        gui = Gui.normal() // Creates the GuiBuilder for a normal GUI
                .setStructure(
                        "# # # # # # # # #",
                        "# . . . } . . . #",
                        "# . < ( % ) > . #",
                        "# . . . { . . . #",
                        "# # # # # # # # #")
                .addIngredient('#', new SimpleItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)))
                .addIngredient('<', new HealthItem())
            .addIngredient('%', new EnchantItem())
                .addIngredient('>', new DropOffItem(plugin.getDropConfig()))
            .addIngredient('(', new ArrowRightItem())
            .addIngredient(')', new ArrowLeftItem())
            .addIngredient('}', new ArrowDownItem())
            .addIngredient('{', new ArrowUpItem())
                .build();
    }


    public void open(Player player) {

        Window window = Window.single()
                .setViewer(player)
                .setTitle(TextUtil.translate("&bMagiczna butelka"))
                .setGui(gui)
                .build();

        window.open();

    }
}
