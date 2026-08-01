package com.antarktidov.ducksmod.entity.custom;

import java.util.Arrays;
import java.util.Comparator;

public enum DuckVariant {
    WHITE(0),
    MALE_MALLARD(1),
    FEMALE_MALLARD(2),
    MUSCOVY(3);
    //BABY(4);
    
    private static final DuckVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(DuckVariant::getId)).toArray(DuckVariant[]::new);
    private final int id;

    DuckVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static DuckVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
