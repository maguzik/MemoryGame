package org.example.model.motorola;

import java.util.Objects;

public class Card {
    private CardValue value;
    private Location location;
    private boolean isFound;
    private boolean isChosen;

    public Card(Location location) {
        this.location = location;
    }

    public boolean isFound() {
        return isFound;
    }

    public boolean isChosen() {
        return isChosen;
    }

    public void hide() {
        isChosen = false;
    }

    public void markAsFound() {
        isFound = true;
    }

    public void choose() {
        isChosen = true;
    }

    public String getPrintValue() {
        if (isFound || isChosen) {
            return value.name();
        }
        return "X ";

    }

    public CardValue getValue() {
        return value;
    }

    public void setValue(CardValue value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Card{" +
                "value=" + value +
                ", location=" + location +
                ", isFound=" + isFound +
                '}';
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return isFound == card.isFound && isChosen == card.isChosen && value == card.value && Objects.equals(location, card.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, location, isFound, isChosen);
    }
}
