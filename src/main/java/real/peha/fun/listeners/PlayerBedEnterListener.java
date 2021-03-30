package real.peha.fun.listeners;

import org.bukkit.event.Listener;

import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerBedEnterEvent;

import real.peha.fun.Beds;
import real.peha.fun.WorldsPlugin;

public final class PlayerBedEnterListener implements Listener {
    public static void listen(WorldsPlugin plugin) {
		PlayerBedEnterListener listener = new PlayerBedEnterListener();

        plugin.getServer().getPluginManager().registerEvents(listener, plugin);
    }

	@EventHandler
	public void PlayerBedEnter(PlayerBedEnterEvent e) {
		Block bed = e.getBed();
		String bedId = Beds.getId(bed);

		Map<?, ?> bedConfig = Beds.find(bedId);

		if (bedConfig != null) {
			Player player = e.getPlayer();
			String worldId = bedConfig.get("worldId").toString();
			World world = Bukkit.getServer().getWorld(worldId);

			if (world != null) {
				player.sendMessage("Teleporting to " + worldId + " world...");

				Location spawn = world.getSpawnLocation();
				Location loc = new Location(world, spawn.getX(), spawn.getY(), spawn.getZ());

				player.teleport(loc);
			} else {
				player.sendMessage("В этот мир нельзя телепортироваться");
			}
		}
	}
}
