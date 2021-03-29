package real.peha.fun.listeners;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import real.peha.fun.WorldsPlugin;

public final class SpawnMobListener implements Listener {
    public static void listen(WorldsPlugin plugin) {
		SpawnMobListener listener = new SpawnMobListener();

        plugin.getServer().getPluginManager().registerEvents(listener, plugin);
    }

	@EventHandler
	public void onDeath(EntityDeathEvent e) {
		
	}
}