package com.game.Battleship.models;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameTests {

    @Test
    public void updateBoardWithAHorizontalShip(){
        Ship ship = new Ship(Cell.Type.DESTROYER, 2 );
        Game game = new Game();
        game.updateBoardWithShips(ship, 0, 1);
        assertEquals(game.getHumanPlayer().getBoard().getBoardField().get(1).getValue(), Cell.Value.SHIP);
        assertEquals(game.getHumanPlayer().getBoard().getBoardField().get(2).getValue(), Cell.Value.SHIP);
    }

    @Test
    public void updateBoardWithAVerticalShip(){
        Ship ship = new Ship(Cell.Type.DESTROYER, 2 );
        ship.setHorizontal(false);
        Game game = new Game();
        game.updateBoardWithShips(ship, 0, 1);
        assertEquals(game.getHumanPlayer().getBoard().getBoardField().get(1).getValue(), Cell.Value.SHIP);
        assertEquals(game.getHumanPlayer().getBoard().getBoardField().get(11).getValue(), Cell.Value.SHIP);
    }

    @Test
    public void updateBoardWhenStrikeWhenIsAMissed(){
        Game game = new Game();
        game.updateBoardWithStrike(0, 1 );
        assertEquals(game.getComputerPlayer().getBoard().getBoardField().get(1).getValue(), Cell.Value.MISSED);
    }

    @Test
    public void updateBoardWhenStrikeWhenIsAHIT(){
        Game game = new Game();
        game.getComputerPlayer().getBoard().getBoardField().get(1).setValue(Cell.Value.SHIP);
        game.updateBoardWithStrike(0, 1 );
        assertEquals(game.getComputerPlayer().getBoard().getBoardField().get(1).getValue(), Cell.Value.HIT);
    }
}
