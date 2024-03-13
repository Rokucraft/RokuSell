package com.rokucraft.rokusell.di;

import com.rokucraft.rokusell.RokuSellPlugin;
import dagger.Module;
import dagger.Provides;
import org.slf4j.Logger;

import java.nio.file.Path;

@Module
public abstract class RokuSellModule {
    @Provides
    static Logger provideLogger(RokuSellPlugin plugin) {
        return  plugin.getSLF4JLogger();
    }

    @Provides
    static Path provideDataPath(RokuSellPlugin plugin) {
        return plugin.getDataFolder().toPath();
    }
}
