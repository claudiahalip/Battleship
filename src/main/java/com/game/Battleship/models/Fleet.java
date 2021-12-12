package com.game.Battleship.models;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class Fleet {

    private List<Ship> fleet;
    private Ship carrier;
    private Ship battleship;
    private Ship cruiser;
    private Ship submarine;
    private  Ship destroyer;

    public Fleet() {

        this.carrier = new Ship(Cell.Type.CARRIER, 5);
        this.battleship = new Ship(Cell.Type.BATTLESHIP,4 );
        this.cruiser = new Ship(Cell.Type.CRUISER,3);
        this.submarine = new Ship(Cell.Type.SUBMARINE, 3);
        this.destroyer = new Ship(Cell.Type.DESTROYER, 2 );
        this.fleet =  new ArrayList<>(Arrays.asList(
                carrier,
                battleship,
                cruiser,
                submarine,
                destroyer
        ));
    }

    public List<Ship> getFleet(){ return this.fleet; }

    public Ship getCarrier() { return this.carrier; }

    public Ship getBattleship() { return this.battleship; }

    public Ship getCruiser() { return this.cruiser; }

    public Ship getSubmarine() { return  this.submarine; }

    public Ship getDestroyer() {return this.destroyer; }
}
