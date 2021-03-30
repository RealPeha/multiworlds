package real.peha.fun.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import real.peha.fun.BaseCommand;
import real.peha.fun.Worlds;

public class WorldLoadCommand implements BaseCommand {
    public Boolean execute(CommandSender sender, String command, String alias, String[] args) {
        if (args.length == 0) {
            Worlds.loadWorlds(sender);

            sender.sendMessage("Миры загружены успешно");

            return true;
        }

        String worldId = args[0];

        if (Bukkit.getWorld(worldId) != null) {
            sender.sendMessage("Этот мир уже загружен");

            return true;
        }

        try {
            sender.sendMessage("Загружаю мир...");

            Worlds.load(sender, worldId);

            sender.sendMessage("Успех");
        } catch (Exception ex) {
            sender.sendMessage(ex.getMessage());
        }

        return true;
    }
}
