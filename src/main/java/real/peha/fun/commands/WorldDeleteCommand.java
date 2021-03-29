package real.peha.fun.commands;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;

import real.peha.fun.BaseCommand;
import real.peha.fun.Worlds;

public class WorldDeleteCommand implements BaseCommand {
    public Boolean execute(CommandSender sender, String command, String alias, String[] args) {
        if (args.length == 0) {
            return false;
        }

        String worldId = args[0];
        World world = Bukkit.getServer().getWorld(worldId);

        if (world != null) {
            Worlds.unload(worldId, false);

            File folder = world.getWorldFolder();

            deleteDirectory(folder);
        }

        Worlds.delete(worldId);

        return true;
    }

    private void deleteDirectory(File path) {
        if (path.exists()) {
            File[] files = path.listFiles();

            for(int i = 0; i < files.length; i++) {
                File file = files[i];

                if (file.isDirectory()) {
                    deleteDirectory(file);
                } else {
                    file.delete();
                }
            }

            path.delete();
        }
    }
}
