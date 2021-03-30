package real.peha.fun;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import real.peha.fun.commands.*;
import real.peha.fun.listeners.*;

public class WorldsPlugin extends JavaPlugin {
    private CommandsHandler commandsHandler = new CommandsHandler();
    private static WorldsPlugin instance;

    public static WorldsPlugin getInstance() {
        return instance;
    }

    private static void setInstance(WorldsPlugin plugin) {
        WorldsPlugin.instance = plugin;
    }

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveConfig();
        
        setInstance(this);

        // Register listeners
        PlayerBedEnterListener.listen(this);
        BlockPlaceListener.listen(this);

        // Register commands
        commandsHandler.register(new String[]{"world", "wr"}, new WorldCommand());

        if (Config.getBoolean("worlds-preload")) {
            Worlds.loadWorlds();
        }

        getLogger().info("Hello wantid");
    }

    @Override
    public void onDisable() {
        saveConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        if (!sender.hasPermission("worldsplugin.usage")) {
            sender.sendMessage(getConfig().getString("no-permission").replaceAll("&", "§"));

            return true;
        }

        String command = cmd.getName().toLowerCase();

        return commandsHandler.execute(sender, command, alias, args);
    }
}
