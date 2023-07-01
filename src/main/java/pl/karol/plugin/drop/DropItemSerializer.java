package pl.karol.plugin.drop;

import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import org.bukkit.inventory.ItemStack;

public class DropItemSerializer implements ObjectSerializer<DropItem> {
    @Override
    public boolean supports( Class<? super DropItem> type) {
        return DropItem.class.isAssignableFrom(type);
    }

    @Override
    public void serialize( DropItem object,  SerializationData data,  GenericsDeclaration generics) {
        data.add("item", object.getItem());
        data.add("chance", object.getChance());
    }

    @Override
    public DropItem deserialize( DeserializationData data,  GenericsDeclaration generics) {
        return new DropItem(
                data.get("item", ItemStack.class),
                data.get("chance", Double.class)
        );
    }
}
