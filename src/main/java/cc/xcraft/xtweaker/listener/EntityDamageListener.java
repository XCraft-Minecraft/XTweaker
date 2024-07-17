package cc.xcraft.xtweaker.listener;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageListener implements Listener {

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player damager = (Player) event.getDamager();
            if (event.getEntity() instanceof LivingEntity) {
                LivingEntity damaged = (LivingEntity) event.getEntity();
                double health = damaged.getHealth() - event.getFinalDamage();
                if (health < 0) {
                    health = 0;
                }
                String healthMessage = damaged.getName() + String.format(" %.1f", health) + "§c♥" + "§f/" + String.format("%.1f", damaged.getMaxHealth()) + "§c♥";
                damager.sendMessage(healthMessage);
            }
        }
    }
}
