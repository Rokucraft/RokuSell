package com.rokucraft.rokusell.data;

import com.rokucraft.rokusell.di.DataPath;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import javax.inject.Inject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ItemRepository {
    private final YamlConfigurationLoader loader;
    private List<Item> items;

    @Inject
    public ItemRepository(@DataPath Path dataPath) {
        Path itemPath = dataPath.resolve("items.yml");
        if (Files.notExists(itemPath)) {
            try {
                Files.createDirectories(itemPath.getParent());
                Files.createFile(itemPath);
            } catch (IOException e) {
                throw new RuntimeException("Couldn't create items.yml", e);
            }
        }
        loader = YamlConfigurationLoader.builder()
                .path(itemPath)
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
