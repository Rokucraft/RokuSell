package com.rokucraft.rokusell.command;

import com.rokucraft.rokusell.data.ItemRepository;
import com.rokucraft.rokusell.data.Shop;
import net.kyori.adventure.text.format.NamedTextColor;
import org.incendo.cloud.context.CommandContext;
import org.incendo.cloud.context.CommandInput;
import org.incendo.cloud.parser.ArgumentParseResult;
import org.incendo.cloud.parser.ArgumentParser;
import org.incendo.cloud.parser.ParserDescriptor;
import org.incendo.cloud.suggestion.BlockingSuggestionProvider;

import javax.inject.Inject;

import static net.kyori.adventure.text.Component.text;

public class ShopParser<C> implements ArgumentParser<C, Shop>, BlockingSuggestionProvider.Strings<C> {

    private final ItemRepository shopRepository;

    @Inject
    public ShopParser(
            ItemRepository shopRepository
    ) {
        this.shopRepository = shopRepository;
    }

    public ParserDescriptor<C, Shop> parser() {
        return ParserDescriptor.of(this, Shop.class);
    }

    @Override
    public ArgumentParseResult<Shop> parse(CommandContext commandContext, CommandInput commandInput) {
        final String input = commandInput.peekString();
        Shop shop = shopRepository.getShop(input);
        if (shop == null) {
            return ArgumentParseResult.failure(new ParseException(input));
        }
        commandInput.readString();
        return ArgumentParseResult.success(shop);
    }

    @Override
    public Iterable<String> stringSuggestions(CommandContext commandContext, CommandInput input) {
        return shopRepository.getShops()
                .stream()
                .map(Shop::name)
                .toList();
    }

    public static final class ParseException extends ComponentException {
        public ParseException(String input) {
            super(text()
                    .color(NamedTextColor.RED)
                    .append(text("Shop"))
                    .appendSpace()
                    .append(text(input))
                    .appendSpace()
                    .append(text("not found"))
            );
        }
    }
}
