package com.game.Battleship.controllers;

import com.game.Battleship.helpers.Validator;
import com.game.Battleship.models.Board;
import com.game.Battleship.models.Cell;
import com.game.Battleship.models.Game;
import com.game.Battleship.models.Ship;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class BoardController {

    Game game;
    Validator validator;

    public BoardController(Game game){
        this.game = game;
        this.validator = new Validator();
    }

    @GetMapping("/boards")
    public List<Board> getAllBoards() {

        return game.getAllBoards();
    }

    //json object example: {"row": 0, "column": 1, "shipType": "Cruiser", "shipSize": 3, "isHorizontal": false }
    @PatchMapping(value = "/boards/place")
    public String  placeShip(@RequestBody Map<String, Object> changes) {

        Board board = game.getHumanPlayer().getBoard();
        Integer row = (Integer) changes.get("row");
        Integer column = (Integer) changes.get("column");
        Integer index = game.convertCoordinatesToIndex(board, row, column);
        if (validator.isShipPlacementValid(board, index )) {

            String stringShipType = (String) changes.get("shipType");
            Cell.Type shipType = Cell.Type.valueOf(stringShipType.toUpperCase());
            Integer shipSize = (Integer) changes.get("shipSize");
            Boolean isHorizontal = (Boolean) changes.get("isHorizontal");
            Ship ship = new Ship(shipType, shipSize);
            ship.setHorizontal(isHorizontal);
            return game.updateBoardWithShips(ship, index);
        }else return "The ship cannot overlap!";
    }

    //json object example: {"row": 0, "column": 1}
    @PatchMapping(value = "/boards/hit")
    public String strikeTheBoard(@RequestBody Map<String, Object> changes) {

        Board board = game.getComputerPlayer().getBoard();
        Integer row = (Integer) changes.get("row");
        Integer column = (Integer) changes.get("column");
        Integer index = game.convertCoordinatesToIndex(board, row, column);
        if (validator.isStrikeValid(board, index)){
            return game.updateBoardWithStrike(index);
        }else return "The spot cannot be hit twice!";
    }
}