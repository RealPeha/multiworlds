package real.peha.fun.listeners;

import org.bukkit.event.Listener;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;

import real.peha.fun.Beds;
import real.peha.fun.WorldsPlugin;

public final class BlockBreakListener implements Listener {
    public static void listen(WorldsPlugin plugin) {
		BlockBreakListener listener = new BlockBreakListener();

        plugin.getServer().getPluginManager().registerEvents(listener, plugin);
    }

	@EventHandler
	public void BlockPlace(BlockBreakEvent e) {
		Block block = e.getBlock();

		if (block.getType().equals(Material.RED_BED)) {
			String bedId = Beds.getId(block);

			Beds.deleteFromList(bedId);
		}
	}
}