package net.dovtech.lightbridges.events;

import net.dovtech.lightbridges.util.areas.AreaGroup;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class EntityLeaveAreaGroupEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();
    private Entity entity;
    private AreaGroup areaGroup;
    private Material material;
    private int materialData;

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
