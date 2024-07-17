package cc.xcraft.xtweaker.util;

import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;

public class BlockNameMapper {
    private static final Map<Material, String> blockNameMap = new HashMap<>();

    static {
        blockNameMap.put(Material.OAK_LOG, "橡木原木");
        blockNameMap.put(Material.BIRCH_LOG, "桦木原木");
        blockNameMap.put(Material.SPRUCE_LOG, "云杉原木");
        blockNameMap.put(Material.JUNGLE_LOG, "丛林原木");
        blockNameMap.put(Material.ACACIA_LOG, "金合欢原木");
        blockNameMap.put(Material.DARK_OAK_LOG, "深色橡木原木");
        blockNameMap.put(Material.OAK_LEAVES, "橡树叶");
        blockNameMap.put(Material.BIRCH_LEAVES, "桦树叶");
        blockNameMap.put(Material.SPRUCE_LEAVES, "云杉树叶");
        blockNameMap.put(Material.JUNGLE_LEAVES, "丛林树叶");
        blockNameMap.put(Material.ACACIA_LEAVES, "金合欢树叶");
        blockNameMap.put(Material.DARK_OAK_LEAVES, "深色橡树叶");
    }

    public static String getBlockName(Material material) {
        return blockNameMap.getOrDefault(material, material.name());
    }
}
