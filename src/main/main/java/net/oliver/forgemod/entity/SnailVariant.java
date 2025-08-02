package net.oliver.forgemod.entity;

import java.util.Arrays;
import java.util.Comparator;

public enum SnailVariant {
    DIRT(0),
    SAND(1);

    private static final SnailVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(SnailVariant::getId)).toArray(SnailVariant[]::new);
    private final int id;

    SnailVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static SnailVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
