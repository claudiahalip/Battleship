package com.game.Battleship.models;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTests {

    @Test
    public void updateBoardWithAHorizontalShip(){

        Ship ship = new Ship(Cell.Type.DESTROYER, 2 );
        Game game = new Game();
        game.updateBoardWithShips(ship, 1);
        assertEquals(game.getHumanPlayer().getBoard().getBoardField().get(1).getValue(), Cell.Value.SHIP);
        assertEquals(game.getHumanPlayer().getBoard().getBoardField().get(2).getValue(), Cell.Value.SHIP);
    }

    @Test
    public void updateBoardWithAVerticalShip(){

        Ship ship = new Ship(Cell.Type.DESTROYER, 2 );
        ship.setHorizontal(false);
        Game game = new Game();
        game.updateBoardWithShips(ship, 1);
        assertEquals(game.getHumanPlayer().getBoard().getBoardField().get(1).getValue(), Cell.Value.SHIP);
        assertEquals(game.getHumanPlayer().getBoard().getBoardField().get(11).getValue(), Cell.Value.SHIP);
    }

    @Test
    public void updateBoardWhenStrikeWhenIsAMissed(){

        Game game = new Game();
        game.updateBoardWithStrike( 1 );
        assertEquals(game.getComputerPlayer().getBoard().getBoardField().get(1).getValue(), Cell.Value.MISSED);
    }

    @Test
    public void updateBoardWhenStrikeWhenIsAHIT(){

        Game game = new Game();
        game.getComputerPlayer().getBoard().getBoardField().get(1).setValue(Cell.Value.SHIP);
        game.updateBoardWithStrike( 1 );
        assertEquals(game.getComputerPlayer().getBoard().getBoardField().get(1).getValue(), Cell.Value.HIT);
    }

    @Test void updateBoardWithStrikeReturnsTheMessageThatYouMissedWhenMissed(){

        Game game = new Game();
        String message = game.updateBoardWithStrike( 1 );
        assertEquals(message, "You've missed!");
    }

    @Test void updateBoardWithStrikeReturnsMessageThatTheGameIsOver() {

        Game game = new Game();
        Cell cell = new Cell(Cell.Type.DESTROYER, Cell.Value.SHIP);
        Cell cell1 = new Cell(Cell.Type.DESTROYER, Cell.Value.HIT);
        game.getComputerPlayer().getBoard().getBoardField().set(0, cell);
        game.getComputerPlayer().getBoard().getBoardField().set(1, cell1);
        List<Ship> fleet = game.getComputerPlayer().getFleet();
        for(Ship ship : fleet){
            if(!ship.getType().equals(Cell.Type.DESTROYER)) {
                ship.setSunk(true);
            }
        }
        String message = game.updateBoardWithStrike( 0 );
        assertEquals( "The game is over!", message);
    }

    @Test void updateBoardWithStrikeReturnsMessageThatTheShipHasBeenSunkAndTheNameOfTheShip(){

        Game game = new Game();
        Cell cell1 = new Cell(Cell.Type.DESTROYER, Cell.Value.HIT);
        Cell cell2 = new Cell(Cell.Type.DESTROYER, Cell.Value.SHIP);
        game.getComputerPlayer().getBoard().getBoardField().set(0, cell1);
        game.getComputerPlayer().getBoard().getBoardField().set(1, cell2);
        String message = game.updateBoardWithStrike( 1 );
        assertEquals(message, "The DESTROYER has sunk");
    }

    @Test void updateBoardWithStrikeReturnsMessageThatTheShipWasHitAndTheNameOfTheShip(){

        Game game = new Game();
        Cell cell = new Cell(Cell.Type.SUBMARINE, Cell.Value.SHIP);
        game.getComputerPlayer().getBoard().getBoardField().set(1, cell);
        String message = game.updateBoardWithStrike( 1 );
        assertEquals(message, "The SUBMARINE has been hit!");
    }
}
