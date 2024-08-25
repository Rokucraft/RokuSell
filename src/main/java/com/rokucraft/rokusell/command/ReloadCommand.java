package com.rokucraft.rokusell.command;

import com.rokucraft.rokusell.data.ItemRepository;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.CommandSender;
import org.incendo.cloud.Command;

import javax.inject.Inject;

import static net.kyori.adventure.text.Component.text;

public class ReloadCommand extends RokuSellCommand {

    private final ItemRepository itemRepository;

    @Inject
    public ReloadCommand(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    protected Command.Builder<? extends CommandSender> configure(Command.Builder<CommandSender> builder) {
        return builder.literal("reload")
                .permission("rokusell.command.reload")
                .handler(ctx -> {
                    itemRepository.loadShops();
                    ctx.sender().sendMessage(text("Shop configuration reloaded", NamedTextColor.GREEN));
                });
    }
}
