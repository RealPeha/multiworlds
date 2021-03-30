package real.peha.fun.commands;

import java.util.Map;

import org.bukkit.command.CommandSender;

import real.peha.fun.BaseCommand;
import real.peha.fun.Worlds;

public class WorldInfoCommand implements BaseCommand {
    public Boolean execute(CommandSender sender, String command, String alias, String[] args) {
        if (args.length == 0) {
            return false;
        }

        Map<?, ?> world = Worlds.find(args[0]);
        
        if (world == null) {
            sender.sendMessage("Такого мира не существует");
        } else {
            String info = "id: " + world.get("id") + "\n";

            if (world.containsKey("type")) {
                info += "type: " + world.get("type") + "\n";
            }

            sender.sendMessage(info);
        }

        return true;
    }
}
