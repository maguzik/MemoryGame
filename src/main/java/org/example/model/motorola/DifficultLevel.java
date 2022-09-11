package org.example.model.motorola;

import java.util.Arrays;

public class DifficultLevel {
    private final int guessChances;
    private final int pairsToFind;
    private final Description description;

    public DifficultLevel(Description description) {
        switch (description) {
            case EASY: {
                this.pairsToFind = 4;
                this.guessChances = 3;
                break;
            }
            case HARD: {
                this.pairsToFind = 8;
                this.guessChances = 15;
                break;
            }
            default:
                this.pairsToFind = 8;
                this.guessChances = 15;
                break;
        }
        this.description = description;
    }

    public int getGuessChances() {
        return guessChances;
    }

    public Description getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Description: " + this.description.name() + " GuessChances: " + this.guessChances + " Pairs to find: " + this.pairsToFind;
    }

    public enum Description {
        EASY,
        HARD;

        static Description fromString(String value) {
            return Arrays.stream(Description.values())
                    .filter(val -> val.name().equalsIgnoreCase(value))
                    .findFirst()
                    .orElseThrow(NoSuchFieldError::new);
        }
    }
}
