package com.game.Battleship.controllers;
import com.game.Battleship.models.Board;
import com.game.Battleship.models.Cell;
import com.game.Battleship.models.Game;
import com.game.Battleship.models.Ship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
public class BoardController {

    @Autowired
    private Game game;

    @GetMapping("/boards")
    public List<Board> getAllBoards() {

        return game.getAllBoards();
    }

    //json object example: {"row": 0, "column": 1, "shipType": "Cruiser", "shipSize": 3, "isHorizontal": false }
    @PatchMapping(value = "/boards/place")
    public Board placeShip(@RequestBody Map<String, Object> changes) {

        Integer row = (Integer) changes.get("row");
        Integer column = (Integer) changes.get("column");
        String stringShipType = (String) changes.get("shipType");
        Cell.Type shipType = Cell.Type.valueOf(stringShipType.toUpperCase());
        Integer shipSize = (Integer) changes.get("shipSize");
        Boolean isHorizontal = (Boolean) changes.get("isHorizontal");
        Ship ship = new Ship(shipType, shipSize);
        ship.setHorizontal(isHorizontal);
        return game.updateBoardWithShips(ship, row, column);
    }

    //json object example: {"row": 0, "column": 1}
    @PatchMapping(value = "/boards/hit")
    public Board strikeTheBoard(@RequestBody Map<String, Object> changes) {

        Integer row = (Integer) changes.get("row");
        Integer column = (Integer) changes.get("column");
        return game.updateBoardWithStrike(row, column);
    }
}