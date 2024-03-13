package com.rokucraft.rokusell;

import com.rokucraft.rokusell.di.DaggerRokuSellComponent;
import com.rokucraft.rokusell.di.RokuSellComponent;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.incendo.cloud.CommandManager;

public class RokuSellPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        RokuSellComponent component = DaggerRokuSellComponent.builder()
                .plugin(this)
                .build();

        component.listeners().forEach(l -> this.getServer().getPluginManager().registerEvents(l, this));

        CommandManager<CommandSender> commandManager = component.commandManager();
        component.commands().forEach(commandManager::command);
    }
}
