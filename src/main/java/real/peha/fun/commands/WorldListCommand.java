package real.peha.fun.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.command.CommandSender;

import real.peha.fun.BaseCommand;
import real.peha.fun.Worlds;

public class WorldListCommand implements BaseCommand {
    public Boolean execute(CommandSender sender, String command, String alias, String[] args) {
        List<Map<?, ?>> worlds = Worlds.getWorldsList();
        ArrayList<String> worldNames = new ArrayList<String>();

        for (Map<?, ?> world: worlds) {
            worldNames.add(world.get("id").toString());
        }

        sender.sendMessage(String.join(", ", worldNames));

        return true;
    }
}
