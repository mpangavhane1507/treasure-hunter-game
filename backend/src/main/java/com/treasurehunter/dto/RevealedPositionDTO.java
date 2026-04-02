package com.treasurehunter.dto;

public class RevealedPositionDTO {
    private int row;
    private int col;
    private boolean isTreasure;
    private Integer proximityValue;
    
    public RevealedPositionDTO() {}
    
    public RevealedPositionDTO(int row, int col, boolean isTreasure, Integer proximityValue) {
        this.row = row;
        this.col = col;
        this.isTreasure = isTreasure;
        this.proximityValue = proximityValue;
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
    
    public boolean isTreasure() {
        return isTreasure;
    }
    
    public void setTreasure(boolean treasure) {
        isTreasure = treasure;
    }
    
    public Integer getProximityValue() {
        return proximityValue;
    }
    
    public void setProximityValue(Integer proximityValue) {
        this.proximityValue = proximityValue;
    }
}
