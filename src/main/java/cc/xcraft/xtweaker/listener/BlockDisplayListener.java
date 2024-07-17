package cc.xcraft.xtweaker.listener;

import cc.xcraft.xtweaker.util.BlockNameMapper;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class BlockDisplayListener implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Block targetBlock = player.getTargetBlockExact(5); // 获取玩家准星指向的方块，范围为5格

        if (targetBlock != null && targetBlock.getType() != Material.AIR) {
            Material blockType = targetBlock.getType();
            String blockName = BlockNameMapper.getBlockName(blockType);

            // 在ActionBar显示方块的中文名字
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(blockName));
        }
    }
}
