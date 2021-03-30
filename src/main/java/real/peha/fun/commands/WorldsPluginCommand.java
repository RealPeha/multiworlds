package real.peha.fun.commands;

import java.util.Arrays;

import org.bukkit.command.CommandSender;

import real.peha.fun.BaseCommand;
import real.peha.fun.CommandsHandler;

public class WorldsPluginCommand implements BaseCommand {
    private CommandsHandler commandsHandler = new CommandsHandler();

    public WorldsPluginCommand() {
        commandsHandler.register(new String[]{"reload", "r"}, new ReloadCommand());
        commandsHandler.register(new String[]{"create", "c"}, new WorldCreateCommand());
        commandsHandler.register(new String[]{"list", "ls"}, new WorldListCommand());
        commandsHandler.register(new String[]{"delete", "d"}, new WorldDeleteCommand());
        commandsHandler.register(new String[]{"info", "i"}, new WorldInfoCommand());
        commandsHandler.register("load", new WorldLoadCommand());
        commandsHandler.register("unload", new WorldUnloadCommand());
        commandsHandler.register("bed", new WorldBedCommand());
    }

    public Boolean execute(CommandSender sender, String command, String alias, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("Yes");

            return true;
        }

        return commandsHandler.execute(sender, args[0], alias, Arrays.copyOfRange(args, 1, args.length));
    }
}
