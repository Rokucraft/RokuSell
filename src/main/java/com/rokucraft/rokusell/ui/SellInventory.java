package com.rokucraft.rokusell.ui;

import com.rokucraft.rokusell.data.Item;
import com.rokucraft.rokusell.data.ItemRepository;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class SellInventory implements InventoryHolder {

    private final Inventory inventory;
    private final ItemRepository itemRepository;
    private final Consumer<Double> onSell;

    public SellInventory(ItemRepository itemRepository, Player player, Consumer<Double> onSell) {
        this.itemRepository = itemRepository;
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

    public double getWorth(ItemStack itemStack) {
        return itemRepository.getItems().stream()
                .filter(i -> i.matches(itemStack))
                .findFirst()
                .map(Item::worth)
                .orElse(0.0) * itemStack.getAmount();
    }
}
