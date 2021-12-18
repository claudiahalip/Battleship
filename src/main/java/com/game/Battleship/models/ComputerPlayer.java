package com.game.Battleship.models;

import java.util.List;

public class ComputerPlayer {

    private Board board;
    private Fleet fleet;

    public ComputerPlayer(){

        this.board = new Board();
        this.fleet = new Fleet();
    }

    public Board getBoard(){ return this.board; }

    public List<Ship> getFleet() { return  fleet.getFleet(); }
}
