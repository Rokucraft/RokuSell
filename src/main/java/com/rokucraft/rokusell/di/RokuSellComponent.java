package com.rokucraft.rokusell.di;

import com.rokucraft.rokusell.RokuSellPlugin;
import com.rokucraft.rokusell.command.RokuSellCommand;
import com.rokucraft.rokusell.ui.InventoryUiModule;
import dagger.BindsInstance;
import dagger.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.incendo.cloud.CommandManager;

import javax.inject.Singleton;
import java.util.Set;

@Singleton
@Component(modules = {RokuSellModule.class, CommandsModule.class, VaultModule.class, InventoryUiModule.class})
public interface RokuSellComponent {

    CommandManager<CommandSender> commandManager();
    Set<RokuSellCommand> commands();

    Set<Listener> listeners();

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder plugin(RokuSellPlugin plugin);
        RokuSellComponent build();
    }
}
