package cc.xcraft.xtweaker.listener;

import org.bukkit.event.Listener;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.GameMode;
import org.bukkit.util.Vector;

import java.util.HashSet;
import java.util.Set;

public class TreesChopperListener implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();

        // 检查玩家是否在下蹲，使用的工具是否是斧头，以及是否砍的是树木
        if (player.isSneaking() && isAxe(itemInHand.getType()) && isLog(block.getType())) {
            // 砍掉整棵树
            chopTree(block, player);
        }
    }

    private boolean isAxe(Material material) {
        switch (material) {
            case WOODEN_AXE:
            case STONE_AXE:
            case IRON_AXE:
            case GOLDEN_AXE:
            case DIAMOND_AXE:
            case NETHERITE_AXE:
                return true;
            default:
                return false;
        }
    }

    private boolean isLog(Material material) {
        switch (material) {
            case OAK_LOG:
            case SPRUCE_LOG:
            case BIRCH_LOG:
            case JUNGLE_LOG:
            case ACACIA_LOG:
            case DARK_OAK_LOG:
                return true;
            default:
                return false;
        }
    }

    private void chopTree(Block block, Player player) {
        Set<Block> blocksToChop = new HashSet<>();
        collectTreeBlocks(block, blocksToChop);

        for (Block treeBlock : blocksToChop) {
            if (player.getGameMode() != GameMode.CREATIVE) {
                treeBlock.breakNaturally(player.getInventory().getItemInMainHand());
            } else {
                treeBlock.setType(Material.AIR);
            }
        }
    }

    private void collectTreeBlocks(Block block, Set<Block> blocksToChop) {
        if (!blocksToChop.contains(block) && isLog(block.getType())) {
            blocksToChop.add(block);
            for (Vector direction : getDirections()) {
                Block relativeBlock = block.getRelative(direction.getBlockX(), direction.getBlockY(), direction.getBlockZ());
                if (isLog(relativeBlock.getType())) {
                    collectTreeBlocks(relativeBlock, blocksToChop);
                }
            }
        }
    }

    private Vector[] getDirections() {
        return new Vector[]{
                new Vector(1, 0, 0), new Vector(-1, 0, 0),
                new Vector(0, 1, 0), new Vector(0, -1, 0),
                new Vector(0, 0, 1), new Vector(0, 0, -1)
        };
    }
}

