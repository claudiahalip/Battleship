package com.game.Battleship.models;
import  java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Ship {

    private String type;
    private int size;
    private List<Cell> shipBody;
    private boolean isHorizontal;

    public Ship(){};

    public Ship(String type,int size){
        this.type = type;
        this.size = size;
        this.shipBody = generateShipBody();
        this.isHorizontal = true;
    }

    public String getType(){
        return this.type;
    }

    public int getSize(){
        return this.size;
    }

    public List<Cell> getShipBody(){ return this.shipBody;}

    public Boolean getHorizontal(){ return isHorizontal; }

    public void setHorizontal(boolean b){
        this.isHorizontal = b;
    }

    public boolean isShipSunk(){
        for(Cell cell : this.shipBody) {
            if (cell.getValue() != null && !cell.getValue().equals("Hit")) {
                continue;
            }
            return false;
        }

        return true;
    }

    private List<Cell> generateShipBody(){
        // create an InStream with elements from 0 to this.size
        IntStream streamRange = IntStream.range(0, this.size);

        List<Cell> result;
        //for each element in the inStream creates a new Cell object and collect them to a list
        result = streamRange.mapToObj(i -> new Cell(this.getType())).collect(Collectors.toList());

        return result;

    }

}
