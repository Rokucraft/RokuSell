package com.rokucraft.rokusell.data;

import com.rokucraft.rokusell.di.DataPath;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import javax.inject.Inject;
import java.nio.file.Path;
import java.util.List;

public class ItemRepository {
    private final YamlConfigurationLoader loader;
    private List<Item> items;

    @Inject
    public ItemRepository(@DataPath Path dataPath) {
        loader = YamlConfigurationLoader.builder()
                .path(dataPath.resolve("items.yml"))
                .build();
    }

    public List<Item> getItems() {
        if (this.items == null) {
            try {
                this.items = this.loader.load().getList(Item.class);
            } catch (ConfigurateException e) {
                throw new RuntimeException("Failed to load items.", e);
            }
        }
        return this.items;
    }
}
