package real.peha.fun;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.bukkit.WorldCreator;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldType;
import org.bukkit.command.CommandSender;

public class Worlds {
    public static void generate(Map<?, ?> worldConfig) throws Exception {
        generate(null, worldConfig);
    }

    public static void generate(CommandSender sender, Map<?, ?> worldConfig) throws Exception {
        String worldId = worldConfig.get("id").toString();

        if (worldId.equals("logs") || worldId.equals("world") || worldId.equals("world_nether") || worldId.equals("world_the_end")) {
            throw new Exception("Нельзя создать мир с такими названием");
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
                throw new Exception("Указано неправильное окружение");
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
                throw new Exception("Указан неправильный тип мира");
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
            throw new Exception("Произошла ошибка при генерации мира");
        }
    }

    public static List<Map<?, ?>> getWorldsList() {
        return Config.getSection("worlds");
    }

    public static Map<?, ?> find(String worldId) {
        return Config.find("worlds", "id", worldId);
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
        Config.deleteFromList("worlds", "id", worldId);
    }

    public static void loadWorlds() {
        loadWorlds(null);
    }

    public static void loadWorlds(CommandSender sender) {
        List<Map<?, ?>> worlds = Worlds.getWorldsList();

        for (Map<?, ?> world: worlds) {
            Worlds.load(sender, world.get("id").toString());
        }
    }

    public static void load(String worldId) {
        load(null, worldId);
    }

    public static void load(CommandSender sender, String worldId) {
        Map<?, ?> world = find(worldId);

        if (world != null) {
            try {
                generate(sender, world);
            } catch (Exception ex) {
                Bukkit.getLogger().warning(worldId + ": " + ex.getMessage());
            }
        }
    }

    public static void unload(String worldId) {
        Bukkit.getServer().unloadWorld(worldId, true);
    }

    public static void unload(String worldId, Boolean save) {
        Bukkit.getServer().unloadWorld(worldId, save);
    }
}
