package com.rokucraft.rokusell.data;

import com.rokucraft.rokusell.di.DataPath;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class ItemRepository {
    private final Logger logger;
    private final Path shopsPath;
    private final Map<String, Shop> shops = new HashMap<>();

    @Inject
    public ItemRepository(@DataPath Path dataPath, Logger logger) {
        this.logger = logger;
        shopsPath = dataPath.resolve("shops");
    }

    public @Nullable Shop getShop(String string) {
        return shops.get(string);
    }

    public List<Shop> getShops() {
        return List.copyOf(shops.values());
    }

    public void loadShops() {
        shops.clear();
        createShopsDir();
        try (var stream = Files.newDirectoryStream(shopsPath, "*.{yml,yaml}")) {
            for (Path path : stream) {
                Shop shop = loadShop(path);
                if (shop == null) continue;
                shops.put(shop.name(), shop);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private @Nullable Shop loadShop(Path path) {
        var loader = YamlConfigurationLoader.builder()
                .path(path)
                .build();
        try {
            List<Item> items = loader.load().getList(Item.class, List.of());
            String name = path.getFileName().toString().split("\\.")[0];
            logger.info("Loaded shop {}", name);
            return new Shop(name, items);
        } catch (ConfigurateException e) {
            logger.error("Unable to load shop file {}", path.getFileName(), e);
            return null;
        }
    }

    private void createShopsDir() {
        try {
            Files.createDirectories(shopsPath);
        } catch (IOException e) {
            throw new RuntimeException("Could not create shops directory", e);
        }
    }
}
