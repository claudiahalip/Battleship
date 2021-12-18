package com.game.Battleship.controllers;

import com.game.Battleship.models.Fleet;
import com.game.Battleship.models.Ship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShipController {

    @Autowired
    private Fleet fleet;

    @GetMapping("/ships")
    public List<Ship> getShips() {

        return  fleet.getFleet();
    }
}
