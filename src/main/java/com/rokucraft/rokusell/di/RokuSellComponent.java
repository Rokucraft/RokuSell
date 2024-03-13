package com.rokucraft.rokusell.di;

import com.rokucraft.rokusell.RokuSellPlugin;
import com.rokucraft.rokusell.command.RokuSellCommand;
import dagger.BindsInstance;
import dagger.Component;
import org.bukkit.command.CommandSender;
import org.incendo.cloud.CommandManager;

import java.util.Set;

@Component(modules = {RokuSellModule.class, CommandsModule.class, VaultModule.class})
public interface RokuSellComponent {

    CommandManager<CommandSender> commandManager();
    Set<RokuSellCommand> commands();

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder plugin(RokuSellPlugin plugin);
        RokuSellComponent build();
    }
}
