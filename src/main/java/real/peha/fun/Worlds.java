package real.peha.fun;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.bukkit.WorldCreator;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldType;
import org.bukkit.command.CommandSender;

public class Worlds {
    public static String generate(Map<?, ?> worldConfig) {
        return generate(null, worldConfig);
    }

    public static String generate(CommandSender sender, Map<?, ?> worldConfig) {
        String worldId = worldConfig.get("id").toString();

        if (worldId.equals("logs") || worldId.equals("world") || worldId.equals("world_nether") || worldId.equals("world_the_end")) {
            return "Нельзя создать мир с такими названием";
        }

        Object type = worldConfig.get("type");
        Object generator = worldConfig.get("generator");
        Object generatorSettings = worldConfig.get("generatorSettings");
        Object hasStructures = worldConfig.get("hasStructures");
        Object env = worldConfig.get("env");

        WorldCreator wc = new WorldCreator(worldId);

        if (!Objects.isNull(env)) {
            try {
                World.Environment environment = World.Environment.valueOf(env.toString().toUpperCase());

                wc.environment(environment);
            } catch (Exception ex) {
                return "Указано неправильное окружение";
            }
        }

        if (!Objects.isNull(type)) {
            try {
                WorldType worldType = WorldType.valueOf(type.toString().toUpperCase());

                wc.type(worldType);

                if (worldType == WorldType.FLAT && !Objects.isNull(generatorSettings)) {
                    wc.generatorSettings(generatorSettings.toString());
                }
            } catch (Exception ex) {
                return "Указан неправильный тип мира";
            }
        }

        if (!Objects.isNull(generator)) {
            wc.generator(generator.toString(), sender);
        }

        if (!Objects.isNull(hasStructures)) {
            wc.generateStructures((Boolean) hasStructures);
        }

        try {
            wc.createWorld();
        } catch (Exception ex) {
            return "Произошла ошибка при генерации мира";
        }

        return null;
    }

    public static List<Map<?, ?>> getWorldsList() {
        return Config.getSection("worlds");
    }

    public static Map<?, ?> find(String worldId) {
        List<Map<?, ?>> worlds = getWorldsList();

        for (Map<?, ?> world : worlds) {
            String id = world.get("id").toString().trim();

            if (id.equals(worldId)) {
                return world;
            }
        }

        return null;
    }

    public static Boolean isExist(String worldId) {
        return find(worldId) != null;
    }

    public static void addToList(Map<String, Object> worldConfig) {
        List<Map<?, ?>> worlds = getWorldsList();

        worlds.add(worldConfig);

        Config.set("worlds", worlds);
    }

    public static void deleteFromList(String worldId) {
        List<Map<?, ?>> worlds = getWorldsList();

        for (Iterator<Map<?, ?>> iter = worlds.iterator(); iter.hasNext(); ) {
            Map<?, ?> world = iter.next();
            String id = world.get("id").toString().trim();

            if (id.equals(worldId)) {
                iter.remove();
            }
        }

        Config.set("worlds", worlds);
    }

    public static void loadWorlds() {
        List<Map<?, ?>> worlds = Worlds.getWorldsList();

        for (Map<?, ?> world: worlds) {
            Worlds.load(world.get("id").toString());
        }
    }

    public static void load(String worldId) {
        load(null, worldId);
    }

    public static void load(CommandSender sender, String worldId) {
        Map<?, ?> world = find(worldId);

        if (world != null) {
            generate(sender, world);
        }
    }

    public static void unload(String worldId) {
        Bukkit.getServer().unloadWorld(worldId, true);
    }

    public static void unload(String worldId, Boolean save) {
        Bukkit.getServer().unloadWorld(worldId, save);
    }
}
