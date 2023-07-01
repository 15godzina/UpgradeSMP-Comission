package pl.karol.plugin.drop;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import org.bukkit.Material;
import pl.karol.plugin.util.TextUtil;
import xyz.xenondevs.invui.item.builder.ItemBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DropConfig extends OkaeriConfig {

    @Comment("Tutaj są zapisywane dropy.")
    public List<DropItem> drops = Arrays.asList(
            new DropItem(new ItemBuilder(Material.DIAMOND)
                    .setDisplayName(TextUtil.translate("&cTwuj stary goło fiut"))
                    .get(),
                    0.4)
    );
}
