package pl.karol.plugin.util;

import pl.karol.plugin.drop.DropItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomDropsUtil {
    private static final Random random = new Random();
    public static List<DropItem> pickRandomDrops(List<DropItem> drops, int dropsNum) {
        List<DropItem> selectedElements = new ArrayList<>();
        int totalPercentage = getTotalPercentage(drops);

        for (int i = 1; i < dropsNum; i++) {
            int randomNumber = random.nextInt(totalPercentage) + 1;
            int cumulativePercentage = 0;

            for (DropItem drop : drops) {
                cumulativePercentage += drop.getChance();
                if (randomNumber <= cumulativePercentage) {
                    selectedElements.add(drop);
                    break;
                }
            }
        }

        return selectedElements;
    }

    public static int getTotalPercentage(List<DropItem> drops) {
        int totalPercentage = 0;

        for (DropItem drop : drops) {
            totalPercentage += drop.getChance();
        }

        return totalPercentage;
    }

    public static boolean picked() {
        if (random.nextDouble() <= 0.4) {
            return true;
        }
        return false;
    }
}

