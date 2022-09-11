package org.example.model.motorola;

import java.util.Comparator;

public class ComparatorToLocation implements Comparator<Card> {

    @Override
    public int compare(Card card1, Card card2) {
        Location location1 = card1.getLocation();
        Location location2 = card2.getLocation();
        if (location1.getRow() > location2.getRow()) {
            return 1;
        } else if (location1.getRow() == location2.getRow()) {
            if (location1.getCol() > location2.getCol()) {
                return 1;
            } else if (location1.getCol() < location2.getCol()) {
                return -1;
            }
            return 0;
        }
        return -1;
    }
}
