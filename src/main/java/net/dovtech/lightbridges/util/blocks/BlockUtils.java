package net.dovtech.lightbridges.util.blocks;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

public class BlockUtils {

    public static boolean areaCheck(Location point1, Location point2) {


        return false;
    }

    public static void setBlocksinArea(Material material, int blockData, Location point1, Location point2) {
        World world = point1.getWorld();
        for(int x = point1.getBlockX(); x <= point2.getBlockX(); x ++) {
            for(int z = point1.getBlockZ(); z <= point2.getBlockZ(); z ++) {
                Block block = world.getBlockAt(x, point1.getBlockY(), z);
                block.setType(material);
                block.setData((byte) blockData);
            }
        }
    }
}
