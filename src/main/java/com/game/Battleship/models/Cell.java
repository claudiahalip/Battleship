package com.game.Battleship.models;

public class Cell {

    public enum Type { WATER, CARRIER, BATTLESHIP, CRUISER, SUBMARINE, DESTROYER};
    public enum Value { WATER, SHIP, HIT, MISSED};

    private Type type;
    private Value value = Value.WATER;

    public Cell(){};

    public Cell(Type type, Value value){

        this.type = type;
        this.value = value;
    }

    public Type getType(){
        return type;
    }

    public void setType(Type type) {this.type = type; }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) { this.value = value; }
}
