package net.dovtech.lightbridges.util.areas;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import java.util.ArrayList;

public class BoundingBox {

    private Location min;
    private Location max;
    private Location center;

    public BoundingBox(Location min, Location max) {
        this.min = min;
        this.max = max;
        updateCenter();
    }

    public Location getMin() {
        return min;
    }

    public void setMin(Location min) {
        this.min = min;
        updateCenter();
    }

    public Location getMax() {
        return max;
    }

    public void setMax(Location max) {
        this.max = max;
        updateCenter();
    }

    public void grow(int multiplier) {
        min.setX(min.getX() * multiplier);
        min.setY(min.getY() * multiplier);
        min.setZ(min.getZ() * multiplier);
        max.setX(max.getX() * multiplier);
        max.setY(max.getY() * multiplier);
        max.setZ(max.getZ() * multiplier);
        updateCenter();
    }

    public Location getCenter() {
        updateCenter();
        return center;
    }

    public ArrayList<Block> getBlocks() {
        ArrayList<Block> blocks = new ArrayList<>();
        World world = min.getWorld();
        for(int x = min.getBlockX(); x <= max.getBlockX(); x ++) {
            for(int y = min.getBlockY(); y <= max.getBlockY(); y ++) {
                for(int z = min.getBlockZ(); z <= max.getBlockZ(); z ++) {
                    blocks.add(world.getBlockAt(x, y, z));
                }
            }
        }
        return blocks;
    }

    private void updateCenter() {
        double x = (max.getX() - min.getX()) / 2;
        double y = (max.getY() - min.getY()) / 2;
        double z = (max.getZ() - min.getZ()) / 2;
        center.setX(x);
        center.setY(y);
        center.setZ(z);
    }
}
