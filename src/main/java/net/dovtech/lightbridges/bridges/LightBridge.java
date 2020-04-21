package net.dovtech.lightbridges.bridges;

import net.dovtech.lightbridges.util.blocks.BlockUtils;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockRedstoneEvent;

public class LightBridge {

    private Block controller;
    private Block block1;
    private Block block2;
    private BridgeType bridgeType;
    private ActivationType activationType;

    public LightBridge(Block controller, Block block1, Block block2) {
        this.controller = controller;
        this.block1 = block1;
        this.block2 = block2;
        this.bridgeType = BridgeType.CLEAR;
        this.activationType = ActivationType.SWITCH_SAFETY;
    }

    public void changeBridgeType(BridgeType bridgeType) {
        this.bridgeType = bridgeType;
    }

    public void changeActivationType(ActivationType activationType) {
        this.activationType = activationType;
    }

    private void activateBridge() {
        if(BlockUtils.areaCheck(block1.getLocation(), block2.getLocation())) {
            if(activationType == ActivationType.SWITCH_SAFETY) {
                BlockUtils.setBlocksinArea(bridgeType.getBridgeType().getType(), bridgeType.getBlockData(), block1.getLocation(), block2.getLocation());

            } else if(activationType == ActivationType.SWITCH) {

            }
        }
    }

    @EventHandler
    public void onBlockActivateEvent(BlockRedstoneEvent blockRedstoneEvent) {
        if(blockRedstoneEvent.getBlock() == controller) {
            activateBridge();
        }
    }
}
