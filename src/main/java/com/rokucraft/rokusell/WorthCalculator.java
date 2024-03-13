package com.rokucraft.rokusell;

import com.rokucraft.rokusell.data.Item;
import com.rokucraft.rokusell.data.ItemRepository;
import org.bukkit.inventory.ItemStack;

import javax.inject.Inject;

public class WorthCalculator {

    private final ItemRepository itemRepository;

    @Inject
    public WorthCalculator(
            ItemRepository itemRepository
    ) {
        this.itemRepository = itemRepository;
    }

    public double getWorth(ItemStack itemStack) {
        return itemRepository.getItems().stream()
                .filter(i -> i.matches(itemStack))
                .findFirst()
                .map(Item::worth)
                .orElse(0.0) * itemStack.getAmount();
    }
}
