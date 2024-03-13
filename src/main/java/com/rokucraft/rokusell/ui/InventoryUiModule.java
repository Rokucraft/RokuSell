package com.rokucraft.rokusell.ui;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;
import org.bukkit.event.Listener;

@Module
public interface InventoryUiModule {
    @IntoSet
    @Binds
    Listener bindSellInventoryListener(SellInventoryListener listener);
}
