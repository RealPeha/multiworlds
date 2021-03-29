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
    @Parameter(
        names = {"--type", "-t"},
        description = "Test"
    )
    private String type = "normal";

    @Parameter(
        names = {"--env", "-e"},
        description = "Test"
    )
    private int env = 0;

    @Parameter(
        names = {"--flat", "-f"},
        description = "Test"
    )
    private String generatorSettings = "2;0;1;";

    @Parameter(
        names = {"--structures", "-s"},
        description = "Test"
    )
    private Boolean hasStructures = false;

    public Boolean execute(CommandSender sender, String command, String alias, String[] args) {
        if (args.length == 0 || args[0].startsWith("-")) {
            return false;
        }

        String worldId = args[0].trim();

        if (Worlds.isExist(worldId)) {
            sender.sendMessage("Такой мир уже существует");

            return true;
        }

        JCommander.newBuilder()
            .addObject(this)
            .build()
            .parse(Arrays.copyOfRange(args, 1, args.length));

        Map<String, Object> worldConfig = new HashMap<>();

        worldConfig.put("id", worldId);
        worldConfig.put("type", type);
        worldConfig.put("env", env);
        worldConfig.put("hasStructures", hasStructures);

        if (type == "flat") {
            worldConfig.put("generatorSettings", generatorSettings);
        }

        Worlds.generate(worldConfig);

        Worlds.addToList(worldConfig);

        return true;
    }
}
