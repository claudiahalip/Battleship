package com.game.Battleship.helpers;
import com.game.Battleship.models.Board;
import com.game.Battleship.models.Cell;
import org.junit.jupiter.api.Test;

import  static org.junit.jupiter.api.Assertions.*;

public class ValidatorTests {

    @Test
    public void testIfIndexIsValid() {
        Validator validator = new Validator();
        Board board = new Board();
        assertEquals(validator.isIndexValid(board, 5), true);
    }

    @Test
    public void testIfIndexIsNotValid() {
        Validator validator = new Validator();
        Board board = new Board();
        assertEquals(validator.isIndexValid(board, 200), false);
    }

    @Test
    public void testIfACellHasValueWater() {
        Validator validator = new Validator();
        Board board = new Board();
        assertEquals(validator.isWater(board, 4), true);
    }

    @Test
    public void testIfACellDoesntHaveValueWater() {
        Validator validator = new Validator();
        Board board = new Board();
        board.getBoardField().get(4).setValue(Cell.Value.HIT);
        assertEquals(validator.isWater(board, 4), false);
    }

    @Test
    public void testIfPositionIsNotPlayed() {
        Validator validator = new Validator();
        Board board = new Board();
        assertEquals(validator.isPositionNotPlayed(board, 4), true);
    }

    @Test
    public void testIfPositionIstPlayed() {
        Validator validator = new Validator();
        Board board = new Board();
        board.getBoardField().get(4).setValue(Cell.Value.HIT);
        assertEquals(validator.isPositionNotPlayed(board, 4), false);
    }
}