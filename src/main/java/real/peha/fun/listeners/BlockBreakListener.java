package real.peha.fun.listeners;

import org.bukkit.event.Listener;

import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.material.Bed;

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
			Bed bed = (Bed) block.getState().getData();

			Location bedLocation = bed.isHeadOfBed()
				? block.getLocation()
				: block.getRelative(bed.getFacing()).getLocation();

			String bedId = Beds.getId(bedLocation);

			Map<?, ?> bedConfig = Beds.find(bedId);

			if (bedConfig != null) {
				Beds.deleteFromList(bedId);

				e.getPlayer().sendMessage("Кровать в мир " + bedConfig.get("id").toString() + " удалена");
			}
		}
	}
}