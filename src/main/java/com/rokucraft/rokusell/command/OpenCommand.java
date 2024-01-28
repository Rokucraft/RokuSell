package com.rokucraft.rokusell.command;

import com.rokucraft.rokusell.ui.ShopManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.incendo.cloud.Command;

import javax.inject.Inject;

import static org.incendo.cloud.bukkit.parser.PlayerParser.playerParser;

public class OpenCommand extends RokuSellCommand{

    private final ShopManager shopManager;

    @Inject
    public OpenCommand(
            ShopManager shopManager
    ) {

        this.shopManager = shopManager;
    }

    @Override
    protected Command.Builder<? extends CommandSender> configure(Command.Builder<CommandSender> builder) {
        return builder.literal("open")
                .required("player", playerParser())
                .permission("rokusell.command.open")
                .handler(ctx -> {
                    Player player = ctx.get("player");
                    shopManager.open(player);
                });
    }
}
