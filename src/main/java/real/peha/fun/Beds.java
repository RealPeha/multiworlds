package real.peha.fun;

import java.util.Map;

import org.bukkit.block.Block;

public class Beds {
    public static String getId(Block bed) {
        return bed.getWorld().getName()
            + Integer.toString(bed.getX())
            + Integer.toString(bed.getY())
            + Integer.toString(bed.getZ());
    }

    public static Map<?, ?> find(String bedId) {
        return Config.find("beds", "id", bedId);
    }

    public static void addToList(Map<String, Object> bedConfig) {
        Config.addToList("beds", bedConfig);
    }

    public static void deleteFromList(String bedId) {
        Config.deleteFromList("beds", "id", bedId);
    }
}
