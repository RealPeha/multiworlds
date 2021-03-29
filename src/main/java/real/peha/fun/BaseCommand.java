package real.peha.fun;

import org.bukkit.command.CommandSender;

public interface BaseCommand {
    public Boolean execute(CommandSender sender, String command, String alias, String[] args);
}
