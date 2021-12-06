package com.game.Battleship.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.game.Battleship.models.Ship;

@RestController
public class ShipController {

    @GetMapping("/ships")
    public Ship[] getShips() {

        Ship[] allShips = new Ship[5];
        allShips[0] = new Ship("Carrier", 5);
        allShips[1] = new Ship("Battleship",4 );
        allShips[2]= new Ship("Cruiser",3);
        allShips[3] = new Ship("Submarine", 3);
        allShips[4] = new Ship("Destroyer", 2 );

        return allShips;

    }

}
