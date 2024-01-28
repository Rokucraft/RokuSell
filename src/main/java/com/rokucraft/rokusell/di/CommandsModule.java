package com.rokucraft.rokusell.di;

import com.rokucraft.rokusell.RokuSellPlugin;
import com.rokucraft.rokusell.command.OpenCommand;
import com.rokucraft.rokusell.command.RokuSellCommand;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import org.bukkit.command.CommandSender;
import org.incendo.cloud.CommandManager;
import org.incendo.cloud.execution.ExecutionCoordinator;
import org.incendo.cloud.paper.PaperCommandManager;

@Module
abstract class CommandsModule {
    @Provides
    static CommandManager<CommandSender> provideCommandManager(
            RokuSellPlugin plugin
    ) {
        var manager = PaperCommandManager.createNative(plugin, ExecutionCoordinator.simpleCoordinator());
        manager.registerBrigadier();
        return manager;
    }

    @Binds
    @IntoSet
    abstract RokuSellCommand bindOpenCommand(OpenCommand command);
}
