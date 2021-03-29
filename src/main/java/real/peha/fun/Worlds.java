package real.peha.fun;

import java.util.List;
import java.util.Map;

import org.bukkit.WorldCreator;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldType;

public class Worlds {
    public static void generate(Map<String, Object> worldConfig) {
        String worldId = worldConfig.get("id").toString();
        String type = worldConfig.get("type").toString();
        String generatorSettings = null;
        Boolean hasStructures = (Boolean) worldConfig.get("hasStructures");
        int env = (int) worldConfig.get("env");

        if (worldConfig.containsKey("generatorSettings")) {
            generatorSettings = worldConfig.get("generatorSettings").toString();
        }

        WorldCreator wc = new WorldCreator(worldId);

        World.Environment environment = World.Environment.NORMAL;

        if (env == 1) {
            environment = World.Environment.THE_END;
        } else if (env == -1) {
            environment = World.Environment.NETHER;
        }

        WorldType worldType = WorldType.getByName(type);

        wc.environment(environment);

        if (worldType != null) {
            wc.type(worldType);
        } else {
            wc.type(WorldType.NORMAL);
        }

        if (type == "flat") {
            if (generatorSettings == null) {

            } else {
                wc.generatorSettings(generatorSettings);
            }
        }

        wc.generateStructures(hasStructures);

        wc.createWorld();
    }

    public static List<Map<?, ?>> getWorldsList() {
        return Config.getSection("worlds");
    }

    public static Boolean isExist(String worldId) {
        List<Map<?, ?>> worlds = getWorldsList();

        for (Map<?, ?> world : worlds) {
            String id = world.get("id").toString().trim();

            if (id.equals(worldId)) {
                return true;
            }
        }

        return false;
    }

    public static void addToList(Map<String, Object> worldConfig) {
        List<Map<?, ?>> worlds = getWorldsList();

        worlds.add(worldConfig);

        Config.set("worlds", worlds);
    }

    public static void delete(String worldId) {
        List<Map<?, ?>> worlds = getWorldsList();

        for (Map<?, ?> world : worlds) {
            String id = world.get("id").toString().trim();

            if (id.equals(worldId)) {
                worlds.remove(world);
            }
        }

        Config.set("worlds", worlds);
    }

    public static void unload(String worldId) {
        Bukkit.getServer().unloadWorld(worldId, true);
    }

    public static void unload(String worldId, Boolean save) {
        Bukkit.getServer().unloadWorld(worldId, save);
    }
}
