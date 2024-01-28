package com.rokucraft.rokusell.ui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

public class SellInventory implements InventoryHolder {

    private final Inventory inventory;
    private final Player player;

    public SellInventory(Player player) {
        this.player = player;
        this.inventory = Bukkit.createInventory(this, 9);
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }
}
