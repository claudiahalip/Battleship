package com.game.Battleship.helpers;

import com.game.Battleship.models.Board;
import com.game.Battleship.models.Cell;

public class Validator {

    public Validator(){}

    public boolean isStrikeValid(Board board, Integer index){

        if(isIndexValid(board, index) && isPositionNotPlayed(board, index)){
            return true;
        }else  return false;
    }

    public boolean isShipPlacementValid(Board board, Integer index){

        return (isIndexValid(board, index) && isWater(board, index));
    }

    public boolean isIndexValid(Board board, Integer index){

        return (index >= 0 && index <= (int) Math.pow(board.getSize(), 2));
    }

    public boolean isPositionNotPlayed(Board board, Integer index) {

        return (board.getBoardCell(index).getValue().equals(Cell.Value.SHIP) || board.getBoardCell(index).getValue().equals(Cell.Value.WATER));
    }

    public boolean isWater(Board board, Integer index){

        return board.getBoardCell(index).getValue().equals(Cell.Value.WATER);
    }
}
