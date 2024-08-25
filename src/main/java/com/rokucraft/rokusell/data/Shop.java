package com.rokucraft.rokusell.data;

import java.util.List;

public record Shop(
        String name,
        List<Item> items
) {
}
