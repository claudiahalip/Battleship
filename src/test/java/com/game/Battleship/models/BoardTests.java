package com.game.Battleship.models;
import org.junit.jupiter.api.Test;
import  static org.junit.jupiter.api.Assertions.*;

public class BoardTests {

    @Test
    public void addShipsOnTheBoardHorizontal(){
        Board board = new Board();
        Ship  cruiser = new Ship(Cell.Type.CRUISER,3);
        board.addShip(cruiser, 1);
        assertEquals(board.getBoardField().get(1).getValue(), Cell.Value.SHIP);
        assertEquals(board.getBoardField().get(2).getValue(), Cell.Value.SHIP);
        assertEquals(board.getBoardField().get(3).getValue(), Cell.Value.SHIP);
    }

    @Test
    public void addShipsOnTheBoardVertical(){
        Board board = new Board();
        Ship destroyer = new Ship(Cell.Type.DESTROYER, 2 );
        destroyer.setHorizontal(false);
        board.addShip(destroyer, 1);
        assertEquals(board.getBoardField().get(1).getValue(), Cell.Value.SHIP);
        assertEquals(board.getBoardField().get(11).getValue(), Cell.Value.SHIP);
    }
}
