package net.dovtech.lightbridges.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.ArrayList;
import java.util.List;

public class LBCreate implements CommandExecutor {

    private ItemStack bridgeWand = new ItemStack(Material.BLAZE_ROD);
    private Block block1 = null;
    private Block block2 = null;
    private Block controllerBlock = null;
    private int n = 1;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("lightbridges.create") || player.hasPermission("lightbridges.admin")) {
                startCreateBridge(player);
            }
        }

        return false;
    }

    private void startCreateBridge(Player player) {
        ItemMeta meta = bridgeWand.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Bridge Wand");
        List<String> lore = new ArrayList<>();
        lore.set(1, "Right click to set point 1");
        meta.setLore(lore);
        updateLore();
        Inventory inventory = player.getInventory();
        inventory.addItem(bridgeWand);
        player.sendMessage("Right click on a block to set it as point 1");
    }

    private void updateLore() {
        ItemMeta meta = bridgeWand.getItemMeta();
        List<String> lore = meta.getLore();
        if(block1 == null) {
            lore.set(2, ChatColor.LIGHT_PURPLE + "Position 1: -");
        } else {
            lore.set(2, ChatColor.LIGHT_PURPLE + "Position 1: (" + block1.getLocation().getX() + ", " + block1.getLocation().getY() + ", " + block1.getLocation().getZ() + ")");
        }

        if(block2 == null) {
            lore.set(3, ChatColor.LIGHT_PURPLE + "Position 2: -");
        } else {
            lore.set(3, ChatColor.LIGHT_PURPLE + "Position 2: (" + block2.getLocation().getX() + ", " + block2.getLocation().getY() + ", " + block2.getLocation().getZ() + ")");
        }

        if(n == 1) {
            lore.set(1, "Right click to set point 1");
        } else if(n == 2) {
            lore.set(1, "Right click to set point 2");
        } if(n == 3) {
            lore.set(1, "Right click the controller block");
        }

        meta.setLore(lore);
        bridgeWand.setItemMeta(meta);
    }

    @EventHandler
    public void onBlockInteractEvent(PlayerInteractEvent playerInteractEvent) {
        Player player = playerInteractEvent.getPlayer();
        if(playerInteractEvent.getItem() == bridgeWand) {
            if(n == 1) {
                block1 = playerInteractEvent.getClickedBlock();
                n = 2;
                player.sendMessage("Right click on a block to set it as point 2");
                updateLore();
            } else if(n == 2) {
                if(playerInteractEvent.getClickedBlock().getLocation().getBlockY() != block1.getLocation().getBlockY()) {
                    player.sendMessage("Invalid point 2: Both points must be on the same Y level");
                } else if(playerInteractEvent.getClickedBlock().getLocation().getBlockY() == block1.getLocation().getBlockY()) {
                    block2 = playerInteractEvent.getClickedBlock();
                    n = 3;
                    player.sendMessage("Right click the controller block");
                    updateLore();
                }

            } else if(n == 3) {
                controllerBlock = playerInteractEvent.getClickedBlock();
                player.getInventory().remove(bridgeWand);
            }
        }
    }

    @EventHandler
    public void onItemDropEvent(PlayerDropItemEvent playerDropItemEvent) {
        if(playerDropItemEvent.getItemDrop() == bridgeWand) {
            playerDropItemEvent.setCancelled(true);
        }
    }
}
