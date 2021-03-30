package real.peha.fun.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import real.peha.fun.BaseCommand;
import real.peha.fun.Worlds;

public class WorldUnloadCommand implements BaseCommand {
    public Boolean execute(CommandSender sender, String command, String alias, String[] args) {
        if (args.length == 0) {
            return false;
        }

        String worldId = args[0];

        if (Bukkit.getWorld(worldId) == null) {
            sender.sendMessage("Этот мир не загружен");

            return true;
        }

        Worlds.unload(worldId);

        sender.sendMessage("Упех");

        return true;
    }
}
