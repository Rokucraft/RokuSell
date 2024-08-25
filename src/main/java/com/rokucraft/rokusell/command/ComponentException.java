package com.rokucraft.rokusell.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.util.ComponentMessageThrowable;
import org.jetbrains.annotations.Nullable;

import static net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer.plainText;

public class ComponentException extends RuntimeException implements ComponentMessageThrowable {
    private final Component message;

    public ComponentException(Component message) {
        super(plainText().serialize(message));
        this.message = message;
    }

    public ComponentException(ComponentLike message) {
        this(message.asComponent());
    }

    @Override
    public @Nullable Component componentMessage() {
        return message;
    }
}
