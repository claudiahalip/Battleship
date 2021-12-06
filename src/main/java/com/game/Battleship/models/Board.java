package com.game.Battleship.models;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {

    private int defaultBoardSize = 10;
    private int size;
    private List<Cell> boardField;

    public Board(){
        this.size = defaultBoardSize;
        this.boardField = generateBoardField();

    }

    public Board(int size) {
        this.size = size;
        this.boardField = generateBoardField();
    }

    public List<Cell> getBoardField() {
        return boardField;
    }

    public int getSize(){
        return size;
    }

    public void addShip(Ship ship, Integer index){

        if(ship.getHorizontal()) {
            for (int i = 0; i < ship.getSize(); i++) {
                Cell cell = new Cell();
                cell.setValue(ship.getType());
                this.boardField.set(index, cell);
                index++;
            }
        } else {
            for(int i = 0; i < ship.getSize(); i++) {
                Cell cell = new Cell();
                cell.setValue(ship.getType());
                this.boardField.set(index, cell);
                index = index + this.getSize();
            }
        }
    }

    private List<Cell> generateBoardField(){
        // create an InStream with elements from 0 to this.size to the power of 2
        IntStream streamRange = IntStream.range(0, (int) Math.pow(this.size, 2));

        List<Cell> result;
        //for each element in the inStream creates a new Cell object and collect them to a list
        result = streamRange.mapToObj(i -> new Cell("water")).collect(Collectors.toList());

        return result;
    }
}
