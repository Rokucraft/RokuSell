package com.rokucraft.rokusell.ui;

import org.bukkit.entity.Player;

import javax.inject.Inject;

public class ShopManager {

    @Inject
    public ShopManager() {

    }

    public void open(Player player) {
        player.openInventory(new SellInventory(player).getInventory());
    }
}
