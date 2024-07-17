package cc.xcraft.xtweaker;

import cc.xcraft.xtweaker.listener.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class XTweaker extends JavaPlugin {

    private static XTweaker instance;
    private long leafDecayDelay;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        leafDecayDelay = getConfig().getLong("leaf-decay-delay", 5L);

        getServer().getPluginManager().registerEvents(new FarmlandProtectListener(), this);
        getServer().getPluginManager().registerEvents(new TreesChopperListener(), this);
        getServer().getPluginManager().registerEvents(new RightClickHarvestListener(), this);
        getServer().getPluginManager().registerEvents(new TreesChopperListener(), this);
        getServer().getPluginManager().registerEvents(new BlockDisplayListener(), this);
        getServer().getPluginManager().registerEvents(new EntityDamageListener(), this);
    }

    @Override
    public void onDisable() {
        instance = null;
    }
    public static XTweaker getInstance() {
        return instance;
    }
    public long getLeafDecayDelay() {
        return leafDecayDelay;
    }
}
