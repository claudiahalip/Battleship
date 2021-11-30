package com.game.Battleship.models;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    public void setBoardField(List<Cell> boardField) {
        this.boardField = boardField;
    }

    public int getSize(){
        return size;
    }

    public void setSize(int size){ this.size = size; }

    private List<Cell> generateBoardField(){
        // create an InStream with elements from 0 to this.size to the power of 2
        IntStream streamRange = IntStream.range(0, (int) Math.pow(this.size, 2));

        List<Cell> result;
        //for each element in the inStream creates a new Cell object and collect them to a list
        result = streamRange.mapToObj(i -> new Cell()).collect(Collectors.toList());

        return result;
    }
}
