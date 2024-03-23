package com.rokucraft.rokusell.data;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

import static net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer.plainText;

@ConfigSerializable
public record Item(
        Material material,
        String name,
        double worth
) {
    public boolean matches(ItemStack item) {
        return matchesMaterial(item) && matchesName(item);
    }

    private boolean matchesMaterial(ItemStack item) {
        return this.material == null || this.material == item.getType();
    }

    private boolean matchesName(ItemStack item) {
        String displayName = plainText().serializeOrNull(item.getItemMeta().displayName());
        return this.name == null || this.name.equals(displayName);
    }
}
