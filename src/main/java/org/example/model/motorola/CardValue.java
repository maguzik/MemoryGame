package org.example.model.motorola;

import java.util.Arrays;

public enum CardValue {
    PULL(0, "PULL"),
    ACHIEVER(1, "ACHIEVER"),
    PEST(2, "PEST"),
    UNBIASED(3, "UNBIASED"),
    BIKE(4, "BIKE"),
    FEELING(5, "FEELING"),
    SOAK(6, " SOAK"),
    STURDY(7, "STURDY"),
    ;

    private int index;
    private String name;
    private boolean isUsed;

    CardValue(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public static CardValue findByIndex(int index) {
        return Arrays.stream(CardValue.values())
                .filter(cardValue -> cardValue.index == index)
                .findFirst()
                .orElseThrow(NoSuchFieldError::new);
    }

    public void markAsUsed() {
        isUsed = true;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public String getName() {
        return name;
    }


}
