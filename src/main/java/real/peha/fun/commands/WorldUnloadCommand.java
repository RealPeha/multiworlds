package real.peha.fun.commands;

import org.bukkit.command.CommandSender;

import real.peha.fun.BaseCommand;
import real.peha.fun.Worlds;

public class WorldUnloadCommand implements BaseCommand {
    public Boolean execute(CommandSender sender, String command, String alias, String[] args) {
        if (args.length == 0) {
            return false;
        }

        Worlds.unload(args[0]);

        return true;
    }
}
