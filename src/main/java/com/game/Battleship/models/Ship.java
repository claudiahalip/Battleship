package com.game.Battleship.models;
import  java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Ship {

    private Cell.Type type;
    private int size;
    private List<Cell> shipBody;
    private boolean isHorizontal;
    private boolean isSunk;

    public Ship(){};

    public Ship(Cell.Type type, int size){

        this.type = type;
        this.size = size;
        this.shipBody = generateShipBody();
        this.isHorizontal = true;
        this.isSunk = false;
    }

    public Cell.Type getType(){
        return this.type;
    }

    public void setType(Cell.Type type){ this.type = type; }

    public int getSize(){
        return this.size;
    }

    public List<Cell> getShipBody(){ return this.shipBody;}

    public Boolean getHorizontal(){ return isHorizontal; }

    public void setHorizontal(boolean b){
        this.isHorizontal = b;
    }

    public Boolean getSunk(){ return isSunk; }

    public void setSunk(boolean b){ this.isSunk = b; }

    private List<Cell> generateShipBody(){

        // create an InStream with elements from 0 to this.size
        IntStream streamRange = IntStream.range(0, this.size);
        List<Cell> result;
        //for each element in the inStream creates a new Cell object and collect them to a list
        result = streamRange.mapToObj(i -> new Cell(this.getType(), Cell.Value.SHIP)).collect(Collectors.toList());
        return result;
    }
}
