package com.treasurehunter.model;

import java.util.Objects;

public class Coordinate {
    private int row;
    private int col;
    
    public Coordinate() {}
    
    public Coordinate(int row, int col) {
        if (row < 0 || row >= 5 || col < 0 || col >= 5) {
            throw new IllegalArgumentException("Coordinates must be between 0 and 4");
        }
        this.row = row;
        this.col = col;
    }
    
    public int getRow() {
        return row;
    }
    
    public void setRow(int row) {
        this.row = row;
    }
    
    public int getCol() {
        return col;
    }
    
    public void setCol(int col) {
        this.col = col;
    }
    
    public boolean isOrthogonallyAdjacentTo(Coordinate other) {
        return (Math.abs(this.row - other.row) == 1 && this.col == other.col) ||
               (Math.abs(this.col - other.col) == 1 && this.row == other.row);
    }
    
    public boolean isDiagonallyAdjacentTo(Coordinate other) {
        return Math.abs(this.row - other.row) == 1 && Math.abs(this.col - other.col) == 1;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return row == that.row && col == that.col;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
    
    @Override
    public String toString() {
        return "(" + row + "," + col + ")";
    }
}
