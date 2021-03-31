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
import org.bukkit.material.Bed;

import real.peha.fun.Beds;
import real.peha.fun.WorldsPlugin;
import real.peha.fun.Config;

public final class PlayerBedEnterListener implements Listener {
    public static void listen(WorldsPlugin plugin) {
		PlayerBedEnterListener listener = new PlayerBedEnterListener();

        plugin.getServer().getPluginManager().registerEvents(listener, plugin);
    }

	@EventHandler
	public void PlayerBedEnter(PlayerBedEnterEvent e) {
		Player player = e.getPlayer();

		if (!player.hasPermission("worldsplugin.enter")) {
            return;
        }

		Block block = e.getBed();
		Bed bed = (Bed) block.getState().getData();

		Location bedLocation = bed.isHeadOfBed()
			? block.getLocation()
			: block.getRelative(bed.getFacing()).getLocation();

		String bedId = Beds.getId(bedLocation);
		Map<?, ?> bedConfig = Beds.find(bedId);

		if (bedConfig != null) {
			String worldId = bedConfig.get("worldId").toString();
			World world = Bukkit.getServer().getWorld(worldId);

			if (world != null) {
				player.sendMessage("Телепортирую в мир " + worldId + "...");

				Location spawn = world.getSpawnLocation();
				Location loc = new Location(world, spawn.getX(), spawn.getY(), spawn.getZ());

				player.teleport(loc);
			} else {
				player.sendMessage("В этот мир нельзя телепортироваться. Возможно он выгружен или удален");
			}
		}
	}
}
