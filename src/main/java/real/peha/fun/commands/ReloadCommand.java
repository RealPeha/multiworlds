package real.peha.fun.commands;

import org.bukkit.command.CommandSender;

import real.peha.fun.WorldsPlugin;
import real.peha.fun.BaseCommand;

public class ReloadCommand implements BaseCommand {
    public Boolean execute(CommandSender sender, String command, String alias, String[] args) {
        WorldsPlugin plugin = WorldsPlugin.getInstance();

        if (sender.hasPermission("worldsplugin.reload")) {
            plugin.reloadConfig();
            sender.sendMessage("Настройки обновлены");
        } else {
            sender.sendMessage(plugin.getConfig().getString("no-permission-message").replaceAll("&", "§"));
        }

        return true;
    }
}
