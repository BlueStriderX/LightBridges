package net.dovtech.lightbridges.bridges;

import net.dovtech.lightbridges.LightBridges;
import net.dovtech.lightbridges.util.areas.BoundingBox;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class LightBridge implements Listener {

    private Block controller;
    private Block block1;
    private Block block2;
    private BridgeType bridgeType;
    private BoundingBox boundingBox;
    private Boolean locked;

    public LightBridge() {

    }

    public LightBridge(Block controller, Block block1, Block block2) {
        this.controller = controller;
        this.block1 = block1;
        this.block2 = block2;
        this.bridgeType = BridgeType.CLEAR;
        this.boundingBox = new BoundingBox(block1.getLocation(), block2.getLocation());
    }

    public void changeBridgeType(BridgeType bridgeType) {
        this.bridgeType = bridgeType;
    }

    private void activateBridge() {
        if(checkClear() && !locked) {
            for(Block block: boundingBox.getBlocks()) {
                block.setType(bridgeType.getBlockMaterial());
                block.setData((byte) bridgeType.getBlockData());
            }
        }
    }

    private void deactivateBridge() {
        for(Block block : boundingBox.getBlocks()) {
            block.setType(Material.AIR);
        }
    }

    private Boolean checkClear() {
        for(Block block : boundingBox.getBlocks()) {
            if(block.getType() != Material.AIR) {
                return false;
            }
        }
        return true;
    }

    @EventHandler
    public void onBlockActivateEvent(BlockRedstoneEvent blockRedstoneEvent) {
        if(blockRedstoneEvent.getBlock() == controller) {
            locked = !locked;
        }
    }

    @EventHandler
    public void onPlayerLeaveBoundingBoxEvent(PlayerMoveEvent playerMoveEvent) {
        Location from = playerMoveEvent.getFrom();
        World world = block1.getWorld();
        if(from.getWorld() == world) {
            if(boundingBox.getBlocks().contains(from.getBlock())) {
                new BukkitRunnable() {
                    @Override
                    public void run () {
                        deactivateBridge();
                    }
                }.runTaskLater(LightBridges.getPlugin(), 30);
            }
        }
    }

    @EventHandler
    public void onPlayerEnterBoundingBoxEvent(PlayerMoveEvent playerMoveEvent) {
        Location to = playerMoveEvent.getTo();
        World world = block1.getWorld();
        if(to.getWorld() == world) {
            if(boundingBox.getBlocks().contains(to.getBlock())) {
                activateBridge();
            }
        }
    }
}
