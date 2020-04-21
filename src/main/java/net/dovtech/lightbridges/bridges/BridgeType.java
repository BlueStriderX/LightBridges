package net.dovtech.lightbridges.bridges;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum BridgeType {
    INVISIBLE(Material.BARRIER, 0),
    ICE(Material.ICE, 0),
    CLEAR(Material.GLASS, 0),
    WHITE(Material.STAINED_GLASS, 0),
    ORANGE(Material.STAINED_GLASS, 1),
    MAGENTA(Material.STAINED_GLASS, 2),
    LIGHT_BLUE(Material.STAINED_GLASS, 3),
    YELLOW(Material.STAINED_GLASS, 4),
    LIME(Material.STAINED_GLASS, 5),
    PINK(Material.STAINED_GLASS, 6),
    GRAY(Material.STAINED_GLASS, 7),
    LIGHT_GRAY(Material.STAINED_GLASS, 8),
    CYAN(Material.STAINED_GLASS, 9),
    PURPLE(Material.STAINED_GLASS, 10),
    BLUE(Material.STAINED_GLASS, 11),
    BROWN(Material.STAINED_GLASS, 12),
    GREEN(Material.STAINED_GLASS, 13),
    RED(Material.STAINED_GLASS, 14),
    BLACK(Material.STAINED_GLASS, 15);

    private ItemStack bridgeType;
    private int blockData;
    private Material blockMaterial;

    private BridgeType(Material blockMaterial, int blockData) {
        this.blockMaterial = blockMaterial;
        this.blockData = blockData;
        this.bridgeType = new ItemStack(blockMaterial, blockData);
    }

    public ItemStack getBridgeType() {
        return bridgeType;
    }

    public int getBlockData() {
        return blockData;
    }

    public Material getBlockMaterial() {
        return blockMaterial;
    }
}
