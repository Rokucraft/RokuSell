package com.rokucraft.rokusell.ui;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import javax.inject.Inject;

public class SellInventoryListener implements Listener {
    @Inject
    public SellInventoryListener() {
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        if (!(e.getInventory().getHolder(false) instanceof SellInventory sellInventory)) {
            return;
        }
        if (!(e.getPlayer() instanceof Player player)) {
            throw new IllegalStateException("Sell inventory closed by non-player entity");
        }
        sellInventory.sellContents(player);
    }

}
