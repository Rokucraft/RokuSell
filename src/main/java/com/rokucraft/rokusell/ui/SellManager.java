package com.rokucraft.rokusell.ui;

import com.rokucraft.rokusell.data.Shop;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.format.NamedTextColor;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;

import javax.inject.Inject;

import static net.kyori.adventure.sound.Sound.sound;
import static net.kyori.adventure.text.Component.text;
import static org.bukkit.Sound.BLOCK_NOTE_BLOCK_CHIME;

public class SellManager {

    private final Economy economy;

    @Inject
    public SellManager(
            Economy economy
    ) {
        this.economy = economy;
    }

    public void open(Player player, Shop shop) {
        player.openInventory(createSellInventory(shop).getInventory());
    }

    private SellInventory createSellInventory(Shop shop) {
        return new SellInventory(shop, (player, amount) -> {
            if (amount == 0) return;
            economy.depositPlayer(player, amount);
            player.sendMessage(text("Sold items for " + economy.format(amount), NamedTextColor.GREEN));
            player.playSound(sound(BLOCK_NOTE_BLOCK_CHIME, Sound.Source.PLAYER, 1, 1.5F));
        });
    }
}
