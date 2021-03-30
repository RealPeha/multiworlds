package real.peha.fun.commands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import org.bukkit.command.CommandSender;

import real.peha.fun.BaseCommand;
import real.peha.fun.Worlds;

public class WorldCreateCommand implements BaseCommand {
    private class Args {
        @Parameter(names = {"--type", "-t"})
        private String type = null;

        @Parameter(names = {"--env", "-e"})
        private String env = null;

        @Parameter(names = {"--generator", "-g"})
        private String generator = null;

        @Parameter(names = {"--flat", "-f"})
        private String generatorSettings = null;

        @Parameter(names = {"--structures", "-s"})
        private Boolean hasStructures = false;
    }

    public Boolean execute(CommandSender sender, String command, String alias, String[] args) {
        if (args.length == 0 || args[0].startsWith("-")) {
            return false;
        }

        String worldId = args[0].trim();

        if (Worlds.isExist(worldId)) {
            sender.sendMessage("Такой мир уже существует");

            return true;
        }

        Args commandArgs = new Args();

        JCommander.newBuilder()
            .addObject(commandArgs)
            .build()
            .parse(Arrays.copyOfRange(args, 1, args.length));

        Map<String, Object> worldConfig = new HashMap<>();

        worldConfig.put("id", worldId);
        worldConfig.put("type", commandArgs.type);
        worldConfig.put("env", commandArgs.env);
        worldConfig.put("hasStructures", commandArgs.hasStructures);
        worldConfig.put("generatorSettings", commandArgs.generatorSettings);
        worldConfig.put("generator", commandArgs.generator);

        String result = Worlds.generate(sender, worldConfig);

        if (result != null) {
            sender.sendMessage(result);

            return true;
        }

        Worlds.addToList(worldConfig);

        return true;
    }
}
