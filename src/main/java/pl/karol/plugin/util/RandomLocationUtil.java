package pl.karol.plugin.util;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Random;

public class RandomLocationUtil {

    private static final Random random = new Random();
    public static Location pickLocation(Player origin) {
        double minDistance = 2000 + random.nextInt(1000);

        final Location dropLocation = origin.getLocation().clone();
        dropLocation.add(minDistance, 256, minDistance);
        return dropLocation;
    }
}
