package real.peha.fun;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.bukkit.configuration.file.FileConfiguration;

public class Config {
    private static FileConfiguration config() {
        return WorldsPlugin.getInstance().getConfig();
    }

    public static void set(String path, Object value) {
        config().set(path, value);
    }

    public static boolean has(String path) {
        return config().contains(path);
    }

    public static List<Map<?, ?>> getSection(String path) {
        return config().getMapList(path);
    }

    public static Set<String> getSectionKeys(String path) {
        return config().getConfigurationSection(path).getKeys(false);
    }

    public static int getInt(String path) {
        return config().getInt(path);
    }

    public static double getDouble(String path) {
        return config().getDouble(path);
    }

    public static boolean getBoolean(String path) {
        return config().getBoolean(path);
    }

    public static String getString(String path) {
        return config().getString(path);
    }

    public static List<String> getStringList(String path) {
        return config().getStringList(path);
    }

    public static String getRandomStringList(String path) {
        List<String> list = getStringList(path);

        if (list.isEmpty()) {
            return "";
        }

        int n = new Random().nextInt(list.size());
        
        return list.get(n);
    }

    public static Map<?, ?> find(String path, String key, String value) {
        List<Map<?, ?>> listMap = getSection(path);

        for (Map<?, ?> map : listMap) {
            String mapValue = map.get(key).toString();

            if (mapValue.equals(value)) {
                return map;
            }
        }

        return null;
    }

    public static void deleteFromList(String path, String key, String value) {
        List<Map<?, ?>> listMap = getSection(path);

        for (Iterator<Map<?, ?>> iter = listMap.iterator(); iter.hasNext(); ) {
            Map<?, ?> map = iter.next();
            String mapValue = map.get(key).toString();

            if (mapValue.equals(value)) {
                iter.remove();
            }
        }

        Config.set(path, listMap);
    }

    public static void addToList(String path, Map<String, Object> map) {
        List<Map<?, ?>> listMap = getSection(path);

        listMap.add(map);
        config().set(path, listMap);
    }

    public static void addToStringList(String path, String value) {
        List<String> list = getStringList(path);

        list.add(value);
        config().set(path, list);
    }
}
