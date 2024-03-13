package com.rokucraft.rokusell.ui;

import com.rokucraft.rokusell.data.ItemRepository;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;

import javax.inject.Inject;

public class SellManager {

    private final Economy economy;
    private final ItemRepository itemRepository;

    @Inject
    public SellManager(
            Economy economy,
            ItemRepository itemRepository
    ) {
        this.economy = economy;
        this.itemRepository = itemRepository;
    }

    public void open(Player player) {
        player.openInventory(createSellInventory(player).getInventory());
    }

    private SellInventory createSellInventory(Player player) {
        return new SellInventory(itemRepository, player, (amount) -> economy.depositPlayer(player, amount));
    }
}
