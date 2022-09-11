package org.example.model.motorola;

import java.util.Map;
import java.util.Objects;

public class Location {
    private static final Map<Integer, String> rowSymbols = Map.of(1, "A", 2, "B");
    private final int row;
    private final int col;


    public Location(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return row == location.row && col == location.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    public String getUserFriendlyName() {
        return rowSymbols.get(row) + col;
    }

    @Override
    public String toString() {
        return "Location{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }
}
