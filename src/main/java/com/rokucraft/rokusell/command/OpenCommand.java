package com.rokucraft.rokusell.command;

import com.rokucraft.rokusell.ui.SellManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.incendo.cloud.Command;

import javax.inject.Inject;

import static org.incendo.cloud.bukkit.parser.PlayerParser.playerParser;

public class OpenCommand extends RokuSellCommand{

    private final SellManager sellManager;

    @Inject
    public OpenCommand(
            SellManager sellManager
    ) {

        this.sellManager = sellManager;
    }

    @Override
    protected Command.Builder<? extends CommandSender> configure(Command.Builder<CommandSender> builder) {
        return builder.literal("open")
                .required("player", playerParser())
                .permission("rokusell.command.open")
                .handler(ctx -> {
                    Player player = ctx.get("player");
                    sellManager.open(player);
                });
    }
}
