package real.peha.fun.listeners;

import org.bukkit.event.Listener;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

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
			String worldId = meta.getLore().get(0);

			if (worldId != null) {
				block.setMetadata("worldId", new FixedMetadataValue(WorldsPlugin.getInstance(), worldId));
			}
		}
	}
}