package real.peha.fun.listeners;

import org.bukkit.event.Listener;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerBedEnterEvent;
import real.peha.fun.WorldsPlugin;

public final class PlayerBedEnterListener implements Listener {
    public static void listen(WorldsPlugin plugin) {
		PlayerBedEnterListener listener = new PlayerBedEnterListener();

        plugin.getServer().getPluginManager().registerEvents(listener, plugin);
    }

	@EventHandler
	public void PlayerBedEnter(PlayerBedEnterEvent e) {
		Block block = e.getBed();

		if (block.hasMetadata("worldId")) {
			String worldId = block.getMetadata("worldId").get(0).asString();

			e.getPlayer().sendMessage(worldId);
		}
	}
}
