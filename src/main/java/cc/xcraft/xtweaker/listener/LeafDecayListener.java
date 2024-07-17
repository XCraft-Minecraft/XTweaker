package cc.xcraft.xtweaker.listener;

import cc.xcraft.xtweaker.XTweaker;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class LeafDecayListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();

        // 如果破坏的方块是树木
        if (block.getType() == Material.OAK_LOG || block.getType() == Material.BIRCH_LOG ||
                block.getType() == Material.SPRUCE_LOG || block.getType() == Material.JUNGLE_LOG ||
                block.getType() == Material.ACACIA_LOG || block.getType() == Material.DARK_OAK_LOG) {

            // 在一定延迟后开始检查并加速叶子腐烂
            new BukkitRunnable() {
                @Override
                public void run() {
                    checkAndDecayLeaves(block);
                }
            }.runTaskLater(XTweaker.getInstance(), XTweaker.getInstance().getLeafDecayDelay());
        }
    }

    private void checkAndDecayLeaves(Block logBlock) {
        // 检查周围的叶子方块
        for (BlockFace face : BlockFace.values()) {
            Block nearbyBlock = logBlock.getRelative(face);
            if (nearbyBlock.getType() == Material.OAK_LEAVES || nearbyBlock.getType() == Material.BIRCH_LEAVES ||
                    nearbyBlock.getType() == Material.SPRUCE_LEAVES || nearbyBlock.getType() == Material.JUNGLE_LEAVES ||
                    nearbyBlock.getType() == Material.ACACIA_LEAVES || nearbyBlock.getType() == Material.DARK_OAK_LEAVES) {

                // 手动设置叶子方块腐烂
                nearbyBlock.breakNaturally();
            }
        }
    }
}
