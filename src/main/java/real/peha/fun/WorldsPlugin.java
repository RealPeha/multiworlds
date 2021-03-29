package real.peha.fun;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import real.peha.fun.commands.*;
// import real.peha.fun.listeners.*;

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
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
        
        setInstance(this);

        // Register listeners
        // SpawnMobListener.listen(this);

        // Register commands
        commandsHandler.register(new String[]{"jopa"}, new WorldsPluginCommand());

        getLogger().info("Started");
    }

    @Override
    public void onDisable() {
        this.saveConfig();

        getLogger().info("Goodbye");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        String command = cmd.getName().toLowerCase();

        return commandsHandler.execute(sender, command, alias, args);
    }
}
