package com.rokucraft.rokusell.ui;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;

import javax.inject.Inject;

public class SellManager {

    private final Economy economy;

    @Inject
    public SellManager(
            Economy economy
    ) {
        this.economy = economy;
    }

    public void open(Player player) {
        player.openInventory(createSellInventory(player).getInventory());
    }

    private SellInventory createSellInventory(Player player) {
        return new SellInventory(player, (amount) -> economy.depositPlayer(player, amount));
    }
}
