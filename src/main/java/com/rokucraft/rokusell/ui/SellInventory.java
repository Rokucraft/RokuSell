package com.rokucraft.rokusell.ui;

import com.rokucraft.rokusell.data.Item;
import com.rokucraft.rokusell.data.Shop;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import static net.kyori.adventure.text.Component.text;

public class SellInventory implements InventoryHolder {

    private final Inventory inventory;
    private final Shop shop;
    private final BiConsumer<Player, Double> onSell;

    public SellInventory(Shop shop, BiConsumer<Player, Double> onSell) {
        this.shop = shop;
        this.onSell = onSell;
        this.inventory = Bukkit.createInventory(this, 3 * 9, text("Sell your items"));
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }

    public void sellContents(Player player) {
        double totalWorth = 0;
        List<ItemStack> unsoldItems = new ArrayList<>();
        for (@Nullable ItemStack item : inventory) {
            if (item == null) continue;
            double worth = getWorth(item);
            if (worth == 0) {
                unsoldItems.add(item);
                continue;
            }
            totalWorth += worth;
        }
        returnItem(player, unsoldItems.toArray(new ItemStack[0]));
        onSell.accept(player, totalWorth);
    }

    private double getWorth(ItemStack itemStack) {
        return shop.items().stream()
                .filter(i -> i.matches(itemStack))
                .findFirst()
                .map(Item::worth)
                .orElse(0.0) * itemStack.getAmount();
    }

    private void returnItem(Player player, ItemStack... item) {
        if (item.length == 0) return;
        player.getInventory()
                .addItem(item)
                .values()
                .forEach(remaining -> player.getWorld().dropItem(player.getLocation(), remaining));
        player.sendMessage(text()
                .append(text()
                        .color(NamedTextColor.DARK_GRAY)
                        .append(text("["))
                        .append(text("!", NamedTextColor.RED))
                        .append(text("]"))
                ).appendSpace()
                .color(NamedTextColor.GRAY)
                .append(text("I'm unable to accept some of your items."))
                .appendNewline()
                .append(text("I've returned them to your inventory."))
        );
    }
}
