package com.rokucraft.rokusell.ui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class SellInventory implements InventoryHolder {

    private final Inventory inventory;
    private final Consumer<Double> onSell;

    public SellInventory(Player player, Consumer<Double> onSell) {
        this.onSell = onSell;
        this.inventory = Bukkit.createInventory(this, 9);
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }

    public void sellContents() {
        onSell.accept(3.0);
    }
}
