package com.rokucraft.rokusell.data;

import com.rokucraft.rokusell.di.DataPath;
import org.slf4j.Logger;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class ItemRepository {
    private final YamlConfigurationLoader loader;
    private final Logger logger;
    private List<Item> items;

    @Inject
    public ItemRepository(@DataPath Path dataPath, Logger logger) {
        this.logger = logger;
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
        refresh();
    }

    public List<Item> getItems() {
        return this.items;
    }

    public void refresh() {
        try {
            this.items = this.loader.load().getList(Item.class, new ArrayList<>());
            logger.info("Loaded " + items.size() + " items.");
        } catch (ConfigurateException e) {
            throw new RuntimeException("Failed to load items.", e);
        }
    }
}
