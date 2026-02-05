package me.GamingMaster0211.VeinMinerUltimate;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import java.util.*;

public class VeinMinerUltimateListener implements Listener {

    private final VeinMinerUltimate plugin;

    private static final BlockFace[] FACES = {
            BlockFace.UP,
            BlockFace.DOWN,
            BlockFace.NORTH,
            BlockFace.SOUTH,
            BlockFace.EAST,
            BlockFace.WEST
    };

    public VeinMinerUltimateListener(VeinMinerUltimate plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();

        if (!player.hasPermission("veinminer.use")) return;
        if (!VeinMinerUltimateState.isEnabled(player.getUniqueId())) return;

        if (plugin.getConfig().getBoolean("require-sneak") && !player.isSneaking())
            return;

        ItemStack tool = player.getInventory().getItemInMainHand();
        if (!isPickaxe(tool.getType())) return;

        Block start = event.getBlock();
        Material type = start.getType();

        if (isForbidden(type)) return;
        if (!isAllowed(type)) return;

        event.setCancelled(true);
        veinMine(start, tool);
    }

    private void veinMine(Block start, ItemStack tool) {
        int maxBlocks = plugin.getConfig().getInt("max-blocks");

        Set<Block> visited = new HashSet<>();
        Queue<Block> queue = new LinkedList<>();
        Material targetType = start.getType();

        queue.add(start);

        while (!queue.isEmpty() && visited.size() < maxBlocks) {
            Block block = queue.poll();

            if (visited.contains(block)) continue;
            if (block.getType() != targetType) continue;

            visited.add(block);
            block.breakNaturally(tool);

            for (BlockFace face : FACES) {
                queue.add(block.getRelative(face));
            }
        }
    }

    private boolean isPickaxe(Material material) {
        return material.name().endsWith("_PICKAXE");
    }

    private boolean isAllowed(Material material) {
        return plugin.getConfig()
                .getStringList("mineable-blocks")
                .contains(material.name());
    }

    private boolean isForbidden(Material material) {
        String name = material.name();

        if (name.equals("STONE") || name.endsWith("_STONE"))
            return true;

        if (name.endsWith("_PLANKS"))
            return true;

        if (name.endsWith("_LOG") || name.endsWith("_WOOD"))
            return true;

        return false;
    }
}
