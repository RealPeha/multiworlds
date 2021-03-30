package real.peha.fun.commands;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import real.peha.fun.BaseCommand;
import real.peha.fun.Worlds;

public class WorldBedCommand implements BaseCommand {
    public Boolean execute(CommandSender sender, String command, String alias, String[] args) {
        if (args.length == 0) {
            return false;
        }

        String worldId = args[0];

        if (!Worlds.isExist(worldId)) {
            sender.sendMessage("Этот мир не найден");

            return true;
        }

        Player player = (Player) sender;
        ItemStack stack = new ItemStack(Material.RED_BED);
        ItemMeta meta = stack.getItemMeta();

        // meta.setDisplayName(worldId);
        meta.setLore(Arrays.asList(worldId));

        stack.setItemMeta(meta);

        player.getInventory().addItem(stack);

        return true;
    }
}
