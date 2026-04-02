package com.treasurehunter.model;

public class RevealedPosition {
    private Coordinate coordinate;
    private boolean isTreasure;
    private Integer proximityValue;
    
    public RevealedPosition() {}
    
    public RevealedPosition(Coordinate coordinate, boolean isTreasure, Integer proximityValue) {
        this.coordinate = coordinate;
        this.isTreasure = isTreasure;
        this.proximityValue = proximityValue;
    }
    
    public Coordinate getCoordinate() {
        return coordinate;
    }
    
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
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
