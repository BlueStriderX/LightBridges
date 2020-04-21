package net.dovtech.lightbridges.util.areas;

import org.bukkit.Location;

public class BoundingBox {

    private Location point1;
    private Location point2;

    public BoundingBox(Location point1, Location point2) {
        this.point1 = point1;
        this.point2 = point2;
    }
}
