package real.peha.fun.commands;

import org.bukkit.command.CommandSender;

import real.peha.fun.WorldsPlugin;
import real.peha.fun.BaseCommand;

public class ReloadCommand implements BaseCommand {
    public Boolean execute(CommandSender sender, String command, String alias, String[] args) {
        WorldsPlugin.getInstance().reloadConfig();

        sender.sendMessage("Настройки обновлены");

        return true;
    }
}
