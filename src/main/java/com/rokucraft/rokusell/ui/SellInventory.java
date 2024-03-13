package com.rokucraft.rokusell.ui;

import com.rokucraft.rokusell.data.Item;
import com.rokucraft.rokusell.data.ItemRepository;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;

import static net.kyori.adventure.text.Component.text;

public class SellInventory implements InventoryHolder {

    private final Inventory inventory;
    private final ItemRepository itemRepository;
    private final BiConsumer<Player, Double> onSell;

    public SellInventory(ItemRepository itemRepository, BiConsumer<Player, Double> onSell) {
        this.itemRepository = itemRepository;
        this.onSell = onSell;
        this.inventory = Bukkit.createInventory(this, 3 * 9, text("Sell your items here!"));
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }

    public void sellContents(Player player) {
        double totalWorth = 0;
        for (ItemStack item : inventory) {
            if (item == null) continue;
            double worth = getWorth(item);
            if (worth == 0) {
                player.getInventory().addItem(item);
                continue;
            }
            totalWorth += worth;
        }

        onSell.accept(player, totalWorth);
    }

    private double getWorth(ItemStack itemStack) {
        return itemRepository.getItems().stream()
                .filter(i -> i.matches(itemStack))
                .findFirst()
                .map(Item::worth)
                .orElse(0.0) * itemStack.getAmount();
    }
}
