package com.game.Battleship.models;

public class Cell {

    private String type;
    private String value = null;

    public Cell(){};

    public Cell(String type){
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public String getType(){
        return type;
    }

    public void setName(String type){
        this.type = type;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
