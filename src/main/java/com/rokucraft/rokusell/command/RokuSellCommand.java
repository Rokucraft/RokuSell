package com.rokucraft.rokusell.command;

import org.bukkit.command.CommandSender;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.incendo.cloud.bean.CommandBean;
import org.incendo.cloud.bean.CommandProperties;

public abstract class RokuSellCommand extends CommandBean<CommandSender> {

    @Override
    protected @NonNull CommandProperties properties() {
        return CommandProperties.of("rokusell");
    }
}
