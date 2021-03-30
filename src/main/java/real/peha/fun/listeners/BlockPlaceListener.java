package real.peha.fun.listeners;

import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Bed;

import real.peha.fun.Beds;
import real.peha.fun.Worlds;
import real.peha.fun.WorldsPlugin;

public final class BlockPlaceListener implements Listener {
    public static void listen(WorldsPlugin plugin) {
		BlockPlaceListener listener = new BlockPlaceListener();

        plugin.getServer().getPluginManager().registerEvents(listener, plugin);
    }

	@EventHandler
	public void BlockPlace(BlockPlaceEvent e) {
		Block block = e.getBlock();

		if (block.getType().equals(Material.RED_BED)) {
			Player player = e.getPlayer();
			ItemMeta meta = player.getInventory().getItemInMainHand().getItemMeta();

			if (meta.hasLore()) {
				String worldId = meta.getLore().get(0);

				player.sendMessage(worldId);

				if (Worlds.isExistOrSystem(worldId)) {
					Bed bed = (Bed) block.getState().getData();

					Location bedLocation = bed.isHeadOfBed()
						? block.getLocation()
						: block.getRelative(bed.getFacing()).getLocation();

					String bedId = Beds.getId(bedLocation);

					player.sendMessage(bedId);
					
					Map<String, Object> bedConfig = new HashMap<>();

					bedConfig.put("id", bedId);
					bedConfig.put("worldId", worldId);

					Beds.addToList(bedConfig);

					player.sendMessage("Кровать в мир " + worldId + " создана");
				} else {
					player.sendMessage("Мир " + worldId + " не существует");
				}
			}
		}
	}
}