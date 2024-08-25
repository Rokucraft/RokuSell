package com.rokucraft.rokusell.di;

import com.rokucraft.rokusell.RokuSellPlugin;
import com.rokucraft.rokusell.command.*;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.incendo.cloud.CommandManager;
import org.incendo.cloud.execution.ExecutionCoordinator;
import org.incendo.cloud.paper.LegacyPaperCommandManager;

@Module
abstract class CommandsModule {

    @Provides
    static CommandManager<CommandSender> provideCommandManager(
            RokuSellPlugin plugin
    ) {
        var manager = LegacyPaperCommandManager.createNative(plugin, ExecutionCoordinator.simpleCoordinator());
        manager.registerBrigadier();
        manager.exceptionController().registerHandler(
                ComponentException.class,
                (ctx) -> {
                    Component message = ctx.exception().componentMessage();
                    if (message != null) {
                        ctx.context().sender().sendMessage(message);
                    }
                });
        return manager;
    }

    @Binds
    @IntoSet
    abstract RokuSellCommand bindOpenCommand(OpenCommand command);

    @Binds
    @IntoSet
    abstract RokuSellCommand bindReloadCommand(ReloadCommand command);
}
