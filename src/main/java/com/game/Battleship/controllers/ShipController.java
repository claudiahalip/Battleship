package com.game.Battleship.controllers;

import com.game.Battleship.models.Cell;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.game.Battleship.models.Ship;

@RestController
public class ShipController {

    @GetMapping("/ships")
    public Ship[] getShips() {

        Ship[] allShips = new Ship[5];
        allShips[0] = new Ship(Cell.Type.CARRIER, 5);
        allShips[1] = new Ship(Cell.Type.BATTLESHIP,4 );
        allShips[2]= new Ship(Cell.Type.CRUISER,3);
        allShips[3] = new Ship(Cell.Type.SUBMARINE, 3);
        allShips[4] = new Ship(Cell.Type.DESTROYER, 2 );
        return allShips;
    }
}
