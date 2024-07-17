package cc.xcraft.xtweaker.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class RightClickHarvestListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        // 检查是否是右击方块事件
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            // 获取被右击的方块
            Block block = event.getClickedBlock();
            // 确保方块不为空且是可收获的方块（小麦、胡萝卜、马铃薯）
            if (block != null && (block.getType() == Material.WHEAT || block.getType() == Material.CARROTS || block.getType() == Material.POTATOES)) {
                // 获取方块的年龄信息
                Ageable ageable = (Ageable) block.getBlockData();
                // 如果方块已经成熟
                if (ageable.getAge() == ageable.getMaximumAge()) {
                    // 自然地破坏方块
                    block.breakNaturally();
                    // 设置方块的类型为其当前类型（这里可能是个错误）
                    block.setType(block.getType());
                    // 重新获取方块的年龄信息
                    ageable = (Ageable) block.getBlockData();
                    // 将方块的年龄设置为0
                    ageable.setAge(0);
                    // 更新方块的数据
                    block.setBlockData(ageable);
                }
            }
        }
    }
}