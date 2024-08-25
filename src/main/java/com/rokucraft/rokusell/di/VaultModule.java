package com.rokucraft.rokusell.di;

import com.rokucraft.rokusell.RokuSellPlugin;
import dagger.Module;
import dagger.Provides;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.jspecify.annotations.Nullable;

@Module
public class VaultModule {
    @Provides
    static @Nullable Economy provideChat(RokuSellPlugin plugin) {
        RegisteredServiceProvider<Economy> rsp = plugin.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return  null;
        }
        return rsp.getProvider();
    }
}
